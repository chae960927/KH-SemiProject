package com.chat.dto;

import java.util.Date;

public class ChatDto {
	private String chat_num;
	private String from_user;
	private String to_user;
	private String content; 
	private Date chat_time;
	
	public ChatDto() {
		super();
	}

	public ChatDto(String from_user, String to_user, String content) {
		super();
		this.from_user = from_user;
		this.to_user = to_user;
		this.content = content;
	}
	
	

	public ChatDto(String from_user, String to_user, String content, Date chat_time) {
		super();
		this.from_user = from_user;
		this.to_user = to_user;
		this.content = content;
		this.chat_time = chat_time;
	}

	public String getChat_num() {
		return chat_num;
	}

	public void setChat_num(String chat_num) {
		this.chat_num = chat_num;
	}

	public Date getChat_time() {
		return chat_time;
	}

	public void setChat_time(Date chat_time) {
		this.chat_time = chat_time;
	}

	public String getFrom_user() {
		return from_user;
	}

	public void setFrom_user(String from_user) {
		this.from_user = from_user;
	}

	public String getTo_user() {
		return to_user;
	}

	public void setTo_user(String to_user) {
		this.to_user = to_user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
