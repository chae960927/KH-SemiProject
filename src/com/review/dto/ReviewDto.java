package com.review.dto;

import java.util.Date;

public class ReviewDto {
	private int view_no;
	private int cons_no;
	private String view_writer;
	private int view_num;
	private Date view_regdate;
	
	public ReviewDto() {}
	public ReviewDto(int view_no, int cons_no, String view_writer, int view_num, Date view_regdate) {
		super();
		this.view_no = view_no;
		this.cons_no = cons_no;
		this.view_writer = view_writer;
		this.view_num = view_num;
		this.view_regdate = view_regdate;
	}
	
	public int getView_no() {
		return view_no;
	}
	public void setView_no(int view_no) {
		this.view_no = view_no;
	}
	public int getCons_no() {
		return cons_no;
	}
	public void setCons_no(int cons_no) {
		this.cons_no = cons_no;
	}
	public String getView_writer() {
		return view_writer;
	}
	public void setView_writer(String view_writer) {
		this.view_writer = view_writer;
	}
	public int getView_num() {
		return view_num;
	}
	public void setView_num(int view_num) {
		this.view_num = view_num;
	}
	public Date getView_regdate() {
		return view_regdate;
	}
	public void setView_regdate(Date view_regdate) {
		this.view_regdate = view_regdate;
	}
}
