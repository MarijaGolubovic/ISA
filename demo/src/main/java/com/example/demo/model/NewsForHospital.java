package com.example.demo.model;

import com.example.demo.model.enumerations.NewsForHopsitalStatus;

public class NewsForHospital {
	
	private String title;
	private String content;
	private NewsForHopsitalStatus newsStatus;
	private String apiKey;
	private String base64image;
	private String bloodBankName;
	
	public NewsForHospital(String title, String content, String base64im, String bloodBankName) {
		super();
		this.title = title;
		this.content = content;
		this.newsStatus = NewsForHopsitalStatus.ON_HOLD;
		this.apiKey = "";
		this.base64image = base64im;
		this.bloodBankName = bloodBankName;
	}

	public String getBloodBankName() {
		return bloodBankName;
	}

	public void setBloodBankName(String bloodBankName) {
		this.bloodBankName = bloodBankName;
	}

	public String getBase64image() {
		return base64image;
	}

	public void setBase64image(String base64image) {
		this.base64image = base64image;
	}

	public NewsForHopsitalStatus getNewsStatus() {
		return newsStatus;
	}

	public void setNewsStatus(NewsForHopsitalStatus newsStatus) {
		this.newsStatus = newsStatus;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
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

	@Override
	public String toString() {
		return "NewsForHospital [title=" + title + ", content=" + content + "]";
	}
	
	
	
}
