
package com.example.dao;

import java.util.List;

import com.example.entity.User;

public interface UserDao {

	public List<User> findAll();
	public User findByUserIdAndPass(String UserId, String pass);
	public User findByUserId(String userId);
	public int register(String loginId, String password, String name);
}