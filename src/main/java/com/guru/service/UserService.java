package com.guru.service;

import java.util.List;

import com.guru.exceptions.UserNotFound;
import com.guru.model.User;

public interface UserService {
	
	public User create(User user);
	public User delete(int id) throws UserNotFound;
	public Iterable<User> findAll();
	public User update(User user) throws UserNotFound;
	public User findById(int id);
	public Iterable<User> findByLogin(String login);

}
