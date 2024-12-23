package com.akshay.spring.springhibernate.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.akshay.spring.springhibernate.dao.EmployeeDao;
import com.akshay.spring.springhibernate.entity.Employee;

@Component
public class EmployeeBusinessImpl implements EmployeeBusiness {
	
	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public void save(Employee employee) {
		employeeDao.save(employee);
	}

	@Override
	public void update(Employee employee) {
		employeeDao.update(employee);
		
	}

	@Override
	public void delete(Employee employee) {
		employeeDao.delete(employee);
		
	}

	@Override
	public Employee findById(int id) {
		return employeeDao.findById(id);
	}

	@Override
	public List<Employee> findAll() {
		return employeeDao.findAll();
	}

}
