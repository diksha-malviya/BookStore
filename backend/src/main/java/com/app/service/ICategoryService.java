package com.app.service;

import java.util.List;

import com.app.pojos.Category;

public interface ICategoryService {
	//add a new category
	public Category addCategory(Category c);
	
	//get All the Category List
	public List<Category>  getAllCategory();
	
	
	//delete the category by id
	String deleteCategoryDetails(int id);
	
	//fetch category details first in order to update ...
	public Category fetchCategoryDetails(int cat_id);
	
	
	public List<Category> findByName(String name);
}
