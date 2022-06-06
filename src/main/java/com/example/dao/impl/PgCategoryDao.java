package com.example.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.dao.CategoryDao;
import com.example.entity.Category;

@Repository
public class PgCategoryDao implements CategoryDao {
	private static final String SQL_SELECT_ALL = "SELECT id category_id, name category_name FROM categories";
//	private static final String SQL_INSERT_USER = "INSERT INTO products(product_name, price) VALUES(?, ?)";
//	private static final String SQL_SELECT_SEARCH_WHERE_KEYWORD = "SELECT * FROM \r\n"
//			+ " (SELECT product_id, category_id, p.name p_name, price, c.name c_name, description FROM categories c JOIN products p ON c.id = p.category_id) a\r\n"
//			+ "WHERE c_name LIKE '%?%' OR p_name LIKE ?";


	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public List<Category> findAll() {
		String sql = SQL_SELECT_ALL;
		List<Category> resultList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
		return resultList.isEmpty() ? null : resultList;
	}

//	public List<Category> findByKeyword(String keyword) {
//		List<Category> list = new ArrayList<Category>();
//		try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_SEARCH_WHERE_KEYWORD)) {
//			stmt.setString(1, "%" + keyword + "%");
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				Category u = new Category(rs.getInt("id"), rs.getString("name"));
//				list.add(u);
//			}
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//		return list;
//	}
}
