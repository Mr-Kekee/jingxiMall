package com.jingxi.service;

import  com.jingxi.common.pojo.EasyUIDataGridResult;
import com.jingxi.common.pojo.JingXiResult;
import com.jingxi.model.TbItem;
import com.jingxi.vo.ItemVo;

//@Service
public interface ItemService {
	
	public TbItem getTbItemById (long id);
	
	public EasyUIDataGridResult getItemList(int page,int rows);

	public JingXiResult createItem (ItemVo item) ;
	
	public JingXiResult editItem (TbItem item);
	
	public JingXiResult deleteItem (long[] ids);
	
}
