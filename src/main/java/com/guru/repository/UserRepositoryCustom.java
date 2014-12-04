package com.guru.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.guru.model.Event;
import com.guru.model.User;
@Repository
public interface UserRepositoryCustom extends CrudRepository<User, Integer> {
	
	public Iterable<User> findByLogin(String login);
	
	
}
