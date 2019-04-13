package kurs.spring.jdbc.user.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import kurs.spring.jdbc.beans.Likes;
import kurs.spring.jdbc.beans.Posts;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LikesService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Likes insertLike(Integer user_Id, Integer post_Id) {
		Likes like = new Likes();
		
		String sql = "insert into likes(user_id, post_id) values (?, ?);";
		
		try {
			int inserted = jdbcTemplate.update(sql, user_Id, post_Id);
			log.info("Record inserted: " + inserted);
		} catch(DataAccessException e) {
			log.error("Record not inserted! ");
			log.error("Error in the database! ");
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return like;
	}
	
	// delete like
	public void deleteLike(Integer user_Id, Integer post_Id) {
		Likes like = new Likes();
		like.setUserId(user_Id);
		like.setPostId(post_Id);
		
		String sql = "delete from likes where user_id = ? AND post_id = ?";
		
		try {
			int deleted = jdbcTemplate.update(sql, user_Id, post_Id);
			log.info("Record deleted: " + deleted);
		}
		catch(DataAccessException e) {
			log.error("Record not inserted! ");
			log.error("Error in the database! ");
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public List<Posts> getLikesPerPerson(Integer post_Id) {
		List<Posts> likes = new ArrayList<>();
		
		String sql = "select u.username from users u "
				+ "join likes l on u.id = l.user_ID "
				+ "where l.post_ID = ?";
		
		likes = jdbcTemplate.query(sql, new RowMapper<Posts>() {
			@Override
			public Posts mapRow(ResultSet rs, int rowNum) throws SQLException {
				Posts post = new Posts();
				post.setUsername(rs.getString("username"));
				return post;
			}	
		}, post_Id);
		
		return likes;
	}
}
