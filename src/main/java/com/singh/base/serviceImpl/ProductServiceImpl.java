package com.singh.base.serviceImpl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.singh.base.dao.ProductDao;
import com.singh.base.entity.Product;
import com.singh.base.model.ProductModel;
import com.singh.base.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDao dao;
	
	@Autowired
	private ModelMapper mapper;
	
	private static final Logger log = Logger.getLogger(ProductServiceImpl.class);

	@Override
	public Boolean addProduct(Product product) {
		return dao.addProduct(product);
	}

	@Override
	public ProductModel getProductById(Long productId) { // Model Mapper Checked
		 Product product = dao.getProductById(productId);
		 if(product != null) {
			 ProductModel model = this.mapper.map(product, ProductModel.class);
			 System.out.println(model.getClass().getName());
			 return this.mapper.map(product, ProductModel.class);
			 
		 }else {
			 return null;
		 }
	}
	

	@Override
	public List<ProductModel> getAllProducts() {
		List<Product> products = dao.getAllProducts();
		if(!products.isEmpty()) {
			 return products.stream().map(e -> this.mapper.map(e, ProductModel.class)).toList();
		}else {
			return null;
		}
	}
	
	@Override
	public Boolean deleteProduct(Long productId) {
		Boolean status = dao.deleteProduct(productId);
		if (status) {
			return status;
		}else {
			return status;
		}
	}
	
	
	@Override
	public Boolean updateProductByProductId(Long  productId, Map<String, Object> productFields) {
		
		Boolean status = dao.updateProductByProductId(productId, productFields);
		return status;
	}
	
	
	@Override
	public List<ProductModel> sortProducts(String fieldName, String order) {
		List<Product> products = dao.sortProducts(fieldName, order);
		if(!products.isEmpty()) {
			return products.stream().map(e -> this.mapper.map(e, ProductModel.class)).toList();
		}else {
			return null;
		}
	}
	
	
	@Override
	public List<ProductModel> getMaxPriceProducts() {
			List<Product> maxPriceProducts = dao.getMaxPriceProducts();
			if(!maxPriceProducts.isEmpty()) {
				return maxPriceProducts.stream().map(e -> this.mapper.map(e, ProductModel.class)).toList();
			}else {
				return null;
			}
	}
	
	@Override
	public Double countSumOfProductPrice() {
		double sumOfProductPrice = dao.countSumOfProductPrice();
		String formattedNumber = String.format("%.3f", sumOfProductPrice);
		return Double.parseDouble(formattedNumber);
	}
	
	@Override
	public Long getTotalCountOfProducts() {
		return dao.getTotalCountOfProducts();
	}
	

	@Override
	public ProductModel getProductByName(String productName) {
		Product product = dao.getProductByName(productName);
		if(product != null) {
			return this.mapper.map(product, ProductModel.class);
		}else {
			return null;
		}
	}

}
