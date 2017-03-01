package com.stusystem.entity;

import java.util.Date;

public class Message {
	private int id;
	private String title;
	private String content;
	private Date addtime;

	public Message(int id, String title, String content, Date addtime) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.addtime = addtime;
	}

	public Message() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

}
