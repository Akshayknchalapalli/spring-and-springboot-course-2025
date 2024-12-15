package com.akshay.setterinjection.myfirstproject;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

// int , float , double , long => primitive Datatypes
// Integer , Float , Double , Long => wrapper classes

public class Customer {
	private String name;
	private String contact;
	private Properties address;
	
	public Customer() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Properties getAddress() {
		return address;
	}

	public void setAddress(Properties address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", contact=" + contact + ", address=" + address + ", getName()=" + getName()
				+ ", getContact()=" + getContact() + ", getAddress()=" + getAddress() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	public Customer(String name, String contact, Properties address) {
		super();
		this.name = name;
		this.contact = contact;
		this.address = address;
	};
	
	
	
	
	
		

}
