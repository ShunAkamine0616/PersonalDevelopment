package com.example.service;

import java.util.List;

import com.example.entity.Product;

public interface ProductService {

    public Product findById(Integer id);
    public List<Product> findAll(String sort);
    public List<Product> findByKeyword(String keyword, String sort);
    public void insert(Product product);
    public int update(Integer product_id, Product product);
    public int delete(Integer product_id);
}
