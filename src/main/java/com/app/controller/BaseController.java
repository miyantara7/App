package com.app.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import com.app.service.ItemService;

public abstract class BaseController {

	@Autowired
	private ItemService itemService;
	
	@Value("${upload.path}")
	private String path;

	@Bean
	public void createStorage() throws Exception{
		if (!Files.exists(Paths.get(path))) {
			File files = new File(path);
			files.mkdirs();         
		}
	}

}
