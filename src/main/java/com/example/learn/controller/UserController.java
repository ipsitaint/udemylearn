package com.example.learn.controller;

//import java.net.http.HttpHeaders;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.learn.exception.UserNotFoundException;
import com.example.learn.exception.userExistsException;
import com.example.learn.model.User;
import com.example.learn.service.UserService;

@RestController
@RequestMapping(value="/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<User> getAllUser(){
		return userService.getAllUsers();
	}
	
	@PostMapping
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder builder) {
		try {
			
//			user.setOrder(user.getOrder());
//			user.getOrder().setUser(user);
			
			user.setOrder(user.getOrder());				
			
			userService.createUser(user);
			org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
			headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
		}
		catch(userExistsException ux) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ux.getMessage());
		}
	}
	
	@GetMapping("/{id}")
	public Optional<User> getUserById(@PathVariable("id") Long id){
		try {
		return userService.getUserById(id);
		}
		catch(UserNotFoundException ux) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ux.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
		try {
		return userService.updateUserById(id,user);
		}
		catch(UserNotFoundException ux) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ux.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable("id") Long id) {
		 userService.deleteUserById(id);	
	}
	
	@GetMapping("/byusername/{username}")
	public Optional<User> findByUsername(@PathVariable("username") String username) {
		Optional<User> user = userService.getUserByUsername(username);
		return user;
	}
}
