package com.example.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.dao.ProductDao;
import com.example.entity.Product;

import util.Utility;

@Repository
public class PgProductDao implements ProductDao {
	private static final String SQL_INSERT_PRODUCT = "INSERT INTO products(product_id, category_id, name, price, description, created_at) VALUES(:product_id, :category_id, :name, :price, :description, :created_at)";
	private static final String SQL_DELETE_PRODUCT = "DELETE FROM products WHERE product_id = :product_id";
	private static final String SQL_UPDATE_PRODUCT = "UPDATE products SET product_id = :product_id, name = :product_name, price = :price, category_id = :category_id, description = :description, updated_at = :updated_at WHERE product_id = :id";

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	@Autowired
	private JdbcTemplate jdbcTemplate2;


	public List<Product> findAll(String sort) {	
		String column = "product_id"; 
		if(!Utility.isNullOrEmpty(sort)) {
			column = sort;
		}
		String SQL_SELECT_ALL = "SELECT * FROM (SELECT product_id, category_id, p.name product_name, price, image_path, c.name category_name, description, p.created_at created_at FROM categories c JOIN products p ON c.id = p.category_id) a"
				+ " ORDER BY " + column;
	       String sql = SQL_SELECT_ALL;
	        List<Product> resultList = jdbcTemplate2.query(sql, new BeanPropertyRowMapper<Product>(Product.class));

	        return resultList.isEmpty() ? null : resultList;
	}

	public Product findById(Integer product_id) {
		String SQL_SELECT_SEARCH_WHERE_PRODUCT_ID = "SELECT * FROM \r\n"
				+ " (SELECT p.id, product_id, category_id, p.name product_name, price, image_path, c.name category_name, description, p.created_at created_at, p.updated_at updated_at FROM categories c JOIN products p ON c.id = p.category_id) a\r\n"
				+ "WHERE product_id = :product_id";
		String sql = SQL_SELECT_SEARCH_WHERE_PRODUCT_ID;
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("product_id", product_id);

        List<Product> resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Product>(Product.class));
        return resultList.isEmpty() ? null : resultList.get(0);
	}





	public List<Product> findByKeyword(String keyword, String sort) {
		String column = "product_id";
		if(!Utility.isNullOrEmpty(sort)) {
			column = sort;
		}
		String SQL_SELECT_SEARCH_WHERE_KEYWORD = "SELECT * FROM \r\n"
				+ " (SELECT product_id, category_id, p.name product_name, price, c.name category_name, description, p.created_at created_at, p.updated_at updated_at FROM categories c JOIN products p ON c.id = p.category_id) a\r\n"
				+ "WHERE category_name LIKE :c_name OR product_name LIKE :p_name"
				+ " ORDER BY " + column;
		String sql = SQL_SELECT_SEARCH_WHERE_KEYWORD;

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("c_name","%" + keyword + "%");
        param.addValue("p_name","%" + keyword + "%");
        
        List<Product> resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Product>(Product.class));
        return resultList.isEmpty() ? null : resultList;
	}

	public int update(Integer product_id, Product product) {
		 String sql = SQL_UPDATE_PRODUCT;
	        MapSqlParameterSource param = new MapSqlParameterSource();
	        param.addValue("product_id", product.getProductId());
	        param.addValue("category_id", product.getCategoryId());
	        param.addValue("product_name", product.getName());
	        param.addValue("price", product.getPrice());
	        param.addValue("description", product.getDescription());
	        param.addValue("updated_at", product.getCreatedAt());
	        param.addValue("id", product_id);
	        return jdbcTemplate.update(sql, param);
//	        return 0; //更新失敗時のチェックのため
	}

	public int delete(Integer product_id) {
		 String sql = SQL_DELETE_PRODUCT;
	        MapSqlParameterSource param = new MapSqlParameterSource();
	        param.addValue("product_id", product_id);
	        return jdbcTemplate.update(sql, param);
//	        return 0; //更新失敗時のチェックのため
	}

	public void register(Product product) {
		   String sql = SQL_INSERT_PRODUCT;
	        MapSqlParameterSource param = new MapSqlParameterSource();
	        param.addValue("product_id", product.getProductId());
	        param.addValue("category_id", product.getCategoryId());
	        param.addValue("name", product.getName());
	        param.addValue("price", product.getPrice());
	        param.addValue("description", product.getDescription());
	        param.addValue("created_at", product.getCreatedAt());
	        
	        jdbcTemplate.update(sql, param);
	}
}
