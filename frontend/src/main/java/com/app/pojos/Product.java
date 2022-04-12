package com.app.pojos;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book_table")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product extends BaseEntity {
	

	private String name;
	private double price;
	private int quantity;
	private String imageName;
	private String description;
	
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id",nullable = false )
	private Category category;
	

}
