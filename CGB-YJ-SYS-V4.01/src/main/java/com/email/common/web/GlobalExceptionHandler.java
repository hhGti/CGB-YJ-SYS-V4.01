package com.email.common.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.email.common.vo.JsonResult;


@ControllerAdvice
public class GlobalExceptionHandler {

	
	/**
	 * @ExceptionHandler 注解描述的方法为一个异常处理方法
	 * @return
	 */
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public JsonResult doHandleRuntimeException(
			RuntimeException e) {
		e.printStackTrace();
		return new JsonResult(e);
	}
	
}
