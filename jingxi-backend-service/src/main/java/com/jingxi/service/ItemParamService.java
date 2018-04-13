package com.jingxi.service;

import com.jingxi.common.pojo.EasyUIDataGridResult;
import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.model.TbItemParam;

public interface ItemParamService {
	
	public EasyUIDataGridResult getItemParam(int page,int rows);

	public JingXiResult ifCidExist (long cid);

	public JingXiResult saveItemParam (long cid,String params);
	
	public JingXiResult deleteItemParam (long[] ids);
	
	public JingXiResult updateItemParam (TbItemParam itemParam,String params);
}
