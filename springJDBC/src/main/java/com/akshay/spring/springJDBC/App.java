package com.akshay.spring.springJDBC;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.akshay.spring.springJDBC.business.EmployeeBusiness;
import com.akshay.spring.springJDBC.business.EmployeeBusinessImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        EmployeeBusiness employeeBusiness  = (EmployeeBusinessImpl) context.getBean("employeeBusiness");
        employeeBusiness.printEmployeeHikes();
    }
}
