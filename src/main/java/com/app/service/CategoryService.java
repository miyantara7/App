package com.app.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CategoryDao;
import com.app.model.Category;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	public void add(Category category) throws Exception{
		try {
			category.setCreatedBy("admin");
			category.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			category.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
			category.setUpdatedBy("admin");
			categoryDao.add(category);
		} catch (Exception e) {
			throw e;
		}
	}
}
