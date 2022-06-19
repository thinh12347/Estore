package com.estore.dao;

import java.util.List;

import com.estore.entity.Product;

public interface ProductDAO {
	Product findById(Integer id);
	List<Product> findAll();
	Product create(Product entity);
	void update(Product entity);
	Product delete(Integer id);
	List<Product> findByCategoryId(Integer categoryid);
	List<Product> findByKeywords(String keywords);
	List<Product> findByIds(String ids);
	List<Product> findBySpecial(Integer id);
	long getPageCount(int pageSize);
	List<Product> getPage(int pageNo, int pageSize);
}
