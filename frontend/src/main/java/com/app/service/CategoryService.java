package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.pojos.Category;
import com.app.repository.CategoryRepository;


@Service
@Transactional
public class CategoryService  implements ICategoryService{
@Autowired
private CategoryRepository catRepo;

//add a category
public Category addCategory(Category c)
{
	return catRepo.save(c);
}

//get all the category 
public List<Category>  getAllCategory()
{
	return catRepo.findAll();
	
}

@Override
public String deleteCategoryDetails(int id) {
	catRepo.deleteById(id);
	return "Category Details with ID " + id + " deleted successfuly... ";
}


@Override
public Category fetchCategoryDetails(int cat_id) {
	
	
	return catRepo.findById(cat_id)
			.orElseThrow(() -> new RuntimeException("Category by ID " + cat_id + " not found!!!!"));
}

@Override
public List<Category> findByName(String name) {
	
	return null;
}

}
