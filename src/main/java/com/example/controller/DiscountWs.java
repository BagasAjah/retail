package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.service.DiscountService;

@Controller
public class DiscountWs {
	
	@Autowired
	private DiscountService discountService;

	@RequestMapping(value = "/getDiscount")
	public ResponseEntity<?>  getDiscount(@RequestParam long storeId, @RequestParam long userId, @RequestParam double totalAmount) {
		double  result = discountService.getDiscount(storeId, userId, totalAmount);
		ResponseEntity<?> resp = new ResponseEntity<>(result, HttpStatus.OK);
		return resp;
	}
}
