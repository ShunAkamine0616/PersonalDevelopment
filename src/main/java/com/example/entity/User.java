package com.example.entity;

public class User {
	private Integer id;
    private String loginId;
    private String password;
    private String name;
    private Integer role;
  
    public User() {
    }
    
    public User(String loginId, String password) {
    	this.loginId = loginId;
    	this.password = password;
    }
    
    public User(Integer id, String loginId, String password) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
    }
    
    public User(String loginId, String password, String name, Integer role) {
        this.loginId = loginId;
        this.name = name;
        this.password = password;
        this.role = role;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}
}