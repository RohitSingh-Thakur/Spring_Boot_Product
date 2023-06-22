package com.singh.base.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.singh.base.dao.SupplierDao;
import com.singh.base.entity.Supplier;
import com.singh.base.model.SupplierModel;
import com.singh.base.service.SupplierService;


@Service
public class SupplierServiceImpl implements SupplierService {
	
	@Autowired
	private SupplierDao dao;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public Boolean addSupplier(Supplier supplierData) {
		return dao.addSupplier(supplierData);
	}

	@Override
	public SupplierModel getSupplierById(Long supplierId) {
		Supplier supplier = dao.getSupplierById(supplierId);
		if(supplier != null) {
			return this.mapper.map(supplier, SupplierModel.class);
		}else {
			return null;	
		}
	}

	@Override
	public SupplierModel getSupplierByName(String supplierName) {
		Supplier supplier = dao.getSupplierByName(supplierName);
		if(supplier != null) {
			return this.mapper.map(supplier, SupplierModel.class);
		}else {
			return null;
		}	
	}

	@Override
	public List<SupplierModel> getAllSupplier() {
		List<Supplier> supplierList = dao.getAllSupplier();
		if(!supplierList.isEmpty()) {
			return supplierList.stream().map(e -> this.mapper.map(e, SupplierModel.class)).toList();
		}else {
			return null;	
		}
	}

}
