package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.controller.form.LoginForm;

@Controller
public class LogoutController {
	@Autowired
	HttpSession session;
	@RequestMapping("/logout")
	public String index(@ModelAttribute("index") LoginForm loginform, Model model) {
		session.invalidate();
		return "logout";
	}
}
