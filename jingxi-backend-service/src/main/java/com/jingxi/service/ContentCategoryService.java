package com.jingxi.service;

import java.util.List;

import com.jingxi.common.pojo.EUTreeNode;
import com.jingxi.common.pojo.JingXiResult;

public interface ContentCategoryService {

	List<EUTreeNode> getCategoryList(long parantId);
	
	JingXiResult insertContentCategory(long parentId,String name);
	
	JingXiResult deleteContentCategory(long id);
	
	JingXiResult updateContentCategory(long id,String text);
}
