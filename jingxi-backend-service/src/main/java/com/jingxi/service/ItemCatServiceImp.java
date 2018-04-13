package com.jingxi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingxi.mapper.TbItemCatMapper;
import com.jingxi.mapper.TbItemParamMapper;
import com.jingxi.model.TbItemCat;
import com.jingxi.model.TbItemCatExample;
import com.jingxi.model.TbItemCatExample.Criteria;
import com.jingxi.model.TbItemParam;
import com.jingxi.model.TbItemParamExample;

@Service
public class ItemCatServiceImp implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Autowired
	private TbItemParamMapper itemParamMapper;
	
	@Override
	public List<TbItemCat> getItemCatList(Long parentId) throws Exception {
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		//根据parentId查询子节点
		criteria.andParentIdEqualTo(parentId);
		//返回子节点列表
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		return list;
	}

	public String getParamByCid(long cid) {
		TbItemParamExample example = new TbItemParamExample();
		TbItemParamExample.Criteria criteria= example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		if(list.size() != 0){
			return list.get(0).getParamData();
		}
		return null;
	}
}
