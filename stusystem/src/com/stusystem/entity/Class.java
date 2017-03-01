package com.stusystem.entity;

public class Class {
	private int class_id;
	private String class_name;
	private Profession profession;
	private int class_studentnum;
	private Teacher teacher;

	public Class() {
		super();
	}

	public Class(int class_id, String class_name, Profession profession,
			int class_studentnum, Teacher teacher) {
		super();
		this.class_id = class_id;
		this.class_name = class_name;
		this.profession = profession;
		this.class_studentnum = class_studentnum;
		this.teacher = teacher;
	}

	public int getClass_id() {
		return class_id;
	}

	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}

	public String getClass_name() {
		return class_name;
	}

	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}

	public Profession getProfession() {
		return profession;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}

	public int getClass_studentnum() {
		return class_studentnum;
	}

	public void setClass_studentnum(int class_studentnum) {
		this.class_studentnum = class_studentnum;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}



}
