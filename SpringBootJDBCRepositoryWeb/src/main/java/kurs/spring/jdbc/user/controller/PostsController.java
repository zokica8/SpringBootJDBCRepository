package kurs.spring.jdbc.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kurs.spring.jdbc.beans.Posts;
import kurs.spring.jdbc.user.service.PostsService;

@RestController
@RequestMapping("/posts")
public class PostsController {
	
	@Autowired
	private PostsService postsService;
	
	@PostMapping("/add")
	public Integer insertPost(@RequestBody Posts post) {
		return postsService.insertPost(post);
	}
	
	@GetMapping("/allPosts")
	public List<Posts> findAllPosts() {
		return postsService.findAllPosts();
	}
	
	@DeleteMapping("/delete/{id}")
	public void deletePost(@PathVariable Integer id) {
		postsService.deletePostById(id);
	}
	
	@PutMapping("/updatePost")
	public Posts updatePost(@RequestBody Posts post) {
		return postsService.updatePost(post);
	}
	
	@GetMapping("/trending")
	public List<Posts> getMostTrendingMessages() {
		return postsService.getMostTrendingMessages();
	}

}
