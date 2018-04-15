package com.jingxi.sso.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.common.util.JsonUtils;
import com.jingxi.model.TbUser;
import com.jingxi.sso.service.RegistService;

@Controller
public class RegistController {

	@Autowired
	private RegistService userService;
	
	@RequestMapping("/register/user/check/{param}/{type}")
	@ResponseBody
	public Object checkData(@PathVariable String param, @PathVariable Integer type) {
		
		JingXiResult result = null;
		
		//参数有效性校验
		if (StringUtils.isBlank(param)) {
			result = JingXiResult.build(400, "校验内容不能为空");
		}
		if (type == null) {
			result = JingXiResult.build(400, "校验内容类型不能为空");
		}
		if (type != 1 && type != 2 && type != 3 ) {
			result = JingXiResult.build(400, "校验内容类型错误");
		}
		//校验出错
		if (null != result) {
			return result;
		}
		//调用服务
		try {
			result = userService.checkData(param, type);
			
		} catch (Exception e) {
			result = JingXiResult.build(500, ExceptionUtils.getStackTrace(e));
		}
		return result;
	}
	
	@RequestMapping(value="/user/register", method=RequestMethod.POST)
	@ResponseBody
	public JingXiResult register(TbUser user) {
		try {
			return userService.register(user);
		} catch (Exception e) {
			return JingXiResult.build(500, ExceptionUtils.getStackTrace(e));
		}
	}
	
	@RequestMapping("/user/showRegister")
	public String showRegister() {
		return "register";
	}
	
}
