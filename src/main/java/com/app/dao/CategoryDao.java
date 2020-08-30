package com.app.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.helper.Builder;
import com.app.model.Category;

@Repository
public class CategoryDao extends BaseDao<Category>{

	public CategoryDao() {
		setClazz(Category.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getHeadCategory() throws Exception{
		List<Object[]> list = em.createNativeQuery(
				Builder.build("select id,code,name from tb_m_categories tc " , 
						"where tc.cat_id is null"))
				.getResultList();
		
		List<Object> listCategories = new ArrayList<>();
		
		for(Object[] o : list) {
			LinkedHashMap<String, Object> category = new LinkedHashMap<>();
			category.put("id", (String)o[0]);
			category.put("code", (String)o[1]);
			category.put("name", (String)o[2]);
			listCategories.add(category);
		}
		return listCategories;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getPojoSubCategory(String id) throws Exception{
		List<Object[]> list = em.createNativeQuery(
				Builder.build("select id,code,name from tb_m_categories tc " , 
						"where tc.cat_id = :id "))
				.setParameter("id", id)
				.getResultList();
		
		List<Object> listCategories = new ArrayList<>();
		
		for(Object[] o : list) {
			LinkedHashMap<String, Object> category = new LinkedHashMap<>();
			category.put("id", (String)o[0]);
			category.put("code", (String)o[1]);
			category.put("name", (String)o[2]);
			listCategories.add(category);
		}
		return listCategories;
	}
	
	@SuppressWarnings("unchecked")
	public List<Category> getSubCategory(String id) throws Exception{
		List<Object[]> list = em.createNativeQuery(
				Builder.build("select id,code,name from tb_m_categories tc " , 
						"where tc.cat_id = :id "))
				.setParameter("id", id)
				.getResultList();

		return bMapperList(list, Category.class, "id","code","name");
	}
}
