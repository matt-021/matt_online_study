package com.matt.ucenter.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matt.commonutils.JwtUtils;
import com.matt.commonutils.R;
import com.matt.ucenter.entity.Member;
import com.matt.ucenter.entity.vo.LoginVo;
import com.matt.ucenter.entity.vo.RegisterVo;
import com.matt.ucenter.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author zylin
 * @since 2021-05-18
 */
@RestController
@RequestMapping("/ucenterservice/apimember")
@CrossOrigin
@Api(description="登入注册")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "会员登录")
    @PostMapping("login")
    public R login(@RequestBody LoginVo loginVo) {
        String token = memberService.login(loginVo);
        return R.ok().data("token", token);
    }

    @ApiOperation(value = "会员注册")
    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return R.ok();
    }
    //根据token获取用户信息
    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request) {
        //调用jwt工具类的方法。根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库根据用户id获取用户信息
        Member member = memberService.getById(memberId);
        return R.ok().data("userInfo",member);
    }
}
