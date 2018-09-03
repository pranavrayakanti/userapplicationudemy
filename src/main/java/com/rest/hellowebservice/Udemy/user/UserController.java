package com.rest.hellowebservice.Udemy.user;

import java.net.URI;
import java.util.ArrayList;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class UserController {
	
	@Autowired
	private UserDao service;
	
	
	@GetMapping(path="/users")
	public ArrayList<User> getusers() {
		
		return service.getusers();
	}
	@GetMapping(path="/user/{id}")
	public Resource  getuser(@PathVariable Integer id) {
				User user= service.getUser(id);
				if(user==null) {
					throw new UserNotFoundException("id" +id);
				}
				
				
				Resource<User> resource=new Resource<User>(user);
				
			ControllerLinkBuilder linkTo=	linkTo(methodOn(this.getClass()).getusers());
						
				resource.add(linkTo.withRel("allusers"));		
				return resource;
			}
	
	@PostMapping(path="/users")
	public ResponseEntity<Object> adduser(@Valid @RequestBody User user) {
	    User savedUser=service.saveUser(user);
		URI location=ServletUriComponentsBuilder.
		fromCurrentRequest().path("/{id}").
		buildAndExpand(savedUser.getId()).
		toUri();
		return ResponseEntity.created(location).build();
			
	}
	
	
	@DeleteMapping(path="/user/{id}")
	public void  deleteUser(@PathVariable Integer id) {
				User user= service.getUser(id);
				if(user==null) {
					throw new UserNotFoundException("id" +id);
				}
				
			}
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping(path="/wish")
	public String message(@RequestHeader(name="Accept-Language",required=false)Locale locale) {
		
		return messageSource.getMessage("good.morning.message",null, locale);
		
	}
	
	
	
	
	
	
	
	

}
