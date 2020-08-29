package com.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_shopping_chart")
public class ShoppingCart extends BaseEntity {

	@JoinColumn(name="user_id")
	@ManyToOne
	private Users user;
	@JoinColumn(name="merchant_id")
	@ManyToOne
	private Merchant merchant;
	@JoinColumn(name="voucher_id")
	@ManyToOne
	private Voucher voucher;
	private int totalPrice;
	
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
}
