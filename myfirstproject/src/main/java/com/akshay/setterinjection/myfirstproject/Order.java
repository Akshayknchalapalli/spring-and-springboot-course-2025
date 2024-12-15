package com.akshay.setterinjection.myfirstproject;

import org.springframework.beans.factory.annotation.Autowired;

public class Order {
	private String productId;
	private String productName;
	@Autowired
	private Customer customer1;
	
	public Order() {};
	
	public Order(Customer customer) {
		this.customer1 = customer;
	}
	
	public Order(String productId, String productName, Customer customer1) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.customer1 = customer1;
	}
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public Customer getCustomer1() {
		return customer1;
	}

	public void setCustomer1(Customer customer1) {
		this.customer1 = customer1;
	}

	@Override
	public String toString() {
		return "Order [productId=" + productId + ", productName=" + productName + ", customer1=" + customer1
				+ ", getProductId()=" + getProductId() + ", getProductName()=" + getProductName() + ", getCustomer()="
				+ getCustomer1() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	
	
	
	
	
}
