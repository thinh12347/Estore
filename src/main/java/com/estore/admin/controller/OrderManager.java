package com.estore.admin.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.estore.dao.OrderDAO;
import com.estore.dao.OrderDetailDAO;
import com.estore.entity.Order;

@Controller
public class OrderManager {
	@Autowired
	OrderDAO dao;
	@Autowired
	OrderDetailDAO ddao;

//	@InitBinder
//	public void initBinder(WebDataBinder dataBinder) {
//		StringTrimmerEditor editor = new StringTrimmerEditor(true);
//		dataBinder.registerCustomEditor(String.class, editor);
//	}

	@RequestMapping("/admin/order/index")
	public String index(Model model) {
		Order entity = new Order();
		model.addAttribute("entity", entity);
		model.addAttribute("details", ddao.findByOrder(entity));
		model.addAttribute("list", dao.findAll());
		return "admin/order/index";
	}

	@RequestMapping("/admin/order/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		Order entity = dao.findById(id);
		model.addAttribute("entity", entity);
		model.addAttribute("details", ddao.findByOrder(entity));
		model.addAttribute("list", dao.findAll());
		return "admin/order/index";
	}

	@RequestMapping("/admin/order/create")
	public String create(RedirectAttributes model, @ModelAttribute("entity") Order entity) {
		dao.create(entity);
		model.addAttribute("message", "Thêm mới thành công!");
		return "redirect:/admin/order/index";
	}

	@RequestMapping("/admin/order/update")
	public String update(RedirectAttributes model,@Valid @ModelAttribute("entity") Order entity,BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("message", "Please fix some following errors");
			return "admin/order/index";
		}
		dao.update(entity);
		model.addAttribute("message", "Cập nhật thành công!");
		return "redirect:/admin/order/edit/" + entity.getId();
	}

	@RequestMapping(value = { "/admin/order/delete", "/admin/order/delete/{id}" })
	public String delete(Model model, @RequestParam(value = "id", required = false) Integer id1,
			@PathVariable(value = "id", required = false) Integer id2) {
		if (id1 != null) {
			dao.delete(id1);
		} else {
			dao.delete(id2);
		}
		model.addAttribute("message", "Xóa thành công!");
		return "redirect:/admin/order/index";
	}

	int pageSize = 8;

	@ResponseBody
	@RequestMapping("/pager/order/page-count")
	public long pageCount() {
		return dao.getPageCount(pageSize);
	}

	@ResponseBody
	@RequestMapping("/pager/order/page/{pageNo}")
	public List<Order> getPage(@PathVariable("pageNo") int pageNo) {
		List<Order> list = dao.getPage(pageNo, pageSize);
		return list;
	}
}
