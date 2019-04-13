package kurs.spring.jdbc.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kurs.spring.jdbc.beans.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	public List<User> findByFirstName(String firstName);
	public List<User> findByLastName(String lastName);
	public List<User> findByAge(Integer age);
	
	@Transactional
	public void deleteByFirstName(String firstName);
	
	@Transactional
	public void deleteByLastName(String lastName);
}
