package com.app.service;

import java.sql.Timestamp;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.MerchantDao;
import com.app.model.Merchant;

@Service
@Transactional
public class MerchantService {

	@Autowired
	private MerchantDao merchantDao;
	
	public void add(Merchant merchant) throws Exception{
		try {
			merchant.setCreatedBy("system");
			merchant.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			merchant.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
			merchant.setUpdatedBy("system");
			merchantDao.add(merchant);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
}
