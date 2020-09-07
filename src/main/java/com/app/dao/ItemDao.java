package com.app.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.app.helper.Builder;
import com.app.model.Items;
import com.app.pojo.PojoIdSelector;
import com.app.pojo.PojoItem;
import com.app.pojo.PojoSearchItem;

@Repository
public class ItemDao extends BaseDao<Items> {

	public ItemDao() {
		setClazz(Items.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getAllItem() throws Exception{
		List<Object[]> list = em.createNativeQuery(
				Builder.build("select tmi.id, tmi.name as item,tml.name as location,",
						"tm.name as merchant,tmi.price ",
						"from tb_m_items tmi " , 
						"left join tb_merchant tm on tm.id = tmi.merchant_id " , 
						"join tb_m_location tml on tm.location_id = tml.id " ,
						"where tmi.sale <= 0 and tmi.is_active = true " , 
						"group by tmi.id, tmi.name ,tml.name,tm.name,tmi.price,tmi.sale "))
				.getResultList();
		
		List<Object> listItem = new ArrayList<>();
		
		for(Object[] o : list) {
			LinkedHashMap<String, Object> item = new LinkedHashMap<>();
			item.put("id", (String)o[0]);
			item.put("name", (String)o[1]);
			item.put("location", (String)o[2]);
			item.put("merchant", (String)o[3]);
			item.put("price", (int)o[4]);
			listItem.add(item);
		}
		return listItem;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getItemSale() throws Exception{
		List<Object[]> list = em.createNativeQuery(
				Builder.build("select tmi.id, tmi.name as item,tml.name as location,",
						"tm.name as merchant," ,
						"tmi.price,tmi.sale,sum(tmi.price - (tmi.price * tmi.sale/100)) as priceSale ",
						"from tb_m_items tmi " , 
						"left join tb_merchant tm on tm.id = tmi.merchant_id " , 
						"join tb_m_location tml on tm.location_id = tml.id " ,
						"where tmi.sale > 0 and tmi.is_active = true " , 
						"group by tmi.id, tmi.name ,tml.name,tm.name,tmi.price,tmi.sale"))
				.getResultList();
		
		List<Object> listItem = new ArrayList<>();
		
		for(Object[] o : list) {
			LinkedHashMap<String, Object> item = new LinkedHashMap<>();
			item.put("id", (String)o[0]);
			item.put("name", (String)o[1]);
			item.put("location", (String)o[2]);
			item.put("merchant", (String)o[3]);
			item.put("price", (int)o[4]);
			item.put("sale", (int)o[5]);
			item.put("priceSale", ((BigInteger)o[6]).intValue());
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
						"JOIN tb_m_categories tc on tc.id = tmi.cat_id " , 
						"WHERE tmi.id = :itemId " ))
				.setParameter("itemId", id)
				.getResultList();
		
		return bMapperList(list, PojoItem.class, "id","name","price","quantity","sale","description","merchant","category").get(0);
	}
	
	public String setQueryForItemSearch(StringBuilder sb,List<PojoIdSelector> listCategory) throws Exception{ 

		if (!listCategory.isEmpty() || listCategory != null) {
			for (int i = 1; i <= listCategory.size(); i++) {
				if (i == 1) {
					sb.append(" AND item.cat_id = :catId" + i);
				} else {
					sb.append(" OR item.cat_id = :catId" + i);
				}
			}
		}
		return sb.toString();
	}
	
	public Query setParamForItemSearch(Query query,List<PojoIdSelector> listCategory) throws Exception{  

		int counter = 0;
		if (!listCategory.isEmpty() || listCategory != null) {
			for (PojoIdSelector o : listCategory) {
				counter++;
				query.setParameter("catId"+counter, o.getId());
			}
		}
		
		return query;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getItemBySearch(PojoSearchItem pojoSearch) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("select item.id,item.itemName,item.location,item.cat_id,item.merchant,item.price,item.sale,item.priceSale ");
		sb.append("FROM ( ");
		sb.append("select tmi.id, tmi.name as itemName,tml.name as location,tmi.cat_id,tm.name as merchant,");
		sb.append("tmi.price,tmi.sale,sum(tmi.price - (tmi.price * tmi.sale/100)) as priceSale from tb_m_items tmi ");
		sb.append("join tb_merchant tm on tmi.merchant_id = tm.id ");
		sb.append("join tb_m_location tml on tm.location_id = tml.id ");
		sb.append("WHERE tmi.is_active = true ");
		sb.append("group by tmi.id, tmi.name ,tml.name,tm.name,tmi.price,tmi.sale ) as item ");
		sb.append("where 1=1 ");

		
		if (pojoSearch.getInquiry() != null && !pojoSearch.getInquiry().isEmpty()) {
			sb.append(" AND POSITION(LOWER('").append(pojoSearch.getInquiry()).append("') in LOWER(CONCAT(")
					.append("item.id,item.itemName,item.location,item.cat_id,"
							+ "item.merchant,item.price,item.sale,item.priceSale")
					.append("))) > 0 ");
		}

		Query query = em.createNativeQuery(setQueryForItemSearch(sb, pojoSearch.getListCategories()));
		
		List<Object[]> list = setParamForItemSearch(query, pojoSearch.getListCategories()).getResultList();

		List<Object> listItem = new ArrayList<>();

		for (Object[] o : list) {
			LinkedHashMap<String, Object> item = new LinkedHashMap<>();
			item.put("id", (String) o[0]);
			item.put("name", (String) o[1]);
			item.put("location", (String) o[2]);
			item.put("merchant", (String) o[4]);
			item.put("price", (int) o[5]);
			item.put("sale", (int) o[6]);
			item.put("priceSale", ((BigInteger) o[7]).intValue());
			listItem.add(item);
		}
		return listItem;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getAllItemByUser(String userId) throws Exception{
		List<Object[]> list = em.createNativeQuery(
				Builder.build("select tmi.id, tmi.name as item,tmi.quantity ,tmi.price ,tc.name " , 
						"from tb_m_items tmi " , 
						"left join tb_m_categories tc on tmi.cat_id = tc.id " , 
						"left join tb_merchant tm on tm.id = tmi.merchant_id " , 
						"join tb_users tu on tu.id = tm.user_id " ,
						"WHERE tu.id = :userId "))
				.setParameter("userId", userId)
				.getResultList();
		
		List<Object> listItem = new ArrayList<>();
		
		for(Object[] o : list) {
			LinkedHashMap<String, Object> item = new LinkedHashMap<>();
			item.put("id", (String)o[0]);
			item.put("name", (String)o[1]);
			item.put("quantity", (int)o[2]);
			item.put("price", (int)o[3]);
			item.put("category", (String)o[4]);
			listItem.add(item);
		}
		return listItem;
	}

}
