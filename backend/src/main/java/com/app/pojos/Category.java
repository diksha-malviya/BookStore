package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="category")
public class Category extends BaseEntity {
	
	//category_id,name
	@Column(length = 30)
	private String name;

}


//1 | Fiction         |
//|  2 | History         |
//|  4 | Poetry          |
//|  5 | Biography       |
//|  6 | Non-fiction     |
//|  7 | CookBook        |
//|  8 | Science-fiction
