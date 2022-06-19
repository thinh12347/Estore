package com.estore.admin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.estore.dao.CustomerDAO;
import com.estore.service.MailService;

@Controller
public class AdminHomeController {
	@Autowired
	CustomerDAO dao;
	@Autowired
	HttpSession session;
	@Autowired
	MailService mailer;

	@RequestMapping("/admin/home/index")
	public String index() {
		return "redirect:/account/login";
	}


}
