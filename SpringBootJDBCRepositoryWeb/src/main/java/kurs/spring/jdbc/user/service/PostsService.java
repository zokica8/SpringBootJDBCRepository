package kurs.spring.jdbc.user.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import kurs.spring.jdbc.beans.Posts;
import kurs.spring.jdbc.user.repository.PostsRepository;

@Service
public class PostsService {
	
	@Autowired
	private PostsRepository postsRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Integer insertPost(Posts post) {
		Posts inserted = postsRepository.save(post);
		return inserted.getPost_ID();
	}
	
	// only thing that needs to be changed!
	public Posts updatePost(Posts post) {
		Optional<Posts> posts = postsRepository.findById(post.getPost_ID());
		if(posts.isPresent()) {
			Posts updatedPost = posts.get();
			
			updatedPost.setContent(post.getContent());
			updatedPost.setTimeOfMessage(post.getTimeOfMessage());
			updatedPost.setUser(post.getUser());
			
			return postsRepository.save(updatedPost);
		}
		
		return null;
	}
	
	public void deletePostById(Integer id) {
		postsRepository.deleteById(id);
	}
	
	public List<Posts> findAllPosts() {
		return postsRepository.findAll();
	}
	
	public List<Posts> getMostTrendingMessages() {
		List<Posts> posts = new ArrayList<>();
		
		String sql = "select u.username, p.post_ID, p.content, p.time_Of_Message, count(l.post_ID) as Likes from posts p "
				+ "left join likes l on l.post_ID = p.post_ID "
				+ "join users u on p.fk_user_id = u.id "
				+ "group by p.post_ID "
				+ "order by Likes desc "
				+ "limit 0, 10;";
		
		posts = jdbcTemplate.query(sql, new RowMapper<Posts>() {
			@Override
			public Posts mapRow(ResultSet rs, int rowNum) throws SQLException {
				Posts post = new Posts();
				post.setUsername(rs.getString("username"));
				post.setPost_ID(rs.getInt("post_ID"));
				post.setContent(rs.getString("content"));
				post.setTimeOfMessage(rs.getTimestamp("time_Of_Message").toLocalDateTime());
				post.setCount(rs.getInt("Likes"));
				return post;
			}	
		});
		return posts;
	}

}
