package com.example.controller.form;

import javax.validation.constraints.NotBlank;
public class InsertForm {

	@NotBlank
	private String japanese;
	@NotBlank
    private String english;
//	@NotNull
//    private Integer difficulty;
	
	public String getJapanese() {
		return japanese;
	}
	public void setJapanese(String japanese) {
		this.japanese = japanese;
	}
	public String getEnglish() {
		return english;
	}
	public void setEnglish(String english) {
		this.english = english;
	}
//	public Integer getDifficulty() {
//		return difficulty;
//	}
//	public void setDifficulty(Integer difficulty) {
//		this.difficulty = difficulty;
//	}
}