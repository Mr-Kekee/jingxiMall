package com.jingxi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingxi.common.pojo.EUTreeNode;
import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.service.ContentCategoryService;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
	
	@Autowired
	private ContentCategoryService contentCategoryService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> getContentCatList(
			@RequestParam(value="id",defaultValue="0")Long parentId) {
		return contentCategoryService.getCategoryList(parentId);
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public JingXiResult createContentCategory(Long parentId,String name) {
		return contentCategoryService.insertContentCategory(parentId, name);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public JingXiResult deleteContentCategory(
			@RequestParam(value="id",defaultValue="0")Long id){
		return contentCategoryService.deleteContentCategory(id);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public JingXiResult updateContentCategory (long id,String name) {
		return contentCategoryService.updateContentCategory(id, name);
	}
}
