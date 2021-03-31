package com.matt.service_base.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.matt.commonutils.R;
import com.matt.service_base.util.ExceptionUtil;

import lombok.extern.slf4j.Slf4j;



/**
 * 统一异常处理类
 * 
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	//指定Exception异常执行这个方法
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public R error(Exception e){
		e.printStackTrace();
		return R.error().message("统一异常处理类执行了");
	}
	//指定ArithmeticException异常执行这个方法
		@ExceptionHandler(ArithmeticException.class)
		@ResponseBody
		public R error(ArithmeticException e){
			e.printStackTrace();
			return R.error().message("ArithmeticException异常处理类执行了");
		}
		
		//自定义异常
		@ExceptionHandler(MattException.class)
		@ResponseBody
		public R error(MattException e){
			log.error(ExceptionUtil.getMessage(e));
		    e.printStackTrace();
		    return R.error().message(e.getMeg()).code(e.getCode());
		}
		
}