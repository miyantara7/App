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
import com.app.model.Voucher;
import com.app.pojo.PojoIdSelector;
import com.app.service.VoucherService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@EnableSwagger2
@RequestMapping(value = "/api/v1/admin/voucher")
public class VoucherController {

	@Autowired
	private VoucherService voucherService;
	
	@PostMapping
	public ResponseEntity<?> add(@RequestBody Voucher voucher) throws Exception {
		try {
			voucherService.add(voucher);
			return ResponseEntity.ok(Builder
					.build(Constants.VOUCHER, Constants.HAS_BEEN_ADDED));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Builder.build(Constants.ITEM, e.getMessage()));
		}
	}
	
	@PutMapping("/edit")
	public ResponseEntity<?> editPerson(@RequestBody Voucher voucher) throws Exception {
		try {
			voucherService.editVoucher(voucher);
			return new ResponseEntity<>(Builder
					.build(Constants.VOUCHER, Constants.HAS_BEEN_UPDATED), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/edit-active")
	public ResponseEntity<?> editActive(@RequestBody  Voucher voucher) throws Exception {
		try {
			voucherService.editActive(voucher);
			return ResponseEntity.ok(Builder
					.build(Constants.VOUCHER, Constants.HAS_BEEN_UPDATED));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Builder.build(Constants.ITEM, e.getMessage()));
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteList(@RequestBody List<PojoIdSelector> listVoucher) throws Exception {
		try {
			voucherService.deleteList(listVoucher);
			return ResponseEntity.ok(Builder
					.build(Constants.VOUCHER, Constants.HAS_BEEN_DELETED));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Builder.build(Constants.ITEM, e.getMessage()));
		}
	}
	@DeleteMapping("/detail/{id}/delete")
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			voucherService.delete(id);
			return ResponseEntity.ok(Builder
					.build(Constants.VOUCHER, Constants.HAS_BEEN_DELETED));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Builder.build(Constants.ITEM, e.getMessage()));
		}
	}
	
	@GetMapping(value = "/detail/{id}")
	public ResponseEntity<?> getDetail(@PathVariable String id) throws Exception {
		try {			
			return new ResponseEntity<>(voucherService.getById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping(value = "/get-list")
	public ResponseEntity<?> getById() throws Exception {
		try {			
			return new ResponseEntity<>(voucherService.getAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
