package com.estore.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estore.entity.Product;

@Transactional
@Repository
public class ProductDAOImpl implements ProductDAO {
	@Autowired
	SessionFactory factory;

	@Override
	public Product findById(Integer id) {
		Session session = factory.getCurrentSession();
		Product entity = session.find(Product.class, id);
		return entity;
	}

	@Override
	public List<Product> findAll() {
		String hql = "from Product";
		Session session = factory.getCurrentSession();
		TypedQuery<Product> query = session.createQuery(hql, Product.class);
		List<Product> list = query.getResultList();
		return list;
	}

	@Override
	public Product create(Product entity) {
		Session session = factory.getCurrentSession();
		session.save(entity);
		return entity;
	}

	@Override
	public void update(Product entity) {
		Session session = factory.getCurrentSession();
		session.update(entity);

	}

	@Override
	public Product delete(Integer id) {
		Session session = factory.getCurrentSession();
		Product entity = session.find(Product.class, id);
		session.delete(entity);
		return entity;
	}

	@Override
	public List<Product> findByCategoryId(Integer categoryid) {
		String hql = "from Product p where p.category.id=:cid";
		Session session = factory.getCurrentSession();
		TypedQuery<Product> query = session.createQuery(hql, Product.class);
		query.setParameter("cid", categoryid);
		List<Product> list = query.getResultList();
		return list;
	}

	@Override
	public List<Product> findByKeywords(String keywords) {
		String hql = "from Product p where p.name Like :kw OR  p.category.name LIKE :kw OR  p.category.nameVN LIKE :kw ";
		Session session = factory.getCurrentSession();
		TypedQuery<Product> query = session.createQuery(hql, Product.class);
		query.setParameter("kw", "%" + keywords + "%");
		List<Product> list = query.getResultList();
		return list;
	}

	@Override
	public List<Product> findByIds(String ids) {
		String hql = "from Product p Where p.id in (" + ids + ")";
		Session session = factory.getCurrentSession();
		TypedQuery<Product> query = session.createQuery(hql, Product.class);
		List<Product> list = query.getResultList();
		return list;
	}

	@Override
	public List<Product> findBySpecial(Integer id) {
		Session session = factory.getCurrentSession();
		String hql = "from Product p";
		TypedQuery<Product> query = session.createQuery(hql, Product.class);
		switch (id) {
		case 0://Bán chạy
			hql = "from Product p ORDER BY size(p.orderDetails) DESC";
			break;
		case 1://Xem nhiều
			hql = "from Product p ORDER BY p.viewCount DESC";
			break;
		case 2://giảm giá
			hql = "from Product p where p.discount > 0";
			break;
		case 3://đặc biệt
			hql = "from Product p WHERE p.special=true";
			break;
		}
		query = session.createQuery(hql, Product.class);
		query.setMaxResults(12);
		List<Product> list = query.getResultList();
		return list;
	}

	@Override
	public long getPageCount(int pageSize) {
		String hql = "select count(p) FROM Product p";
		Session session = factory.getCurrentSession();
		TypedQuery<Long> query = session.createQuery(hql, Long.class);
		Long rowCount = query.getSingleResult();
		long pageCount = (long) Math.ceil(1.0 * rowCount / pageSize);
		return pageCount;
	}

	@Override
	public List<Product> getPage(int pageNo, int pageSize) {
		String hql = "from Product";
		Session session = factory.getCurrentSession();
		TypedQuery<Product> query = session.createQuery(hql, Product.class);
		query.setFirstResult(pageNo*pageSize);
		query.setMaxResults(pageSize);
		List<Product> list = query.getResultList();
		return list;
	}
}
