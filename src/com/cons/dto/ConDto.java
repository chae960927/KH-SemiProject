package com.cons.dto;

import java.util.Date;

public class ConDto {
	private int con_no;
	private String con_writer;
	private String con_title;
	private String con_content;
	private String user_img;
	private int con_push;
	private Date con_regdate;
	
	//기본 생성자
	public ConDto() {
		
	}

	//매개변수 생성자
	public ConDto(int con_no, String con_writer, String con_title, String con_content, String user_img,
			int con_push, Date con_regdate) {
		super();
		this.con_no = con_no;
		this.con_writer = con_writer;
		this.con_title = con_title;
		this.con_content = con_content;
		this.user_img = user_img;
		this.con_push = con_push;
		this.con_regdate = con_regdate;
	}

	
	//getter & setter
	public int getcon_no() {
		return con_no;
	}

	public void setcon_no(int con_no) {
		this.con_no = con_no;
	}

	public String getcon_writer() {
		return con_writer;
	}

	public void setcon_writer(String con_writer) {
		this.con_writer = con_writer;
	}

	public String getcon_title() {
		return con_title;
	}

	public void setcon_title(String con_title) {
		this.con_title = con_title;
	}

	public String getcon_content() {
		return con_content;
	}

	public void setcon_content(String con_content) {
		this.con_content = con_content;
	}

	public String getUser_img() {
		return user_img;
	}

	public void setUser_img(String user_img) {
		this.user_img = user_img;
	}

	public Date getcon_regdate() {
		return con_regdate;
	}

	public void setcon_regdate(Date con_regdate) {
		this.con_regdate = con_regdate;
	}

	public int getCon_push() {
		return con_push;
	}

	public void setCon_push(int con_push) {
		this.con_push = con_push;
	}
}
