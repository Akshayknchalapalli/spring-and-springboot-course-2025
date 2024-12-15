package com.akshay.setterinjection.myfirstproject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
        ApplicationContext context = new ClassPathXmlApplicationContext("com/akshay/setterinjection/myfirstproject/config.xml");
        
        Customer customer = (Customer) context.getBean("customer"); // Object class
        
        System.out.println(customer.toString());
        
        Order order = (Order) context.getBean("order");
        System.out.println(order.toString());
        
        
    }
}
