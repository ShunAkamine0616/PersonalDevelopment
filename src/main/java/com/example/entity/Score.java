package com.example.entity;

import java.sql.Timestamp;

public class Score {
//	private Integer id;
//    private String userId;
	private Integer lank;
	private String loginId;
    private Integer score;
    private Integer difficulty;
    private Integer miss;
    private Timestamp times;
	
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
	public Integer getMiss() {
		return miss;
	}
	public void setMiss(Integer miss) {
		this.miss = miss;
	}
	public Timestamp getTimes() {
		return times;
	}
	public void setTimes(Timestamp times) {
		this.times = times;
	}
}
