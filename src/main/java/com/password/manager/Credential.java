package com.password.manager;

public class Credential {
	private Integer id;
	private String url;
	private String username;
	private String password;
	public Credential(Integer id, String url, String username, String password) {
		super();
		this.id = id;
		this.url = url;
		this.username = username;
		this.password = password;
	}
	public Integer getId() {
		return id;
	}
	public String getUrl() {
		return url;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	
	
}
