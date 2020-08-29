package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.helper.Constants;
import com.app.helper.Builder;
import com.app.model.Items;
import com.app.service.ItemService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@EnableSwagger2
@RequestMapping(value = "/api/v1/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@PostMapping
	public ResponseEntity<?> add(@RequestBody Items item) throws Exception {
		try {
			itemService.add(item);
			return ResponseEntity.ok(Builder
					.build(Constants.ITEM, Constants.HAS_BEEN_ADDED));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Builder.build(Constants.ITEM, e.getMessage()));
		}
	}
}
