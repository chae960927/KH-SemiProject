package com.report.dto;

import java.util.Date;

public class reportDto {
	private int rep_no; //신고번호
	private String rep_name; //신고자명
	private String rep_id; //신고대상 명
	private String rep_member; //신고대상 등급
	private String rep_content; //신고대상 글내용
	private String rep_reason; //신고사유
	private int rep_push; //조회수???
	private Date rep_regdate; //신고날짜
	private int rep_listnumber; //신고대상 게시글 번호
	private int board; //신고대상 게시글 유형
	
	public reportDto() {
		super();
	}

	public reportDto(int rep_no, String rep_name, String rep_id, String rep_member, String rep_content,
			String rep_reason, int rep_push, Date rep_regdate, int rep_listnumber, int board) {
		super();
		this.rep_no = rep_no;
		this.rep_name = rep_name;
		this.rep_id = rep_id;
		this.rep_member = rep_member;
		this.rep_content = rep_content;
		this.rep_reason = rep_reason;
		this.rep_push = rep_push;
		this.rep_regdate = rep_regdate;
		this.rep_listnumber = rep_listnumber;
		this.board = board;
	}

	public int getRep_no() {
		return rep_no;
	}

	public void setRep_no(int rep_no) {
		this.rep_no = rep_no;
	}

	public String getRep_name() {
		return rep_name;
	}

	public void setRep_name(String rep_name) {
		this.rep_name = rep_name;
	}

	public String getRep_id() {
		return rep_id;
	}

	public void setRep_id(String rep_id) {
		this.rep_id = rep_id;
	}

	public String getRep_member() {
		return rep_member;
	}

	public void setRep_member(String rep_member) {
		this.rep_member = rep_member;
	}

	public String getRep_content() {
		return rep_content;
	}

	public void setRep_content(String rep_content) {
		this.rep_content = rep_content;
	}

	public String getRep_reason() {
		return rep_reason;
	}

	public void setRep_reason(String rep_reason) {
		this.rep_reason = rep_reason;
	}

	public int getRep_push() {
		return rep_push;
	}

	public void setRep_push(int rep_push) {
		this.rep_push = rep_push;
	}

	public Date getRep_regdate() {
		return rep_regdate;
	}

	public void setRep_regdate(Date rep_regdate) {
		this.rep_regdate = rep_regdate;
	}

	public int getRep_listnumber() {
		return rep_listnumber;
	}

	public void setRep_listnumber(int rep_listnumber) {
		this.rep_listnumber = rep_listnumber;
	}

	public int getBoard() {
		return board;
	}

	public void setBoard(int board) {
		this.board = board;
	}
}
