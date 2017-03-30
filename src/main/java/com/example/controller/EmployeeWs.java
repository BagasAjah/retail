package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;

@RestController
public class EmployeeWs {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@RequestMapping(value = "/getEmployees")
	public List<Employee>  getDiscount() {
		List<Employee>  result = new ArrayList<>();
		employeeRepository.findAll().forEach(result::add);;
		return result;
	}
}
