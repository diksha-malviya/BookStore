package com.app.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.ProductDTO;
import com.app.dto.ProductResponseDto;
import com.app.pojos.Category;
import com.app.pojos.Product;
import com.app.repository.CategoryRepository;
import com.app.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
@Transactional
public class ProductService implements IProductService {
	private static String location=System.getProperty("user.dir")+"/src/main/resources/static/productImages";
	//dependency : 
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private CategoryService categoryservice;
	

	
	@Override
	public String deleteProductByID(int id) {
		productRepo.deleteById(id);
		return 	 "Product Details with ID " + id + " deleted successfuly... ";
				
			
	}

	@Override
	public ProductResponseDto getProductDetailsByID(int id) throws IOException {
		
		ProductResponseDto productdto= new ProductResponseDto(); //create product response object
		Product productDetails = productRepo.findById(id).get();
		System.out.println(productDetails.toString());
		
		//set all the data to DTO 
		productdto.setId(productDetails.getId());
		productdto.setCategoryName(productDetails.getCategory().getName()); //set cat name
		productdto.setPrice(productDetails.getPrice());
		productdto.setDescription(productDetails.getDescription());
		productdto.setQuantity(productDetails.getQuantity());
		productdto.setProductName(productDetails.getName());
		productdto.setCategoryId(productDetails.getId());
		//IMAGE
		Path path = Paths.get(location, productDetails.getImageName()); //set the path
		System.out.println(path.toString());
		System.out.println(location);
		productdto.setImageName(productDetails.getImageName());  //set img name
		productdto.setData(Files.readAllBytes(path)); //read the img
		productdto.setType(Files.probeContentType(path)); //set the content type
		return productdto;
	}

	
	
	@Override
	public List<ProductResponseDto> getAllProducts() {
		
		List<Product> productList=productRepo.findAll();
		
		List<ProductResponseDto> productdto=new ArrayList<ProductResponseDto>();
		//ProductResponseDto productdto = new ProductResponseDto();
		//iterate over the list one by one and set the data
		productList.forEach(p ->{
			
			ProductResponseDto prod= new ProductResponseDto(); //create a new object for each -----list obj
			
			//set all the data to DTO 
			prod.setId(p.getId());
			prod.setCategoryName(p.getCategory().getName()); //set cat name
			prod.setPrice(p.getPrice());
			prod.setDescription(p.getDescription());
			prod.setQuantity(p.getQuantity());
			prod.setProductName(p.getName());
			prod.setCategoryId(p.getId());
			//IMAGE
			Path path = Paths.get(location, p.getImageName()); //set the path
			System.out.println(path.toString());
			System.out.println(location);
			prod.setImageName(p.getImageName());  //set img name
			try {
								prod.setData(Files.readAllBytes(path));} catch (IOException e) {e.printStackTrace();
			} //read the img
			try {           prod.setType(Files.probeContentType(path));} catch (IOException e) {
				e.printStackTrace();
			} //set the content type
				productdto.add(prod);});
		return productdto;
	}



	@Override
	public List<ProductResponseDto> findAllByCategoryByID(int id) {
		List<Product> productList=productRepo.findAllByCategory_id(id);
		List<ProductResponseDto> productdto=new ArrayList<ProductResponseDto>();
productList.forEach(p ->{
			
			ProductResponseDto prod= new ProductResponseDto(); //create a new object for each -----list obj
			
			//set all the data to DTO 
			prod.setId(p.getId());
			prod.setCategoryName(p.getCategory().getName()); //set cat name
			prod.setPrice(p.getPrice());
			prod.setDescription(p.getDescription());
			prod.setQuantity(p.getQuantity());
			prod.setProductName(p.getName());
			prod.setCategoryId(p.getId());
			//IMAGE
			Path path = Paths.get(location, p.getImageName()); //set the path
			System.out.println(path.toString());
			System.out.println(location);
			prod.setImageName(p.getImageName());  //set img name
			try {
								prod.setData(Files.readAllBytes(path));} catch (IOException e) {e.printStackTrace();
			} //read the img
			try {           prod.setType(Files.probeContentType(path));} catch (IOException e) {
				e.printStackTrace();
			} //set the content type
				productdto.add(prod);});
		return productdto;
	}



	@Override
	public Product addNewProduct(String productDto, MultipartFile file) throws IOException {
		
		//Product product=new Product(); //create object of product       	//image----imganme in db----file in folder
		
		Product product = new Product();
			ProductDTO pdata = new ObjectMapper().readValue(productDto, ProductDTO.class);
			//Product p= new Product();
			//set the values
			product.setId(pdata.getId());
			product.setName(pdata.getName())	;
			product.setDescription(pdata.getDescription());
			//get the category using category id-----and set to product
			product.setCategory(categoryservice.fetchCategoryDetails(pdata.getCategoryId()));
			product.setPrice(pdata.getPrice());
			product.setQuantity(pdata.getQuantity());
			String imageUUID;
			if(!file.isEmpty())
			{
				imageUUID=file.getOriginalFilename(); //give the file name
				Path fileNameAndPath=Paths.get(location,imageUUID);
				Files.write(fileNameAndPath, file.getBytes()); //save thw file
			}
			else //updateion img .....if user again adding image then store upadted name
			{
				imageUUID=file.getOriginalFilename();
			}
			pdata.setImageName(imageUUID); //set the imagename
			product.setImageName(imageUUID);
			System.out.println(pdata.toString());
		
		
		return productRepo.save(product);
		
	}

	@Override
	public Product UpdateProduct(int id ,String productDto, MultipartFile file) throws IOException {
		
		
		
		
		  Product product = productRepo.findById(id).get(); //get the product data using id
		 System.out.println(product.toString());
		 
			ProductDTO pdata = new ObjectMapper().readValue(productDto, ProductDTO.class);
	
			//set the values
			product.setId(product.getId());
			product.setName(pdata.getName())	;
			product.setDescription(pdata.getDescription());
			//get the category using category id-----and set to product
			product.setCategory(categoryservice.fetchCategoryDetails(pdata.getCategoryId()));
			product.setPrice(pdata.getPrice());
			product.setQuantity(pdata.getQuantity());
			String imageUUID;
			if(!file.isEmpty())
			{
				imageUUID=file.getOriginalFilename(); //give the file name
				Path fileNameAndPath=Paths.get(location,imageUUID);
				Files.write(fileNameAndPath, file.getBytes()); //save thw file
			}
			else //updateion img .....if user again adding image then store upadted name
			{
				imageUUID=file.getOriginalFilename();
			}
			pdata.setImageName(imageUUID); //set the imagename
			product.setImageName(imageUUID);
			System.out.println(pdata.toString());
		return productRepo.save(product);
	}

	



//	@Override
//	public Product addNewProduct(Product product) {
//		// TODO Auto-generated method stub
//		return null;
//	}





	




}
