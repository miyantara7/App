package com.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.ShoppingCartDetailDao;
import com.app.model.ShoppingCartDetail;

@Service
@Transactional
public class ShoppingCartDetailService {

	@Autowired
	private ShoppingCartDetailDao shoppingCartDetailDao;
	
	public void add(ShoppingCartDetail cartDetail) throws Exception{
		try {
			shoppingCartDetailDao.add(cartDetail);
		} catch (Exception e) {
			throw e;
		}
	}
}
