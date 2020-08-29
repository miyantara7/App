package com.app.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.helper.Builder;
import com.app.model.Items;
import com.app.pojo.PojoItem;
import com.app.pojo.PojoItemDetail;

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
	

	
	@SuppressWarnings("unchecked")
	public PojoItem getPojoItemById(String id) throws Exception{
		List<Object[]> list = em.createNativeQuery(
				Builder.build("select tmi.id,tmi.name,tmi.price,tmi.quantity,tmi.sale,tmi.description," , 
						"tm.name as merchantName ,tc.name as catName " , 
						"FROM tb_m_items tmi " ,
						"LEFT JOIN tb_merchant tm on tmi.merchant_id = tm.id " , 
						"JOIN tb_categories tc on tc.id = tmi.cat_id " , 
						"WHERE tmi.id = :itemId"))
				.setParameter("itemId", id)
				.getResultList();
		
		return bMapperList(list, PojoItem.class, "id","name","price","quantity","sale","description","merchant","category").get(0);
	}
	
}
