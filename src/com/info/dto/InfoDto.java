package com.info.dto;

import java.util.Date;

public class InfoDto {
	private int info_no;
	private String info_writer;
	private String info_title;
	private String info_content;
	private String user_img;
	private int info_push;
	private Date info_regdate;
	
	//기본 생성자
	public InfoDto() {
		
	}

	//매개변수 생성자
	public InfoDto(int info_no, String info_writer, String info_title, String info_content, String user_img,
			int info_push, Date info_regdate) {
		super();
		this.info_no = info_no;
		this.info_writer = info_writer;
		this.info_title = info_title;
		this.info_content = info_content;
		this.user_img = user_img;
		this.info_push = info_push;
		this.info_regdate = info_regdate;
	}

	
	//getter & setter
	public int getInfo_no() {
		return info_no;
	}

	public void setInfo_no(int info_no) {
		this.info_no = info_no;
	}

	public String getInfo_writer() {
		return info_writer;
	}

	public void setInfo_writer(String info_writer) {
		this.info_writer = info_writer;
	}

	public String getInfo_title() {
		return info_title;
	}

	public void setInfo_title(String info_title) {
		this.info_title = info_title;
	}

	public String getInfo_content() {
		return info_content;
	}

	public void setInfo_content(String info_content) {
		this.info_content = info_content;
	}

	public String getUser_img() {
		return user_img;
	}

	public void setUser_img(String user_img) {
		this.user_img = user_img;
	}

	public int getInfo_push() {
		return info_push;
	}

	public void setInfo_push(int info_push) {
		this.info_push = info_push;
	}

	public Date getInfo_regdate() {
		return info_regdate;
	}

	public void setInfo_regdate(Date info_regdate) {
		this.info_regdate = info_regdate;
	}

	
	
}
