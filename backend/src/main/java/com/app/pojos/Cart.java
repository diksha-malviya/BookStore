package com.app.pojos;

import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "cart_items")
public class Cart  extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(name="book_id")
	private Product product;
	
	
	@ManyToOne
	@JoinColumn(name = "cust_id")
	private User customer;
	
	
	
	@Column(length = 20)
	private int quantity;
	
	

}
