package com.jingxi.service;

import com.jingxi.common.pojo.EasyUIDataGridResult;
import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.model.TbContent;

public interface ContentService {
	
	EasyUIDataGridResult getContentList(int pageNum,int pageSize,long categoryId);

	JingXiResult addContent(TbContent content);
}
