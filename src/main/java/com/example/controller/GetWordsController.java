package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Word;
import com.example.service.ScoreService;
import com.example.service.WordService;

@RestController
public class GetWordsController {
	@Autowired
	WordService wordService;
	@Autowired
	ScoreService scoreService;
	@Autowired
	MessageSource messageSource;

//	@GetMapping("/getWords")
//	public List<Word> search(Model model) {
//
//		List<Word> wordList = null;
//		wordList = wordService.findAll("id");
//		return wordList;
//	}
	
	@GetMapping("/getWords")
	public Map<String,String> search(Model model) {
		Map<String, String> map = new HashMap<>();
		List<Word> wordList = null;
		wordList = wordService.findAll("id");
		for(Word w: wordList) {
			map.put(w.getJapanese(), w.getEnglish());
		}
		return map;
	}
	
	@GetMapping("/socreInsert")
	public int scoreInsert(@RequestParam("loginId") String loginId, @RequestParam("score") String score,Model model) {
		Integer scoreInt = Integer.parseInt(score);
		return scoreService.insert(loginId, scoreInt);
	}
}
