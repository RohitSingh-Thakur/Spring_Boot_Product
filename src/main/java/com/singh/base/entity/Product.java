package com.singh.base.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.singh.base.constants.GlobalConstants_ValidateProduct;
import com.singh.base.constants.Global_RegexConstants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "Product_Table")
public class Product{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long productId;
	
	@NotBlank(message = GlobalConstants_ValidateProduct.productNameIsEmpty)
	@Length(min = 2, max = 100, message = GlobalConstants_ValidateProduct.productNameLenght)
	@Pattern(regexp = Global_RegexConstants.stringNotContainsDigit, message = GlobalConstants_ValidateProduct.productNameLenght)
	@Column(nullable = false,unique = true)
	private String productName;
	
	@OneToOne
	@JoinColumn(name = "supplierId")
	private Supplier supplierId;
	
	@OneToOne
	@JoinColumn(name = "categoryId")
	private Category categoryId;
	
	@NotNull(message = GlobalConstants_ValidateProduct.productQuantityIsEmpty)
	@Min(value = 1, message = GlobalConstants_ValidateProduct.productQuantityIsValid)
	@Column(nullable = false)
	private Long productQuantity;
	
	@NotNull(message = GlobalConstants_ValidateProduct.productPriceIsEmpty)
	@Min(value = 1, message = GlobalConstants_ValidateProduct.productPriceIsValid)
	@Column(nullable = false)
	private Double productPrice;
}
