package com.akshay.spring.springJDBC.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.akshay.spring.springJDBC.dto.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public void insertEmployee(Employee employee) {
		// code to insert employee object into database

	}

	@Override
	public List<Employee> findAllEmployees() {
		// code to return all employees into Employee Table
		String url = "jdbc:postgresql://localhost:5432/my_database";
        String username = "postgres";
        String password = "Akshay@123";

        // SQL SELECT query
        String selectQuery = "SELECT * FROM employees";
        
        List<Employee> employees = new ArrayList<Employee>();
        
     try {
		// Establish the connection
		    Connection connection = DriverManager.getConnection(url, username, password);
		    // Create a statement
		    Statement statement = connection.createStatement();
		    // Execute the query and get a result set
		    ResultSet resultSet = statement.executeQuery(selectQuery);
		    
		    while(resultSet.next()) {
		    	String id = resultSet.getString(1);
		    	String name = resultSet.getString(2);
		    	int exp = resultSet.getInt(3);
		    	
		    	Employee employee = new Employee(id, name, exp);
		    	
		    	employees.add(employee);
		    }
		    connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return employees;
	}

	@Override
	public Employee getElementById(String id) {
		//  code to get employee based on particular id
		return null;
	}

}
