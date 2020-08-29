package com.app.dao;

import org.springframework.stereotype.Repository;

import com.app.model.PaymentMethod;

@Repository
public class PaymentMethodDao extends BaseDao<PaymentMethod> {

	public PaymentMethodDao() {
		setClazz(PaymentMethod.class);
	}
}
