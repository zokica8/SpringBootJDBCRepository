package kurs.spring.jdbc.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kurs.spring.jdbc.beans.Posts;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Integer> {

}
