package com.example.controller.form;

import javax.validation.constraints.NotBlank;
public class UpdateForm {

	@NotBlank
	private String japanese;
	@NotBlank
    private String english;
	
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
}