package com.app.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.ProductDTO;
import com.app.dto.ProductResponseDto;
import com.app.pojos.Product;

public interface IProductService {

	
	//public Product addNewProduct(Product product);

	public String deleteProductByID(int id);

	public ProductResponseDto getProductDetailsByID(int id) throws IOException;

	public List<ProductResponseDto> getAllProducts();

	public List<ProductResponseDto> findAllByCategoryByID(int id);

	public Product addNewProduct(String productDto, MultipartFile file) throws IOException;
	
	public Product UpdateProduct(int id, String productDto, MultipartFile file) throws IOException;


	
	
}
