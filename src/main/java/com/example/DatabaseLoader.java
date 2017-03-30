package com.example;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.model.Employee;
import com.example.model.Store;
import com.example.repository.EmployeeRepository;
import com.example.repository.StoreRepository;

@Component
public class DatabaseLoader implements CommandLineRunner {

	private final EmployeeRepository employeeRepository;

	private final StoreRepository storeRepository;

	@Autowired
	public DatabaseLoader(EmployeeRepository employeeRepository, StoreRepository storeRepository) {
		this.employeeRepository = employeeRepository;
		this.storeRepository = storeRepository;
	}

	@Override
	public void run(String... strings) throws Exception {
		
		Date dateNow = new Date();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateNow);
		cal.add(Calendar.YEAR, -3);
		Date past3Years = cal.getTime();
		
		long store1Id = this.storeRepository.save(new Store("Store 1", 1)).getId();
		long store2Id = this.storeRepository.save(new Store("Store 2", 1)).getId();
		long groceries1Id = this.storeRepository.save(new Store("Groceries", 2)).getId();

		this.employeeRepository.save(new Employee("Employee 1", store1Id, 1, dateNow));
		this.employeeRepository.save(new Employee("Employee 2", store2Id, 1, dateNow));
		this.employeeRepository.save(new Employee("Affiliate 1", store1Id, 2, dateNow));
		this.employeeRepository.save(new Employee("Non Employee", groceries1Id, 3, dateNow));
		this.employeeRepository.save(new Employee("Cust more than 2 year", 0, 3, past3Years));
	}
}
// end::code[]