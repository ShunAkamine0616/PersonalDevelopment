package com.example.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.dao.WordDao;
import com.example.entity.Word;

import util.Utility;

@Repository
public class PgWordDao implements WordDao{
	private static final String SQL_INSERT_PRODUCT = "INSERT INTO words(japanese, english, difficulty, created_at, updated_at) VALUES(:japanese, :english, :difficulty, :created_at, :updated_at)";
	private static final String SQL_DELETE_WORD =  "DELETE FROM words WHERE id = :id";
	private static final String SQL_UPDATE_WORD = "UPDATE words SET japanese = :japanese, english = :english, difficulty = :difficulty, updated_at = :updated_at WHERE id = :id";
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	@Autowired
	private JdbcTemplate jdbcTemplate2;
	public List<Word> findAll(String sort) {
		String sql = "SELECT * FROM words ORDER BY " + sort;
		List<Word> resultList = jdbcTemplate2.query(sql, new BeanPropertyRowMapper<Word>(Word.class));

		return resultList.isEmpty() ? null : resultList;
	}
	
	public List<Word> findByKeyword(String keyword, String sort) {
		String column = "id";
		if(!Utility.isNullOrEmpty(sort)) {
			column = sort;
		}
		String SQL_SELECT_SEARCH_WHERE_KEYWORD = "SELECT * FROM words WHERE japanese LIKE :japanese OR english LIKE :english ORDER BY " + column;
		String sql = SQL_SELECT_SEARCH_WHERE_KEYWORD;

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("japanese","%" + keyword + "%");
        param.addValue("english","%" + keyword + "%");
        
        List<Word> resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Word>(Word.class));
        return resultList.isEmpty() ? null : resultList;
	}
	
	public Word findById(Integer id) {
		String SQL_SELECT_SEARCH_WHERE_ID = "SELECT * FROM words WHERE id = :id";
		String sql = SQL_SELECT_SEARCH_WHERE_ID;
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", id);

        List<Word> resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Word>(Word.class));
        return resultList.isEmpty() ? null : resultList.get(0);
	}
	
	public int delete(Integer id) {
		 String sql = SQL_DELETE_WORD;
	        MapSqlParameterSource param = new MapSqlParameterSource();
	        param.addValue("id", id);
	        return jdbcTemplate.update(sql, param);
//	        return 0; //更新失敗時のチェックのため
	}
	
	public int update(Integer id, Word word) {
		 String sql = SQL_UPDATE_WORD;
	        MapSqlParameterSource param = new MapSqlParameterSource();
	        param.addValue("japanese", word.getJapanese());
	        param.addValue("english", word.getEnglish());
	        param.addValue("difficulty", word.getDifficulty());
	        param.addValue("updated_at", word.getUpdatedAt());
	        param.addValue("id", id);
	        return jdbcTemplate.update(sql, param);
//	        return 0; //更新失敗時のチェックのため
	}
	
	public int insert(Word word) {
		   String sql = SQL_INSERT_PRODUCT;
	        MapSqlParameterSource param = new MapSqlParameterSource();
	        param.addValue("japanese", word.getJapanese());
	        param.addValue("english", word.getEnglish());
	        param.addValue("difficulty", word.getDifficulty());
	        param.addValue("created_at", word.getCreatedAt());
	        param.addValue("updated_at", word.getUpdatedAt());
	        
	        return jdbcTemplate.update(sql, param);
	}
}
