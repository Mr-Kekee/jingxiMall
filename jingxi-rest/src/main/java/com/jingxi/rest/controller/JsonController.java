package com.jingxi.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//需要在main/java 下运行，在test运行无效
@Controller
public class JsonController {
	
	@RequestMapping(value="/exchangeData",
			produces=MediaType. APPLICATION_JSON_VALUE + ";charset=utf-8")
			//produces 指定回传的内容支持 中文
	@ResponseBody//返回的内容 转换为 json
	public String toThis (HttpServletRequest request) {
		//ajax jsonpCallback
		String callback = request.getParameter("jsonpCallback");
		//json callback
		//String callback = request.getParameter("callback");
		String json = "([{name:'珂珂',age:'31'},{name:'joe',age:'20'}])";
		callback+=json;
		System.out.println(callback);
		return callback;
	}
}
