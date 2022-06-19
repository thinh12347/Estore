package com.estore.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Categories")
public class Category {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@NotNull
	@Pattern(regexp = "^[a-zA-Z ]{2,64}", message = "nhập loại thiệp từ 2-64 kí tự thường, in hoa")
	String name;
	@NotNull(message = "Tên không được trống")
	String nameVN;
	@JsonIgnore
	@OneToMany(mappedBy = "category")
	List<Product> products;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameVN() {
		return nameVN;
	}
	public void setNameVN(String nameVN) {
		this.nameVN = nameVN;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
