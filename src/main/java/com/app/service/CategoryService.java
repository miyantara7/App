package com.app.service;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CategoryDao;
import com.app.model.Category;
import com.app.pojo.PojoCategories;
import com.app.pojo.PojoIdSelector;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	public Category getById(String id) throws Exception{
		Category category = categoryDao.getById(id);
		if(category == null) {
			throw new Exception("Category not found !");
		}else {
			return category;
		}
	}
	
	public PojoCategories getDetailCategoryById(String id) throws Exception{
		PojoCategories pojoCat = new PojoCategories();
		pojoCat.setCategories(getById(id));
		pojoCat.setListCategories(getSubCategory(id));
		return pojoCat;
	}
	
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
		return categoryDao.getPojoSubCategory(id);
	}
	
	public void editCategory(Category tempCategory) throws Exception{
		try {
			Category category = getById(tempCategory.getId());
			category.setCode(tempCategory.getCode());
			category.setName(category.getName());
			edit(category);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void editSubCategory(Category tempCategory) throws Exception{
		try {
			Category category = getById(tempCategory.getId());
			category.setCode(tempCategory.getCode());
			category.setName(category.getName());
			category.setCategory(tempCategory.getCategory());
			edit(category);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void editActive(Category tempCategory) throws Exception{
		try {
			Category category = getById(tempCategory.getId());
			category.setIsActive(false);
			edit(category);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void edit(Category category) throws Exception{
		try {
			categoryDao.edit(category);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void deleteList(List<PojoIdSelector> listCategory) throws Exception{
		for(PojoIdSelector category : listCategory) {
			delete(category.getId());
		}
	}
	
	public void deleteSubCategory(String id) throws Exception{
		List<Category> listCategory = categoryDao.getSubCategory(id);
		for(Category category : listCategory) {
			categoryDao.deleteById(category.getId());
		}
	}
	
	public void delete(String id) throws Exception{
		try {
			deleteSubCategory(id);
			categoryDao.deleteById(id);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<Category> getAll() throws Exception{
		return categoryDao.getAll();
	}
}
