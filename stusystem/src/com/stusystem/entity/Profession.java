package com.stusystem.entity;

public class Profession {
	private int pro_id;
	private String pro_name;
	private String pro_abb;
	private int pro_classnum;
	private int pro_studentnum;
	private int pro_teachernum;

	public Profession() {
		super();
	}

	public Profession(int pro_id, String pro_name, String pro_abb) {
		super();
		this.pro_id = pro_id;
		this.pro_name = pro_name;
		this.pro_abb = pro_abb;
	}

	public Profession(int pro_id, String pro_name, String pro_abb,
			int pro_classnum, int pro_studentnum, int pro_teachernum) {
		super();
		this.pro_id = pro_id;
		this.pro_name = pro_name;
		this.pro_abb = pro_abb;
		this.pro_classnum = pro_classnum;
		this.pro_studentnum = pro_studentnum;
		this.pro_teachernum = pro_teachernum;
	}

	public int getPro_id() {
		return pro_id;
	}

	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}

	public String getPro_name() {
		return pro_name;
	}

	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}

	public String getPro_abb() {
		return pro_abb;
	}

	public void setPro_abb(String pro_abb) {
		this.pro_abb = pro_abb;
	}

	public int getPro_classnum() {
		return pro_classnum;
	}

	public void setPro_classnum(int pro_classnum) {
		this.pro_classnum = pro_classnum;
	}

	public int getPro_studentnum() {
		return pro_studentnum;
	}

	public void setPro_studentnum(int pro_studentnum) {
		this.pro_studentnum = pro_studentnum;
	}

	public int getPro_teachernum() {
		return pro_teachernum;
	}

	public void setPro_teachernum(int pro_teachernum) {
		this.pro_teachernum = pro_teachernum;
	}

}
