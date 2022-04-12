package com.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AddToCartDto;
import com.app.dto.CartDto;
import com.app.dto.response.MessageResponse;
import com.app.pojos.User;
import com.app.repository.UserRepository;
import com.app.security.JwtUtils;
import com.app.service.IShoppingcartService;
import com.app.service.UserDetailsImpl;
import com.app.service.userDetailsServiceImpl;
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/cart")
public class CartController {
	
	
	@Autowired
	private IShoppingcartService cartservice;
	
	@Autowired
	private JwtUtils jwtutils;
	
	
	@Autowired
	private UserRepository userrepo;
	
	
	
	
	//add the product in the cart selected by user
	@PostMapping("/add")
	public ResponseEntity<MessageResponse> addProductToCart(@RequestBody AddToCartDto  cartdto,Authentication auth)
	{
			//step1:Get the current principle 
			User user = userrepo.findByUsername(auth.getName()).orElseThrow(() -> new RuntimeException("Please Login in "));
		 
			System.out.println(user.getEmail() + "-------"+ user.getId());
			System.out.println(auth.getName());

			cartservice.addProduct(cartdto, user);
		
		
		return new    ResponseEntity<>(new MessageResponse("Added to Cart Sucessfully"),HttpStatus.CREATED);
		
		
	//return null;
		
		
		
		
	}
	
	
	
	// get all cart items for a user
    @GetMapping("/")
    public ResponseEntity<CartDto>    getCartItems(Authentication auth) {
    	//step1:Get the current principle 
    	System.out.println("...............................");
		User user = userrepo.findByUsername(auth.getName()).orElseThrow(() -> new RuntimeException("Please Login in "));
		System.out.println(user.getUsername());
        
		
		//2. get cart items
			CartDto cartDto = cartservice.listcartItems(user);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }
    
    
    
    // delete a cart item for a user

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<MessageResponse> deleteCartItem(@PathVariable("cartItemId") Integer itemId,
                                                    Authentication auth) {
    	//step1:Get the current principle 
    	System.out.println("...............................");
		User user = userrepo.findByUsername(auth.getName()).orElseThrow(() -> new RuntimeException("Please Login in "));
		System.out.println(user.getUsername());
       cartservice.deletCartItem(itemId,user);
        
        return new ResponseEntity<>(new MessageResponse( "Item has been removed"), HttpStatus.OK);

    }

}
