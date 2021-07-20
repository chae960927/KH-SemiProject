package com.pagging;

public class Paging {
	private int page = 1; //현재페이지(get)
	private int totalCount; //row 전체의 수(get)
	private int beginPage; //출력시작
	private int endPage; //출력 끝
	private int displayRow = 10; //한 페이지에 몇 개의 로우 (선택 set)
	private int displayPage = 10; //한 페이지에 몇 개의 페이지 (선택set)
	boolean prev; //prev 버튼이 보일건지 안보일건지
	boolean next; //next 버튼이 보일건지 안보일건지
	// prev, next, beginPage, endPage를 계산해서 만든다.
	// 2+9 = 11, 11/10 = 1, 1*10 = 10
	// 10+9 = 19, 19/10 = 1, 1*10 = 10
	// 11+9 = 20, 20/10 = 2, 2*10 = 20
	// 20+9 = 29, 29/10 = 2, 2*10 = 20
	// 111+9 = 120 120/10 = 12, 12*10 = 120
	
	// (2+9)/10 * 10 (1번 방법)
	//endPage = ((page+(displayPage-1))/displayPage)*displayPage;
	
	// 1/10 0.1(올림) 1 (2번 방법)
    
	public Paging() {}
	public Paging(int page, int totalCount, int beginPage, int endPage, int displayRow, int displayPage, boolean prev,
			boolean next) {
		super();
		this.page = page;
		this.totalCount = totalCount;
		this.beginPage = beginPage;
		this.endPage = endPage;
		this.displayRow = displayRow;
		this.displayPage = displayPage;
		this.prev = prev;
		this.next = next;
	}
	
	private void paging() {
        endPage = ((int)Math.ceil(page/(double)displayPage))*displayPage;
        System.out.println("endPage : " + endPage);
        
        beginPage = endPage - (displayPage - 1);
        System.out.println("beginPage : " + beginPage);
        

        int totalPage = (int)Math.ceil(totalCount/(double)displayRow);
        
        if(totalPage<endPage){
            endPage = totalPage;
            next = false;
        }else{
            next = true;
        }
        prev = (beginPage==1)?false:true;//page가 11이상에만 나온다.
        System.out.println("endPage : " + endPage);
        System.out.println("totalPage : " + totalPage);
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		paging();
	}
	public int getBeginPage() {
		return beginPage;
	}
	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getDisplayRow() {
		return displayRow;
	}
	public void setDisplayRow(int displayRow) {
		this.displayRow = displayRow;
	}
	public int getDisplayPage() {
		return displayPage;
	}
	public void setDisplayPage(int displayPage) {
		this.displayPage = displayPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
}
