package com.app.dao;

import org.springframework.stereotype.Repository;

import com.app.model.Merchant;

@Repository
public class MerchantDao extends BaseDao<Merchant> {

	public MerchantDao() {
		setClazz(Merchant.class);
	}
}
