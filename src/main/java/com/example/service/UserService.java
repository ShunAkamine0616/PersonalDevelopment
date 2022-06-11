package com.example.service;

import java.util.List;

import com.example.entity.User;

public interface UserService {

    public List<User> findAll();
    public User findByUserId(String UserId);
    public User findByUserIdAndPass(String UserId, String pass);
    public int register(String loginId, String password, String name);
}
