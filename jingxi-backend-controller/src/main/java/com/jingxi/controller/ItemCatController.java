package com.jingxi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingxi.common.util.JsonUtils;
import com.jingxi.model.TbItemCat;
import com.jingxi.service.ItemCatService;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	
	@SuppressWarnings({"rawtypes","unchecked"})
	@RequestMapping("/list")
	@ResponseBody
	public List categoryList(@RequestParam (value="id",defaultValue="0") Long parentId) throws Exception {
		List catList = new ArrayList();
		List<TbItemCat> list = itemCatService.getItemCatList(parentId);
		for (TbItemCat tbItemCat : list) {
			Map node = new HashMap<>();
			node.put("id", tbItemCat.getId());
			node.put("text", tbItemCat.getName());
			node.put("state", tbItemCat.getIsParent()?"closed":"open");
			catList.add(node);
		}
		return catList;
	}
	
	@RequestMapping(value="/getParam",
			produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public String getParamByCid(@RequestParam long cid) {
		return itemCatService.getParamByCid(cid);
	}
}
