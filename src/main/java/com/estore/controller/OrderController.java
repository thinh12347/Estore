package com.estore.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.estore.dao.OrderDAO;
import com.estore.dao.OrderDetailDAO;
import com.estore.entity.Customer;
import com.estore.entity.Order;
import com.estore.entity.OrderDetail;
import com.estore.entity.Product;
import com.estore.service.CartService;
import com.estore.service.MailService;

@Controller
public class OrderController {
	@Autowired
	HttpSession session;
	@Autowired
	CartService cart;
	@Autowired
	OrderDAO dao;
	@Autowired
	OrderDetailDAO ddao;
	@Autowired
	MailService mailer;
	@Autowired
	HttpServletRequest request;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor editor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, editor);
	}

	@GetMapping("/order/checkout")
	public String showForm(@ModelAttribute("order") Order order) {
		Customer user = (Customer) session.getAttribute("user");
		order.setOrderDate(new Date());
		order.setCustomer(user);
		order.setAmount(cart.getAmount());
		return "order/checkout";
	}

	@PostMapping("/order/checkout")
	public String purchase(Model model, @Validated @ModelAttribute("order") Order order, BindingResult errors,
		 Customer user) throws IllegalStateException, IOException, MessagingException {
		if (errors.hasErrors()) {
			model.addAttribute("message", "Làm ơn! điền theo biểu mẫu");
			return "order/checkout";
		}
		if (order.getAmount() == 0) {
			model.addAttribute("message", "Không có sản phẩm trong giỏ hàng");
			return "order/checkout";
		}
		Collection<Product> list = cart.getItems();
		List<OrderDetail> details = new ArrayList<>();
		for (Product product : list) {
			OrderDetail detail = new OrderDetail();
			detail.setOrder(order);
			detail.setProduct(product);
			detail.setUnitPrice(product.getUnitPrice());
			detail.setQuantity(product.getQuantity());
			detail.setDiscount(product.getDiscount());
			details.add(detail);
		}
		dao.create(order, details);
		cart.clear();
		
		return "redirect:/order/list";
	}

	@GetMapping("/order/list")
	public String list(Model model) {
		Customer user = (Customer) session.getAttribute("user");
		List<Order> orders = dao.findByUser(user);
		model.addAttribute("orders", orders);
		return "order/list";
	}
	
	@GetMapping("/order/detail/{id}")
	public String detailid(Model model, @PathVariable("id") Integer id) {
		Order order = dao.findById(id);
		List<OrderDetail> details = ddao.findByOrder(order);
		model.addAttribute("order", order);
		model.addAttribute("details", details);
		return "order/detail";
	}

	@GetMapping("/order/items")
	public String items(Model model) {
		Customer user = (Customer) session.getAttribute("user");
		List<Product> list = dao.findItemsByUser(user);
		model.addAttribute("list", list);
		return "product/list";
	}
	@GetMapping("/order/delete")
	public String deleteCustomer(@RequestParam("orderId") int theId) {

		// delete the customer
		dao.delete(theId);
		return "redirect:/order/list";
	}
	@RequestMapping("/order/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		Order entity = dao.findById(id);
		model.addAttribute("order", entity);
		model.addAttribute("details", ddao.findByOrder(entity));
		model.addAttribute("list", dao.findAll());
		return "redirect:/order/list";
	}
	@RequestMapping("/order/update")
	public String update(RedirectAttributes model,@Valid @ModelAttribute("order") Order entity,BindingResult result) {
		dao.update(entity);
		model.addAttribute("message", "Cập nhật thành công!");
		return "redirect:/order/edit/" + entity.getId();
	}
	
	
	
}
