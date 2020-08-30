package com.app.service;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.LocationDao;
import com.app.model.Location;
import com.app.pojo.PojoIdSelector;

@Service
@Transactional
public class LocationService {

	@Autowired
	private LocationDao locationDao;
	
	public Location getById(String id) throws Exception{
		Location location = locationDao.getById(id);
		if(location == null) {
			throw new Exception("Location not found !");
		}else {
			return location;
		}
	}
	
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
	
	public void editLocation(Location tempLocation) throws Exception{
		try {
			Location location = getById(tempLocation.getId());
			location.setCode(tempLocation.getCode());
			location.setName(location.getName());
			edit(location);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void editActive(Location tempLocation) throws Exception{
		try {
			Location location = getById(tempLocation.getId());
			location.setIsActive(false);
			edit(location);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void edit(Location location) throws Exception{
		try {
			locationDao.add(location);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void deleteList(List<PojoIdSelector> listLocation) throws Exception{
		for(PojoIdSelector location : listLocation) {
			delete(location.getId());
		}
	}
	
	public void delete(String id) throws Exception{
		try {
			locationDao.deleteById(id);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<Location> getAll() throws Exception{
		return locationDao.getAll();
	}
}
