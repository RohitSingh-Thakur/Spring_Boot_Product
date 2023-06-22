package com.singh.base.service;

import java.util.List;

import com.singh.base.entity.Category;
import com.singh.base.model.CategoryModel;

public interface CategoryService {
	
	public Boolean addCategory(Category category);
	public CategoryModel getCategoryById(Long categoryId);
	public CategoryModel getCategoryByName(String categoryName);
	public List<CategoryModel> getAllCAtegory();
}
