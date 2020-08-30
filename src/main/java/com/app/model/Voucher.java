package com.app.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_m_voucher")
public class Voucher extends BaseMaster {

	private String code;
	private String name;
	private int price;
	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
