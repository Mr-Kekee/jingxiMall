package com.jingxi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jingxi.common.pojo.EasyUIDataGridResult;
import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.mapper.TbItemCatMapper;
import com.jingxi.mapper.TbItemParamMapper;
import com.jingxi.model.TbItemCat;
import com.jingxi.model.TbItemParam;
import com.jingxi.model.TbItemParamExample;
import com.jingxi.model.TbItemParamExample.Criteria;
import com.jingxi.vo.ItemParamVo;

@Service
public class ItemParamServiceImp implements ItemParamService{
	
	@Autowired
	private TbItemParamMapper itemParamMapper;
	
	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Override
	public EasyUIDataGridResult getItemParam(int page, int rows) {
		TbItemParamExample paramExample = new TbItemParamExample();
		PageHelper.startPage(page, rows);
		List<TbItemParam> itemParamList = itemParamMapper.selectByExampleWithBLOBs(paramExample);
		List<ItemParamVo> showList = new ArrayList<>();
		for (TbItemParam itemParam : itemParamList){
			ItemParamVo itemParamVo = new ItemParamVo();
			itemParamVo.setId(itemParam.getId());
			itemParamVo.setItemCatId(itemParam.getItemCatId());
			itemParamVo.setCreated(itemParam.getCreated());
			itemParamVo.setUpdated(itemParam.getUpdated());
			itemParamVo.setParamData(itemParam.getParamData());
			long cid = itemParam.getItemCatId();
			TbItemCat itemCat = itemCatMapper.selectByPrimaryKey(cid);
			itemParamVo.setItemCatName(itemCat.getName());
			showList.add(itemParamVo);
		}
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(showList);
		PageInfo<TbItemParam> pageInfo = new PageInfo<>(itemParamList);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public JingXiResult ifCidExist(long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> item = itemParamMapper.selectByExample(example);
		if(item.size()==0){
			return JingXiResult.build(200, "该类目尚未添加" , true );
		}else{
			return JingXiResult.build(200, "该类目已经添加",false);
		}
	}

	@Override
	public JingXiResult saveItemParam(long cid,String params) {
		TbItemParam itemParam = new TbItemParam();
		itemParam.setItemCatId(cid);
		Date date = new Date();
		itemParam.setCreated(date);
		itemParam.setUpdated(date);
		itemParam.setParamData(params);
		itemParamMapper.insert(itemParam);
		return JingXiResult.ok();
	}

	@Override
	public JingXiResult deleteItemParam(long[] ids) {
		for (long id : ids){
			itemParamMapper.deleteByPrimaryKey(id);
		}
		return JingXiResult.ok();
	}

	@Override
	public JingXiResult updateItemParam(TbItemParam itemParam, String params) {
		Date date = new Date();
		itemParam.setUpdated(date);
		itemParam.setParamData(params);
		itemParamMapper.updateByPrimaryKeySelective(itemParam);
		return JingXiResult.ok();
	}
}
