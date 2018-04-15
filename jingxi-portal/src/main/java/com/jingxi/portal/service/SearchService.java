package com.jingxi.portal.service;

import com.jingxi.common.pojo.EasyUIDataGridResult;
import com.jingxi.common.pojo.SearchResult;

public interface SearchService {

	EasyUIDataGridResult searchItem(String queryString, Long page, Integer rows);
}
