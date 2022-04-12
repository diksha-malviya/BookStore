package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	List<Product> findAllByCategory_id(int id);

	
}
