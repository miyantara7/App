package com.app.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_m_location")
public class Location extends BaseMaster {

	private String code;
	private String name;
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
	
}
