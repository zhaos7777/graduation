package com.stusystem.entity;

public class Topic {
	private int topicid;
	private String topicname;
	private int newsnum;

	public int getNewsnum() {
		return newsnum;
	}

	public void setNewsnum(int newsnum) {
		this.newsnum = newsnum;
	}

	public Topic() {
		super();
	}

	public Topic(int topicid, String topicname) {
		super();
		this.topicid = topicid;
		this.topicname = topicname;
	}

	public int getTopicid() {
		return topicid;
	}

	public void setTopicid(int topicid) {
		this.topicid = topicid;
	}

	public String getTopicname() {
		return topicname;
	}

	public void setTopicname(String topicname) {
		this.topicname = topicname;
	}

}
