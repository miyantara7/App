package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.dao.TempShoppingCartDao;
import com.app.model.Items;
import com.app.model.TempShoppingCart;
import com.app.model.Users;
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
			tempCart.setTotalPrice(item.getPrice() * tempCart.getQuantity());
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
}
