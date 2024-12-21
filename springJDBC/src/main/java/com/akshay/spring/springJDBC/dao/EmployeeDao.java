package com.akshay.spring.springJDBC.dao;

import java.util.List;

import com.akshay.spring.springJDBC.dto.Employee;

public interface EmployeeDao {
	void insertEmployee(Employee employee);
	List<Employee> findAllEmployees();
	Employee getEmployeeById(int id);
}
