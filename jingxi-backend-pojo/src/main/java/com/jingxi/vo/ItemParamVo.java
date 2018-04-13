package com.jingxi.vo;

import java.util.Date;

public class ItemParamVo {
	
	private Long id;
	private Long itemCatId;
	private String itemCatName;
	private Date created;
    private Date updated;
    private String paramData;
    
    public ItemParamVo () {}
    
	public ItemParamVo(Long id, Long itemCatId, String itemCatName
			, Date created, Date updated, String paramData) {
		super();
		this.id = id;
		this.itemCatId = itemCatId;
		this.itemCatName = itemCatName;
		this.created = created;
		this.updated = updated;
		this.paramData = paramData;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getItemCatId() {
		return itemCatId;
	}
	public void setItemCatId(Long itemCatId) {
		this.itemCatId = itemCatId;
	}
	public String getItemCatName() {
		return itemCatName;
	}
	public void setItemCatName(String itemCatName) {
		this.itemCatName = itemCatName;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getParamData() {
		return paramData;
	}

	public void setParamData(String paramData) {
		this.paramData = paramData;
	}
    
    
}
