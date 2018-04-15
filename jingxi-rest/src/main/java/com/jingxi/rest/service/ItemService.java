package com.jingxi.rest.service;

import com.jingxi.common.pojo.JingXiResult;

public interface ItemService {
	
	JingXiResult getItemBaseInfo (long itemId);
	
	JingXiResult getItemParam(long itemId);
}
