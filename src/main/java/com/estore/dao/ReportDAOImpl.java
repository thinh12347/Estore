package com.estore.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class ReportDAOImpl implements ReportDAO {
	@Autowired
	SessionFactory factory;

	@Override
	public List<Object[]> inventory() {
		String sql = "SELECT p.category.nameVN," 
				+ "SUM(p.quantity), " 
				+ "SUM(p.unitPrice*p.quantity),"
				+ "MIN(p.unitPrice),"
				+ "MAX(p.unitPrice),"
				+ "AVG(p.unitPrice)" 
				+ "FROM Product p GROUP BY p.category.nameVN";

		Session session = factory.getCurrentSession();
		TypedQuery<Object[]> query = session.createQuery(sql, Object[].class);
		List<Object[]> list = query.getResultList();
		return list;
	}

	@Override
	public List<Object[]> revenueByCategory() {
		String sql = "SELECT d.product.category.nameVN," 
				+ "SUM(d.quantity), " 
				+ "SUM(d.unitPrice*d.quantity),"
				+ "MIN(d.unitPrice),"
				+ "MAX(d.unitPrice),"
				+ "AVG(d.unitPrice)" 
				+ "FROM OrderDetail d GROUP BY d.product.category.nameVN";

		Session session = factory.getCurrentSession();
		TypedQuery<Object[]> query = session.createQuery(sql, Object[].class);
		List<Object[]> list = query.getResultList();
		return list;
	}

	@Override
	public List<Object[]> revenueByCustomer() {
		String sql = "SELECT  d.order.customer.id," 
				+ "SUM(d.quantity), " 
				+ "SUM(d.unitPrice*d.quantity),"
				+ "MIN(d.unitPrice),"
				+ "MAX(d.unitPrice),"
				+ "AVG(d.unitPrice)" 
				+ "FROM OrderDetail d GROUP BY d.order.customer.id ORDER BY SUM(d.unitPrice*d.quantity) DESC";

		Session session = factory.getCurrentSession();
		TypedQuery<Object[]> query = session.createQuery(sql, Object[].class);
		List<Object[]> list = query.getResultList();
		return list;
	}

	@Override
	public List<Object[]> revenueByYear() {
		String sql = "SELECT  YEAR(d.order.orderDate)," 
				+ "SUM(d.quantity), " 
				+ "SUM(d.unitPrice*d.quantity),"
				+ "MIN(d.unitPrice),"
				+ "MAX(d.unitPrice),"
				+ "AVG(d.unitPrice)" 
				+ "FROM OrderDetail d GROUP BY Year(d.order.orderDate) ORDER BY YEAR(d.order.orderDate) DESC";

		Session session = factory.getCurrentSession();
		TypedQuery<Object[]> query = session.createQuery(sql, Object[].class);
		List<Object[]> list = query.getResultList();
		return list;
	}

	@Override
	public List<Object[]> revenueByQuarter() {
		String sql = "SELECT  CEILING(Month(d.order.orderDate) / 3.0)," 
				+ "SUM(d.quantity), " 
				+ "SUM(d.unitPrice*d.quantity),"
				+ "MIN(d.unitPrice),"
				+ "MAX(d.unitPrice),"
				+ "AVG(d.unitPrice)" 
				+ "FROM OrderDetail d GROUP BY CEILING(Month(d.order.orderDate) / 3.0) ORDER BY CEILING(Month(d.order.orderDate) / 3.0) DESC";

		Session session = factory.getCurrentSession();
		TypedQuery<Object[]> query = session.createQuery(sql, Object[].class);
		List<Object[]> list = query.getResultList();
		return list;
	}

	@Override
	public List<Object[]> revenueByMonth() {
		String sql = "SELECT  Month(d.order.orderDate)," 
				+ "SUM(d.quantity), " 
				+ "SUM(d.unitPrice*d.quantity),"
				+ "MIN(d.unitPrice),"
				+ "MAX(d.unitPrice),"
				+ "AVG(d.unitPrice)" 
				+ "FROM OrderDetail d GROUP BY Month(d.order.orderDate) ORDER BY Month(d.order.orderDate) DESC";

		Session session = factory.getCurrentSession();
		TypedQuery<Object[]> query = session.createQuery(sql, Object[].class);
		List<Object[]> list = query.getResultList();
		return list;
	}

}
