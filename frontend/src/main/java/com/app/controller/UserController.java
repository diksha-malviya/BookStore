package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.response.UserResponse;
import com.app.pojos.User;
import com.app.repository.UserRepository;
import com.app.security.JwtUtils;
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class UserController {
	@Autowired
	private JwtUtils jwtutils;
	
	
	@Autowired
	private UserRepository userrepo;
	
	
	//get the user details-------Login in
	@GetMapping
	public ResponseEntity<?> getLoggedUserDetails(Authentication auth)
	{
		//step1:Get the current principle 
		User user = userrepo.findByUsername(auth.getName()).orElseThrow(() -> new RuntimeException("Please Login in "));
		UserResponse u = new UserResponse();
		u.setEmail(user.getEmail());
		u.setUsername(user.getUsername());
		//u.setRole(user.getRoles()).;
		System.out.println(user.getEmail());
		System.out.println(user.getUsername());
		
		return  new ResponseEntity<>(u,HttpStatus.OK);
	}
	
	
	
}
