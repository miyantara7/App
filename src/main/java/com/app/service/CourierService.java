package com.app.service;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CourierDao;
import com.app.model.Courier;
import com.app.pojo.PojoIdSelector;

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
			courier.setCreatedBy("admin");
			courier.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			courier.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
			courier.setUpdatedBy("admin");
			courierDao.add(courier);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void editCourier(Courier tempCourier) throws Exception{
		try {
			Courier courier = getById(tempCourier.getId());
			courier.setCode(tempCourier.getCode());
			courier.setName(courier.getName());
			courier.setPrice(tempCourier.getPrice());
			edit(courier);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void editActive(Courier tempCourier) throws Exception{
		try {
			Courier courier = getById(tempCourier.getId());
			courier.setIsActive(false);
			edit(courier);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void edit(Courier courier) throws Exception{
		try {
			courierDao.add(courier);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void deleteList(List<PojoIdSelector> listCourier) throws Exception{
		for(PojoIdSelector courier : listCourier) {
			delete(courier.getId());
		}
	}
	
	
	public void delete(String id) throws Exception{
		try {
			Courier courier = getById(id);
			courierDao.delete(courier);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<Courier> getAll() throws Exception{
		return courierDao.getAll();
	}
}
