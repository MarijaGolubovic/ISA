package com.example.demo.model;

public class NewsForHospital {
	
	private String title;
	private String content;
	private String bloodBankName;
	
	public NewsForHospital(String title, String content, String bloodBankName) {
		super();
		this.title = title;
		this.content = content;
		this.bloodBankName = bloodBankName;
	}

	public NewsForHospital() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getBloodBankName() {
		return bloodBankName;
	}

	public void setBloodBankName(String bloodBankName) {
		this.bloodBankName = bloodBankName;
	}

	@Override
	public String toString() {
		return "NewsForHospital [title=" + title + ", content=" + content + ", bloodBankName=" + bloodBankName + "]";
	}
	
	
	
}
