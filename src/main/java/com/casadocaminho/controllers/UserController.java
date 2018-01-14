package com.casadocaminho.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.casadocaminho.models.User;
import com.casadocaminho.services.UserDetailsServiceImpl;

@Controller
@RequestMapping("user")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserDetailsServiceImpl userService;
	
	@RequestMapping("cadastrar/{username}/{password}")
	public void cadastrar(@PathVariable("username") String username, @PathVariable("password") String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(new BCryptPasswordEncoder().encode(password));
		userService.cadastrar(user);
	}
	

}
