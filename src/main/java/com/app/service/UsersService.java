package com.app.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.UsersDao;
import com.app.model.Users;

@Service
public class UsersService {

	@Autowired
	private UsersDao usersDao;
	
	public void add(Users users) throws Exception{
		try {
			users.setCreatedBy("system");
			users.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			users.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
			users.setUpdatedBy("system");
			usersDao.add(users);
		} catch (Exception e) {
			throw e;
		}
	}
}
