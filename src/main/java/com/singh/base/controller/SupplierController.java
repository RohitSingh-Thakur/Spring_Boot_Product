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

import com.singh.base.constants.GlobalHttpRequest_Supplier;
import com.singh.base.constants.Global_ExceptionConstants;
import com.singh.base.entity.Supplier;
import com.singh.base.exceptions.NoRecordFoundByIdException;
import com.singh.base.exceptions.NoRecordFoundWithGivenName;
import com.singh.base.exceptions.NoRecordsFound;
import com.singh.base.exceptions.RecordAlreadyExistException;
import com.singh.base.model.SupplierModel;
import com.singh.base.service.SupplierService;

@RestController
public class SupplierController {
	
	@Autowired
	private SupplierService service;
	
	@PostMapping(GlobalHttpRequest_Supplier.ADD_SUPPLIER)
	public ResponseEntity<String> addSupplier(@Valid @RequestBody Supplier supplierData){
		
		boolean isAdded = service.addSupplier(supplierData);
		if (isAdded) {
			return new ResponseEntity<String>("Saved !", HttpStatus.CREATED);
		} else {
			throw new RecordAlreadyExistException(Global_ExceptionConstants.RECORD_ALREADY_FOUND_OF_SUPPLIER_EXCEPTION);
		}
	}

	@GetMapping(GlobalHttpRequest_Supplier.GET_SUPPLIER_BY_ID)
	public ResponseEntity<SupplierModel> getSupplierById(@PathVariable Long supplierId) {
		SupplierModel supplierModel = service.getSupplierById(supplierId);
		if (supplierModel != null) {
			return new ResponseEntity<SupplierModel>(supplierModel, HttpStatus.FOUND);
		} else {
			throw new NoRecordFoundByIdException(Global_ExceptionConstants.NO_RECORD_FOUND_FOR_GIVEN_SUPPLIER_ID_EXCEPTION);
		}
	}
	
	@GetMapping(GlobalHttpRequest_Supplier.GET_SUPPLIER_BY_NAME) 
	public ResponseEntity<SupplierModel> getSupplierByName(@RequestParam String supplierName) {
		SupplierModel supplierModel = service.getSupplierByName(supplierName);
		if (supplierModel != null) {
			return new ResponseEntity<SupplierModel>(supplierModel, HttpStatus.FOUND);
		} else {
			throw new NoRecordFoundWithGivenName(Global_ExceptionConstants.NO_RECORD_FOUND_FOR_GIVEN_SUPPLIER_NAME_EXCEPTION);
		}
	}
	
	@GetMapping(GlobalHttpRequest_Supplier.GET_ALL_SUPPLIER) 
	public ResponseEntity<List<SupplierModel>> getAllSupplier() {
		List<SupplierModel> listModels = service.getAllSupplier();
		if (!listModels.isEmpty()) {
			return new ResponseEntity<List<SupplierModel>>(listModels, HttpStatus.FOUND);
		} else {
			throw new NoRecordsFound(Global_ExceptionConstants.EMPTY_SUPPLIER_EXCEPTION);
		}
	}
}
