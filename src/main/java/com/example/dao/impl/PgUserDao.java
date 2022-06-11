package com.example.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.dao.UserDao;
import com.example.entity.User;

@Repository
public class PgUserDao implements UserDao {
	private static final String SQL_SELECT_ALL = "SELECT * FROM users ORDER BY login_id";
	private static final String SQL_SELECT_WHERE_USER_ID_AND_PASS = "SELECT * FROM users WHERE login_id = :login_id and password = :password";
	private static final String SQL_SELECT_WHERE_USER_ID = "SELECT * FROM users WHERE login_id = :login_id";
	private static final String SQL_INSERT = "INSERT INTO users (login_id, password, name, role) VALUES(:loginId, :password, :name, 2)";
	

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	@Autowired
	private JdbcTemplate jdbcTemplate2;

	public List<User> findAll() {
		String sql = SQL_SELECT_ALL;
		List<User> resultList = jdbcTemplate2.query(sql, new BeanPropertyRowMapper<User>(User.class));

		return resultList.isEmpty() ? null : resultList;
	}

	public User findByUserId(String UserId) {
		String sql = SQL_SELECT_WHERE_USER_ID;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("login_id", UserId);
        
        List<User> resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<User>(User.class));
		return resultList.isEmpty() ? null : resultList.get(0);
	}
	
	public User findByUserIdAndPass(String UserId, String pass) {
		String sql = SQL_SELECT_WHERE_USER_ID_AND_PASS;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("login_id", UserId);
        param.addValue("password", pass);
        
        List<User> resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<User>(User.class));
		return resultList.isEmpty() ? null : resultList.get(0);
	}
	
	public int register(String loginId, String password, String name) {
		 String sql = SQL_INSERT;
	        MapSqlParameterSource param = new MapSqlParameterSource();
	        param.addValue("loginId", loginId);
	        param.addValue("password", password);
	        param.addValue("name", name);
	        return jdbcTemplate.update(sql, param);
	}
	
	
}
