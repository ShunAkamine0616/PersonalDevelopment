
package com.example.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Category;
@Service
public interface CategoryDao {

	public List<Category> findAll();
//    public List<Category> findByKeyword(String keyword);
}