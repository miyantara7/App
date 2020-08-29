package com.app.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_temp_shopping_chart")
public class TempShoppingCart extends BaseEntity {

	@JoinColumn(name="item_id")
	@ManyToOne
	private Items item;
	@JoinColumn(name="merchant_id")
	@ManyToOne
	private Merchant merchant;
	@JoinColumn(name="user_id")
	@ManyToOne
	private Users user;
	private int quantity;
	private int price;
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Items getItem() {
		return item;
	}
	public void setItem(Items item) {
		this.item = item;
	}
	public Merchant getMerchant() {
		return merchant;
	}
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
}
