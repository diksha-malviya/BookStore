package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.pojos.Category;

public interface CategoryRepository  extends JpaRepository<Category, Integer>{

}
