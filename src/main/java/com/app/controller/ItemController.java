package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.helper.Builder;
import com.app.helper.Constants;
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
	public ResponseEntity<?> add(String item,MultipartFile file) throws Exception {
		try {
			itemService.add(item,file);
			return ResponseEntity.ok(Builder
					.build(Constants.ITEM, Constants.HAS_BEEN_ADDED));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Builder.build(Constants.ITEM, e.getMessage()));
		}
	}
	
	@PutMapping("/edit")
	public ResponseEntity<?> editPerson(@RequestBody Items item) throws Exception {
		try {
			itemService.edit(item);
			return new ResponseEntity<>(Builder
					.build(Constants.ITEM, Constants.HAS_BEEN_UPDATED), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/add-photo")
	public ResponseEntity<?> addPhoto(String item,MultipartFile file) throws Exception {
		try {
			itemService.addPhotoItem(item,file);
			return ResponseEntity.ok(Builder
					.build(Constants.ITEM, Constants.HAS_BEEN_ADDED));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Builder.build(Constants.ITEM, e.getMessage()));
		}
	}
	
	@GetMapping(value = "/detail/{id}")
	public ResponseEntity<?> getAbsentByPaging(@PathVariable String id) throws Exception {
		try {			
			return new ResponseEntity<>(itemService.getItemById(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
