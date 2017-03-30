package com.example.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee {

	public static final int EMPLOYEE = 1;
	public static final int AFFILIATE = 2;
	public static final int OTHER = 3;
	
	private @Id @GeneratedValue Long id;
	private String fullName;
	private long storeId;
	private int employeeType;
	private Date createdDate;

	public Employee() {}

	public Employee(String name, long storeId, int employeeType, Date createdDate) {
		this.fullName = name;
		this.storeId = storeId;
		this.employeeType = employeeType;
		this.createdDate = createdDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public int getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(int employeeType) {
		this.employeeType = employeeType;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
}
