package com.akshay.spring.springhibernate;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.akshay.spring.springhibernate.business.EmployeeBusiness;
import com.akshay.spring.springhibernate.business.EmployeeBusinessImpl;
import com.akshay.spring.springhibernate.entity.Employee;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        EmployeeBusiness employeeBusiness  = (EmployeeBusinessImpl) context.getBean("employeeBusinessImpl");
//        Employee employee = new Employee();
//        employee.setId(4);
//        employee.setEname("Gopal");
//        employee.setAddress("Akivedu");
//        employee.setContact("+91 66666666");
//        employee.setExp(10);
//        
//        employeeBusiness.save(employee);
        
//        Employee employee = employeeBusiness.findById(4);
//        employee.setEname("Anil");
//        employeeBusiness.update(employee);
        
//        Employee employee = employeeBusiness.findById(2);
//        employeeBusiness.delete(employee);
        
        List<Employee> employees = employeeBusiness.findAll();
        for(Employee e : employees) {
        	System.out.print(" id: " + e.getId());
        	System.out.print(" - Name: " + e.getEname());
        	System.out.println();
        }
    }
}
