
package com.example.dao;

import java.util.List;

import com.example.entity.Product;

public interface ProductDao {

	public List<Product> findAll(String sort);
    public Product findById(Integer id);
    public List<Product> findByKeyword(String keyword, String sort);
    public int update(Integer product_id, Product product);
    public int delete(Integer product_id);
    public void register(Product product);
}