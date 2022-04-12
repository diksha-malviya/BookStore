package com.app.pojos;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="order_details")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private	Integer id;
	
	private String username;
	private String address;
	private String mobileNo;
	private String email;
	private double totalprice;
	@Enumerated(EnumType.STRING)
	private PaymentType  payType;
	
	
	//one order has multiple Cart_items
	@OneToMany
	private List<Cart> cart;
	
	//one user can place multiple orders
	@ManyToOne
	private User customer;

	@Override
	public String toString() {
		return "Order [id=" + id + ", username=" + username + ", address=" + address + ", mobileNo=" + mobileNo
				+ ", email=" + email + ", payType=" + payType + ", cart=" + cart + ", customer=" + customer + "]";
	}
	
	
	
	

}
