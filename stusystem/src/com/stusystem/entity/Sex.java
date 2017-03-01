package com.stusystem.entity;

public class Sex {
	private int sex_id;
	private String sex_name;

	public Sex() {
		super();
	}

	public Sex(int sex_id, String sex_name) {
		super();
		this.sex_id = sex_id;
		this.sex_name = sex_name;
	}

	public int getSex_id() {
		return sex_id;
	}

	public void setSex_id(int sex_id) {
		this.sex_id = sex_id;
	}

	public String getSex_name() {
		return sex_name;
	}

	public void setSex_name(String sex_name) {
		this.sex_name = sex_name;
	}

}
