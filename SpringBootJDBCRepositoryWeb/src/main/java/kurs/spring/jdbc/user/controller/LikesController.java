package kurs.spring.jdbc.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kurs.spring.jdbc.beans.Posts;
import kurs.spring.jdbc.user.service.LikesService;

@RestController
@RequestMapping("/likes")
public class LikesController {
	
	@Autowired
	private LikesService likesService;
	
	@PostMapping("/add")
	public void insertLike(
			@RequestParam(name="userId")Integer user_Id, 
			@RequestParam(name="postId")Integer post_Id) {
		likesService.insertLike(user_Id, post_Id);
	}
	
	@DeleteMapping("/delete")
	public void deleteLike(
			@RequestParam(name="userId")Integer user_Id, 
			@RequestParam(name="postId")Integer post_Id) {
		likesService.deleteLike(user_Id, post_Id);
	}
	
	@GetMapping("/likesPerPerson/{post_Id}")
	public List<Posts> getLikesPerPerson(@PathVariable Integer post_Id) {
		return likesService.getLikesPerPerson(post_Id);
	}

}
