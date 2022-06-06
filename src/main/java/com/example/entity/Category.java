package com.example.entity;

public class Category {
	private Integer categoryId;
	private String categoryName;
	private Integer createdAt;
	private Integer updatedAt;
	
	public Category() {
		
	}
	
	public Category(Integer categoryId, String categoryName, Integer createdAt) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.createdAt = createdAt;
	}
	
	public Category(Integer categoryId, String categoryName) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
	
	public Category(String categoryName, Integer createdAt) {
		this.categoryName = categoryName;
		this.createdAt = createdAt;
	}
	
	public Category(Integer categoryId, String categoryName, Integer createdAt, Integer updatedAt) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Integer createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Integer updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}
