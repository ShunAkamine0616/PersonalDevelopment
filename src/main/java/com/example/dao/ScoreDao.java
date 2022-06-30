package com.example.dao;
import java.util.List;

import com.example.entity.Score;

public interface ScoreDao {
	public List<Score> findAll(Integer level);
	public int insert(String loginId, Integer score, Integer level, Integer miss);
//	public List<Score> findByKeyword(Integer user_id);
}
