package com.example.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Employee;
import com.example.model.Store;
import com.example.repository.EmployeeRepository;
import com.example.repository.StoreRepository;

@Service
public class DiscountService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private StoreRepository storeRepository;
	
	public double getDiscount(long storeId, long userId, double totalAmount) {
		
		double discount = 0;
		double totalDiscount = 0;
		if (totalAmount > 100) {
			int mod = (int) (totalAmount/100);
			totalAmount = totalAmount - (mod * 5);
		}
		Store store = storeRepository.findOne(storeId);
		if(store.getStoreType() == Store.GROCERIES) {
			return totalAmount;
		}
		
		Employee employee = employeeRepository.findOne(userId);
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.YEAR, -2);
		Date past2Years = cal.getTime();
		
		if(employee.getEmployeeType() == Employee.EMPLOYEE) {
			discount = 30;
		} else if(employee.getEmployeeType() == Employee.AFFILIATE) {
			discount = 15;
		} else if(employee.getCreatedDate().compareTo(past2Years) < 0) {
			discount = 5;
		}
		
		if(discount > 0) {
			totalDiscount = (totalAmount/100) * discount;
			totalAmount = totalAmount - totalDiscount;
		}
		
		return totalAmount;
	}
}
