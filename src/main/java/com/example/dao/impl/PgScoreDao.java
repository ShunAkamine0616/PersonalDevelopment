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

@Repository
public class PgScoreDao implements ScoreDao {
	private static final String SQL_INSERT_SCORE = "INSERT INTO scores (login_id, score, difficulty, miss, times) VALUES(:login_id, :score, :level, :miss, current_timestamp)";

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	@Autowired
	private JdbcTemplate jdbcTemplate2;
	public List<Score> findAll(Integer level) {
		String SQL_SELECT_ALL = "SELECT RANK() OVER(ORDER BY score DESC) AS lank, login_id, score, miss,  FROM (SELECT s.login_id, score FROM scores s JOIN users u ON s.login_id = u.login_id WHERE s.difficulty = :level) a LIMIT 30";
		String sql = SQL_SELECT_ALL;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("level",level);
		List<Score> resultList = jdbcTemplate.query(sql, param,new BeanPropertyRowMapper<Score>(Score.class));
		return resultList.isEmpty() ? null : resultList;
	}
	public int insert(String loginId, Integer score, Integer level, Integer miss) {
		String sql = SQL_INSERT_SCORE;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("login_id", loginId);
		param.addValue("score", score);
		param.addValue("level", level);
		param.addValue("miss", miss);
		return jdbcTemplate.update(sql, param);
	}
}
