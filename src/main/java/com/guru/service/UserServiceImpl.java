package com.guru.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guru.exceptions.UserNotFound;
import com.guru.repository.UserRepositoryCustom;
import com.guru.model.User;
import com.guru.model.UserDetails;
import com.guru.model.UserRoles;

@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserRepositoryCustom userRepository;

	@Override
	public User create(User iUser) {
		User user = new User();   //org.springframework.orm.jpa.JpaSystemException: org.hibernate.exception.ConstraintViolationException
		try{
			user = userRepository.save(iUser);
		} catch( org.springframework.orm.jpa.JpaSystemException exception){
			
			if(exception.getCause().getCause().equals("org.hibernate.exception.ConstraintViolationException")){
				System.out.println("in here");
			}
			iUser.setRole("PRIMARYKEYVOILATION");
			user= iUser;
			
		}
		 return user;
	}
	
	@Override
	@Transactional
	public User findById(int id) {
		return userRepository.findOne(id);
	}

	@Override
	@Transactional(rollbackFor=UserNotFound.class)
	public User delete(int id) throws UserNotFound {
		User deletedUser = userRepository.findOne(id);
		
		if (deletedUser == null)
			throw new UserNotFound();
		
		userRepository.delete(deletedUser);
		return deletedUser;
	}

	@Override
	@Transactional
	public Iterable<User> findAll() {
		
			return userRepository.findAll();
	}

	@Override
	@Transactional(rollbackFor=UserNotFound.class)
	public User update(User iUser) throws UserNotFound {
		User updatedUser =  null;
		
		
		for (User lUser: userRepository.findByLogin(iUser.getLogin())){
			updatedUser =  lUser;
		}
		
		if (updatedUser == null)
			throw new UserNotFound();
		
		updatedUser.setEnabled(iUser.getEnabled());;
		updatedUser.setPassword(iUser.getPassword());
		updatedUser.setRole(iUser.getRole());
		return updatedUser;

	}

	@Override
	public Iterable<User> findByLogin(String login) {
		return userRepository.findByLogin(login);
		
	}

	//@Override
	//public Iterable<User> findByUserName(String userName) {
	//	return userRepository.findByUserName(userName);
	//}

}
