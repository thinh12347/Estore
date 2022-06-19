package com.estore.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Customers")
public class Customer {
	@Id
	@NotNull(message = "Email không được để trống")
	@Email(message = "Nhập đúng đinh dạng email")
	String id;
	@NotNull(message = "Mật khẩu không để trống")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,64}$", message = "Nhập password từ 6-64 ký tự, ít nhất một chữ hoa, một chữ thường và một số")
	String password;
	@NotNull(message = "Họ và tên không được để trống")
	@Pattern(regexp = "^[a-zA-Z ]{1,64}", message = "nhập họ tên từ 6-16 kí tự thường, in hoa")
	String fullname;
	String photo;
	@NotNull(message = "Địa chỉ không được để trống")
	String Diachi;
	Boolean activated;
	Boolean admin;
	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	List<Order> orders;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getDiachi() {
		return Diachi;
	}
	public void setDiachi(String diachi) {
		Diachi = diachi;
	}
	public Boolean getActivated() {
		return activated;
	}
	public void setActivated(Boolean activated) {
		this.activated = activated;
	}
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	

}
