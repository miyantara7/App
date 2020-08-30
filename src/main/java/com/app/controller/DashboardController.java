package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojo.BasePojo;
import com.app.service.CategoryService;
import com.app.service.ItemService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@EnableSwagger2
@RequestMapping(value = "/api/v1/dashboard")
public class DashboardController {

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping(value = "/item")
	public ResponseEntity<?> getAllItem(){
		try {
			return new ResponseEntity<>(itemService.getAllItem(),HttpStatus.OK);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/hot-sale")
	public ResponseEntity<?> getItemSale(){
		try {
			return new ResponseEntity<>(itemService.getItemSale(),HttpStatus.OK);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/categories")
	public ResponseEntity<?> getHeadCategory(){
		try {
			return new ResponseEntity<>(categoryService.getHeadCategory(),HttpStatus.OK);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/categories/{id}")
	public ResponseEntity<?> getSubCategory(@PathVariable String id){
		try {
			return new ResponseEntity<>(categoryService.getSubCategory(id),HttpStatus.OK);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
	@GetMapping(value = "/item/{id}")
	public ResponseEntity<?> getAbsentByPaging(@PathVariable String id) throws Exception {
		try {			
			return new ResponseEntity<>(itemService.getItemById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/search")
	public ResponseEntity<?> getItemBySearch(String inquiry, List<BasePojo> listCategory) throws Exception {
		try {			
			return new ResponseEntity<>(itemService.getItemBySearch(inquiry,listCategory), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
}
