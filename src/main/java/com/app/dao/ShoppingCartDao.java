package com.app.dao;

import org.springframework.stereotype.Repository;

import com.app.model.ShoppingCart;

@Repository
public class ShoppingCartDao extends BaseDao<ShoppingCart> {

	public ShoppingCartDao() {
		setClazz(ShoppingCart.class);
	}
	
	
}
