package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * 异常处理类
 * @author jiangqinqin
 *
 */
@ControllerAdvice
public class ExceptionHandler {
	private Map<String, Object> exceptionHandler(HttpServletRequest request,Exception e){
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("success", false);
		map.put("errMsg", e.getMessage());
		return map;
	}
}
