package com.singh.base.dao;

import java.util.List;

import com.singh.base.entity.Category;
import com.singh.base.model.CategoryModel;

public interface CategoryDao {
	public Boolean addCategory(Category category);
	public Category getCategoryById(Long categoryId);
	public Category getCategoryByName(String categoryName);
	public List<Category> getAllCAtegory();
}
