package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.TempShoppingCartDao;
import com.app.model.Items;
import com.app.model.TempShoppingCart;
import com.app.pojo.PojoTempShoppingCart;

@Service
@Transactional
public class TempShoppingCartService {

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private TempShoppingCartDao tempShoppingCartDao;
	
	public void add(TempShoppingCart tempCart) throws Exception{
		try {
			Items item = itemService.getById(tempCart.getItem().getId());
			if(String.valueOf(item.getSale()) == null) {
				item.setSale(0);
			}		
			tempCart.setTotalPrice((item.getPrice() - (item.getPrice() * item.getSale()/100)) * tempCart.getQuantity());
			tempCart.setItem(item);
			tempCart.setItemPrice(item.getPrice());
			tempCart.setMerchant(item.getMerchant());
			tempShoppingCartDao.add(tempCart);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<PojoTempShoppingCart> getCartTempByUserId(String userId) throws Exception {
		return tempShoppingCartDao.getCartTempByUserId(userId);
	}
	
	public void delete(String id) throws Exception{
		try {
			tempShoppingCartDao.deleteByid(id);
		} catch (Exception e) {
			throw e;
		}
	}
}
