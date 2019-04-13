package kurs.spring.jdbc.user.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kurs.spring.jdbc.beans.User;
import kurs.spring.jdbc.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Resource
	private UserService userService;
	
	@PostMapping("/add")
	public Integer createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@GetMapping("/findAll")
	public List<User> findAllUsers() {
		return userService.findAllUsers();
	}
	
	@GetMapping("/findByFirstName")
	public List<User> findByFirstName(@RequestParam(name="firstName", required = false) String firstName) {
		return userService.findByFirstName(firstName);
	}
	
	@GetMapping("/sorted")
	public List<User> findByFirstNameSorted() {
		return userService.sortByFirstName();
	}
	
	@GetMapping("/findByLastName")
	public List<User> findByLastName(@RequestParam(name="lastName", required = false) String lastName) {
		return userService.findByLastName(lastName);
	}
	
	@GetMapping("/age")
	public List<User> findByAge(@RequestParam(name="age", required = false) Integer age) {
		return userService.findByAge(age);
	}
	
	@PutMapping("/update")
	public User updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public void deleteUserById(@PathVariable Integer id) {
		userService.deleteUserById(id);
	}
	
	@DeleteMapping("deleteByFirstName/{firstName}")
	public void deleteUserByFirstName(@PathVariable String firstName) {
		userService.deleteUserByFirstName(firstName);
	}
	
	@DeleteMapping("deleteByLastName/{lastName}")
	public void deleteUserByLastName(@PathVariable String lastName) {
		userService.deleteUserByLastName(lastName);
	}

}
