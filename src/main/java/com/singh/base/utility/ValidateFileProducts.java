package com.singh.base.utility;

import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.singh.base.constants.GlobalConstants_ValidateProduct;
import com.singh.base.dao.ProductDao;
import com.singh.base.entity.Category;
import com.singh.base.entity.Product;
import com.singh.base.entity.Supplier;
import com.singh.base.model.CategoryModel;
import com.singh.base.model.SupplierModel;
import com.singh.base.service.CategoryService;
import com.singh.base.service.SupplierService;

@Component
public class ValidateFileProducts {
	
	@Autowired
	private SupplierService supplierServiceValidate;
	
	@Autowired
	private CategoryService categoryServiceValidate;
	
	@Autowired
	private ModelMapper mapper;
	
	Supplier supplier;
	Category category;
	
	public Map<String, String> validateFileProducts(Product product){
		
		Map<String, String> validProduct = new HashedMap<String, String>();
		
		if(product.getProductName() == null) {
			validProduct.put("Product Name : ", GlobalConstants_ValidateProduct.productNameIsEmpty+GlobalConstants_ValidateProduct.productNameLenght);
		}
		SupplierModel supplierModel = supplierServiceValidate.getSupplierById(product.getSupplierId().getSupplierId());
		supplier = this.mapper.map(supplierModel, Supplier.class);
		if(supplier == null) {
			validProduct.put("Supplier ID : ", "No Supplier ID Found");
		}
		
		CategoryModel categoryModel = categoryServiceValidate.getCategoryById(product.getCategoryId().getCategoryId());
		category = this.mapper.map(categoryModel, Category.class);
		if(category == null) {
			validProduct.put("Category ID : ", "No Category ID Found");
		}
		
		if(product.getProductQuantity()<=0) {
			validProduct.put("Product Quantity : ", "Invalid Product Quantity, Shoud Be Greater Than 0");
		}
		
		if(product.getProductPrice()<=0) {
			validProduct.put("Product Price : ", "Invalid Product Price, Shoud Be Greater Than 0");
		}
		
		return validProduct;
	}

}
