package com.faq.dto;

public class FaqDto {
	private int faq_no;
	private String faq_title;
	private String faq_content;
	
	//기본 생성자
	public FaqDto() {
		
	}

	public FaqDto(int faq_no, String faq_title, String faq_content) {
		super();
		this.faq_no = faq_no;
		this.faq_title = faq_title;
		this.faq_content = faq_content;
	}

	public int getFaq_no() {
		return faq_no;
	}

	public void setFaq_no(int faq_no) {
		this.faq_no = faq_no;
	}

	public String getFaq_title() {
		return faq_title;
	}

	public void setFaq_title(String faq_title) {
		this.faq_title = faq_title;
	}

	public String getFaq_content() {
		return faq_content;
	}

	public void setFaq_content(String faq_content) {
		this.faq_content = faq_content;
	}
}
