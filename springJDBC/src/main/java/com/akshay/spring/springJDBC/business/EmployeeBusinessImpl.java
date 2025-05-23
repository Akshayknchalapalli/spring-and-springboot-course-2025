package com.akshay.spring.springJDBC.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.akshay.spring.springJDBC.dao.EmployeeDao;
import com.akshay.spring.springJDBC.dto.Employee;

@Component("employeeBusiness")
public class EmployeeBusinessImpl implements EmployeeBusiness {
	
	@Autowired
	private EmployeeDao employeeDao;

	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}



	@Override
	public void printEmployeeHikes() {
		// Ask Employee for dao data
		List<Employee> employees = employeeDao.findAllEmployees();
		
		// check each employee and print hike
		if(employees != null) {
			for(Employee employee: employees) {
				if(employee.getExp() >= 15) {
					System.out.println("Employee with id " + employee.getId() + " with Name " + employee.getName() + " with Experince " + employee.getExp() + " yrs got 30% hike");
				} else if (employee.getExp() >= 10) {
					System.out.println("Employee with id " + employee.getId() + " with Name " + employee.getName() + " with Experince " + employee.getExp() + " yrs got 20% hike");
				} else if (employee.getExp() >= 5) {
					System.out.println("Employee with id " + employee.getId() + " with Name " + employee.getName() + " with Experince " + employee.getExp() + " yrs got 10% hike");
				} else {
					System.out.println("Employee with id " + employee.getId() + " with Name " + employee.getName() + " with Experince " + employee.getExp() + " yrs got 5% hike");
				}
			}
		}
		
	}
	@Override
	public void insertEmployee(Employee employee) {
		employeeDao.insertEmployee(employee);
	}
	@Override
	public Employee getEmployeeById(int id) {
		return employeeDao.getEmployeeById(id);
		
	}

}
