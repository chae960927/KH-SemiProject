package com.notice.dto;

import java.util.Date;

public class NoticeDto {
	private int notice_no;
	private String notice_cat;
	private String notice_writer;
	private String notice_title;
	private String notice_content;
	private int notice_push;
	private Date notice_regdate;
	
	//기본 생성자
	public NoticeDto() {
		
	}

	public NoticeDto(int notice_no, String notice_cat, String notice_writer, String notice_title, String notice_content,
			int notice_push, Date notice_regdate) {
		super();
		this.notice_no = notice_no;
		this.notice_cat = notice_cat;
		this.notice_writer = notice_writer;
		this.notice_title = notice_title;
		this.notice_content = notice_content;
		this.notice_push = notice_push;
		this.notice_regdate = notice_regdate;
	}

	public int getNotice_no() {
		return notice_no;
	}

	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
	}

	public String getNotice_cat() {
		return notice_cat;
	}

	public void setNotice_cat(String notice_cat) {
		this.notice_cat = notice_cat;
	}

	public String getNotice_writer() {
		return notice_writer;
	}

	public void setNotice_writer(String notice_writer) {
		this.notice_writer = notice_writer;
	}

	public String getNotice_title() {
		return notice_title;
	}

	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}

	public String getNotice_content() {
		return notice_content;
	}

	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}

	public int getNotice_push() {
		return notice_push;
	}

	public void setNotice_push(int notice_push) {
		this.notice_push = notice_push;
	}

	public Date getNotice_regdate() {
		return notice_regdate;
	}

	public void setNotice_regdate(Date notice_regdate) {
		this.notice_regdate = notice_regdate;
	}
}
