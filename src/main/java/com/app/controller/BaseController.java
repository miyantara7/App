package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.app.service.ItemService;

public abstract class BaseController {

	@Autowired
	private ItemService itemService;
	
	@Bean
	public void insertItem() throws Exception{ 
		itemService.createUUID();
	}
	
}
