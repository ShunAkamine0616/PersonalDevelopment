package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Score;
import com.example.entity.Word;
import com.example.service.ScoreService;
import com.example.service.WordService;

import util.Utility;

@Controller
public class SearchResultController {
	@Autowired
	WordService wordService;
	@Autowired
	ScoreService scoreService;
	@Autowired
	MessageSource messageSource;

	@RequestMapping("/searchInput")
	public String menu(Model model) {
		return "wordSearch";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam("keyword") String keyword, Model model) {

		List<Word> wordList = null;
		System.out.println("keyword = " + keyword);
		if (Utility.isNullOrEmpty(keyword)) {
			wordList = wordService.findAll("id");
			model.addAttribute("wordList", wordList);
			if(wordList.size() == 0) {
				model.addAttribute("successMsg", "検索条件と十分に一致する結果が見つかりません");
			} else {
				//			model.removeAttribute("successMsg");
			}
		} else {
			wordList = wordService.findByKeyword(keyword, "id");
			//		categoryList = cService.findByKeyword(keyword);
			if(wordList == null) {
				model.addAttribute("successMsg", "検索条件と十分に一致する結果が見つかりません");
			} else {
				//			model.removeAttribute("successMsg");
			}
			model.addAttribute("wordList", wordList);
			//		model.setAttribute("categoryList", categoryList);
		} 
		return "wordSearch";
	}

	@GetMapping("/searchByKeyword")
	public String searchByKeyword(@RequestParam("keyword") String keyword, @RequestParam("sort") String sort, Model model) {
		List<Word> wordList = null;
		System.out.println("keyword = " + keyword);
		wordList = wordService.findByKeyword(keyword, sort);
		//		categoryList = cService.findByKeyword(keyword);
		if(wordList == null) {
			model.addAttribute("successMsg", "検索条件と十分に一致する結果が見つかりません");
		} else {
			//			model.removeAttribute("successMsg");
		}
		model.addAttribute("wordList", wordList);
		//		model.setAttribute("categoryList", categoryList);

		return "wordSearch";

	}
	
	@RequestMapping("/score")
	public String score(@RequestParam("level") Integer level, Model model) {
		List<Score> scoreList = null;
		scoreList = scoreService.findAll(level);
		//		categoryList = cService.findByKeyword(keyword);
		if(scoreList == null) {
			model.addAttribute("successMsg", "スコアが一件も見つかりません");
		} else {
			//			model.removeAttribute("successMsg");
		}
		model.addAttribute("scoreList", scoreList);
		//		model.setAttribute("categoryList", categoryList);

		return "score";
	}
	
	@GetMapping("/scoreView")
	public String scoreView(Model model) {

//		List<Score> scoreList = null;
//		scoreList = scoreService.findAll("score DESC");
//		//		categoryList = cService.findByKeyword(keyword);
//		if(scoreList == null) {
//			model.addAttribute("successMsg", "スコアが一件も見つかりません");
//		} else {
//			//			model.removeAttribute("successMsg");
//		}
//		model.addAttribute("scoreList", scoreList);
		//		model.setAttribute("categoryList", categoryList);

		return "score";

	}
}
