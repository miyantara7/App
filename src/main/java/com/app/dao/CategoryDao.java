package com.app.dao;

import org.springframework.stereotype.Repository;

import com.app.model.Category;

@Repository
public class CategoryDao extends BaseDao<Category>{

	public CategoryDao() {
		setClazz(Category.class);
	}
}
