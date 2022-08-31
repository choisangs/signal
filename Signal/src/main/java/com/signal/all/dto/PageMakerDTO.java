package com.signal.all.dto;

import com.signal.enter.controller.Criteria;

//by태섭, 페이지 이동 인터페이스 구현을 위한 PageMakerDTO 생성_2022_08_12
public class PageMakerDTO {

	/* 시작 페이지 화면에 표시되는 페이지 '시작 번호'에 대한 정보입니다. */
    private int startPage;
    
    /* 끝 페이지 화면에 표시되는 페이지 '끝 번호'에 대한 정보입니다.*/
    private int endPage;
    
    /* 화면에 보이는 10개 페이지의 '이전 페이지', '다음 페이지' 존재 유무에 대한 정보입니다.*/
    private boolean prev, next;
    
    /*전체 게시물 수 해당 정보가 있어야 'startPage', 'endPage', 'prev', 'next'의 값을 구할 수 있어서 선언하였습니다.*/
    private int total;
    
    /* 현재 페이지, 페이지당 게시물 표시수 정보 Criteria 클래스의 pageNum(현재 페이지) 변수 값을 얻기 위해 선언하였습니다. */
    private Criteria cri;
    
    /* 생성자 */
    /* 해당 생성자는 전달받은 Criteria와 total 정보를 활용하여 계산 과정을 거친 후 PageMakerDTO의 변수에 대한 값을 초기화하게 됩니다. */
    public PageMakerDTO(Criteria cri, int total) {
        
        this.cri = cri;
        this.total = total;
        
        /* 마지막 페이지 */
        this.endPage = (int)(Math.ceil(cri.getPageNum()/10.0))*10;
        /* 시작 페이지 */
        this.startPage = this.endPage - 9;
        
        /* 전체 마지막 페이지 */
        int realEnd = (int)(Math.ceil(total * 1.0/cri.getAmount()));
        
        /* 전체 마지막 페이지(realend)가 화면에 보이는 마지막페이지(endPage)보다 작은 경우, 보이는 페이지(endPage) 값 조정 */
        if(realEnd < this.endPage) {
            this.endPage = realEnd;
        }
        
        /* 시작 페이지(startPage)값이 1보다 큰 경우 true */
        this.prev = this.startPage > 1;
        
        /* 마지막 페이지(endPage)값이 1보다 큰 경우 true */
        this.next = this.endPage < realEnd;
    }
    /*생성자 2 - cri 없이 */
    public PageMakerDTO(int pageNum, int total) {
           
          // this.cri = cri;
           this.total = total;
           
           /* 마지막 페이지 */
           this.endPage = (int)(Math.ceil(pageNum/10.0))*10;
           /* 시작 페이지 */
           this.startPage = this.endPage - 9;
           
           /* 전체 마지막 페이지 */
           int realEnd = (int)(Math.ceil(total * 1.0/10));
           
           /* 전체 마지막 페이지(realend)가 화면에 보이는 마지막페이지(endPage)보다 작은 경우, 보이는 페이지(endPage) 값 조정 */
           if(realEnd < this.endPage) {
               this.endPage = realEnd;
           }
           
           /* 시작 페이지(startPage)값이 1보다 큰 경우 true */
           this.prev = this.startPage > 1;
           
           /* 마지막 페이지(endPage)값이 1보다 큰 경우 true */
           this.next = this.endPage < realEnd;
    }
	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
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

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}
    
    
}
