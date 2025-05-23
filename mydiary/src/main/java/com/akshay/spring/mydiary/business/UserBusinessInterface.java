package com.akshay.spring.mydiary.business;

import java.util.List;

import com.akshay.spring.mydiary.entities.User;

public interface UserBusinessInterface {
	public void save(User user);
	public void update(User user);
	public void delete(User user);
	
	public User findById(int id);
	public List<User> findAll();
	public User findByUsername(String username);
}

