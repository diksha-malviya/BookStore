package com.app.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.pojos.Product;
import com.app.repository.ProductRepository;

@SpringBootTest
public class TestProductRepo {
	
	@Autowired
	private ProductRepository prodRepo;
	
	@Test
	public void testFindbyall()
	{
		List<Product> list = prodRepo.findAllByCategory_id(1);
		//System.out.println(list.get(1));
		System.out.println(list.get(0).getPrice());
		assertEquals(500, list.get(0).getPrice());
	}

}
