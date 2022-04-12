package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.OrderDto;
import com.app.dto.OrderResponseDto;
import com.app.dto.response.MessageResponse;
import com.app.pojos.User;
import com.app.repository.UserRepository;
import com.app.security.JwtUtils;
import com.app.service.IOrderService;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("api/order")
public class OrderController {
	
	@Autowired
	private IOrderService orderservice;
	@Autowired
	private JwtUtils jwtutils;
	@Autowired
	private UserRepository userrepo;
	
	
	
	@PostMapping
	public ResponseEntity<OrderResponseDto>  placeOrder(@RequestBody  OrderDto  orderdto ,Authentication auth){
		//step1:Get the current principle 
		User user = userrepo.findByUsername(auth.getName()).orElseThrow(() -> new RuntimeException("Please Login in "));
	 
		System.out.println(user.getEmail() + "-------"+ user.getId());
		System.out.println(auth.getName());
	
		return new    ResponseEntity<>(orderservice.placeOrderDetails(orderdto ,user),HttpStatus.CREATED);
		
	}

}
