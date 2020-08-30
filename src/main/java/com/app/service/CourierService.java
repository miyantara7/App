package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CourierDao;
import com.app.model.Courier;

@Service
@Transactional
public class CourierService {

	@Autowired
	private CourierDao courierDao;
	
	public Courier getById(String id) throws Exception{
		Courier courier = courierDao.getById(id);
		if(courier == null) {
			throw new Exception("Courier not found !");
		}else {
			return courier;
		}
	}
	
	public void add(Courier courier) throws Exception{
		try {
			courierDao.add(courier);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<Courier> getAll() throws Exception{
		return courierDao.getAll();
	}
}
