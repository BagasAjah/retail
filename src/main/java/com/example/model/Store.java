package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Store {

	public static final int STORE = 1;
	public static final int GROCERIES =2;

	private @Id @GeneratedValue Long id;
	private String storeName;
	private int storeType;

	public Store() {
	}

	public Store(String name, int storeType) {
		this.storeName = name;
		this.storeType = storeType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public int getStoreType() {
		return storeType;
	}

	public void setStoreType(int storeType) {
		this.storeType = storeType;
	}

}
