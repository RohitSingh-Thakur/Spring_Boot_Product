package com.singh.base.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.singh.base.constants.GlobalConstants_ValidateCategory;
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
@Table(name = "Category_Table")
public class Category {  // Validation Done
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryId;
	
	@NotBlank(message = GlobalConstants_ValidateCategory.categoryNameIsEmpty)
	@Length(min = 2, max = 20, message = GlobalConstants_ValidateCategory.categoryNameLenght)
	@Pattern(regexp = Global_RegexConstants.stringNotContainsDigit, message = GlobalConstants_ValidateCategory.categoryNameLenght)
	@Column(nullable = false,unique = true)
	private String categoryName;
	
	
	@NotBlank(message = GlobalConstants_ValidateCategory.discriptionIsEmpty)
	@Length(min = 2, max = 100, message = GlobalConstants_ValidateCategory.discriptionLenght)
	@Column(nullable = false,unique = true)
	private String discription;
	
	@NotNull(message = GlobalConstants_ValidateCategory.discountIsEmpty)
	@Min(value = 0,message = GlobalConstants_ValidateCategory.discountIsValid)
	@Column(nullable = false)
	private Integer discount;
	
	@NotNull(message = GlobalConstants_ValidateCategory.gstIsEmpty)
	@Min(value = 0,message = GlobalConstants_ValidateCategory.gstIsValid)
	@Column(nullable = false)
	private Integer gst;
	
	@NotNull(message = GlobalConstants_ValidateCategory.deliveryChargeIsEmpty)
	@Min(value = 0,message = GlobalConstants_ValidateCategory.deliveryChargeIsValid)
	@Column(nullable = false)
	private Integer deliveryCharge;
}
