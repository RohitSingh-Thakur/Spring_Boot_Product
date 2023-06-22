package com.singh.base.dao;

import java.util.List;

import com.singh.base.entity.Supplier;

public interface SupplierDao {
	public Boolean addSupplier(Supplier supplier);
	public Supplier getSupplierById(Long supplierId);
	public Supplier getSupplierByName(String supplierName);
	public List<Supplier> getAllSupplier();
}
