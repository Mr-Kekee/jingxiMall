package com.jingxi.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jingxi.common.pojo.EasyUIDataGridResult;
import com.jingxi.common.pojo.SearchResult;
import com.jingxi.portal.service.SearchService;

@Controller
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	
	@Value("${SEARCH_RESULT_ROWS}")
	private Integer SEARCH_RESULT_ROWS;
	
	@RequestMapping("/search")
	public String search( @RequestParam("q") String queryString, 
			@RequestParam(defaultValue="1") Long page, Model model) throws Exception {
		//解决乱码问题
		queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
		EasyUIDataGridResult result = searchService.searchItem(queryString, page,SEARCH_RESULT_ROWS);
		model.addAttribute("query", queryString);
		//每页*个，页数=total/*+1
		model.addAttribute("totalPages", result.getTotal()/SEARCH_RESULT_ROWS+1);
		model.addAttribute("itemList", result.getRows());
		model.addAttribute("page", page);
		model.addAttribute("rows", SEARCH_RESULT_ROWS);
		
		return "search";
	}
}
