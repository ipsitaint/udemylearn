package com.example.learn.controller;

//import java.net.http.HttpHeaders;
import java.util.List;
//import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "User Management Restful Api", value = "UserController")
@RestController
@RequestMapping(value = "/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "Retrieve List of users")
	@GetMapping()
	public List<User> getAllUser() {
		return userService.getAllUsers();
	}

	@ApiOperation(value = "Create a new user")
	@PostMapping
	public ResponseEntity<Void> createUser(
			@ApiParam("User information for a new user to be created.") @Valid @RequestBody User user,
			UriComponentsBuilder builder) {
		try {

//			user.setOrder(user.getOrder());
//			user.getOrder().setUser(user);

			user.setOrder(user.getOrder());

			userService.createUser(user);
			org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
			headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} catch (userExistsException ux) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ux.getMessage());
		}
	}

	@ApiOperation(value = "Get user by their ID")
	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id") Long id) {
		try {
			Optional<User> userOptional = userService.getUserById(id);
			return userOptional.get();
		} catch (UserNotFoundException ux) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ux.getMessage());
		}
	}

//	@GetMapping("/{id}")
//	public EntityModel<User> getUserById(@PathVariable("id") Long id){
//		try {
//		 Optional<User> userOptional = userService.getUserById(id);
//		 User user = userOptional.get();
//		 Long userid = user.getId();
//		 String k = this.getClass()+"/"+userid;
//		 user.add(new Link(k));
//		 EntityModel<User> finalEntity = new EntityModel<User>(user);
//		 return finalEntity;
//		}
//		catch(UserNotFoundException ux) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ux.getMessage());
//		}
//	}

	@PutMapping("/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
		try {
			return userService.updateUserById(id, user);
		} catch (UserNotFoundException ux) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ux.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
	}

	@GetMapping("/byusername/{username}")
	public User findByUsername(@PathVariable("username") String username) {
		Optional<User> userOptional = userService.getUserByUsername(username);
		return userOptional.get();
	}

	@PostMapping("/encrypt")
	public String encryptFunction(@RequestBody String value) {
		return userService.encryptFunction(value);
	}

	@PostMapping("/decrypt")
	public String decryptFunction(@RequestBody String value) {
		return userService.decryptFunction(value);
	} 
	
	@PutMapping("/put/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id, @Valid @RequestBody User userDetails) throws UserNotFoundException {
		return userService.updateUser( id,  userDetails);
	}
	
	@PatchMapping("/patch/{id}")
	public User updatepartialUser(@PathVariable(value = "id") Long id, @Valid @RequestBody User userDetails) throws UserNotFoundException {
		return userService.updatePartially( id,  userDetails);
	}
	

	
}
