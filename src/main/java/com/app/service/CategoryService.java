package com.app.service;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CategoryDao;
import com.app.model.Category;

@Service
@Transactional
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
	
	public List<Object> getHeadCategory() throws Exception{
		return categoryDao.getHeadCategory();
	}
	
	public List<Object> getSubCategory(String id) throws Exception{
		return categoryDao.getSubCategory(id);
	}
}
