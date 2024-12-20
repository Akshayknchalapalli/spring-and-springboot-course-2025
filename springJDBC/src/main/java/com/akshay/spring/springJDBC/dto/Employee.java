package com.akshay.spring.springJDBC.dto;

public class Employee {
	int id;
	String name;
	int exp;
	public Employee(int id, String name, int exp) {
		super();
		this.id = id;
		this.name = name;
		this.exp = exp;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	
}
