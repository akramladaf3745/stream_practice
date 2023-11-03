package com.stream.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.stream.EmployeeRepo;

@RestController
public class StringController {
	
	@Autowired
	private EmployeeRepo employeeRepo;

	
}
