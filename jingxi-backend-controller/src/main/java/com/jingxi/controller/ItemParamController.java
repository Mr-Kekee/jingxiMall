package com.jingxi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingxi.common.pojo.EasyUIDataGridResult;
import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.model.TbItemParam;
import com.jingxi.service.ItemParamService;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {
	
	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult itemParam(Integer page,Integer rows) {
		EasyUIDataGridResult result = itemParamService.getItemParam(page, rows);
		return result;
	}
	
	@RequestMapping(value = "/query/itemcatid/{cid}")
	@ResponseBody
	//  判断选择的目录是否已经添加过规格
	public JingXiResult ifCid (@PathVariable(value = "cid") long cid) {
		return itemParamService.ifCidExist(cid);
	}
	
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public JingXiResult saveItemParam (@PathVariable(value = "cid") long cid
			,@RequestParam(value = "paramData") String params) {
		return itemParamService.saveItemParam(cid, params);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public JingXiResult deleteItemParam (long[] ids) {
		return itemParamService.deleteItemParam(ids);
	}
	
	@RequestMapping("/update/{id}")
	@ResponseBody
	public JingXiResult updateItemParam (TbItemParam itemParam,
			@RequestParam(value = "paramData") String params) {
		return itemParamService.updateItemParam(itemParam, params);
	}
}
