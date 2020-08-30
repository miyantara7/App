package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.helper.Builder;
import com.app.helper.Constants;
import com.app.model.Courier;
import com.app.pojo.PojoIdSelector;
import com.app.service.CourierService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@EnableSwagger2
@RequestMapping(value = "/api/v1/admin/courier")
public class CourierController {

	@Autowired
	private CourierService courierSrvice;
	
	@PostMapping
	public ResponseEntity<?> add(@RequestBody Courier courier) throws Exception {
		try {
			courierSrvice.add(courier);
			return ResponseEntity.ok(Builder
					.build(Constants.COURIER, Constants.HAS_BEEN_ADDED));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Builder.build(Constants.ITEM, e.getMessage()));
		}
	}
	
	@PutMapping("/edit")
	public ResponseEntity<?> edit(@RequestBody Courier courier) throws Exception {
		try {
			courierSrvice.editCourier(courier);
			return ResponseEntity.ok(Builder
					.build(Constants.COURIER, Constants.HAS_BEEN_UPDATED));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Builder.build(Constants.ITEM, e.getMessage()));
		}
	}
	
	@PutMapping("/edit-active")
	public ResponseEntity<?> editActive(@RequestBody Courier courier) throws Exception {
		try {
			courierSrvice.editActive(courier);
			return ResponseEntity.ok(Builder
					.build(Constants.COURIER, Constants.HAS_BEEN_UPDATED));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Builder.build(Constants.ITEM, e.getMessage()));
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteList(@RequestBody List<PojoIdSelector> listCourier) throws Exception {
		try {
			courierSrvice.deleteList(listCourier);
			return ResponseEntity.ok(Builder
					.build(Constants.COURIER, Constants.HAS_BEEN_DELETED));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Builder.build(Constants.ITEM, e.getMessage()));
		}
	}
	@DeleteMapping("/detail/{id}/delete")
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			courierSrvice.delete(id);
			return ResponseEntity.ok(Builder
					.build(Constants.COURIER, Constants.HAS_BEEN_DELETED));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Builder.build(Constants.ITEM, e.getMessage()));
		}
	}
	
	@GetMapping(value = "/detail/{id}")
	public ResponseEntity<?> getDetail(@PathVariable String id) throws Exception {
		try {			
			return new ResponseEntity<>(courierSrvice.getById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping(value = "/get-list")
	public ResponseEntity<?> getAll() throws Exception {
		try {			
			return new ResponseEntity<>(courierSrvice.getAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
