package com.singh.base.controller;

import java.util.List;  
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.singh.base.constants.GlobalHttpRequest_Product;
import com.singh.base.constants.Global_ExceptionConstants;
import com.singh.base.entity.Product;
import com.singh.base.exceptions.NoRecordFoundByIdException;
import com.singh.base.exceptions.NoRecordFoundWithGivenName;
import com.singh.base.exceptions.NoRecordsFound;
import com.singh.base.exceptions.RecordAlreadyExistException;
import com.singh.base.model.ProductModel;
import com.singh.base.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@PostMapping(GlobalHttpRequest_Product.ADD_PRODUCT)
	public ResponseEntity<Boolean> addProduct(@Valid @RequestBody Product product) {
		Boolean isAdded = service.addProduct(product);
		if (isAdded) {
			return new ResponseEntity<>(isAdded, HttpStatus.CREATED);
		} else {
			throw new RecordAlreadyExistException(Global_ExceptionConstants.RECORD_ALREADY_FOUND_OF_PRODUCT_EXCEPTION);
		}
	}
	
	@GetMapping(GlobalHttpRequest_Product.GET_PRODUCT_BY_ID)
	public ResponseEntity<ProductModel> getProductById(@PathVariable Long productId) {
		ProductModel productModel = service.getProductById(productId);
		if (productModel != null) {
			return new ResponseEntity<ProductModel>(productModel, HttpStatus.FOUND);
		} else {
			throw new NoRecordFoundByIdException(Global_ExceptionConstants.NO_RECORD_FOUND_FOR_GIVEN_PRODUCT_ID_EXCEPTION);
		}
	}
	
	@GetMapping(GlobalHttpRequest_Product.GET_ALL_PRODUCTS)
	public ResponseEntity<List<ProductModel>> getAllProduct() {
		List<ProductModel> modelList = service.getAllProducts();
		if (!modelList.isEmpty()) {
			return new ResponseEntity<List<ProductModel>>(modelList, HttpStatus.FOUND);
		} else {
			throw new NoRecordsFound(Global_ExceptionConstants.EMPTY_PRODUCT_EXCEPTION);
		}
	}
	
	@DeleteMapping(GlobalHttpRequest_Product.DELETE_PRODUCT)
	public ResponseEntity<Boolean> deleteProduct(@PathVariable Long productId) {
		Boolean isDeleted = service.deleteProduct(productId);
		if (isDeleted) {
			return new ResponseEntity<Boolean>(isDeleted, HttpStatus.OK);
		} else {
			throw new NoRecordFoundByIdException(Global_ExceptionConstants.NO_PRODUCT_RECORD_FOUND_TO_DELETE_EXCEPTION);
		}
	}
	
	@PatchMapping(GlobalHttpRequest_Product.UPDATE_PRODUCT_BY_PRODUCT_ID)
	public ResponseEntity<Boolean> updateProduct(@Valid @PathVariable Long productId, @RequestBody Map<String, Object> productFields) {
			Boolean status = service.updateProductByProductId(productId, productFields);
		if (status) {
			return new ResponseEntity<Boolean>(status, HttpStatus.OK);
		} else {
			throw new NoRecordFoundByIdException(Global_ExceptionConstants.NO_RECORD_FOUND_FOR_GIVEN_PRODUCT_ID_EXCEPTION);
		}
	}
	
	@GetMapping(GlobalHttpRequest_Product.SORT_PRODUCT)
	public ResponseEntity<List<ProductModel>> sortProducts(@RequestParam String fieldName, @RequestParam String order) {
		List<ProductModel> list = service.sortProducts(fieldName, order);
		if (!list.isEmpty()) {
			return new ResponseEntity<List<ProductModel>>(list, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<List<ProductModel>>(HttpStatus.NO_CONTENT); // Exception Handling Not Done
		}
	}
	
	
	@GetMapping(GlobalHttpRequest_Product.GET_MAX_PRICE_PRODUCT)
	public ResponseEntity<List<ProductModel>> getMaxPriceProducts() {
		List<ProductModel> list = service.getMaxPriceProducts();
		if (!list.isEmpty()) {
			return new ResponseEntity<List<ProductModel>>(list, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<List<ProductModel>>(HttpStatus.NO_CONTENT);  // Exception Handling Not Done
		}
	}
	
	@GetMapping(GlobalHttpRequest_Product.GET_COUNT_SUMOF_PRODUCT_PRICE)
	public ResponseEntity<Object> countSumOfProductPrice(){
		Double sumOfProductPrice = service.countSumOfProductPrice();
		if(sumOfProductPrice>0) {
			return new ResponseEntity<>(sumOfProductPrice, HttpStatus.OK);
		}else {
			throw new NoRecordsFound();  
		}
	}
	
	@GetMapping(GlobalHttpRequest_Product.GET_TOTAL_PRODUCTS_COUNT) 
	public ResponseEntity<Object> getTotalCountOfProducts(){
		Long productCount = service.getTotalCountOfProducts();
		if(productCount>0) {
			return new ResponseEntity<>(productCount, HttpStatus.OK);
		}else {
			throw new NoRecordsFound();  
		}
	}
	
	@GetMapping(GlobalHttpRequest_Product.GET_PRODUCT_BY_NAME)
	public ResponseEntity<ProductModel> getProductByName(@RequestParam String productName){
		ProductModel productModel = service.getProductByName(productName);
		if(productModel != null) {
			return new ResponseEntity<ProductModel>(productModel,HttpStatus.FOUND);
		}else {
			throw new NoRecordFoundWithGivenName(Global_ExceptionConstants.NO_RECORD_FOUND_FOR_GIVEN_PRODUCT_NAME_EXCEPTION);
		}
	}

}