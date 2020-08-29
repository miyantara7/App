package com.app.dao;

import org.springframework.stereotype.Repository;

import com.app.model.Location;

@Repository
public class LocationDao extends BaseDao<Location> {

	public LocationDao() {
		setClazz(Location.class);
	}
}
