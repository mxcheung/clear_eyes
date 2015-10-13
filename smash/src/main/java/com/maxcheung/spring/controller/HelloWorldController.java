package com.maxcheung.spring.controller;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.maxcheung.spring.model.Data;
import com.maxcheung.spring.model.Request;
import com.maxcheung.spring.model.Response;

@RestController
public class HelloWorldController {

	@RequestMapping("/hello")
	public String hello(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "helloworld";
	}

	@RequestMapping(produces = "application/json", method = RequestMethod.GET, value = "/data")
	@ResponseBody
	public ResponseEntity<Data> getData(@RequestHeader(value = "User-Agent") String userAgent,
			@RequestParam(value = "ID", defaultValue = "xxxx") String id) {
		// code goes here
		Data data = new Data();
		data.setSomeData("xyz");
		data.setUserAgent(userAgent);
		data.setId(id);

		return new ResponseEntity<Data>(data, HttpStatus.CREATED);
	}

	@RequestMapping(produces = "application/json", method = RequestMethod.POST, value = "/data2")
	@ResponseBody
	public ResponseEntity<Response> getData2(@RequestHeader(value = "User-Agent") String userAgent,
			@RequestBody Request someData) {
		// code goes here
		Response response = new Response();
		response.setSomeData(someData.getSomeData());
		response.setUserAgent(userAgent);
		response.setId(someData.getId());
		response.setAdditionalInfo("additionalInfo 1234");
		
		BigDecimal sum = BigDecimal.ZERO;
		sum = addBigDecimal(sum, someData.getCreditAmt());
		sum = addBigDecimal(sum, someData.getMiscAmt());
		response.setTotalAmt(sum);
		return new ResponseEntity<Response>(response, HttpStatus.CREATED);
	}

	private BigDecimal addBigDecimal(BigDecimal sum, BigDecimal amt) {
		BigDecimal newTotal = sum ;
		if (amt != null) {
			newTotal = newTotal.add(amt);
		} 
			
		return newTotal;
	}
	
	


}
