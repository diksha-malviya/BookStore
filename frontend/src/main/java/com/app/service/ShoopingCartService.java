package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Exception.CustomException;
import com.app.dto.AddToCartDto;
import com.app.dto.CartDto;
import com.app.dto.CartItemDto;
import com.app.pojos.Cart;
import com.app.pojos.Product;
import com.app.pojos.User;
import com.app.repository.CartItemRepository;
import com.app.repository.ProductRepository;
//CartDto: items ,totalcost
//cartItremDto : id,quanitity,product
//AddtoCartDto : id,book-id,productid
@Service
@Transactional
public class ShoopingCartService implements IShoppingcartService {

	@Autowired
	private CartItemRepository cartrepo;
	
	@Autowired
	private ProductRepository productrepo;
	
	
	
	
	
	//get all the carts items of user and also display total
	public CartDto listcartItems(User user)
	{
		List<Cart> cartList = cartrepo.findByCustomer(user);
		
		
		cartList.forEach(i -> System.out.println(i));
		List<CartItemDto> cartItems = new ArrayList<>();
		double totalcost=0;
		
		//find the total
		for (Cart cart: cartList) {
	            CartItemDto cartItemDto = new CartItemDto(cart); //id qunatity product----->getprice
	            
	            
	            totalcost  += cartItemDto.getQuantity() * cart.getProduct().getPrice();
	            
	            
	            cartItems.add(cartItemDto);
	        }
		
		System.out.println(totalcost);
		
		CartDto cartDto = new CartDto();//final object that we have to pass--->FE
        
		cartDto.setTotalCost(totalcost); //set total price
        
		cartDto.setCartItems(cartItems); 
        
		return  cartDto;
		
		
		
		
	}
	
	
	
	public void addProduct(AddToCartDto cartdto, User user )
	{
		
		//get the product
		Product product=productrepo.findById(cartdto.getBookId()).get();
		
		Cart cart= new Cart();//create cart object and set the properties
		
		cart.setProduct(product);
		cart.setQuantity(cartdto.getQuantity());
		cart.setCustomer(user);
		
		
		cartrepo.save(cart); //save the data in Cart details
			System.out.println(user.getId() + "------------"+user.getEmail());
	}



	@Override
	public void deletCartItem(Integer itemId, User user) {
		 Optional<Cart> cart = cartrepo.findById(itemId);

		 
		 System.out.println(cart.toString());
		 
		 //check whether cart id is valid or not
	        if (cart.isEmpty()) {
	            throw new CustomException("cart item id is invalid: " + itemId);
	        }

	        Cart cartdetails = cart.get();
System.out.println(cartdetails.getCustomer().getId());
System.out.println(user.getId());

	        if (cartdetails.getCustomer().getId()!= user.getId()) {
	            throw  new CustomException("cart item does not belong to user: " +itemId );
	        
	        }

	        
	        System.out.println(user.toString());
	        cartrepo.deleteById(itemId);

		
	}




}
