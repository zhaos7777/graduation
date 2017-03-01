package com.stusystem.entity;

public class Teacher {
	private int teacher_id;
	private String teacher_name;
	private int teacher_age;
	private Sex sex;
	private Profession pro;
	private int teacher_classnum;

	public Teacher() {
		super();
	}

	public Teacher(int teacher_id, String teacher_name, int teacher_age,
			Sex sex, Profession pro, int teacher_classnum) {
		super();
		this.teacher_id = teacher_id;
		this.teacher_name = teacher_name;
		this.teacher_age = teacher_age;
		this.sex = sex;
		this.pro = pro;
		this.teacher_classnum = teacher_classnum;
	}

	public int getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}

	public String getTeacher_name() {
		return teacher_name;
	}

	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}

	public int getTeacher_age() {
		return teacher_age;
	}

	public void setTeacher_age(int teacher_age) {
		this.teacher_age = teacher_age;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public Profession getPro() {
		return pro;
	}

	public void setPro(Profession pro) {
		this.pro = pro;
	}

	public int getTeacher_classnum() {
		return teacher_classnum;
	}

	public void setTeacher_classnum(int teacher_classnum) {
		this.teacher_classnum = teacher_classnum;
	}

}
