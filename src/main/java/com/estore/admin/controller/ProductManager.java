package com.estore.admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.estore.dao.ProductDAO;
import com.estore.entity.Product;

@Controller
public class ProductManager {
	@Autowired
	ProductDAO dao;
	@Autowired
	ServletContext app;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor editor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, editor);
	}

	@RequestMapping("/admin/product/index")
	public String index(Model model) {
		Product entity = new Product();
		model.addAttribute("entity", entity);
		model.addAttribute("list", dao.findAll());
		return "admin/product/index";
	}

	@RequestMapping("/admin/product/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {

		Product entity = dao.findById(id);
		model.addAttribute("entity", entity);
		model.addAttribute("list", dao.findAll());

		return "admin/product/index";
	}

	@RequestMapping("/admin/product/create")
	public String create(RedirectAttributes model, @Validated @ModelAttribute("entity") Product entity,
			BindingResult errors, @RequestParam("image_file") MultipartFile file)
			throws IllegalStateException, IOException {
		if (errors.hasErrors()) {
			model.addAttribute("message", "Please fix some following errors");
			return "admin/product/index";
		}

		if (file.isEmpty()) {
			entity.setImage("adduser.png");
		} else {
			entity.setImage(file.getOriginalFilename());
			String path = app.getRealPath("/static/images/product/" + entity.getImage());
			file.transferTo(new File(path));
		}
		entity.setViewCount(0);
		entity.setQuantity(0);
		dao.create(entity);
		model.addAttribute("message", "Thêm mới thành công!");
		return "redirect:/admin/product/index";
	}

	@RequestMapping("/admin/product/update")
	public String update(RedirectAttributes model, @Valid @ModelAttribute("entity") Product entity,
			BindingResult errors, @RequestParam("image_file") MultipartFile file)
			throws IllegalStateException, IOException {
		if (errors.hasErrors()) {
			model.addAttribute("message", "Please fix some following errors");
			return "admin/product/index";
		}
		if (!file.isEmpty()) {
			String dir = app.getRealPath("/static/images/product");
			File f = new File(dir, file.getOriginalFilename());
			file.transferTo(f);
			entity.setImage(f.getName());
		}
		entity.getProductDate();
		entity.getViewCount();
		dao.update(entity);
		model.addAttribute("message", "Cập nhật thành công!");
		return "redirect:/admin/product/edit/" + entity.getId();
	}

	@RequestMapping(value = { "/admin/product/delete", "/admin/product/delete/{id}" })
	public String delete(Model model, @RequestParam(value = "id", required = false) Integer id1,
			@PathVariable(value = "id", required = false) Integer id2) {
		if (id1 != null) {
			dao.delete(id1);
		} else {
			dao.delete(id2);
		}
		model.addAttribute("message", "Xóa thành công!");
		return "redirect:/admin/product/index";
	}

	int pageSize = 8;

	@ResponseBody
	@RequestMapping("/pager/product/page-count")
	public long pageCount() {
		return dao.getPageCount(pageSize);
	}

	@ResponseBody
	@RequestMapping("/pager/product/page/{pageNo}")
	public List<Product> getPage(@PathVariable("pageNo") int pageNo) {
		List<Product> list = dao.getPage(pageNo, pageSize);
		return list;
	}
}
