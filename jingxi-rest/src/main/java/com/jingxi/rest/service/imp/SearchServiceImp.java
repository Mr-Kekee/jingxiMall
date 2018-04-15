package com.jingxi.rest.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.jingxi.common.pojo.ItemInfo;
import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.common.pojo.SearchResult;
import com.jingxi.mapper.TbItemMapper;
import com.jingxi.model.TbItem;
import com.jingxi.model.TbItemExample;
import com.jingxi.rest.service.SearchService;

@Service
public class SearchServiceImp implements SearchService {

	@Autowired
	private TbItemMapper itemMapper;
	
	@Override
	public JingXiResult getSearchResult(String queryString, String page, String rows) {
		TbItemExample example = new TbItemExample();
		//分页
		PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(rows));
		TbItemExample.Criteria criteria = example.createCriteria();
		
		char[] charKey = queryString.toCharArray();
		StringBuffer key = new StringBuffer();
		for(int i=0;i<charKey.length;i++) {
			key.append("%"+charKey[i]);
		}
		key.append("%");
		String keyString = key.toString();
		criteria.andTitleLike(keyString);
		
		List<TbItem> list = itemMapper.selectByExample(example);
		
		return JingXiResult.ok(list);
	}
	
//	public JingXiResult getSearchResult(String queryString) {
//		TbItemExample example = new TbItemExample();
//		TbItemExample.Criteria criteria = example.createCriteria();
//		criteria.andTitleLike(queryString);
//		List<TbItem> list = itemMapper.selectByExample(example);
//		List<ItemInfo> resultList = new ArrayList<ItemInfo>();
//		for(TbItem item : list) {
//			ItemInfo itemResult = (ItemInfo) item;
//			resultList.add(itemResult);
//		}
//		SearchResult result = new SearchResult();
//		result.setItemList(resultList);
//		result.setTotal(resultList.size());
//		result.setPage(1);//从第一页开始?
//		//PageHelper.startPage(1, resultList.size());
//		
//		return JingXiResult.ok(result);
//	}
}
