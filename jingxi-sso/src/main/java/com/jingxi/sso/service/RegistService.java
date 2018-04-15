package com.jingxi.sso.service;

import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.model.TbUser;

public interface RegistService {

	JingXiResult checkData(String param, Integer type) throws Exception;

	JingXiResult register(TbUser user);
}
