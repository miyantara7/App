package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.PaymentMethodDao;
import com.app.model.PaymentMethod;
import com.app.pojo.PojoIdSelector;

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
	
	public void editPaymentMethod(PaymentMethod paymentMethods) throws Exception{
		try {
			PaymentMethod paymentMethod = getById(paymentMethods.getId());
			paymentMethod.setCode(paymentMethods.getCode());
			paymentMethod.setName(paymentMethod.getName());
			paymentMethod.setPrice(paymentMethods.getPrice());
			edit(paymentMethod);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void editActive(PaymentMethod paymentMethods) throws Exception{
		try {
			PaymentMethod paymentMethod = getById(paymentMethods.getId());
			paymentMethod.setIsActive(false);
			edit(paymentMethod);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void edit(PaymentMethod paymentMethod) throws Exception{
		try {
			paymentMethodDao.edit(paymentMethod);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void deleteList(List<PojoIdSelector> listPaymentMethod) throws Exception{
		for(PojoIdSelector paymentMethod : listPaymentMethod) {
			delete(paymentMethod.getId());
		}
	}
	
	
	public void delete(String id) throws Exception{
		try {
			paymentMethodDao.deleteById(id);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<PaymentMethod> getAll() throws Exception{
		return paymentMethodDao.getAll();
	}
}
