package com.example.controller;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
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
import com.example.entity.Word;
import com.example.service.UserService;
import com.example.service.WordService;

@Controller
public class InsertController {
	@Autowired
    HttpServletRequest request;
	@Autowired
	HttpSession session;
	@Autowired
	UserService userService;
	@Autowired
	MessageSource messageSource;
	@Autowired
	WordService wordService;
	
	@RequestMapping(value="InsertController", method = RequestMethod.GET)
	public String index(@ModelAttribute("insert") InsertForm insertform, Model model) {
		System.out.println("bbbbbbb");
		return "insert";
	}

	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public String result(@Validated @ModelAttribute("insert") InsertForm insertform, BindingResult bindingResult, Model model) {
		// 入力チェック
		if (bindingResult.hasErrors()) {
			return "insert";
		}
		String difficulty = request.getParameter("difficulty");
		int difficultyInt = Integer.parseInt(difficulty);
//		boolean error = false;
//		if(wordService.findById(insertform.getId()) != null) {
//			error = true;
//			session.setAttribute("insertErrMsg", "商品IDが重複しています");
//			return "insert";
//		}

		Date nowDate = new Date();
		Timestamp timestamp = new Timestamp(nowDate.getTime());
		session.setAttribute("japanese", insertform.getJapanese());
		session.setAttribute("english", insertform.getEnglish());
		session.setAttribute("difficulty", difficultyInt);
		
		Word word = new Word(insertform.getJapanese(), 
				insertform.getEnglish(), difficultyInt, timestamp, timestamp);

		if(wordService.insert(word) == 0) {
			session.setAttribute("insertMsg", "登録に失敗しました。入力内容を見直してください。");
		}
		session.setAttribute("successMsg", "登録が完了しました");
		return "wordSearch";
	}
}