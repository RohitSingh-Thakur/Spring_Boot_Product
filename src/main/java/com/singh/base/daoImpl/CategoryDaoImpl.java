package com.singh.base.daoImpl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.singh.base.dao.CategoryDao;
import com.singh.base.entity.Category;
import com.singh.base.model.CategoryModel;

@Repository
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	private SessionFactory factory;
	
	@Override
	public Boolean addCategory(Category category) {
		Boolean isAdded = true;
		try(Session session = factory.openSession()) {
			session.save(category);
			session.beginTransaction().commit();
		} catch (PersistenceException e) {
			isAdded = false;
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		return isAdded;
	}

	@Override
	public Category getCategoryById(Long categoryId) {
		Category category = null;
		try(Session session = factory.openSession()) {
			category = session.get(Category.class, categoryId);
			if(category != null) {
				return category;
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			category = null;
		}
		return category;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Category getCategoryByName(String categoryName) {
		Category category = null;
		try(Session session = factory.openSession()) {
			Criteria criteria = session.createCriteria(Category.class);
			List<Category> list = criteria.add(Restrictions.ilike("categoryName", categoryName, MatchMode.ANYWHERE)).list();
			if(!list.isEmpty()) {
				category = (Category)list.get(0);
			}else {
				category = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			category = null;
		}
		return category;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Category> getAllCAtegory() {
		List<Category> categoryList = null;
		try(Session session = factory.openSession()) {
			
			categoryList = session.createCriteria(Category.class).list();
			if(!categoryList.isEmpty()) {
				return categoryList;
			}else {
				categoryList = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			categoryList = null;
		}
		return categoryList;
	}

}
