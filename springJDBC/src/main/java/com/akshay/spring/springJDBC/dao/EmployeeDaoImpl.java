package com.akshay.spring.springJDBC.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.akshay.spring.springJDBC.dto.Employee;

public class EmployeeDaoImpl implements EmployeeDao {
	
	JdbcTemplate jdbcTemplate;
	
	

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void insertEmployee(Employee employee) {
		// code to insert employee object into database
		
//		String query = "INSERT INTO employees VALUES('" + employee.getId() + "', '" + employee.getName() + 
//				"', " + employee.getExp() + ")";
		
		String query = "INSERT INTO employees VALUES(? , ? , ?)";

		
		System.out.println("Query: " + query);
		
		jdbcTemplate.update(query , employee.getId() , employee.getName() , employee.getExp());

	}

	@Override
	public List<Employee> findAllEmployees() {
		
        
		return null;
	}

	@Override
	public Employee getElementById(String id) {
		//  code to get employee based on particular id
		return null;
	}

}
