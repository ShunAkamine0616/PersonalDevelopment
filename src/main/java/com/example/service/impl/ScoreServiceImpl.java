package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.ScoreDao;
import com.example.entity.Score;
import com.example.service.ScoreService;

@Service
public class ScoreServiceImpl implements ScoreService {
	@Autowired
	private ScoreDao scoreDao;
	public List<Score> findAll(String sort) {
		return scoreDao.findAll(sort);
	}
	public int insert(String loginId, Integer score) {
		return scoreDao.insert(loginId, score);
	}
}
