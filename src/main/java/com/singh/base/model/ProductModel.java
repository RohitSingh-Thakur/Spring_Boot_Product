package com.singh.base.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Builder
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
