package com.app.controller;

import java.util.List;

import javax.transaction.Transactional;

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
import com.app.model.Location;
import com.app.pojo.PojoIdSelector;
import com.app.service.LocationService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@EnableSwagger2
@RequestMapping(value = "/api/v1/admin/location")
public class LocationContoller {

	@Autowired
	private LocationService locationService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> add(@RequestBody Location location) throws Exception {
		try {
			locationService.add(location);
			return ResponseEntity.ok(Builder
					.build(Constants.LOCATION, Constants.HAS_BEEN_ADDED));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Builder.build(Constants.ITEM, e.getMessage()));
		}
	}
	
	@PutMapping("/edit")
	public ResponseEntity<?> edit(@RequestBody Location location) throws Exception {
		try {
			locationService.editLocation(location);
			return ResponseEntity.ok(Builder
					.build(Constants.LOCATION, Constants.HAS_BEEN_UPDATED));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Builder.build(Constants.ITEM, e.getMessage()));
		}
	}
	
	@PutMapping("/edit-active")
	public ResponseEntity<?> editActive(@RequestBody Location location) throws Exception {
		try {
			locationService.editActive(location);
			return ResponseEntity.ok(Builder
					.build(Constants.LOCATION, Constants.HAS_BEEN_UPDATED));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Builder.build(Constants.ITEM, e.getMessage()));
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteList(@RequestBody List<PojoIdSelector> listLocation) throws Exception {
		try {
			locationService.deleteList(listLocation);
			return ResponseEntity.ok(Builder
					.build(Constants.LOCATION, Constants.HAS_BEEN_DELETED));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Builder.build(Constants.ITEM, e.getMessage()));
		}
	}
	@DeleteMapping("/detail/{id}/delete")
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			locationService.delete(id);
			return ResponseEntity.ok(Builder
					.build(Constants.LOCATION, Constants.HAS_BEEN_DELETED));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Builder.build(Constants.ITEM, e.getMessage()));
		}
	}
	
	@GetMapping(value = "/detail/{id}")
	public ResponseEntity<?> getDetail(@PathVariable String id) throws Exception {
		try {			
			return new ResponseEntity<>(locationService.getById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping(value = "/get-list")
	public ResponseEntity<?> getById() throws Exception {
		try {			
			return new ResponseEntity<>(locationService.getAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
