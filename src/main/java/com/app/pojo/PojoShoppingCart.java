package com.app.pojo;

import java.util.List;

public class PojoShoppingCart {

	private List<PojoTempShoppingCart> cart;
	private int totalPrice;
	public List<PojoTempShoppingCart> getCart() {
		return cart;
	}
	public void setCart(List<PojoTempShoppingCart> cart) {
		this.cart = cart;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
}
