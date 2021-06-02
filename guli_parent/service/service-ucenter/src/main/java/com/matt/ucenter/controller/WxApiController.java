package com.matt.ucenter.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.matt.commonutils.JwtUtils;
import com.matt.service_base.exception.MattException;
import com.matt.ucenter.entity.Member;
import com.matt.ucenter.service.MemberService;
import com.matt.ucenter.util.ConstantPropertiesUtil;
import com.matt.ucenter.util.HttpClientUtils;

@CrossOrigin
@Controller // 注意这里没有配置 @RestController
@RequestMapping("/api/ucenter/wx")
public class WxApiController {

	@Autowired
    private MemberService memberService;
	
	/**
	 * 扫完微信二维码调用此方法
	 * @param code
	 * @param state
	 * @return
	 */
	@GetMapping("callback")
	public String callback(String code, String state) {

		// 得到授权临时票据code
		System.out.println(code);
		System.out.println(state);

		// 从redis中将state获取出来，和当前传入的state作比较
		// 如果一致则放行，如果不一致则抛出异常：非法访问

		// 向认证服务器发送请求换取access_token
		String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" + "?appid=%s" + "&secret=%s"
				+ "&code=%s" + "&grant_type=authorization_code";

		String accessTokenUrl = String.format(baseAccessTokenUrl, ConstantPropertiesUtil.WX_OPEN_APP_ID,
				ConstantPropertiesUtil.WX_OPEN_APP_SECRET, code);

		String result = null;
		try {
			result = HttpClientUtils.get(accessTokenUrl);
			System.out.println("accessToken=============" + result);
		} catch (Exception e) {
			throw new MattException(20001, "获取access_token失败");
		}

		// 解析json字符串
		Gson gson = new Gson();
		HashMap map = gson.fromJson(result, HashMap.class);
		String accessToken = (String) map.get("access_token");
		String openid = (String) map.get("openid");

		// 查询数据库当前用用户是否曾经使用过微信登录
		Member member = memberService.getByOpenid(openid);
		if (member == null) {
			System.out.println("新用户注册");

			// 访问微信的资源服务器，获取用户信息
			String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" + "?access_token=%s" + "&openid=%s";
			String userInfoUrl = String.format(baseUserInfoUrl, accessToken, openid);
			String resultUserInfo = null;
			try {
				resultUserInfo = HttpClientUtils.get(userInfoUrl);
				System.out.println("resultUserInfo==========" + resultUserInfo);
			} catch (Exception e) {
				throw new MattException(20001, "获取用户信息失败");
			}

			// 解析json
			HashMap<String, Object> mapUserInfo = gson.fromJson(resultUserInfo, HashMap.class);
			String nickname = (String) mapUserInfo.get("nickname");
			String headimgurl = (String) mapUserInfo.get("headimgurl");

			// 向数据库中插入一条记录
			member = new Member();
			member.setNickname(nickname);
			member.setOpenid(openid);
			member.setAvatar(headimgurl);
			memberService.save(member);
		}

		// 生成jwt
		String token = JwtUtils.getJwtToken(member.getId(),member.getNickname());

		//存入cookie  不用这种方式 cookie不能跨域，所以这里使用url重写
		//CookieUtils.setCookie(request, response, "guli_jwt_token", token);
		return "redirect:http://localhost:3000?token=" + token;
	}

	@GetMapping("login")
	public String genQrConnect(HttpSession session) {
//		授权url参数说明
//		参数	是否必须	说明
//		appid	是	应用唯一标识
//		redirect_uri	是	请使用urlEncode对链接进行处理
//		response_type	是	填code
//		scope	是	应用授权作用域，拥有多个作用域用逗号（,）分隔，网页应用目前仅填写snsapi_login即
//		state	否	用于保持请求和回调的状态，授权请求后原样带回给第三方。该参数可用于防止csrf攻击（跨站请求伪造攻击），建议第三方带上该参数，可设置为简单的随机数加session进行校验
//		
		// 微信开放平台授权baseUrl
		String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" + "?appid=%s" + "&redirect_uri=%s"
				+ "&response_type=code" + "&scope=snsapi_login" + "&state=%s" + "#wechat_redirect";

		// 回调地址
		String redirectUrl = ConstantPropertiesUtil.WX_OPEN_REDIRECT_URL; // 获取业务服务器重定向地址
		try {
			redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8"); // url编码
		} catch (UnsupportedEncodingException e) {
			throw new MattException(20001, e.getMessage());
		}

		// 防止csrf攻击（跨站请求伪造攻击）
		// String state = UUID.randomUUID().toString().replaceAll("-",
		// "");//一般情况下会使用一个随机数
		String state = "imhelen";// 为了让大家能够使用我搭建的外网的微信回调跳转服务器，这里填写你在ngrok的前置域名
		System.out.println("state = " + state);

		// 采用redis等进行缓存state 使用sessionId为key 30分钟后过期，可配置
		// 键："wechar-open-state-" + httpServletRequest.getSession().getId()
		// 值：satte
		// 过期时间：30分钟

		// 生成qrcodeUrl
		String qrcodeUrl = String.format(baseUrl, ConstantPropertiesUtil.WX_OPEN_APP_ID, redirectUrl, state);

		return "redirect:" + qrcodeUrl;
	}
}