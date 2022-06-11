package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Word;
import com.example.service.WordService;

@Controller
public class DetailController {
	@Autowired
	HttpSession session;
	@Autowired
	WordService wordService;
	
	@GetMapping("detail")
	public String search(@RequestParam("id") Integer id, Model model) {
		Word word = wordService.findById(id);
		session.setAttribute("word", word);
		return "detail";
	}
}
