package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.helper.Builder;
import com.app.helper.Constants;
import com.app.model.TempShoppingCart;
import com.app.service.ItemService;
import com.app.service.ShoppingCartService;
import com.app.service.TempShoppingCartService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@EnableSwagger2
@RequestMapping(value = "/api/v1/cart")
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService cartService;
	
	@Autowired
	private ItemService itemSErvice;
	
	@PostMapping("/add-temp")
	public ResponseEntity<?> addTempCart(@RequestBody TempShoppingCart cart) throws Exception {
		try {
			cartService.addTempCart(cart);
			return ResponseEntity.ok(Builder
					.build(Constants.ITEM, Constants.HAS_BEEN_ADDED));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Builder.build(Constants.ITEM, e.getMessage()));
		}
	}
	
	@GetMapping(value = "/get-cart")
	public ResponseEntity<?> getCartTempByUserId(@RequestParam String userId) throws Exception {
		try {			
			return new ResponseEntity<>(cartService.getCartTempByUserId(userId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
