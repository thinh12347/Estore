package com.estore.admin.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.estore.dao.CategoryDAO;
import com.estore.entity.Category;

@Controller
public class CategoryManager {
	@Autowired
	CategoryDAO dao;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor editor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, editor);
	}

	@RequestMapping("/admin/category/index")
	public String index(Model model) {
		Category entity = new Category();
		model.addAttribute("entity", entity);
		model.addAttribute("list", dao.findAll());
		return "admin/category/index";

	}

	@RequestMapping("/admin/category/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		Category entity = dao.findById(id);
		model.addAttribute("entity", entity);
		model.addAttribute("list", dao.findAll());
		return "admin/category/index";
	}

	@RequestMapping("/admin/category/create")
	public String create(RedirectAttributes model, @Valid @ModelAttribute("entity") Category entity,
			BindingResult result) {
		if (result.hasErrors()) {
			return "admin/category/index";
		}
		dao.create(entity);
		model.addAttribute("message", "Thêm mới thành công!");
		return "redirect:/admin/category/index";
	}

	@RequestMapping("/admin/category/update")
	public String update(RedirectAttributes model, @Valid @ModelAttribute("entity") Category entity,
			BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("message", "Please fix some following errors");
			return "admin/category/index";
		}
		dao.update(entity);
		model.addAttribute("message", "Cập nhật thành công!");
		return "redirect:/admin/category/edit/" + entity.getId();
	}

	@RequestMapping(value = { "/admin/category/delete", "/admin/category/delete/{id}" })
	public String delete(Model model, @RequestParam(value = "id", required = false) Integer id1,
			@PathVariable(value = "id", required = false) Integer id2) {
		if (id1 != null) {
			dao.delete(id1);
		} else {
			dao.delete(id2);
		}
		model.addAttribute("message", "Xóa thành công!");
		return "redirect:/admin/category/index";
	}

	int pageSize = 8;

	@ResponseBody
	@RequestMapping("/pager/category/page-count")
	public long pageCount() {
		return dao.getPageCount(pageSize);
	}

	@ResponseBody
	@RequestMapping("/pager/category/page/{pageNo}")
	public List<Category> getCount(@PathVariable("pageNo") int pageNo) {
		List<Category> list = dao.getPage(pageNo, pageSize);
		return list;
	}
}
