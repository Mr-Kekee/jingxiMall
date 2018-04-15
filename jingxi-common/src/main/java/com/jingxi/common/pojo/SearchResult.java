package com.jingxi.common.pojo;

import java.util.List;

public class SearchResult {
	
	private List<ItemInfo> itemList;
	private long pageCount;
	private long page;
	private long total;
	
	public List<ItemInfo> getItemList() {
		return itemList;
	}
	public void setItemList(List<ItemInfo> itemList) {
		this.itemList = itemList;
	}
	public long getPageCount() {
		return pageCount;
	}
	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}
	public long getPage() {
		return page;
	}
	public void setPage(long page) {
		this.page = page;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	
}
