package com.example.entity;

public class Score {
//	private Integer id;
//    private String userId;
	private String loginId;
    private Integer score;
 
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
}
