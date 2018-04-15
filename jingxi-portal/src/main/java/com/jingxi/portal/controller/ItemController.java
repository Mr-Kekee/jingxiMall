package com.jingxi.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingxi.common.pojo.ItemInfo;
import com.jingxi.portal.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/{itemId}")
	public String showItem(@PathVariable Long itemId,Model model) {
		ItemInfo item = itemService.getItemById(itemId);
		model.addAttribute("item",item);
		
		return "item";
	}
	
	@RequestMapping(value="/param/{itemId}",
			produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemParam(@PathVariable Long itemId) {
		return itemService.getItemParam(itemId);
	}
	
	@RequestMapping(value="/desc/{itemId}",
			produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemDesc(Long itemId) {
		return null;
	}
}
