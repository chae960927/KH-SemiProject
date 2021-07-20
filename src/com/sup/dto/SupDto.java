package com.sup.dto;

import java.util.Date;

public class SupDto {
	private int sup_no;
	private int group_no;
	private int group_sq;
	private int title_tab;
	private String sup_writer;
	private String sup_title;
	private String sup_content;
	private int sup_push;
	private Date sup_regdate;
	
	//기본 생성자
	public SupDto() {
		
	}

	public SupDto(int sup_no, int group_no, int group_sq, int title_tab, String sup_writer, String sup_title,
			String sup_content, int sup_push, Date sup_regdate) {
		super();
		this.sup_no = sup_no;
		this.group_no = group_no;
		this.group_sq = group_sq;
		this.title_tab = title_tab;
		this.sup_writer = sup_writer;
		this.sup_title = sup_title;
		this.sup_content = sup_content;
		this.sup_push = sup_push;
		this.sup_regdate = sup_regdate;
	}

	public int getSup_no() {
		return sup_no;
	}

	public void setSup_no(int sup_no) {
		this.sup_no = sup_no;
	}

	public int getGroup_no() {
		return group_no;
	}

	public void setGroup_no(int group_no) {
		this.group_no = group_no;
	}

	public int getGroup_sq() {
		return group_sq;
	}

	public void setGroup_sq(int group_sq) {
		this.group_sq = group_sq;
	}

	public int getTitle_tab() {
		return title_tab;
	}

	public void setTitle_tab(int title_tab) {
		this.title_tab = title_tab;
	}

	public String getSup_writer() {
		return sup_writer;
	}

	public void setSup_writer(String sup_writer) {
		this.sup_writer = sup_writer;
	}

	public String getSup_title() {
		return sup_title;
	}

	public void setSup_title(String sup_title) {
		this.sup_title = sup_title;
	}

	public String getSup_content() {
		return sup_content;
	}

	public void setSup_content(String sup_content) {
		this.sup_content = sup_content;
	}

	public int getSup_push() {
		return sup_push;
	}

	public void setSup_push(int sup_push) {
		this.sup_push = sup_push;
	}

	public Date getSup_regdate() {
		return sup_regdate;
	}

	public void setSup_regdate(Date sup_regdate) {
		this.sup_regdate = sup_regdate;
	}
}
