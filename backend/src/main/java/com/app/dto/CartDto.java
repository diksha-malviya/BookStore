package com.app.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CartDto {
	
	
	private List<CartItemDto> cartItems;
    private double totalCost;
	
	
	
	 public List<CartItemDto> getCartItems() {
		return cartItems;
	}
	public void setCartItems(List<CartItemDto> cartItems) {
		this.cartItems = cartItems;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	
}
