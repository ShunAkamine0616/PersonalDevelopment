package com.example.service;

import java.util.List;

import com.example.entity.User;

public interface UserService {

    public List<User> findAll();
    public User findByUserIdAndPass(String UserId, String pass);
    
}
