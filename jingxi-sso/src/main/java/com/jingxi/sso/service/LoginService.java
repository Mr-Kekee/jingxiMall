package com.jingxi.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jingxi.common.pojo.JingXiResult;

public interface LoginService {
	JingXiResult login(String username, String password, HttpServletRequest request, HttpServletResponse response);
}
