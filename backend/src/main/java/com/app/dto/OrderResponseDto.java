package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
	
private String msg;
private	String name;
private	String email;
private String mobileno;
private String address;
private String paymentmode;
private double price;
}
