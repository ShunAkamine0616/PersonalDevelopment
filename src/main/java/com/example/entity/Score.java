package com.example.entity;

public class Score {
//	private Integer id;
//    private String userId;
	private Integer lank;
	private String loginId;
    private Integer score;
    private Integer difficulty;
	
	
	public Integer getLank() {
		return lank;
	}
	public void setLank(Integer lank) {
		this.lank = lank;
	}
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
	public Integer getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}
}
