package com.login.dto;

public class UserDto {
	private String user_id;
	private String user_pw;
	private String user_name;
	private String nick_name;
	private String user_img;
	private String user_addr;
	private String user_addr_de;
	private String user_phone;
	private String user_email;
	private String member;
	private String biz_num;
	public UserDto() {
		super();
	}
	public UserDto(String user_id, String user_pw, String user_name, String nick_name, String user_img,
			String user_addr, String user_addr_de, String user_phone, String user_email, String member, String biz_num) {
		super();
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_name = user_name;
		this.nick_name = nick_name;
		this.user_img = user_img;
		this.user_addr = user_addr;
		this.user_addr_de = user_addr_de;
		this.user_phone = user_phone;
		this.user_email = user_email;
		this.member = member;
		this.biz_num = biz_num;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public String getUser_img() {
		return user_img;
	}
	public void setUser_img(String user_img) {
		this.user_img = user_img;
	}
	public String getUser_addr() {
		return user_addr;
	}
	public void setUser_addr(String user_addr) {
		this.user_addr = user_addr;
	}
	public String getUser_addr_de() {
		return user_addr_de;
	}
	public void setUser_addr_de(String user_addr_de) {
		this.user_addr_de = user_addr_de;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public String getBiz_num() {
		return biz_num;
	}
	public void setBiz_num(String biz_num) {
		this.biz_num = biz_num;
	}

	
}
