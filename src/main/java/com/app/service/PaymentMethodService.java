package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.PaymentMethodDao;
import com.app.model.Courier;
import com.app.model.PaymentMethod;

@Service
@Transactional
public class PaymentMethodService {

	@Autowired
	private PaymentMethodDao paymentMethodDao;
	
	public PaymentMethod getById(String id) throws Exception{
		PaymentMethod paymentMethod = paymentMethodDao.getById(id);
		if(paymentMethod == null) {
			throw new Exception("Courier not found !");
		}else {
			return paymentMethod;
		}
	}
	
	public void add(PaymentMethod paymentMethod) throws Exception{
		try {
			paymentMethodDao.add(paymentMethod);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<PaymentMethod> getAll() throws Exception{
		return paymentMethodDao.getAll();
	}
}
