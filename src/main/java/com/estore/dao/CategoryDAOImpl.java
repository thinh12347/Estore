package com.estore.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.estore.entity.Category;

@Transactional
@Repository
public class CategoryDAOImpl implements CategoryDAO {
	@Autowired
	SessionFactory factory;

	@Override
	public Category findById(Integer id) {
		Session session = factory.getCurrentSession();
		Category entity=session.find(Category.class, id);
		return entity;
	}

	@Override
	public List<Category> findAll() {
		String sql="from Category";
		Session session = factory.getCurrentSession();
		TypedQuery<Category> query = session.createQuery(sql,Category.class);
		List<Category> list = query.getResultList();
		return list;
	}

	@Override
	public Category create(Category entity) {
		Session session = factory.getCurrentSession();
		session.save(entity);
		return entity;
	}

	@Override
	public void update(Category entity) {
		Session session = factory.getCurrentSession();
		session.update(entity);

	}

	@Override
	public Category delete(Integer id) {
		Session session = factory.getCurrentSession();
		Category entity = session.find(Category.class, id);
		session.delete(entity);
		return entity;
	}

	@Override
	public long getPageCount(int pageSize) {
		String sql = "select count(c) FROM Category  c";
		Session session = factory.getCurrentSession();
		TypedQuery<Long> query = session.createQuery(sql, Long.class);
		Long rowCount = query.getSingleResult();
		long pageCount = (long) Math.ceil(1.0 * rowCount / pageSize);
		return pageCount;
	}

	@Override
	public List<Category> getPage(int pageNo, int pageSize) {
		String sql = "from Category ";
		Session session = factory.getCurrentSession();
		TypedQuery<Category> query = session.createQuery(sql, Category.class);
		query.setFirstResult(pageNo*pageSize);
		query.setMaxResults(pageSize);
		List<Category> list = query.getResultList();
		return list;
	}

}
