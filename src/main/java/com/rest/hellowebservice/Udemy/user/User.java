package com.rest.hellowebservice.Udemy.user;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description="details of user")
public class User {
	
	private Integer id;
	@Size(min=2,message="atlest 2 letters")
	@ApiModelProperty(notes="name atleast 2 char")//for swagger doc
	private String name;
		@Past(message="date should be past")//validation
	@ApiModelProperty(notes="birthday should be past")//for swagger doc
	private Date dob;
    //add default constructor for json response--post	
	public User(){
		
	}
	
	
	public User(int id, String name, Date dob) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
	}
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", dob=" + dob + "]";
	}
	
	

}
