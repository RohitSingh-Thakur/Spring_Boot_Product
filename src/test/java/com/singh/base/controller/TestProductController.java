package com.singh.base.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.singh.base.entity.Category;
import com.singh.base.entity.Product;
import com.singh.base.entity.Supplier;
import com.singh.base.model.CategoryModel;
import com.singh.base.model.ProductModel;
import com.singh.base.model.SupplierModel;
import com.singh.base.service.ProductService;


@WebMvcTest(value = ProductController.class)
public class TestProductController {
	
	
	@MockBean // Creates a mock object for given component 
	private ProductService service;
	
	@Autowired
	private MockMvc mockMvc; // used to send request to Rest API  
	
	@Test
	@Disabled
	public void testAddProduct() throws Exception {
		
		when(service.addProduct(ArgumentMatchers.any())).thenReturn(true);
		
		Category category = new Category(12L, "Hardware", "All Hardware Products Are Available Here", 50, 12, 200);
		Supplier supplier = new Supplier(45L, "Baba Suppliers", "Mumbai", "404454", "India", "4545454545");
		
		Product product = new Product(45L, "MotherBourd", supplier, category, 0L, 1500d);
		
		ObjectMapper mapper = new ObjectMapper();
		String productJson = mapper.writeValueAsString(product); //write() to convert Object into JSon format
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addProduct")
				.contentType(MediaType.APPLICATION_JSON) //used to tell server in which format client is sending the request
				.content(productJson);
		
		ResultActions perform = mockMvc.perform(requestBuilder);
		MvcResult andReturn = perform.andReturn();
		MockHttpServletResponse response = andReturn.getResponse();
		int status = response.getStatus();
		
		assertEquals(201, status);
		
	}
	
	@Test
	public void getProductById() throws Exception {
		
		/* Long productId = 155L;
		
		CategoryModel category = CategoryModel.builder()
				.categoryId(30L).categoryName("Stationary")
				.discription("Category Products").deliveryCharge(100)
				.discount(50).gst(12).build();
		
		SupplierModel supplier = SupplierModel.builder()
				.supplierId(102L).supplierName("Pritam LTD")
				.city("Mumbai").country("India").mobileNumber("4545454545")
				.postalCode("000145").build();
		
		ProductModel product = ProductModel.builder()
				.productId(15L).categoryId(category).supplierId(supplier)
				.productName("Pencile").productPrice(250d).productQuantity(800L).build();
		
		 when(service.getProductById(productId)).thenReturn(product);
		 
		 ResultActions response = mockMvc.perform(get("/getProductById/{productId}", productId));
		 response.andExpect(status().isFound())
         .andDo(print()); <<<<<<<<<<<<<<<<<<FIRST WAY TO TEST API FOR GET>>>>>>>>>>>>>>>>>>>*/ 
         
	}

}
