package com.matt.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient //nacos 服务注册
@EnableFeignClients    //feign 客户端服务调用
@ComponentScan(basePackages = {"com.matt"})
public class EduApplication {
	public static void main(String[] args) {
		SpringApplication.run(EduApplication.class, args);
	}
}

