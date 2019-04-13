package kurs.spring.jdbc.beans;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Table(name="posts")
@Getter
@Setter
public class Posts implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="postId")
	private Integer post_ID;
	
	@Transient
	private String username;
	
	@NotBlank
	private String content;
	
	private LocalDateTime timeOfMessage;
	
	@ManyToOne
	@JoinColumn(name="FK_user_ID")
	@JsonBackReference
	private User user;
	
	@Transient 
	private Integer count;
	
}
