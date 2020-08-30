package com.app.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.helper.Builder;
import com.app.model.Voucher;

@Repository
public class VoucherDao extends BaseDao<Voucher> {

	public VoucherDao() {
		setClazz(Voucher.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getAllVoucherActive() throws Exception{
		List<Object[]> list = em.createNativeQuery(
				Builder.build("select id,code,name,price from tb_m_voucher  " , 
						"where is_active is true"))
				.getResultList();
		
		List<Object> listVoucher = new ArrayList<>();
		
		for(Object[] o : list) {
			LinkedHashMap<String, Object> voucher = new LinkedHashMap<>();
			voucher.put("id", (String)o[0]);
			voucher.put("code", (String)o[1]);
			voucher.put("name", (String)o[2]);
			voucher.put("price", (int)o[3]);
			listVoucher.add(voucher);
		}
		return listVoucher;
	}
}
