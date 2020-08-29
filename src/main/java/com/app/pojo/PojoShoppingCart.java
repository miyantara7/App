package com.app.pojo;

import java.util.List;

import com.app.model.Courier;
import com.app.model.Merchant;
import com.app.model.PaymentMethod;
import com.app.model.Users;
import com.app.model.Voucher;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class PojoShoppingCart {

	private List<PojoTempShoppingCart> cart;

	private Merchant merchant;

	private Users user;
	private int totalPrice;

	private Voucher voucher;

	private PaymentMethod paymentMethod;

	private Courier courier;
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
	public Courier getCourier() {
		return courier;
	}
	public void setCourier(Courier courier) {
		this.courier = courier;
	}
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public Voucher getVoucher() {
		return voucher;
	}
	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}
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
