package com.estore.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estore.bean.MailInfo;
import com.estore.dao.ProductDAO;
import com.estore.entity.Product;
import com.estore.service.CookieService;
import com.estore.service.MailService;

@Controller
public class ProductController {

	@Autowired
	ProductDAO dao;
	@Autowired
	CookieService cookieService;
	@Autowired
	MailService mail;
	@Autowired
	HttpSession session;

	@RequestMapping("/product/list-by-category/{cid}")
	public String listByCategory(Model model, @PathVariable("cid") Integer categoryid) {
		List<Product> list = dao.findByCategoryId(categoryid);
		model.addAttribute("list", list);
		return "product/list";
	}

	@RequestMapping("/product/list-by-keywords")
	public String listByKeywords(Model model, @RequestParam("keywords") String keywords) {
		List<Product> list = dao.findByKeywords(keywords);
		model.addAttribute("list", list);
		return "product/list";
	}

	@RequestMapping("/product/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Product product = dao.findById(id);
		model.addAttribute("prod", product);
		// Tăng số lần xem
		product.setViewCount(product.getViewCount() + 1);
		dao.update(product);
		// Hàng cùng loại
		List<Product> list = dao.findByCategoryId(product.getCategory().getId());
		model.addAttribute("list", list);
		// Hàng yêu thích
		Cookie cookie = cookieService.read("favo");
		if (cookie != null) {
			String ids = cookie.getValue();
			List<Product> favo_list = dao.findByIds(ids);
			model.addAttribute("favo", favo_list);
		}
		// Hàng đã xem
		Cookie viewed = cookieService.read("viewed");
		String value = id.toString();
		if (viewed != null) {
			value = viewed.getValue();
			value += "," + id.toString();
		}
		cookieService.create("viewed", value, 10);
		List<Product> viewed_list = dao.findByIds(value);
		model.addAttribute("viewed", viewed_list);
		return "product/detail";
	}

	@ResponseBody
	@RequestMapping("/product/add-to-favor/{id}")
	public Boolean addToFavorite(Model model, @PathVariable("id") Integer id) {
		Cookie favo = cookieService.read("favo");
		String value = id.toString();
		if (favo != null) {
			value = favo.getValue();
			if (!value.contains(id.toString())) {
				value += "," + id.toString();
			} else {
				return false;
			}
		}
		favo = cookieService.create("favo", value, 30);
		return true;
	}

	@RequestMapping("/product/list-by-special/{id}")
	public String listBySpecial(Model model, @PathVariable("id") Integer id) {
		List<Product> list = dao.findBySpecial(id);
		model.addAttribute("list", list);
		return "product/list";
	}
	
	@ResponseBody
	@RequestMapping("/product/send-to-friend")
	public Boolean sendToFriend(Model model, MailInfo info, HttpServletRequest request) {
		info.setSubject("Thông tin hàng hóa");
		try {
			String id = request.getParameter("id");
			String link = request.getRequestURL().toString().replace("send-to-friend", "detail/" + id);
			info.setBody(info.getBody() + "<hr><a href='" + link + "'>Xem chi tiết...</a>");
			mail.send(info);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
}
