package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.dto.AddToCartDto;
import com.app.dto.CartDto;
import com.app.pojos.Cart;
import com.app.pojos.User;

public interface IShoppingcartService {
	public void addProduct(AddToCartDto dto ,User user );
	
	
	public CartDto listcartItems(User user);


	public void deletCartItem(Integer itemId, User user);
}
