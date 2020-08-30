package com.app.pojo;

import java.util.List;

public class PojoSearchItem {

	private String inquiry;
	private List<PojoIdSelector> listCategories;
	public String getInquiry() {
		return inquiry;
	}
	public void setInquiry(String inquiry) {
		this.inquiry = inquiry;
	}
	public List<PojoIdSelector> getListCategories() {
		return listCategories;
	}
	public void setListCategories(List<PojoIdSelector> listCategories) {
		this.listCategories = listCategories;
	}
}
