package kurs.spring.jdbc.beans;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Table(name="users")
@Getter
@Setter
public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String firstName;
	private String lastName;
	private Integer age;
	private Boolean gender;
	private String username;
	private String password;
	
	@OneToMany(mappedBy="user")
	@JsonManagedReference
	private Set<Posts> posts;
	
	@ManyToMany
	@JoinTable(name="likes", joinColumns = @JoinColumn(name="userId"),
	inverseJoinColumns = @JoinColumn(name="postId")) 
	private Set<Posts> likes;

}
