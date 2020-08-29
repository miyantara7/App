package com.app.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_merchant")
public class Merchant extends BaseMaster {

	private String name;
	private String codePos;
	private String phone;
	@JoinColumn(name="location_id")
	@ManyToOne
	private Location location;
	@JoinColumn(name="user_id")
	@ManyToOne
	private Users user;
	public String getCodePos() {
		return codePos;
	}
	public void setCodePos(String codePos) {
		this.codePos = codePos;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
