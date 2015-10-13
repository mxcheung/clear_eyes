package com.maxcheung.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.maxcheung.employee.data.EmpRequest;
import com.maxcheung.employee.data.EmpResponse;

@RestController
public class EmployeeController {

	@RequestMapping(produces = "application/json", method = RequestMethod.POST, value = "/employee")
	@ResponseBody
	public ResponseEntity<EmpResponse> getEmployee(@RequestHeader(value = "User-Agent") String userAgent,
			@RequestBody EmpRequest request) {
		// code goes here
		EmpResponse response = new EmpResponse();
		response.setGender("Male");
		response.setId(request.getId());
		response.setName(request.getName());

		return new ResponseEntity<EmpResponse>(response, HttpStatus.CREATED);
	}

}
