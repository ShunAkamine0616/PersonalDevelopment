package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Product;
import com.example.service.ProductService;

import util.Utility;

@Controller
public class SearchResultController {
	@Autowired
	ProductService productService;
	@Autowired
	MessageSource messageSource;

	@GetMapping("/search")
	public String search(@RequestParam("keyword") String keyword, Model model) {

		List<Product> productList = null;
		System.out.println("keyword = " + keyword);
		if (Utility.isNullOrEmpty(keyword)) {
			productList = productService.findAll("product_id");
			model.addAttribute("productList", productList);
			if(productList.size() == 0) {
				model.addAttribute("successMsg", "検索条件と十分に一致する結果が見つかりません");
			} else {
				//			model.removeAttribute("successMsg");
			}
		} else {
			productList = productService.findByKeyword(keyword, "product_id");
			//		categoryList = cService.findByKeyword(keyword);
			if(productList == null) {
				model.addAttribute("successMsg", "検索条件と十分に一致する結果が見つかりません");
			} else {
				//			model.removeAttribute("successMsg");
			}
			model.addAttribute("productList", productList);
			//		model.setAttribute("categoryList", categoryList);
		} 
		return "menu";
	}

	@GetMapping("/searchByKeyword")
	public String searchByKeyword(@RequestParam("keyword") String keyword, @RequestParam("sort") String sort, Model model) {

		List<Product> productList = null;
		System.out.println("keyword = " + keyword);
		productList = productService.findByKeyword(keyword, sort);
		//		categoryList = cService.findByKeyword(keyword);
		if(productList == null) {
			model.addAttribute("successMsg", "検索条件と十分に一致する結果が見つかりません");
		} else {
			//			model.removeAttribute("successMsg");
		}
		model.addAttribute("productList", productList);
		//		model.setAttribute("categoryList", categoryList);

		return "menu";

	}
}
