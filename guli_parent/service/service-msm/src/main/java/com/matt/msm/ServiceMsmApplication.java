package com.matt.msm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


/**
 * @descripe 阿里云短信服务
 * @author zylin
 * @date 2021年5月18日
 */
@ComponentScan({"com.matt"})
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//取消数据源自动配置
public class ServiceMsmApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceMsmApplication.class, args);
	}
}