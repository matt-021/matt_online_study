package com.matt.ucenter.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.matt.commonutils.JwtUtils;
import com.matt.commonutils.MD5;
import com.matt.service_base.exception.MattException;
import com.matt.ucenter.entity.Member;
import com.matt.ucenter.entity.vo.LoginVo;
import com.matt.ucenter.entity.vo.RegisterVo;
import com.matt.ucenter.mapper.MemberMapper;
import com.matt.ucenter.service.MemberService;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author zylin
 * @since 2021-05-18
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 会员登录
     * @param loginVo
     * @return
     */
    @Override
    public String login(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();

        //校验参数
        if(StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(mobile)) {
            throw new MattException(20001,"手机号或者秘密非空");
        }

        //获取会员
        Member member = baseMapper.selectOne(new QueryWrapper<Member>().eq("mobile", mobile));
        if(null == member) {
            throw new MattException(20001,"根据手机号查不到数据");
        }

        //校验密码
        if(!MD5.encrypt(password).equals(member.getPassword())) {
            throw new MattException(20001,"密码错误");
        }

        //校验是否被禁用
        if(member.getDisabled()) {
            throw new MattException(20001,"该账号被警用");
        }

        //使用JWT生成token字符串
        String token = JwtUtils.getJwtToken(member.getId(), member.getNickname());
        return token;
    }

    /**
     * 会员注册
     * @param registerVo
     */
    @Override
    public void register(RegisterVo registerVo) {
        //获取注册信息，进行校验
        String nickname = registerVo.getNickname();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();

        //校验参数
        if(StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(code)) {
            throw new MattException(20001,"注册信息不能为空");
        }

		/*
		 * //校验校验验证码 //从redis获取发送的验证码 String mobleCode =
		 * redisTemplate.opsForValue().get(mobile); if(!code.equals(mobleCode)) { throw
		 * new MattException(20001,"验证码错误"); }
		 */

        //查询数据库中是否存在相同的手机号码
        Integer count = baseMapper.selectCount(new QueryWrapper<Member>().eq("mobile", mobile));
        if(count.intValue() > 0) {
            throw new MattException(20001,"数据库已有相同的号码");
        }

        //添加注册信息到数据库
        Member member = new Member();
        member.setNickname(nickname);
        member.setMobile(registerVo.getMobile());
        member.setPassword(MD5.encrypt(password));
        member.setDisabled(false);
        //member.setAvatar("https://image.baidu.com/search/detail?ct=503316480&z=0&tn=baiduimagedetail&ipn=d&cl=2&cm=1&sc=0&lm=-1&ie=gb18030&pn=1&rn=1&di=232540&ln=30&word=%CD%B7%CF%F1&os=923129928,39809669&cs=1950846641,3729028697&objurl=http%3A%2F%2Fpic1.zhimg.com%2F50%2Fv2-fce4f8a778fe3f24bca2cafc709b6847_hd.jpg&bdtype=0&simid=0,0&pi=0&adpicid=0&timingneed=0&spn=0&is=0,0&fr=ala&oriquery=%E5%A4%B4%E5%83%8F&ala=1&alatpl=portait&pos=1");
        this.save(member);
    }
}
