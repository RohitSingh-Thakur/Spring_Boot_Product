package com.singh.base.service;

import java.util.List;

import com.singh.base.entity.Supplier;
import com.singh.base.model.SupplierModel;

public interface SupplierService {
	public Boolean addSupplier(Supplier supplier);
	public SupplierModel getSupplierById(Long supplierId);
	public SupplierModel getSupplierByName(String supplierName);
	public List<SupplierModel> getAllSupplier();
}
