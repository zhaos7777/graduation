package com.stusystem.entity;

public class Student {
	private int student_id;
	private String student_name;
	private Sex sex;
	private int student_age;
	private Class cla;
	private Profession profession;
	private String student_home;
	private String student_idcard;

	public Student() {
		super();
	}

	public Student(int student_id, String student_name, Sex sex,
			int student_age, Class cla, Profession profession,
			String student_home, String student_idcard) {
		super();
		this.student_id = student_id;
		this.student_name = student_name;
		this.sex = sex;
		this.student_age = student_age;
		this.cla = cla;
		this.profession = profession;
		this.student_home = student_home;
		this.student_idcard = student_idcard;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public int getStudent_age() {
		return student_age;
	}

	public void setStudent_age(int student_age) {
		this.student_age = student_age;
	}

	public Class getCla() {
		return cla;
	}

	public void setCla(Class cla) {
		this.cla = cla;
	}

	public Profession getProfession() {
		return profession;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}

	public String getStudent_home() {
		return student_home;
	}

	public void setStudent_home(String student_home) {
		this.student_home = student_home;
	}

	public String getStudent_idcard() {
		return student_idcard;
	}

	public void setStudent_idcard(String student_idcard) {
		this.student_idcard = student_idcard;
	}

}
