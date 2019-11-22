package com.example.learn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.learn.exception.UserNotFoundException;
import com.example.learn.exception.userExistsException;
import com.example.learn.model.User;
import com.example.learn.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class UserService {
	 Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public User createUser(User user) throws userExistsException{
		
//		Optional<User> existingUser=userRepository.findByUsername(user.getUsername());
//		if(existingUser != null) {
//			throw new userExistsException("User already exists in repository");
//		}else {
			return userRepository.save(user);
//		}
			
	}
	
	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("User not found in User repository.");
		}
		return user;
	}
	
	public User updateUserById(Long id, User user) throws UserNotFoundException{
		Optional<User> optionalUser = userRepository.findById(id);
		if(!optionalUser.isPresent()) {
			throw new UserNotFoundException("User not found in User repository, Provide correct Id");
		}else {
			user.setId(id);
			return userRepository.save(user);	
		}
	}
	
	public void deleteUserById(Long id){
		Optional<User> optionalUser= userRepository.findById(id);
		if(!optionalUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User not found in user Repository");
		}else {
			userRepository.deleteById(id);
		}
	}
	
	public Optional<User> getUserByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		return user;
	} 
	
}
