package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CategoryDao;
import com.example.entity.Category;
import com.example.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryDao categoryDao;
	public List<Category> find() {
		return categoryDao.findAll();
	}

//	public List<Category> findByKeyword(String keyword) {
//		return categoryDao.findByKeyword(keyword);
//	}
}
