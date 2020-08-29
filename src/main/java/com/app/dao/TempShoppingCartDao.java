package com.app.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.helper.Builder;
import com.app.model.TempShoppingCart;
import com.app.pojo.PojoTempShoppingCart;

@Repository
public class TempShoppingCartDao extends BaseDao<TempShoppingCart> {

	@SuppressWarnings("unchecked")
	public List<PojoTempShoppingCart> getCartTempByUserId(String userId) throws Exception{
		List<Object[]> list = em.createNativeQuery(
				Builder.build("select ttsc.id,tm.name ,tml.name locName, tmi.name itemName," ,
						"tmi.price ,ttsc.quantity,ttsc.user_id,ttsc.total_price " , 
						"from tb_temp_shopping_chart ttsc " , 
						"join tb_m_items tmi on ttsc.item_id = tmi.id " , 
						"join tb_merchant tm on ttsc.merchant_id = tm.id " ,
						"join tb_m_location tml on tml.id = tm.location_id " , 
						"where ttsc.user_id = :userId"))
				.setParameter("userId", userId)
				.getResultList();
		
		return bMapperList(list, PojoTempShoppingCart.class, "id","merchantName","location",
				"itemName","price","quantity","userId","totalItemPrice");
	}
}
