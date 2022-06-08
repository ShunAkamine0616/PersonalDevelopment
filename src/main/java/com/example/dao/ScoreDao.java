package com.example.dao;
import java.util.List;

import com.example.entity.Score;

public interface ScoreDao {
	public List<Score> findAll(String sort);
	public int insert(String loginId, Integer score);
//	public List<Score> findByKeyword(Integer user_id);
}
