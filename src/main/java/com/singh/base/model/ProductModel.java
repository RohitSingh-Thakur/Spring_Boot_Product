package com.singh.base.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {
	private Long productId;
	private String productName;
	private SupplierModel supplierId;
	private CategoryModel categoryId;
	private Long productQuantity;
	private Double productPrice;
}
