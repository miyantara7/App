package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.TempShoppingCart;
import com.app.pojo.PojoShoppingCart;
import com.app.pojo.PojoTempShoppingCart;

@Service
public class ShoppingCartService {

	@Autowired
	private TempShoppingCartService tempShoppingCartService;
	
	public void addTempCart(TempShoppingCart tempCart) throws Exception{
		tempShoppingCartService.add(tempCart);
	}

	public PojoShoppingCart getCartTempByUserId(String userId) throws Exception {
		PojoShoppingCart pojoShoppingCart = new PojoShoppingCart();
		try {
			List<PojoTempShoppingCart> listTempCart = tempShoppingCartService.getCartTempByUserId(userId);
			int total = 0;
			for (PojoTempShoppingCart pojo : listTempCart) {
				total += pojo.getTotalItemPrice();
			}
			pojoShoppingCart.setCart(listTempCart);
			pojoShoppingCart.setTotalPrice(total);
			return pojoShoppingCart;
		} catch (Exception e) {
			throw e;
		}
	}
}
