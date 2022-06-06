package com.example.entity;

import java.sql.Timestamp;

public class Product {
	private Integer id;
	private Integer productId;
	private Category category; 
	private Integer categoryId;
	private String productName;
	private Integer price;

	private String description;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	


//	public Product(Integer productId, Integer categoryId, 
//			String name, Integer price,  String description, Timestamp createdAt) {
//		this.productId = productId;
//		this.categoryId = categoryId;
////		this.category = new Category(categoryId, category_name);
//		this.name = name;
//		this.price = price;
//		this.description = description;
//		this.createdAt = createdAt;
//	}
//	
//	public Product(Integer productId, Integer categoryId, 
//			String productName, Integer price,  String description, Timestamp createdAt, Timestamp updatedAt) {
//		this.productId = productId;
//		this.categoryId = categoryId;
////		this.category = new Category(categoryId, category_name);
//		this.productName = productName;
//		this.price = price;
//		this.description = description;
//		this.createdAt = createdAt;
//		this.updatedAt = updatedAt;
//	}
	public Product() {
		
	}
	
	// 登録
	public Product(Integer productId, Integer categoryId, 
			String productName, Integer price,  String description, Timestamp createdAt) {
//		this.id = id;
		this.productId = productId;
		this.categoryId = categoryId;
//		this.category = new Category(categoryId, category_name);
		this.productName = productName;
		this.price = price;
		this.description = description;
		this.createdAt = createdAt;
	}
	
	// 更新
	public Product(Integer productId, Integer categoryId, 
			String productName, Integer price,  String description, Timestamp createdAt, Timestamp updatedAt) {
//		this.id = id;
		this.productId = productId;
		this.categoryId = categoryId;
//		this.category = new Category(categoryId, category_name);
		this.productName = productName;
		this.price = price;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	

	public Product(Integer productId, Integer categoryId, String category_name,
			String productName, Integer price,  String description, Timestamp createdAt) {
		this.productId = productId;
		this.categoryId = categoryId;
		this.category = new Category(categoryId, category_name);
		this.productName = productName;
		this.price = price;
		this.description = description;
		this.createdAt = createdAt;
	}
	
	public Product(Integer id, Integer productId, Integer categoryId, String category_name,
			String productName, Integer price,  String description, Timestamp createdAt, Timestamp updatedAt) {
		this.id = id;
		this.productId = productId;
		this.categoryId = categoryId;
		this.category = new Category(categoryId, category_name);
		this.productName = productName;
		this.price = price;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return productName;
	}

	public void setName(String name) {
		this.productName = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	// 追加
	public void setCategoryId(int categoryId) {
		if(this.category == null) {
			this.category = new Category();
		}
		category.setCategoryId(categoryId);
	}
	
	// 追加
	public void setCategoryName(String categoryName) {
		if(this.category == null) {
			this.category = new Category();
		}
		category.setCategoryName(categoryName);
	}
}
