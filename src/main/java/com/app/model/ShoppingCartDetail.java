package com.app.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_shopping_chart_detail")
public class ShoppingCartDetail extends BaseEntity{

	@JoinColumn(name="item_id")
	@ManyToOne
	private Items item;
	private int quantity;
	private int price;
	@JoinColumn(name="shopping_chart_id")
	@ManyToOne
	private ShoppingCart shoppingCart;
	@JoinColumn(name="merchant_id")
	@ManyToOne
	private Merchant merchant;
	public Items getItem() {
		return item;
	}
	public void setItem(Items item) {
		this.item = item;
	}
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
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}
	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	public Merchant getMerchant() {
		return merchant;
	}
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
}
