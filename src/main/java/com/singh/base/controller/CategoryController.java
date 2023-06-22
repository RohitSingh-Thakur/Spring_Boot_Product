package com.singh.base.controller;

import java.util.List; 

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.singh.base.constants.GlobalHttpRequest_Category;
import com.singh.base.constants.Global_ExceptionConstants;
import com.singh.base.entity.Category;
import com.singh.base.exceptions.NoRecordFoundByIdException;
import com.singh.base.exceptions.NoRecordFoundWithGivenName;
import com.singh.base.exceptions.NoRecordsFound;
import com.singh.base.exceptions.RecordAlreadyExistException;
import com.singh.base.model.CategoryModel;
import com.singh.base.service.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService service;
	
	@PostMapping(GlobalHttpRequest_Category.ADD_CATEGORY)
	public ResponseEntity<Boolean> addCategory(@Valid @RequestBody Category category){
		Boolean isAdded = service.addCategory(category);
		if(isAdded) {
			return new ResponseEntity<>(isAdded,HttpStatus.CREATED);
		}else {
			throw new RecordAlreadyExistException(Global_ExceptionConstants.RECORD_ALREADY_FOUND_OF_CATEGORY_EXCEPTION);
		}
	}
	
	
	@GetMapping(GlobalHttpRequest_Category.GET_CATEGORY_BY_ID) 
	public ResponseEntity<CategoryModel> getCategoryById(@PathVariable Long categoryId){
		CategoryModel categoryModel = service.getCategoryById(categoryId);
		if(categoryModel!=null) {
			return new ResponseEntity<CategoryModel>(categoryModel,HttpStatus.FOUND);
		}else {
			throw new NoRecordFoundByIdException(Global_ExceptionConstants.NO_RECORD_FOUND_FOR_GIVEN_CATEGORY_ID_EXCEPTION);
		}
	}
	
	@GetMapping(GlobalHttpRequest_Category.GET_CATEGORY_BY_NAME)
	public ResponseEntity<CategoryModel> getCategoryByName(@RequestParam String categoryName){
		CategoryModel categoryModel = service.getCategoryByName(categoryName);
		if(categoryModel != null) {
			return new ResponseEntity<CategoryModel>(categoryModel,HttpStatus.FOUND);
		}else {
			throw new NoRecordFoundWithGivenName(Global_ExceptionConstants.NO_RECORD_FOUND_FOR_GIVEN_CATEGORY_NAME_EXCEPTION);
		}
	}
	
	@GetMapping(GlobalHttpRequest_Category.GET_ALL_CATEGORY)
	public ResponseEntity<List<CategoryModel>> getAllCategory(){
		List<CategoryModel> listModels = service.getAllCAtegory();
		if(!listModels.isEmpty()) {
			return new ResponseEntity<List<CategoryModel>>(listModels,HttpStatus.FOUND);
		}else {
			throw new NoRecordsFound(Global_ExceptionConstants.EMPTY_CATEGORY_EXCEPTION);
		}
	}

}
