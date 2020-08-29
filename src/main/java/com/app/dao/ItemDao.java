package com.app.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.helper.Builder;
import com.app.model.Items;

@Repository
public class ItemDao extends BaseDao<Items> {

	public ItemDao() {
		setClazz(Items.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<?> getAllItem() throws Exception{
		List<Object[]> list = em.createNativeQuery(
				Builder.build("select *from tb_m_items"))
				.getResultList();
		
		List<Object> listItem = new ArrayList<>();
		
		for(Object[] o : list) {
			LinkedHashMap<String, Object> item = new LinkedHashMap<>();
			item.put("id", (String)o[0]);
			item.put("name", (String)o[7]);
			item.put("price", (int)o[8]);
			listItem.add(item);
		}
		return listItem;
	}
	
	public void createUUID() {
		em.createNativeQuery(Builder.build("CREATE EXTENSION IF NOT EXISTS \"uuid-ossp\";"));
	}
}
