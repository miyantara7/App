package com.app.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.model.ItemsDetail;
import com.app.pojo.PojoItemDetail;

@Repository
public class ItemDetailDao extends BaseDao<ItemsDetail> {

	@SuppressWarnings("unchecked")
	public List<PojoItemDetail> getPojoDetailItemByItemId(String id) throws Exception{
		List<Object[]> list = em.createNativeQuery("select id,file_name,file_type,item_id from tb_items_detail where item_id = :itemId")
				.setParameter("itemId", id)
				.getResultList();
		
		return bMapperList(list, PojoItemDetail.class, "id","fileName","fileType","itemId");
	}
}
