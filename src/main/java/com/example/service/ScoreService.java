package com.example.service;

import java.util.List;

import com.example.entity.Score;

public interface ScoreService {
	public List<Score> findAll(Integer level);
	public int insert(String loginId, Integer score, Integer level);
}
