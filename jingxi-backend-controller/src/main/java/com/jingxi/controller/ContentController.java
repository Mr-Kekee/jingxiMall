package com.jingxi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingxi.common.pojo.EasyUIDataGridResult;
import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.model.TbContent;
import com.jingxi.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/query/list")
	@ResponseBody
	public EasyUIDataGridResult getContentList (int page,int rows,long categoryId) {
		return contentService.getContentList(page, rows,categoryId);
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public JingXiResult addContent (TbContent content) {
		return contentService.addContent(content);
	}
}
