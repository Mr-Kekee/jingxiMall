package com.jingxi.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jingxi.common.pojo.EasyUIDataGridResult;
import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.common.util.HttpClientUtil;
import com.jingxi.mapper.TbContentMapper;
import com.jingxi.model.TbContent;
import com.jingxi.model.TbContentExample;

@Service
public class ContentServiceImp implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	
	@Value("${SYNC_CONTENT}")
	private String SYNC_CONTENT;
	
	@Override
	public EasyUIDataGridResult getContentList(int pageNum,int pageSize,long categoryId) {
		TbContentExample example = new TbContentExample();
		TbContentExample.Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		PageHelper.startPage(pageNum, pageSize);
		List<TbContent> showList = contentMapper.selectByExample(example);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(showList);
		PageInfo<TbContent> pageInfo = new PageInfo<>(showList);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@SuppressWarnings("static-access")
	@Override
	public JingXiResult addContent(TbContent content) {
		Date date = new Date();
		content.setCreated(date);
		content.setUpdated(date);
		contentMapper.insert(content);
		
		try{
			HttpClientUtil.doGet(REST_BASE_URL+SYNC_CONTENT);
					//+content.getCategoryId());
		} catch (Exception e) {
			e.printStackTrace();
			return JingXiResult.build(500, ExceptionUtils.getStackTrace(e));
		}
		
		return new JingXiResult().ok();
	}

}
