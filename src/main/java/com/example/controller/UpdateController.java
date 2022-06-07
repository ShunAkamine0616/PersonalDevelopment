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

import com.example.controller.form.UpdateForm;
import com.example.entity.Word;
import com.example.service.UserService;
import com.example.service.WordService;

@Controller
public class UpdateController {
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

	@RequestMapping(value="updateInput", method = RequestMethod.GET)
	public String index(@ModelAttribute("update") UpdateForm updateform, Model model) {
		System.out.println("bbbbbbb");
		Word word = (Word) session.getAttribute("word");
		updateform.setJapanese(word.getJapanese());
		updateform.setEnglish(word.getEnglish());
		
		
		return "updateInput";
	}

	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String result(@Validated @ModelAttribute("update") UpdateForm updateform, BindingResult bindingResult, Model model) {
		// 入力チェック
		if (bindingResult.hasErrors()) {
			return "updateInput";
		}
		String difficulty = request.getParameter("difficulty");
		Integer difficultyInt = Integer.parseInt(difficulty);
		Word oldWord = (Word) session.getAttribute("word");

		Date nowDate = new Date();
		Timestamp timestamp = new Timestamp(nowDate.getTime());
		session.setAttribute("japanese", updateform.getJapanese());
		session.setAttribute("english", updateform.getEnglish());
		session.setAttribute("difficulty", difficultyInt);

		Word word = new Word(updateform.getJapanese(), updateform.getEnglish(), difficultyInt, oldWord.getCreatedAt(),
				timestamp);

		// すでにword_idが存在しているか確かめるために取得
//		Word p = wordService.findById(updateform.getWordId());
//		if(p != null) { // 同じ商品IDをもつ商品が存在するとき
//			// word_idを変更しようとして、他のレコードのword_idと重複していたら
//			if(p.getId() != oldWord.getId()) { // 他の商品ならば 
//				session.setAttribute("updateErrMsg", "IDが重複しています");
//				return "updateInput";
//			} 
//		}

		try {
			int recordNum = wordService.update(oldWord.getId(), word);
			if(recordNum == 0) {
				session.setAttribute("updateErrMsg", "更新時にエラーが発生しました");
				return "updateInput";
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			session.setAttribute("updateErrMsg", "更新時にエラーが発生しました");
			return "updateInput";
		}

		session.setAttribute("wordList", wordService.findAll("id"));
		session.setAttribute("successMsg", "更新処理が完了しました");
		return "wordSearch";
	}
}
