package com.app.controller;

import java.io.FileInputStream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.app.dto.ProductDTO;
import com.app.dto.ProductResponseDto;
import com.app.pojos.Product;
import com.app.service.ICategoryService;
import com.app.service.IProductService;
import com.fasterxml.jackson.databind.ObjectMapper;




@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/admin/product")
public class ProductController {
	//@Value("${file.upload.location}")
	private static String location=System.getProperty("user.dir")+"/src/main/resources/static/productImages";
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private ICategoryService categoryService;
	
	
	
	
	
	//add a new product
	//ProductIMage is file uploaded by user
	//imgname is the name of img file will be stored in database
	@PostMapping("/add")
	public ResponseEntity<?> addNewProductAndImage(@RequestParam("ProductDto") String productDto ,
			@RequestParam("ProductImage") MultipartFile file
			) throws IOException
	{

	System.out.println(productDto);
		return  new ResponseEntity<>(productService.addNewProduct(productDto,file),HttpStatus.OK);
		
	}
	//___________________________________________________________________________________
	
	@DeleteMapping("/{id}")
	public String deleteProductById(@PathVariable int id )
	{
		return productService.deleteProductByID(id);
		
	}
	//___________________________________________________________________________________


	
	
	//___________________________________________________________________________________
	
	
	//get alll the product details 
	@GetMapping("/")
	public ResponseEntity<?> getAllProductDetails()
	{
		System.out.println("in all product details");
		return  new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);//constructor....(body,status)
	}
	//___________________________________________________________________________________
	
	//get all the product under the specific category
	@GetMapping("/category/{id}")
	public List<ProductResponseDto> getAllProductByCategoryID(@PathVariable int  id)
	{
		
		return productService.findAllByCategoryByID(id);
		
	}
	
	
	//___________________________________________________________________________________
	//Updation
	//first react will check whether id is present or not so 
			//----Get(id)---if yes---->update api will call
		
		
	
	//get the  product details by id
	@GetMapping("/{id}")
	public ResponseEntity<?> getProductDetailById(@PathVariable int id ) throws IOException
	{
		return new ResponseEntity<>(productService.getProductDetailsByID(id), HttpStatus.OK);
		
	}
	//___________________________________________________________________________________
	
	//add request handling method to update existing product details (update a  resource) : PUT
	//react will send the detach Product  object we will save it here so save()---internally update()will fire
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> UpdateProduct(@RequestParam("ProductDto") String productDto ,
			@RequestParam("ProductImage") MultipartFile file
			,@PathVariable int id) throws IOException
	{
		
	
		System.out.println(productDto);
		return  new ResponseEntity<>(productService.UpdateProduct(id,productDto,file),HttpStatus.OK);
		
	}
	
	
	
	

}





























//{
//    "id": 1,
//    "name": "THE GREAT GATSBY",
//    "price": 500.0,
//    "quantity": 2,
//    "imageName": "abc",
//    "description": ".......",
//    "category": {
//        "id": 1,
//        "name": "NOVEL"
//    }
//}


//-------------------------------------------------------------

//{
//"name":"heyyy ",
//"categoryId":2,
//"quantity": 2,
//"price" :500,
//"description":"this is demo"
//}