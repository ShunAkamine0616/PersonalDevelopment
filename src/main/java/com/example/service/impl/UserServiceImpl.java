package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.UserDao;
import com.example.entity.User;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	public List<User> findAll() {
		return userDao.findAll();
	}

	public User findByUserId(String userId) {
		return userDao.findByUserId(userId);
	}
	
	public User findByUserIdAndPass(String userId, String pass) {
		return userDao.findByUserIdAndPass(userId, pass);
	}
	
	public int register(String loginId, String password, String name) {
		return userDao.register(loginId, password, name);
	}
}
