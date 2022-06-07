package com.example.dao;

import java.util.List;

import com.example.entity.Word;
public interface WordDao {
	public List<Word> findAll(String sort);
	public List<Word> findByKeyword(String keyword, String sort);
	public Word findById(Integer id);
	public int insert(Word word);
	public int delete(Integer id);
	public int update(Integer id, Word word);
}
