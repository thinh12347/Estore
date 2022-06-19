package com.estore.dao;

import java.util.List;

import com.estore.entity.Customer;
import com.estore.entity.Order;
import com.estore.entity.OrderDetail;
import com.estore.entity.Product;

public interface OrderDAO {
	Order findById(Integer id);
	List<Order> findAll();
	Order create(Order entity);
	void update(Order entity);
	Order delete(Integer id);
	void create(Order order, List<OrderDetail> details);
	List<Order> findByUser(Customer user);
	List<Product> findItemsByUser(Customer user);
	long getPageCount(int pageSize);
	List<Order> getPage(int pageNo, int pageSize);
}
