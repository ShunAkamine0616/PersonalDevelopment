package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.WordDao;
import com.example.entity.Word;
import com.example.service.WordService;
@Service
public class WordServiceImpl implements WordService {
	@Autowired
	private WordDao wordDao;
	public List<Word> findAll(String sort) {
		return wordDao.findAll(sort);
	}
	public List<Word> findByKeyword(String keyword, String sort) {
		return wordDao.findByKeyword(keyword, sort);
	}
	public List<Word> findByLevel(Integer level) {
		return wordDao.findByLevel(level);
	}
	public Word findById(Integer id) {
		return wordDao.findById(id);
	}
	public int delete(Integer id) {
		return wordDao.delete(id);
	}
	public int insert(Word word) {
		return wordDao.insert(word);
	}
	public int update(Integer id, Word word) {
		return wordDao.update(id, word);
	}
}
