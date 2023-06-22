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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.singh.base.constants.GlobalConstants_ValidateSupplier;
import com.singh.base.constants.Global_RegexConstants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Builder
@Table(name = "Supplier_Table")
public class Supplier {   // Done Validation
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long supplierId;
	
	@NotBlank(message = GlobalConstants_ValidateSupplier.SupplierNameIsEmpty)
	@Length(min = 2, max = 20, message = GlobalConstants_ValidateSupplier.SupplierNameLength)
	@Pattern(regexp = Global_RegexConstants.stringNotContainsDigit, message = GlobalConstants_ValidateSupplier.SupplierNameLength)
	@Column(nullable = false,unique = true)
	private String supplierName;
	
	@NotBlank(message = GlobalConstants_ValidateSupplier.CityIsEmpty)
	@Length(min = 2, max = 20, message = GlobalConstants_ValidateSupplier.CityLength)
	@Pattern(regexp = Global_RegexConstants.stringNotContainsDigit, message = GlobalConstants_ValidateSupplier.CityLength)
	@Column(nullable = false)
	private String city;
	
	@NotBlank(message = GlobalConstants_ValidateSupplier.postalCodeIsEmpty)
	@Pattern(regexp = Global_RegexConstants.postalCode, message = GlobalConstants_ValidateSupplier.PostalCodeIsValid)
	@Column(nullable = false)
	private String postalCode;
	
	@NotBlank(message = GlobalConstants_ValidateSupplier.CountryIsEmpty)
	@Length(min = 2, max = 20, message = GlobalConstants_ValidateSupplier.CountryLength)
	@Pattern(regexp = Global_RegexConstants.stringNotContainsDigit, message = GlobalConstants_ValidateSupplier.CountryLength)
	@Column(nullable = false)
	private String country;
	
	@NotBlank(message = GlobalConstants_ValidateSupplier.MobileNumberIsEmpty)
	@Pattern(regexp = Global_RegexConstants.MobileNumber, message = GlobalConstants_ValidateSupplier.MobileNumberLength)
	@Column(nullable = false)
	private String mobileNumber;
}
