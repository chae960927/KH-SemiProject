package com.pay.dto;

import java.util.Date;

public class PayDto {
	private String pay_no;
	private String pay_user;
	private String pay_company;
	private String pay_name;
	private int pay_money;
	private String pay_info;
	private Date pay_regdate;
	
	public PayDto() {}
	public PayDto(String pay_no, String pay_user, String pay_company, String pay_name, int pay_money, String pay_info, Date pay_regdate) {
		super();
		this.pay_no = pay_no;
		this.pay_user = pay_user;
		this.pay_company = pay_company;
		this.pay_name = pay_name;
		this.pay_money = pay_money;
		this.pay_info = pay_info;
		this.pay_regdate = pay_regdate;
	}
	
	public String getPay_no() {
		return pay_no;
	}
	public void setPay_no(String pay_no) {
		this.pay_no = pay_no;
	}
	public String getPay_user() {
		return pay_user;
	}
	public void setPay_user(String pay_user) {
		this.pay_user = pay_user;
	}
	public String getPay_company() {
		return pay_company;
	}
	public void setPay_company(String pay_company) {
		this.pay_company = pay_company;
	}
	public String getPay_name() {
		return pay_name;
	}
	public void setPay_name(String pay_name) {
		this.pay_name = pay_name;
	}
	public int getPay_money() {
		return pay_money;
	}
	public void setPay_money(int pay_money) {
		this.pay_money = pay_money;
	}
	public String getPay_info() {
		return pay_info;
	}
	public void setPay_info(String pay_info) {
		this.pay_info = pay_info;
	}
	public Date getPay_regdate() {
		return pay_regdate;
	}
	public void setPay_regdate(Date pay_regdate) {
		this.pay_regdate = pay_regdate;
	}
}
