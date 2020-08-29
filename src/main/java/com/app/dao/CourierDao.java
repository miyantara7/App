package com.app.dao;

import org.springframework.stereotype.Repository;

import com.app.model.Courier;

@Repository
public class CourierDao extends BaseDao<Courier>{

	public CourierDao() {
		setClazz(Courier.class);
	}
}
