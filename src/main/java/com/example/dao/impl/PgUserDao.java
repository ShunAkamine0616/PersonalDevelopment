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

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	@Autowired
	private JdbcTemplate jdbcTemplate2;

	public List<User> findAll() {
		String sql = SQL_SELECT_ALL;
		List<User> resultList = jdbcTemplate2.query(sql, new BeanPropertyRowMapper<User>(User.class));

		return resultList.isEmpty() ? null : resultList;
	}

	public User findByUserIdAndPass(String UserId, String pass) {
		String sql = SQL_SELECT_WHERE_USER_ID_AND_PASS;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("login_id", UserId);
        param.addValue("password", pass);
        
        List<User> resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<User>(User.class));
		return resultList.isEmpty() ? null : resultList.get(0);
	}
	
	
}
