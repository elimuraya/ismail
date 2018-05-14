package com.ismail.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ismail.services.UserService;

@Controller
public class UserController {	
	
	@Autowired
	UserService uservice;
	
	@GetMapping("/")
	public String root() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login-signup";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		return uservice.newUser(model);
	}

}
