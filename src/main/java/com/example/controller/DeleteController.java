package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.service.ProductService;

@Controller
public class DeleteController {
	@Autowired
	HttpSession session;
	@Autowired
	ProductService productService;
	@Autowired
	MessageSource messageSource;
	
	@GetMapping("/delete")
	public String delete(@RequestParam("productId") String productId , Model model) {
		int productIdInt = Integer.parseInt(productId);
		if(productService.delete(productIdInt) == 0) {
			session.setAttribute("deleteErrMsg", "削除に失敗しました。");
			return "detail";
		} else {
			session.setAttribute("successMsg", "削除に成功しました");
			session.setAttribute("productList", productService.findAll("product_id")); 
			return "menu";
		}
	}
}
