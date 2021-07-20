package com.calender.dto;

public class CalendarDto {
	private String cal_num;
	private String title;
	private String start;
	private String end;
	private String cal_user;
	public CalendarDto() {
		super();
	}
	public CalendarDto(String cal_title, String cal_start, String cal_end, String cal_user) {
		super();
		this.title = cal_title;
		this.start = cal_start;
		this.end = cal_end;
		this.cal_user = cal_user;
	}
	public CalendarDto(String cal_num, String title, String start, String end, String cal_user) {
		super();
		this.cal_num = cal_num;
		this.title = title;
		this.start = start;
		this.end = end;
		this.cal_user = cal_user;
	}
	public CalendarDto(String cal_title, String cal_start, String cal_end) {
		super();
		this.title = cal_title;
		this.start = cal_start;
		this.end = cal_end;
	}
	public String getCal_num() {
		return cal_num;
	}
	public void setCal_num(String cal_num) {
		this.cal_num = cal_num;
	}
	public String getCal_title() {
		return title;
	}
	public void setCal_title(String cal_title) {
		this.title = cal_title;
	}
	public String getCal_start() {
		return start;
	}
	public void setCal_start(String cal_start) {
		this.start = cal_start;
	}
	public String getCal_end() {
		return end;
	}
	public void setCal_end(String cal_end) {
		this.end = cal_end;
	}
	public String getCal_user() {
		return cal_user;
	}
	public void setCal_user(String cal_user) {
		this.cal_user = cal_user;
	}

	
}
