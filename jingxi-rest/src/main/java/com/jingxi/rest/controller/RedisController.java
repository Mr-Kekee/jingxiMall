package com.jingxi.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.rest.service.RedisService;

@Controller
@RequestMapping("/cache/sync")
public class RedisController {
	
	@Autowired
	private RedisService redisService;
	
	@RequestMapping("/content/{contentCid}")
	public JingXiResult contentCacheSync(@PathVariable Long contentCid) {
		return redisService.syncContent(contentCid);
	}
	
}
