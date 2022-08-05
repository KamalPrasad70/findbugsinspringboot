package com.User.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.User.Entity.User;
import com.User.Repository.UserRepository;

@RestController
public class UserController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping(value="/users")
	public ResponseEntity<List<User>> getUsers(){
		List<User> users = userRepo.findAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		
	}
	@PostMapping(value="/save")
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		LOGGER.info("User creation is started");
		User userCreated = userRepo.save(user);
		LOGGER.info("User SuccessFully Created");
		return  new ResponseEntity<String>("saved user",HttpStatus.OK);
	}	
	
	@PutMapping(value="/update/{id}")
	public ResponseEntity<String> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		User updatedUser =userRepo.findById(id).get();
		updatedUser.setFirstName(user.getFirstName());
		updatedUser.setLastName(user.getLastName());
		updatedUser.setAge(user.getAge());
		userRepo.save(updatedUser);
		return new ResponseEntity<String>("Updated...",HttpStatus.OK);
	}
	
	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") long id) {
		User deleteUser =userRepo.findById(id).get();
		userRepo.delete(deleteUser);
		return new ResponseEntity<String>("Delete user with the id: " +id,HttpStatus.OK);
		
	}
	

}
