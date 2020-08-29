package com.app.dao;

import org.springframework.stereotype.Repository;

import com.app.model.ShoppingCartDetail;

@Repository
public class ShoppingCartDetailDao extends BaseDao<ShoppingCartDetail> {

	public ShoppingCartDetailDao() {
		setClazz(ShoppingCartDetail.class);
	}
}
