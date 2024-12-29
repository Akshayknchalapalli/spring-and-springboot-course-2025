package com.akshay.spring.mydiary.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.akshay.spring.mydiary.dao.UserDaoInterface;
import com.akshay.spring.mydiary.entities.User;

@Component
public class UserBusinessInterfaceImpl implements UserBusinessInterface {
	
	@Autowired
	private UserDaoInterface userDaoInterface;

	public UserDaoInterface getUserInterface() {
		return userDaoInterface;
	}

	public void setUserInterface(UserDaoInterface userInterface) {
		this.userDaoInterface = userInterface;
	}

	@Override
	public void save(User user) {
		userDaoInterface.save(user);

	}

	@Override
	public void update(User user) {
		userDaoInterface.update(user);

	}

	@Override
	public void delete(User user) {
		userDaoInterface.delete(user);

	}

	@Override
	public User findById(int id) {
		return userDaoInterface.findById(id);
	}

	@Override
	public List<User> findAll() {
		return userDaoInterface.findAll();
	}

	@Override
	public User findByUsername(String username) {
		return userDaoInterface.findByUsername(username);
	}

}
