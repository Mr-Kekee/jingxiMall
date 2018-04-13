package com.jingxi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingxi.common.pojo.EasyUIDataGridResult;
import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.model.TbItem;
import com.jingxi.service.ItemService;
import com.jingxi.vo.ItemVo;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/{id}")
	@ResponseBody
	public TbItem getTbItemById(@PathVariable long id) {
		TbItem tbItem = itemService.getTbItemById(id);
		return tbItem;
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page,Integer rows) {
		EasyUIDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public JingXiResult createItem (ItemVo item) {
		return itemService.createItem(item);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public JingXiResult editItem (TbItem item) {
		return itemService.editItem(item);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public JingXiResult deleteItem (long[] ids) {
		return itemService.deleteItem(ids);
	}
	
}
