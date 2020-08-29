package com.app.service;

import java.sql.Timestamp;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.LocationDao;
import com.app.model.Location;

@Service
@Transactional
public class LocationService {

	@Autowired
	private LocationDao locationDao;
	
	public void add(Location location) throws Exception{
		try {
			location.setCreatedBy("admin");
			location.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			location.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
			location.setUpdatedBy("admin");
			locationDao.add(location);
		} catch (Exception e) {
			throw e;
		}
	}
}
