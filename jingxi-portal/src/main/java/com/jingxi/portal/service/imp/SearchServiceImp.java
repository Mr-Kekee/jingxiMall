package com.jingxi.portal.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.jingxi.common.pojo.EasyUIDataGridResult;
import com.jingxi.common.pojo.ItemInfo;
import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.common.pojo.SearchResult;
import com.jingxi.common.util.HttpClientUtil;
import com.jingxi.common.util.JsonUtils;
import com.jingxi.model.TbItem;
import com.jingxi.portal.service.SearchService;

@Service
public class SearchServiceImp implements SearchService{

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	
	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;
	
	@SuppressWarnings("unchecked")
	@Override
	public EasyUIDataGridResult searchItem(String queryString, Long page, Integer rows) {
		//创建查询参数
		Map<String, String> param = new HashMap<>();
		param.put("queryString", queryString);
		param.put("page", page + "");
		param.put("rows", rows + "");
		
		//返回的是JingXiResult . data=List<TbItem>
		String json = HttpClientUtil.doGet(REST_BASE_URL+SEARCH_BASE_URL, param);
		JingXiResult result = JingXiResult.formatToList(json, TbItem.class);
		
		if (result.getStatus() == 200 && result.getData()!=null) {
			List<TbItem> tbItemList = (List<TbItem>) result.getData();
			EasyUIDataGridResult searchResult = new EasyUIDataGridResult();
			searchResult.setRows(tbItemList);
			searchResult.setTotal(tbItemList.size());
			return searchResult;
		}
		return new EasyUIDataGridResult();
	}
	
//	@SuppressWarnings("unchecked")
//	@Override
//	public SearchResult search(String queryString, Long page, Integer rows) {
//		//创建查询参数
//		Map<String, String> param = new HashMap<>();
//		param.put("queryString", queryString);
//		param.put("page", page + "");
//		param.put("rows", rows + "");
//		
//		//返回的是JingXiResult . data=List<TbItem>
//		String json = HttpClientUtil.doGet(REST_BASE_URL+SEARCH_BASE_URL, param);
//		JingXiResult result = JingXiResult.formatToList(json, TbItem.class);
//		
//		if (result.getStatus() == 200 && result.getData()!=null) {
//			SearchResult searchResult = new SearchResult();
//			
//			List<TbItem> tbItemList = (List<TbItem>) result.getData();
//			List<ItemInfo> resultList = new ArrayList<ItemInfo>();
//			//不能进行强转！！！
//			for (TbItem tbItem : tbItemList ){
//				ItemInfo itemInfo = (ItemInfo) tbItem;
//				resultList.add(itemInfo);
//			}
//			
//			searchResult.setTotal(resultList.size());
//			searchResult.setItemList(resultList);
//			
//			return searchResult;
//		}
//		return new SearchResult();
//	}
	
//	public SearchResult search(String queryString, Long page) {
//		//创建查询参数
//		Map<String, String> param = new HashMap<>();
//		param.put("queryString", queryString);
//		param.put("page", page + "");
//		//调用taotao-search的服务进行搜索
//		String json = HttpClientUtil.doGet(REST_BASE_URL+SEARCH_BASE_URL, param);
//		JingXiResult result = JingXiResult.format(json);
//		if (result.getStatus() == 200) {
//			JingXiResult result2 = JingXiResult.formatToPojo(json, SearchResult.class);
//			SearchResult searchResult = (SearchResult) result2.getData();
//			return searchResult;
//		}
//		return new SearchResult();
//	}

	
}
