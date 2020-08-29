package com.app.pojo;

import java.util.List;

public class PojoItems {

	private PojoItem item;
	private List<PojoItemDetail> listItemDetail;
	public PojoItem getItem() {
		return item;
	}
	public void setItem(PojoItem item) {
		this.item = item;
	}
	public List<PojoItemDetail> getListItemDetail() {
		return listItemDetail;
	}
	public void setListItemDetail(List<PojoItemDetail> listItemDetail) {
		this.listItemDetail = listItemDetail;
	}
}
