package com.jingxi.portal.service;

import com.jingxi.common.pojo.ItemInfo;

public interface ItemService {
	
	ItemInfo getItemById(Long itemId);
	String getItemParam(Long itemId);
}
