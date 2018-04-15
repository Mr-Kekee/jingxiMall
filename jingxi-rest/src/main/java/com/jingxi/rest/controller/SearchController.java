package com.jingxi.rest.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.rest.service.SearchService;

@Controller
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/search")
	@ResponseBody
	public JingXiResult getSearchResult (
			@RequestParam(value="queryString") String queryString,
			@RequestParam(value="page") String page,
			@RequestParam(value="rows") String rows) {
		//解决乱码问题
		try {
			queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return searchService.getSearchResult(queryString,page,rows);
	}
}
