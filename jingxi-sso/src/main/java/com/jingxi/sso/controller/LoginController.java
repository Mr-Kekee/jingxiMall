package com.jingxi.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.sso.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="/user/login", method=RequestMethod.POST)
	@ResponseBody
	public JingXiResult login(String username, String password, 
			HttpServletRequest request, HttpServletResponse response) {
		JingXiResult result = loginService.login(username, password, request, response);
		return result;
	}
	
	@RequestMapping("/user/showLogin")
	public String showRegister(String redirect, Model model) {
		//把url参数传递到jsp
		model.addAttribute("redirect", redirect);
		return "login";
	}
	
}
