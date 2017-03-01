package com.stusystem.entity;

public class AdminInfo {
	private int id;
	private String username;
	private String password;
	private String type;
	private String name;

	public AdminInfo(String username, String password, String type) {
		super();
		this.username = username;
		this.password = password;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public AdminInfo() {
		super();
	}

	public AdminInfo(int id, String username, String password, String type,String name) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.type = type;
		this.name = name;
	}

}
