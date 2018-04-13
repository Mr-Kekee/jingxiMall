package com.jingxi.service;

import com.jingxi.model.TbItem;
import com.jingxi.model.TbItemExample;
import com.jingxi.model.TbItemParam;
import com.jingxi.model.TbItemParamExample;
import com.jingxi.model.TbItemParamItem;
import com.jingxi.vo.ItemVo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jingxi.common.pojo.EasyUIDataGridResult;
import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.common.util.IDUtil;
import com.jingxi.mapper.TbItemMapper;
import com.jingxi.mapper.TbItemParamItemMapper;
import com.jingxi.mapper.TbItemParamMapper;

@Service
public class ItemServiceImp implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	
	public TbItem getTbItemById(long id) {
		return itemMapper.selectByPrimaryKey(id);
	}

//	@Override
//	public EasyUIDataGridResult getItemList(int page, int rows) {
//		TbItemExample example = new TbItemExample();
//		PageHelper.startPage(page, rows);
//		List<TbItem> list = itemMapper.selectByExample(example);
//		List<ItemVo> showList = new ArrayList<>();
//		for (TbItem tbItem : list) {
//			ItemVo itemVo = new ItemVo();
//			//封装tbItem原属性
//			itemVo.setId(tbItem.getId());
//			itemVo.setCid(tbItem.getCid());
//			itemVo.setNum(tbItem.getNum());
//			itemVo.setPrice(tbItem.getPrice());
//			itemVo.setTitle(tbItem.getTitle());
//			itemVo.setStatus(tbItem.getStatus());
//			itemVo.setImage(tbItem.getImage());
//			itemVo.setSellPoint(tbItem.getSellPoint());
//			itemVo.setBarcode(tbItem.getBarcode());
//			itemVo.setCreated(tbItem.getCreated());
//			itemVo.setUpdated(tbItem.getUpdated());
//			//封装tbItem原属性
//			Long cid = tbItem.getCid();
//			TbItemParamExample itemParamExample = new TbItemParamExample();
//			TbItemParamExample.Criteria criteria = itemParamExample.createCriteria();
//			criteria.andItemCatIdEqualTo(cid);
//			List<TbItemParam> itemParam = itemParamMapper.selectByExampleWithBLOBs(itemParamExample);
//			if(itemParam.size()!=0){
//				itemVo.setItemParamId(itemParam.get(0).getId());
//				itemVo.setItemParams(itemParam.get(0).getParamData());
//			}
//			showList.add(itemVo);
//		}
//		EasyUIDataGridResult result = new EasyUIDataGridResult();
//		result.setRows(showList);
//		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
//		result.setTotal(pageInfo.getTotal());
//		return result;
//	}

	public JingXiResult createItem(TbItem item) {
		Date date = new Date();
		//获得商品id
		long id = IDUtil.genItemId();
		item.setId(id);
		item.setStatus((byte)1);
		item.setCreated(date);
		item.setUpdated(date);
		itemMapper.insert(item);
		
		return JingXiResult.ok();
	}

	@Override
	public JingXiResult createItem (ItemVo itemVo) {
		TbItem item = new TbItem();
		Date date = new Date();
		//获得商品id
		long id = IDUtil.genItemId();
		item.setId(id);
		item.setStatus((byte) 1);
		item.setCreated(date);
		item.setUpdated(date);
		
		item.setBarcode(itemVo.getBarcode());
		item.setCid(itemVo.getCid());
		item.setImage(itemVo.getImage());
		item.setNum(itemVo.getNum());
		item.setPrice(itemVo.getPrice());
		item.setSellPoint(itemVo.getSellPoint());
		item.setTitle(itemVo.getTitle());
		
		itemMapper.insert(item);
		
		//同时保存itemParam
		if(itemVo.getItemParams()!=null){
			TbItemParamItem paramItem = new TbItemParamItem();
			paramItem.setParamData(itemVo.getItemParams());
			paramItem.setItemId(item.getId());
			paramItem.setCreated(date);
			paramItem.setUpdated(date);
			
			itemParamItemMapper.insert(paramItem);
		}
		return JingXiResult.ok();
	}
	
	@Override
	public JingXiResult deleteItem(long[] ids) {
		for (long id : ids){
			itemMapper.deleteByPrimaryKey(id);
		}
		
		return JingXiResult.build(200, "删除成功");
	}

	@Override
	public JingXiResult editItem(TbItem item) {
		//商品状态，1，正常 2，下架 3，删除
		item.setStatus((byte) 1);
		int status = itemMapper.updateByPrimaryKeySelective(item);
		if(status == 1){
			return JingXiResult.build(200, "更新成功");
		}else{
			return JingXiResult.build(500, "更新失败");
		}
	}

	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		TbItemExample item = new TbItemExample();
		PageHelper.startPage(page, rows);
		List<TbItem> showList = itemMapper.selectByExample(item);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(showList);
		PageInfo<TbItem> pageInfo = new PageInfo<>(showList);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	
}
