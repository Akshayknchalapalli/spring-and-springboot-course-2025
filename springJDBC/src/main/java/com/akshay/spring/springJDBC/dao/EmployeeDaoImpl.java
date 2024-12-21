package com.akshay.spring.springJDBC.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.akshay.spring.springJDBC.dao.rowmappers.EmployeeRowMapper;
import com.akshay.spring.springJDBC.dto.Employee;

@Component
public class EmployeeDaoImpl implements EmployeeDao {
	
	@Autowired
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
		EmployeeRowMapper rowMapper = new EmployeeRowMapper();
		
         List<Employee> employees =  jdbcTemplate.query("SELECT * FROM employees WHERE employee_experience>?", rowMapper , 10);
         
		return employees;
	}

	@Override
	public Employee getEmployeeById(int id) {
		EmployeeRowMapper rowMapper = new EmployeeRowMapper();
		 Employee employee = jdbcTemplate.queryForObject("SELECT * FROM employees WHERE employee_id = ?", rowMapper , id);
		return employee;
	}


}
