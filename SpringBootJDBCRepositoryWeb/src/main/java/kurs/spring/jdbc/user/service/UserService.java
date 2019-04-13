package kurs.spring.jdbc.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kurs.spring.jdbc.beans.User;
import kurs.spring.jdbc.user.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public Integer createUser(User user) {
		User created = userRepository.save(user);
		return created.getId();
	}
	
	public void deleteUserById(Integer id) {
		userRepository.deleteById(id);
	}
	
	public void deleteUserByFirstName(String firstName) {
		userRepository.deleteByFirstName(firstName);
	}
	
	public void deleteUserByLastName(String lastName) {
		userRepository.deleteByLastName(lastName);
	}
	
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}
	
	public List<User> findByFirstName(String firstName) {
		return userRepository.findByFirstName(firstName);
	}
	
	public List<User> findByLastName(String lastName) {
		return userRepository.findByLastName(lastName);
	}
	
	public List<User> sortByFirstName() {
		Sort sort = Sort.by(Sort.Direction.ASC, "firstName"); // for sorting in ascending order!
		return userRepository.findAll(sort);
	}
	
	public List<User> findByAge(Integer age) {
		return userRepository.findByAge(age);
	}
	
	public User updateUser(User user) {
		Optional<User> updated = userRepository.findById(user.getId());
		if(updated.isPresent()) {
			User updatedUser = updated.get();
			updatedUser.setFirstName(user.getFirstName());
			updatedUser.setLastName(user.getLastName());
			updatedUser.setAge(user.getAge());
			updatedUser.setGender(user.getGender());
			updatedUser.setUsername(user.getUsername());
			updatedUser.setPassword(user.getPassword());
			
			return userRepository.save(updatedUser);
		}
		
		return null;
	}

}
