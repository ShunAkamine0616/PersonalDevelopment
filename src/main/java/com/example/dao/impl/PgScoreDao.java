package com.example.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.dao.ScoreDao;
import com.example.entity.Score;

import util.Utility;

@Repository
public class PgScoreDao implements ScoreDao {
	private static final String SQL_INSERT_SCORE = "INSERT INTO scores (login_id, score) VALUES(:login_id, :score)";

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	@Autowired
	private JdbcTemplate jdbcTemplate2;
	public List<Score> findAll(String sort) {
		String column = "score DESC"; 
		if(!Utility.isNullOrEmpty(sort)) {
			column = sort;
		}
		String SQL_SELECT_ALL = "SELECT s.login_id login_id, score FROM scores s JOIN users u ON s.login_id = u.login_id  ORDER BY " + column;
	       String sql = SQL_SELECT_ALL;
	        List<Score> resultList = jdbcTemplate2.query(sql, new BeanPropertyRowMapper<Score>(Score.class));

	        return resultList.isEmpty() ? null : resultList;
	}
	public int insert(String loginId, Integer score) {
		   String sql = SQL_INSERT_SCORE;
	        MapSqlParameterSource param = new MapSqlParameterSource();
	        param.addValue("login_id", loginId);
	        param.addValue("score", score);
	        return jdbcTemplate.update(sql, param);
	}
}
