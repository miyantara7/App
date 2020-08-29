package com.app.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_shopping_chart")
public class ShoppingCart extends BaseEntity {

	@JoinColumn(name="user_id")
	@ManyToOne
	private Users user;
	@JoinColumn(name="voucher_id")
	@ManyToOne
	private Voucher voucher;
	private int totalPrice;
	@JoinColumn(name="payment_method_id")
	@ManyToOne
	private PaymentMethod paymentMethod;
	@JoinColumn(name="courier_id")
	@ManyToOne
	private Courier courier;
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Voucher getVoucher() {
		return voucher;
	}
	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Courier getCourier() {
		return courier;
	}
	public void setCourier(Courier courier) {
		this.courier = courier;
	}
}
