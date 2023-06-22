package com.singh.base.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.singh.base.dao.SupplierDao;
import com.singh.base.entity.Supplier;

@Repository
public class SupplierDaoImpl implements SupplierDao {

	@Autowired
	private SessionFactory factory;

	@Override
	public Boolean addSupplier(Supplier supplierData) {
		boolean isAdded = true;
		try (Session session = factory.openSession()) {
			session.save(supplierData);
			session.beginTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
			isAdded = false;
		} 
		return isAdded;
	}

	@Override
	public Supplier getSupplierById(Long supplierId) {
		Supplier supplier = null;
		try (Session session = factory.openSession()) {
			supplier = session.get(Supplier.class, supplierId);
		} catch (Exception e) {
			e.printStackTrace();
			supplier = null;
		}
		return supplier;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Supplier getSupplierByName(String supplierName) {
		Supplier supplier = null;
		try (Session session = factory.openSession()) {
			supplier = (Supplier) session.createCriteria(Supplier.class).add(Restrictions.ilike("supplierName", supplierName, MatchMode.ANYWHERE)).list().get(0);
		} catch (Exception e) {
			e.printStackTrace();
			supplier = null;
		}
		return supplier;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Supplier> getAllSupplier() {
		List<Supplier> supplierList = null;
		try (Session session = factory.openSession()) {
			supplierList = (List<Supplier>) session.createCriteria(Supplier.class).list();
		} catch (Exception e) {
			e.printStackTrace();
			supplierList = null;
		}
		return supplierList;
	}

}
