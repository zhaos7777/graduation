package com.stusystem.entity;

import java.util.Date;

public class News {
	private int newsid;
	private String title;
	private Topic topic;
	private String author;
	private String content;
	private Date addtime;

	public News() {
		super();
	}

	public News(int newsid, String title, Topic topic, String author,
			String content, Date addtime) {
		super();
		this.newsid = newsid;
		this.title = title;
		this.topic = topic;
		this.author = author;
		this.content = content;
		this.addtime = addtime;
	}

	public int getNewsid() {
		return newsid;
	}

	public void setNewsid(int newsid) {
		this.newsid = newsid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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
