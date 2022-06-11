package com.example.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.controller.form.LoginForm;
import com.example.controller.form.RegisterForm;
import com.example.entity.User;
import com.example.service.CategoryService;
import com.example.service.ProductService;
import com.example.service.UserService;

@Controller
public class LoginController {
	@Autowired
	HttpSession session;
	@Autowired
	UserService userService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	MessageSource messageSource;
	@Autowired
	ProductService productService;
	
	@RequestMapping("/index")
	public String index(@ModelAttribute("index") LoginForm loginform, Model model) {
		System.out.println("aaaaaaaa");
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String result(@Validated @ModelAttribute("index") LoginForm loginform, BindingResult bindingResult, Model model) {
		// 入力チェック
		if (bindingResult.hasErrors()) {
			return "index";
		}
		User user = userService.findByUserIdAndPass(loginform.getLoginId(), loginform.getPassword());
		// ログイン失敗
		if(user == null) {
			String errMsg = messageSource.getMessage("login.error", null, Locale.getDefault());
			model.addAttribute("loginErrMsg", errMsg);
			return "index";
		} else {
			session.setAttribute("user", user);
			return "menu";
		}
	}
	
	@RequestMapping("back")
	public String index(Model model) {
		System.out.println("aaaaaaaa");
		return "menu";
	}
	
	@RequestMapping("login")
	public String menu(@ModelAttribute("index") LoginForm loginform, Model model) {
		return "menu";
	}
	
	@RequestMapping("loginForm")
	public String loginform(@ModelAttribute("index") LoginForm loginform, Model model) {
		return "index";
	}
	
	@RequestMapping(value="registerInput", method = RequestMethod.GET)
	public String registerInput(@ModelAttribute("register") RegisterForm registerform, Model model) {
		return "userRegistration";
	}
	
	@RequestMapping(value="register", method = RequestMethod.POST)
	public String register(@Validated @ModelAttribute("register") RegisterForm registerform, BindingResult bindingResult, Model model) {
		// 入力チェック
		if (bindingResult.hasErrors()) {
			return "userRegistration";
		}
		if(userService.findByUserId(registerform.getLoginId()) != null) {
			model.addAttribute("registerErrMsg", "そのログインIDは既に存在します。");
			return "userRegistration";
		}
		userService.register(registerform.getLoginId(), registerform.getPassword(), registerform.getName());
		User user =userService.findByUserIdAndPass(registerform.getLoginId(), registerform.getPassword());
		
		// ユーザー登録失敗
		if(user == null) {
			String errMsg = messageSource.getMessage("register.error", null, Locale.getDefault());
			model.addAttribute("registerErrMsg", errMsg);
			return "index";
		} else {
			session.setAttribute("user", user);
			return "menu";
		}
	}
	
	
	@RequestMapping("/title")
	public String title(Model model) {
		System.out.println("aaaaaaaa");
		return "title";
	}
	
	@RequestMapping("/menu")
	public String menu(Model model) {
		System.out.println("aaaaaaaa");
		return "menu";
	}
}
