package com.app.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.core.Authentication;

import com.app.pojos.Cart;
import com.app.pojos.User;
import com.app.repository.CartItemRepository;
import com.app.repository.UserRepository;
import com.app.service.UserDetailsImpl;

@SpringBootTest

public class shoopingcartTest {

	
	@Autowired
	private CartItemRepository cartrepo;
	
	
//
//	@Autowired
//	UserDetailsImpl user;
//	
//	@Test
//	void testFindByCustomer() {
//		long a=1;
//		User username = u.findByUsername(auth.getName()).get();
//		
//	List<Cart> list = cartrepo.findAllByCustomer(username);
//	System.out.println(list);
//	assertEquals(2,list.size());
//	}
}
