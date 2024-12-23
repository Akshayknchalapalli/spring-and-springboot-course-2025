package com.akshay.spring.springhibernate.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.akshay.spring.springhibernate.entity.Employee;

@Component
public class EmployeeDaoImpl implements EmployeeDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	@Transactional(readOnly = false)
	public void save(Employee employee) {
		hibernateTemplate.save(employee);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(Employee employee) {
		hibernateTemplate.update(employee);	
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Employee employee) {
		hibernateTemplate.delete(employee);
	}

	@Override
	public Employee findById(int id) {
		return hibernateTemplate.get(Employee.class, id);
	}

	@Override
	public List<Employee> findAll() {
		return hibernateTemplate.loadAll(Employee.class);
	}
}
