package com.rest.hellowebservice.Udemy.user;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class UserJpaController {

//	@Autowired
//	private UserDao service;
	
	@Autowired
	private UserRepo userrepo;

	@GetMapping(path = "jpa/users")
	public List<User> getusers() {

		return userrepo.findAll();
	}

	@GetMapping(path = "jpa/user/{id}")
	
	@ResponseBody
	public Resource<User> getuser(@PathVariable Integer id) {
		Optional<User> user = userrepo.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User not found for Id  " + id);
			
		}
		

		Resource<User> resource = new Resource<User>(user.get());

		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getusers());

		resource.add(linkTo.withRel("allusers"));
		return resource;
	}

	@PostMapping(path = "jpa/users")
	public ResponseEntity<Object> adduser(@Valid @RequestBody User user) {
		User savedUser = userrepo.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();

	}

	@DeleteMapping(path = "jpa/user/{id}")
	public void deleteUser(@PathVariable Integer id) {
	 userrepo.deleteById(id);


	}

	
	@Autowired
	private MessageSource messageSource;

	// using header

	@GetMapping(path = "jpa/wish")
	public String message(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {

		return messageSource.getMessage("good.morning.message", null, locale);

	}
	
   @GetMapping(path = "jpa/user/{id}/posts")
	
	public List<Post> getuserpost(@PathVariable Integer id) {
		Optional<User> user = userrepo.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User not found for Id  " + id);
			
		}
		
		return user.get().getPost();
		
		
		

//		Resource<User> resource = new Resource<User>(user.get());
//
//		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getusers());
//
//		resource.add(linkTo.withRel("allusers"));
		//return resource;
	}
   
   
   @Autowired
    private postRepo postrepo;
   
	@PostMapping(path = "jpa/users/{id}/posts")
	public ResponseEntity<Object> adduserpost(@Valid @PathVariable int id, @RequestBody Post post) {
		
		Optional<User> useroptional=userrepo.findById(id);
		if (!useroptional.isPresent()) {
			throw new UserNotFoundException("User not found for Id  " + id);
			
		}
		
		User storeduser=useroptional.get();
		post.setUser(storeduser);
		postrepo.save(post);
		
		
		
		
		
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId())
				.toUri();
		return ResponseEntity.created(location).build();

	}

}
