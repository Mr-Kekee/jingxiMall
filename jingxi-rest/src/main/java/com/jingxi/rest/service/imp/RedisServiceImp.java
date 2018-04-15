package com.jingxi.rest.service.imp;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.rest.dao.JedisClient;
import com.jingxi.rest.service.RedisService;

@Service
public class RedisServiceImp implements RedisService {

	@Autowired
	private JedisClient jedisClient;
	
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private static String INDEX_CONTENT_REDIS_KEY ;
	
	@Override
	public JingXiResult syncContent(long contentCid) {
		try{
			jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, contentCid+"");
		} catch (Exception e) {
			e.printStackTrace();
			return JingXiResult.build(500, ExceptionUtils.getStackTrace(e));
		}
		return JingXiResult.ok();
	}

}
