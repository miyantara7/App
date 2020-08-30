package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.ShoppingCartDao;
import com.app.model.Courier;
import com.app.model.Items;
import com.app.model.PaymentMethod;
import com.app.model.ShoppingCart;
import com.app.model.ShoppingCartDetail;
import com.app.model.TempShoppingCart;
import com.app.model.Voucher;
import com.app.pojo.BasePojo;
import com.app.pojo.PojoShoppingCart;
import com.app.pojo.PojoTempShoppingCart;

@Service
@Transactional
public class ShoppingCartService {

	@Autowired
	private TempShoppingCartService tempShoppingCartService;
	
	@Autowired
	private VoucherService voucherService;
		
	@Autowired
	private PaymentMethodService paymentMethodService;
	
	@Autowired
	private CourierService courierService;
	
	@Autowired
	private ShoppingCartDao shoppingCartDao;
	
	@Autowired
	private ShoppingCartDetailService shoppingCartDetailService;
	
	
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
	
	public void checkout(PojoShoppingCart pojoCart) throws Exception{
		int total = 0;
		try {
			ShoppingCart cart = new ShoppingCart();
			Voucher voucher = voucherService.getById(pojoCart.getVoucher().getId());
			PaymentMethod payment = paymentMethodService.getById(pojoCart.getPaymentMethod().getId());
			Courier courier = courierService.getById(pojoCart.getCourier().getId());
			cart.setUser(pojoCart.getUser());
			cart.setVoucher(voucher);
			cart.setPaymentMethod(payment);
			cart.setCourier(courier);
			if(voucher.getType().equals("rupiah")) {
				total+=pojoCart.getTotalPrice() - voucher.getPrice();
			}else {
				total+=pojoCart.getTotalPrice() - (pojoCart.getTotalPrice()* voucher.getPrice()/100);
			}		
			total -= payment.getPrice();
			total -= courier.getPrice();
			cart.setTotalPrice(total);
			shoppingCartDao.add(cart);
			addDetail(pojoCart,cart);
		} catch (Exception e) {
			throw e;
		}
	}

	public void addDetail(PojoShoppingCart pojoCart,ShoppingCart cart) throws Exception{
		for(PojoTempShoppingCart tempShoppingCart : pojoCart.getCart()) {
			ShoppingCartDetail shoppingCartDetail = new ShoppingCartDetail();
			Items item = new Items();
			item.setId(tempShoppingCart.getItemId());
			shoppingCartDetail.setItem(item);
			shoppingCartDetail.setMerchant(pojoCart.getMerchant());
			shoppingCartDetail.setQuantity(tempShoppingCart.getQuantity());
			shoppingCartDetail.setPrice(tempShoppingCart.getPrice());
			shoppingCartDetail.setShoppingCart(cart);
			shoppingCartDetail.setPrice(tempShoppingCart.getPrice());
			shoppingCartDetailService.add(shoppingCartDetail);
		}
		deleteTemp(pojoCart);
	}
	
	public void deleteTemp(PojoShoppingCart pojoCart ) throws Exception{
		for(PojoTempShoppingCart tempShoppingCart : pojoCart.getCart()) {
			tempShoppingCartService.delete(tempShoppingCart.getId());	
		}
	}

	public List<PaymentMethod> getAllPayment() throws Exception{
		return paymentMethodService.getAll();
	}
	
	public List<Courier> getAllCourier() throws Exception{
		return courierService.getAll();
	}
	
	public List<Object> getAllVoucher() throws Exception{
		return voucherService.getAllVoucherActive();
	}
	
	public void deleteItem(List<BasePojo> listItem) throws Exception{
		for(BasePojo o : listItem) {
			tempShoppingCartService.delete(o.getId());
		}
	}
}
