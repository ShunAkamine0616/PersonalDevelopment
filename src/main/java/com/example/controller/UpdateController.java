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

import com.example.controller.form.UpdateForm;
import com.example.entity.Product;
import com.example.service.ProductService;
import com.example.service.UserService;

@Controller
public class UpdateController {
	@Autowired
	HttpSession session;
	@Autowired
	UserService userService;
	@Autowired
	MessageSource messageSource;
	@Autowired
	ProductService productService;

	@RequestMapping(value="updateInput", method = RequestMethod.GET)
	public String index(@ModelAttribute("update") UpdateForm updateform, Model model) {
		System.out.println("bbbbbbb");
		Product product = (Product) session.getAttribute("product");
		updateform.setProductId(product.getProductId());
		updateform.setProductName(product.getProductName());
		updateform.setPrice(product.getPrice());
		updateform.setCategoryId(product.getCategoryId());
		updateform.setDescription(product.getDescription());
		
		return "updateInput";
	}

	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String result(@Validated @ModelAttribute("update") UpdateForm updateform, BindingResult bindingResult, Model model) {
		// 入力チェック
		if (bindingResult.hasErrors()) {
			return "updateInput";
		}
		Product oldProduct = (Product) session.getAttribute("product");

		Date nowDate = new Date();
		Timestamp timestamp = new Timestamp(nowDate.getTime());
		session.setAttribute("productId", updateform.getProductId());
		session.setAttribute("productName", updateform.getProductName());
		session.setAttribute("price", updateform.getPrice());
		session.setAttribute("description", updateform.getDescription());

		Product product = new Product(updateform.getProductId(), updateform.getCategoryId(), 
				updateform.getProductName(), updateform.getPrice(), updateform.getDescription(), timestamp);

		// すでにproduct_idが存在しているか確かめるために取得
		Product p = productService.findById(updateform.getProductId());
		if(p != null) { // 同じ商品IDをもつ商品が存在するとき
			// product_idを変更しようとして、他のレコードのproduct_idと重複していたら
			if(p.getId() != oldProduct.getId()) { // 他の商品ならば 
				session.setAttribute("updateErrMsg", "商品IDが重複しています");
				return "updateInput";
			} 
		}

		try {
			int recordNum = productService.update(oldProduct.getProductId(), product);
			if(recordNum == 0) {
				session.setAttribute("updateErrMsg", "更新時にエラーが発生しました");
				return "updateInput";
			}
			
		} catch(Exception e) {
			session.setAttribute("updateErrMsg", "更新時にエラーが発生しました");
			return "updateInput";
		}

		session.setAttribute("productList", productService.findAll("product_id"));
		session.setAttribute("successMsg", "更新処理が完了しました");
		return "menu";
	}
}
