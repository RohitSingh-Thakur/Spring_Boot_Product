package com.singh.base.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.singh.base.dao.CategoryDao;
import com.singh.base.entity.Category;
import com.singh.base.model.CategoryModel;
import com.singh.base.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao dao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public Boolean addCategory(Category category) {
		return dao.addCategory(category);
	}

	@Override
	public CategoryModel getCategoryById(Long categoryId) {
		 Category category = dao.getCategoryById(categoryId);
		 if(category != null) {
			 return this.mapper.map(category, CategoryModel.class);
		 }else {
			 return null;
		 }
	}

	@Override
	public CategoryModel getCategoryByName(String categoryName) {
		Category category = dao.getCategoryByName(categoryName);
		if(category != null) {
			return this.mapper.map(category, CategoryModel.class);
		}else {
		return null;
		}
	}

	@Override
	public List<CategoryModel> getAllCAtegory() {
		List<Category> categoryList = dao.getAllCAtegory();
		if(!categoryList.isEmpty()) {
			return categoryList.stream().map(e -> this.mapper.map(e, CategoryModel.class)).toList();
		}else {
			return null;	
		}
	}

	
}
