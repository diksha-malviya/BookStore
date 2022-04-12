package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Category;
import com.app.service.ICategoryService;
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("api/admin/category")
public class CategoryController {

	//dependency :categoryService i/f
	@Autowired
	private ICategoryService categoryService;
	
	
	//display all the category
	@GetMapping("/")
	public ResponseEntity<?> getAllCategoryDetails()
	{
		System.out.println("in get all emps");
		return  new ResponseEntity<>(categoryService.getAllCategory(),HttpStatus.OK);//constructor....(body,status)
	}
	
	
	//____________________________________________________
	//add a new Category 
	@PostMapping//("/add")
	public Category addCategoryDetails(@RequestBody  @Valid   Category c) //de-serial (un marshalling)
	{
		
		System.out.println("in add category "+c);
		return categoryService.addCategory(c); //service-->SAVE()
	}
	
	//____________________________________________
	//delete category by ID
	@DeleteMapping("/{id}")
	public String deleteCategoryDetailsByID(@PathVariable int id)
	{
		return categoryService.deleteCategoryDetails(id);
		
	}
	
	//______________________________________________
	//Update Category using ID
	//=>First get by id ....check present or not
	@GetMapping("/{id}")
	public ResponseEntity<?> getCategoryDetails(@PathVariable int id) {
		System.out.println("in get category dtls " + id);
	//	try {
			// invoke service layer's method
			return new ResponseEntity<>(categoryService.fetchCategoryDetails(id), HttpStatus.OK);
	}
	
	//=>Updatee
	@PutMapping//("/{id}")
	public Category updateEmpDetails(@RequestBody  @Valid   Category e) //de-serial (un marshalling) 
	{
		//e : DETACHED POJO , containing updated state
		System.out.println("in add category "+e);
		return categoryService.addCategory(e); //same method is called
	}
			
			
	
}





