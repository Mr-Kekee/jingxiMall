package com.jingxi.sso.service;

import com.jingxi.common.pojo.JingXiResult;

public interface TokenService {

	JingXiResult getUserByToken(String token);
}
