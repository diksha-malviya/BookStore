package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.pojos.Cart;
import com.app.pojos.Product;
import com.app.pojos.User;
@Repository
public interface CartItemRepository extends JpaRepository<Cart, Integer> {
	
	 
	public List<Cart> findByCustomer(User user);

	public Cart findByCustomerAndProduct(User user, Product product);

	public List<Cart> findAllById(long id);

	//public List<Cart> findByCustomer(long i);
	
	

}
