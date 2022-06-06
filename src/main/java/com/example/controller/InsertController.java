package com.example.controller;

import java.sql.Timestamp;
import java.util.Date;

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

import com.example.controller.form.InsertForm;
import com.example.entity.Product;
import com.example.service.ProductService;
import com.example.service.UserService;

@Controller
public class InsertController {
	@Autowired
	HttpSession session;
	@Autowired
	UserService userService;
	@Autowired
	MessageSource messageSource;
	@Autowired
	ProductService productService;
	
	@RequestMapping(value="InsertController", method = RequestMethod.GET)
	public String index(@ModelAttribute("insert") InsertForm insertform, Model model) {
		System.out.println("bbbbbbb");
		return "insert";
	}

	@RequestMapping(value = "insert", method = RequestMethod.GET)
	public String result(@Validated @ModelAttribute("insert") InsertForm insertform, BindingResult bindingResult, Model model) {
		// 入力チェック
		if (bindingResult.hasErrors()) {
			return "insert";
		}
		boolean error = false;
		if(productService.findById(insertform.getProductId()) != null) {
			error = true;
			session.setAttribute("insertErrMsg", "商品IDが重複しています");
			return "insert";
		}

		Date nowDate = new Date();
		Timestamp timestamp = new Timestamp(nowDate.getTime());
		session.setAttribute("productId", insertform.getProductId());
		session.setAttribute("productName", insertform.getProductName());
		session.setAttribute("price", insertform.getPrice());
		session.setAttribute("description", insertform.getDescription());
		
		Product product = new Product(insertform.getProductId(), insertform.getCategoryId(), 
				insertform.getProductName(), insertform.getPrice(), insertform.getDescription(), timestamp);

		productService.insert(product);
		session.setAttribute("successMsg", "登録が完了しました");
		return "menu";
	}
}