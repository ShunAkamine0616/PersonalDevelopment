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
	public List<Word> search(@RequestParam("level") String level, Model model) {
		Map<String, String> map = new HashMap<>();
		List<Word> wordList = null;
		wordList = wordService.findByLevel(Integer.parseInt(level));
		for(Word w: wordList) {
			map.put(w.getJapanese(), w.getEnglish());
		}
		return wordList;
	}
	
	@GetMapping("/socreInsert")
	public int scoreInsert(@RequestParam("loginId") String loginId, @RequestParam("score") String score,@RequestParam("level") String level,Model model) {
		Integer scoreInt = Integer.parseInt(score);
		Integer levelInt = Integer.parseInt(level);
		return scoreService.insert(loginId, scoreInt, levelInt);
	}
}
