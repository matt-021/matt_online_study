package com.matt.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matt.commonutils.R;
import com.matt.edu.client.VodClient;

/*
 * 登入测试
 */
@RestController
@RequestMapping("/edu/user")
@CrossOrigin
public class Login {
	@Autowired
	private VodClient VodClient;
	//login
	@PostMapping("login")
	public R login() {
		
		return R.ok().data("token","matt");
	}
	
	//info
	@GetMapping("info")
	public R info() {
		
		return R.ok().data("roles","角色").data("name","mattinfo").data("avatar","https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2700366571,3225145977&fm=26&gp=0.jpg");
	}
	//测试
	@GetMapping("test")
	public R test() {
		VodClient.test();
		return R.ok();
	}
}
