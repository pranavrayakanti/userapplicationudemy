package com.rest.hellowebservice.Udemy.user;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Post {
	
	
    @Id
    @GeneratedValue
	private Integer id;
	public Integer getId() {
		return id;
	}


	@Override
	public String toString() {
		return "Post [id=" + id + ", decription=" + decription + "]";
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getDecription() {
		return decription;
	}


	public void setDecription(String decription) {
		this.decription = decription;
	}


	private String decription;
	
	
	/* by adding fetch has lazy it will 
	 * retrieve user details only when 
	 * we use post.getuser()
	 * 
	 * */

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private User user;

}
