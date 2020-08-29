package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.ItemService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@EnableSwagger2
@RequestMapping(value = "/api/v1/dashboard")
public class ItemDashboardController {

	@Autowired
	private ItemService itemService;
	
	@GetMapping(value = "/item")
	public ResponseEntity<?> getAllItem(){
		try {
//			PojoPagination listCompetency = competencyService.getCompetencySearch(page, limit, inquiry) ;
			return new ResponseEntity<>(itemService.getAllItem(),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
}
