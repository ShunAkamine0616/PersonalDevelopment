package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.service.WordService;

@Controller
public class DeleteController {
	@Autowired
	HttpSession session;
	@Autowired
	WordService wordService;
	@Autowired
	MessageSource messageSource;
	
	@GetMapping("delete")
	public String delete(@RequestParam("id") String id , Model model) {
		int idInt = Integer.parseInt(id);
		if(wordService.delete(idInt) == 0) {
			session.setAttribute("deleteErrMsg", "削除に失敗しました。");
			return "detail";
		} else {
			model.addAttribute("successMsg", "削除に成功しました");
			session.setAttribute("productList", wordService.findAll("id")); 
			return "wordSearch";
		}
	}
}
