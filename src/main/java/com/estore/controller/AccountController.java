package com.estore.controller;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.estore.bean.MailInfo;
import com.estore.dao.CustomerDAO;
import com.estore.entity.Customer;
import com.estore.service.CookieService;
import com.estore.service.MailService;

@Controller
public class AccountController {
	@Autowired
	CustomerDAO dao;
	@Autowired
	HttpSession session;
	@Autowired
	CookieService cookieService;
	@Autowired
	ServletContext app;
	@Autowired
	MailService mailer;
	@Autowired
	HttpServletRequest request;

	@GetMapping("/account/login")
	public String login(Model model) {
		Cookie ckid = cookieService.read("userid");
		Cookie ckpw = cookieService.read("pass");
		if (ckid != null) {
			String uid = ckid.getValue();
			String pwd = ckpw.getValue();
			model.addAttribute("uid", uid);
			model.addAttribute("pwd", pwd);
		}
		return "account/login";
	}

	@PostMapping("/account/login")
	public String login(Model model, @RequestParam("id") String id, @RequestParam("pw") String pw,
			@RequestParam(value = "rm", defaultValue = "false") boolean rm) {
		Customer customer = dao.findById(id);

		if (customer == null) {
			model.addAttribute("message", "Email ch??a ????ng k??");
		} else if (!pw.equals(customer.getPassword())) {
			model.addAttribute("message", "Password kh??ng h???p l???");
		} else if (!customer.getActivated()) {
			model.addAttribute("message", "T??i kho???n c???a b???n ch??a ???????c k??ch ho???t");
		} else {
			model.addAttribute("message", "????ng nh???p th??nh c??ng");
			session.setAttribute("user", customer);
			if (customer.getAdmin() == true) {
				return "admin/home/index";
			}
			// Ghi nho tai khoan bang cookie
			else if (rm == true) {
				cookieService.create("userid", customer.getId(), 30);
				cookieService.create("pass", customer.getPassword(), 30);
			} else {
				cookieService.delete("userid");
				cookieService.delete("pass");
			}
			String backUrl = (String) session.getAttribute("back-url");
			if (backUrl != null) {
				return "redirect:" + backUrl;
			}
		}
		return "redirect:/home/index";
	}

	@RequestMapping("/account/logoff")
	public String logoff(Model model) {
		session.removeAttribute("user");
		return "redirect:/home/index";
	}

	@GetMapping("/account/register")
	public String register(Model model) {
		Customer user = new Customer();
		model.addAttribute("form", user);
		return "account/register";
	}

	@PostMapping("/account/register")
	public String register(Model model, @Validated @ModelAttribute("form") Customer user, BindingResult errors,
			@RequestParam("photo_file") MultipartFile file)
			throws IllegalStateException, IOException, MessagingException {
		if (errors.hasErrors()) {
			model.addAttribute("message", "L??m ??n! ??i???n theo bi???u m???u");
			return "account/register";
		} else {
			Customer user2 = dao.findById(user.getId());

			if (user2 != null) {
				model.addAttribute("message", "Email ???? ???????c s??? d???ng");
				return "account/register";
			} else if (user.getId() == null) {
				model.addAttribute("message", "Email kh??ng ???????c b??? tr???ng ");
				return "account/register";
			}
		}

		if (file.isEmpty()) {
			user.setPhoto("user.png");
		} else {
			String dir = app.getRealPath("/static/images/customers");
			File f = new File(dir, file.getOriginalFilename());
			file.transferTo(f);
			user.setPhoto(f.getName());
		}
		user.setActivated(false);
		user.setAdmin(false);
		dao.create(user);
		model.addAttribute("message", "????ng k?? th??nh c??ng");
		String from = "nguyenduythinh0101999@gmail.com";
		String to = user.getId();
		String subject = "Welcome";
		String url = request.getRequestURL().toString().replace("register", "activate/" + user.getId());
		String body = "Click <a href='" + url + "'>K??ch ho???t t??i kho???n</a>";
		MailInfo mail = new MailInfo(from, to, subject, body);
		mailer.send(mail);
		return "account/register";
	}

	@GetMapping("/account/activate/{id}")
	public String activate(Model model, @PathVariable("id") String id) {
		Customer user = dao.findById(id);
		user.setActivated(true);
		dao.update(user);
		return "redirect:/account/login";
	}

	@GetMapping("/account/forgot")
	public String forgot(Model model) {
		return "account/forgot";
	}

	@PostMapping("/account/forgot")
	public String forgot(Model model, @RequestParam("id") String id) throws MessagingException {
		Customer user = dao.findById(id);

		if (user == null) {
			model.addAttribute("message", "Email kh??ng h???p l???");
		} else {
			String from = "nguyenduythinh0101999@gmail.com";
			String to = user.getId();
			String subject = "Qu??n m???t kh???u";
			String body = "M???t kh???u c???a b???n l?? " + user.getPassword();
			MailInfo mail = new MailInfo(from, to, subject, body);
			mailer.send(mail);
			model.addAttribute("message", "M???t kh???u c???a b???n ???? ???????c g???i ?????n email c???a b???n");
		}
		return "account/forgot";
	}

	@GetMapping("/account/change")
	public String change(Model model) {
		Customer user = (Customer) session.getAttribute("user");
		model.addAttribute("form", user);
		return "account/change";
	}

	@PostMapping("/account/change")
	public String change(Model model, @ModelAttribute("form") Customer user, @RequestParam("id") String id,BindingResult errors,
			@RequestParam("pw1") String pw1, @RequestParam("pw2") String pw2, @RequestParam("pw") String pw) {
		if (errors.hasErrors()) {
			model.addAttribute("message", "L??m ??n! ??i???n theo bi???u m???u");
			return "account/change";
		}else {
		if (!pw1.equals(pw2)) {
			model.addAttribute("message", "X??c nh???n m???t kh???u kh??ng kh???p");
		} else {
			user = dao.findById(id);
			if (!pw.equals(user.getPassword())) {
				model.addAttribute("message", "M???t kh???u hi???n t???i kh??ng h???p l??? ");
			} else if (pw1.equals("")) {
				model.addAttribute("message", "M???t kh???u m???i kh??ng h???p l???");
			} else if (pw2.equals("")) {
				model.addAttribute("message", "M???t kh???u x??c nh???n kh??ng h???p l???");
			} else {
				user.setPassword(pw1);
				dao.update(user);
				model.addAttribute("message", "Thay ?????i m???t kh???u th??nh c??ng");
			}
			}
		}

		return "account/change";
	}

	@GetMapping("/account/edit")
	public String edit(Model model) {
		Customer user = (Customer) session.getAttribute("user");
		model.addAttribute("form", user);
		return "account/edit";
	}

	@PostMapping("/account/edit")
	public String edit(Model model, @Validated @ModelAttribute("form") Customer user, BindingResult errors,
			@RequestParam("photo_file") MultipartFile file) throws IllegalStateException, IOException {
		if (errors.hasErrors()) {
			model.addAttribute("message", "L??m ??n! ??i???n theo bi???u m???u");
			return "account/edit";
		}
		if (!file.isEmpty()) {
			String dir = app.getRealPath("/static/images/customers");
			File f = new File(dir, file.getOriginalFilename());
			file.transferTo(f);
			user.setPhoto(f.getName());
		}
		dao.update(user);
		session.setAttribute("user", user);

		model.addAttribute("message", "C???p nh???t t??i kho???n th??nh c??ng");
		return "account/edit";
	}

}
