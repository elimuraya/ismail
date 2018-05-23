package com.ismail.controllers;

import com.ismail.models.User;
import com.ismail.services.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.ismail.services.UserService;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {	
	
	@Autowired
	UserService uservice;
	
	@GetMapping("/{id}")
        public ResponseEntity<ResponseWrapper<Optional<User>>> oneUser(@PathVariable("id")  Long id) {
            return uservice.oneUser(id);
        }   
	
	@GetMapping("/all")
	 public ResponseEntity<ResponseWrapper<Optional<User>>> allUser () {
            return uservice.allUsers();
        }
	
	@PostMapping()
	 public ResponseEntity<ResponseWrapper<Optional<User>>> newUser (@RequestBody User usr) {
		return uservice.newUser(usr);
	}
         
    @PutMapping()
	 public ResponseEntity<ResponseWrapper<Optional<User>>> updateUser (@RequestBody User usr) {
		return uservice.updateUser(usr);
	}
        @DeleteMapping("/{id}")
	public ResponseEntity<ResponseWrapper<Optional<User>>> deleteUser (Long id) {
		return uservice.deleteUser(id);
	}
         

}
