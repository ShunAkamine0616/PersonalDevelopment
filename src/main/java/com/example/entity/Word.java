package com.example.entity;

import java.sql.Timestamp;

public class Word {
	private Integer id;
    private String japanese;
    private String english;
    private Integer difficulty;
    private Timestamp createdAt;
    private Timestamp updatedAt;

	public Word() {
    	
    }
	
//	public Word(String japanese, String english, Integer difficulty, Timestamp updatedAt) {
//    	this.japanese = japanese;
//    	this.english = english;
//    	this.difficulty = difficulty;
//    	this.updatedAt= updatedAt;
//    }
    
    public Word(String japanese, String english, Integer difficulty, Timestamp createdAt, Timestamp updatedAt) {
    	this.japanese = japanese;
    	this.english = english;
    	this.difficulty = difficulty;
    	this.createdAt= createdAt;
    	this.updatedAt= updatedAt;
    }
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getJapanese() {
		return japanese;
	}
	public void setJapanese(String japanese) {
		this.japanese = japanese;
	}
	public String getEnglish() {
		return english;
	}
	public void setEnglish(String english) {
		this.english = english;
	}
	public Integer getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
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
}
