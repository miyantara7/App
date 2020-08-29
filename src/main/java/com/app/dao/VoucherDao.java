package com.app.dao;

import org.springframework.stereotype.Repository;

import com.app.model.Voucher;

@Repository
public class VoucherDao extends BaseDao<Voucher> {

	public VoucherDao() {
		setClazz(Voucher.class);
	}
}
