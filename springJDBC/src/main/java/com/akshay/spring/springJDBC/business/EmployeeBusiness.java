package com.akshay.spring.springJDBC.business;

import com.akshay.spring.springJDBC.dto.Employee;

public interface EmployeeBusiness {
	void printEmployeeHikes();
	void insertEmployee(Employee employee);
	Employee getEmployeeById(int id);
}
