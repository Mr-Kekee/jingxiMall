package com.jingxi.rest.service;

import com.jingxi.common.pojo.JingXiResult;

public interface SearchService {
	
	JingXiResult getSearchResult(String queryString, String page, String rows);
}
