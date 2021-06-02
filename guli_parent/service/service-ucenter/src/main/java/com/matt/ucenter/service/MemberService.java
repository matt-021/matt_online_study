package com.matt.ucenter.service;

import com.matt.ucenter.entity.Member;
import com.matt.ucenter.entity.vo.LoginVo;
import com.matt.ucenter.entity.vo.RegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author zylin
 * @since 2021-05-18
 */
public interface MemberService extends IService<Member> {

	String login(LoginVo loginVo);

	void register(RegisterVo registerVo);

	Member getByOpenid(String openid);

}
