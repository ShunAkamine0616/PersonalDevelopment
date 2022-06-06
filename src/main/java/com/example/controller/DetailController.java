package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Product;
import com.example.service.ProductService;

@Controller
public class DetailController {
	@Autowired
	HttpSession session;
	@Autowired
	ProductService productService;
	
	@GetMapping("/detail")
	public String search(@RequestParam("product_id") Integer productId, Model model) {
		Product product = productService.findById(productId);
		session.setAttribute("product", product);
		System.out.println("ccccccc");
		return "detail";
	}
}
