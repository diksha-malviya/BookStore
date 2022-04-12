package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.CartDto;
import com.app.dto.CartItemDto;
import com.app.dto.OrderDto;
import com.app.dto.OrderResponseDto;
import com.app.pojos.Cart;
import com.app.pojos.Order;
import com.app.pojos.User;
import com.app.repository.CartItemRepository;
import com.app.repository.OrderRepository;
@Service
@Transactional
public class OrderService  implements IOrderService{

	@Autowired
	private CartItemRepository cartrepo;
	
	@Autowired
	private OrderRepository orderrepo;
	
	@Override
	public OrderResponseDto  placeOrderDetails(OrderDto orderdto ,User user) {
		List<Cart> list = cartrepo.findByCustomer(user); //get the user ----->cart items
		OrderResponseDto response=new OrderResponseDto();
//		if(list.isEmpty())
//		{
//			//return "Your Cart is Empty...Please add product to cart ";
//			response.setMsg("Your Cart is Empty...Please add product to cart ");
//		return	 response;
//		}
//		else
//		{
			
		
		Order o= new Order(); //order object is created
		o.setCart(list); //set the cart list  to te order
		o.setCustomer(user);
		
		o.setEmail(user.getEmail());
		o.setUsername(orderdto.getName());
		o.setEmail(orderdto.getEmail());
		o.setAddress(orderdto.getAddress());
		o.setMobileNo(orderdto.getMobileno());
		o.setPayType(o.getPayType().valueOf(orderdto.getPaymentmode()));
		o.setTotalprice(orderdto.getPrice());
		orderrepo.save(o); //save the order details
		
		
		response.setAddress(o.getAddress());
		response.setEmail(o.getEmail());
		response.setMobileno(o.getMobileNo());
		response.setPaymentmode(o.getPayType().toString());
		response.setName(o.getUsername());
		response.setPrice(o.getTotalprice());
		response.setMsg("Your Order is Placed Sucessfully.....");
		
		
		
		System.out.println(o.toString());
		
		return response;
	}

	

	
}
