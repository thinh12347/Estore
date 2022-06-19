package com.estore.dao;

import java.util.List;

import com.estore.entity.Customer;

public interface CustomerDAO {
	Customer findById(String id);
	List<Customer> findAll();
	Customer create(Customer entity);
	void update(Customer entity);
	Customer delete(String id);
	long getPageCount(int pageSize);
	List<Customer> getPage(int pageNo, int pageSize);
	Customer findByEmail(String email);
}
