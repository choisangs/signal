package com.signal.jobposting.controller;

import java.util.Arrays;

/* 
 * by 진희, 페이징 쿼리를 동적 제어하기 위해 필요한 데이터 'pageNum'와 'amount'를 같이 파라미터로 
 * 전달하기 위한 용도로 class Criteria 생성 
 * */
public class Criteria {
	
	// 현재 페이지
    private int pageNum;
    
    // 한 페이지 당 보여질 게시물 갯수
    private int amount;

    // 검색 타입 
    private String type;
    
    // 검색 타입 배열
    private String[] typeArr;
    
    // 스킵 할 게시물 수( (pageNum-1) * amount )
    private int skip;
    
    // 로그인 아이디*/
    private String loginId;

    // 기본 생성자 -> 기봅 세팅 : pageNum = 1, amount = 10
    public Criteria() {
        this(1,10);
        this.skip = 0;
    }
    
    // 생성자 => 원하는 pageNum, 원하는 amount
    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
        this.skip = (pageNum-1)*amount;
    }

	public int getPageNum() {
		return pageNum;
	}

	// by 태섭, setPageNum() 메서드 호출하는 의미는 page 값을 변경한다는 의미
	public void setPageNum(int pageNum) {
		this.skip = (pageNum-1)*amount;
		this.pageNum = pageNum;
	}

	public int getAmount() {
		return amount;
	}
	
	// by 태섭, setAmount() 메서드 호출하는 의미는 amount 값을 변경한다는 의미
	public void setAmount(int amount) {
		this.skip = (pageNum-1)*amount;
		this.amount = amount;
	}
    
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		this.typeArr = type.split(""); 
	}

	public String[] getTypeArr() {
		return typeArr;
	}

	public void setTypeArr(String[] typeArr) {
		this.typeArr = typeArr;
	}

	@Override
	public String toString() {
		return "Criteria [pageNum=" + pageNum + ", amount=" + amount + ", skip=" + skip +", type =" + type
				+", typeArr="+ Arrays.toString(typeArr)+"]";
	}
    
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	
	
	
	
}
