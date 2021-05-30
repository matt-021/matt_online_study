package com.matt.ucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.matt"})
@SpringBootApplication//取消数据源自动配置
@EnableDiscoveryClient
@MapperScan("com.matt.ucenter.mapper")
public class ServiceUcApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceUcApplication.class, args);
	}
}