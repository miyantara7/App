package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_m_items")
public class Items extends BaseMaster {

	private String name;
	@JoinColumn(name="cat_id")
	@ManyToOne
	private Category category; 
	@JoinColumn(name="merchant_id")
	@ManyToOne
	private Merchant merchant;
	private int price;
	private int sale;
	@Column(nullable = true)
	private Integer quantity;
	private String description;
	public int getSale() {
		return sale;
	}

	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public void setSale(int sale) {
		this.sale = sale;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

}
