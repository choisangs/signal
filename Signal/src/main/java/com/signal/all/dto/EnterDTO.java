package com.signal.all.dto;

import java.sql.Date;

public class EnterDTO {

	//by태섭, 입사제안현황 관련 변수 선언_2022_08_08
	private String re_title; //이력서 제목
	private String cl_gender; //성별
	private int cl_age; //나이
	private int inter_grade; //면접 평점 수
	private double inter_grade_avg; //면접 평점 평균
	private int gradeCnt; // 면접 평점 수
	private int st_score; //셀프 평점 
	private double st_score_avg; // 셀프 평점 평균
	private Date offer_date; //입사제안 날짜
	private String cl_name; //이름
	
	private String inter_comment;
	private int inter_no; //면접 번호
	private String cl_photo; //사진
	
	//by태섭, 입사제안 버튼 눌렀을 때 채용공고 리스트 보여주기_2022_08_09
	private String jpo_title; //채용공고명
	private int jpo_no; //공고번호
	
	//by태섭, 개인 마이페이지 입사제안현황 리스트 관련 파라미터 추가_2022_08_11
	private String com_name; //기업명
	private String reading_state; //열람여부
	private int offer_no; //제안 번호
	private int offer_state; //제안 
	
	//by태섭, 개인 마이페이지 입사지원현황 리스트 관련 파라미터 추가_2022_08_11
	private Date apply_date; //지원날짜
	private Date inter_date; //면접날짜
	private String inter_result; //지원결과
	
	//by태섭, 기업 마이페이지 입사지원현황 리스트 관련 파라미터 추가_2022_08_17
	private String cl_id; //개인회원 아이디
	
	//by태섭, 기업 아이디 컬럼 추가_2022_08_20
	private String com_id;
	private String re_no;
	
	public String getRe_no() {
		return re_no;
	}
	public void setRe_no(String re_no) {
		this.re_no = re_no;
	}
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	public String getCl_id() {
		return cl_id;
	}
	public void setCl_id(String cl_id) {
		this.cl_id = cl_id;
	}
	public String getCl_name() {
		return cl_name;
	}
	public void setCl_name(String cl_name) {
		this.cl_name = cl_name;
	}
	public Date getApply_date() {
		return apply_date;
	}
	public void setApply_date(Date apply_date) {
		this.apply_date = apply_date;
	}
	public Date getInter_date() {
		return inter_date;
	}
	public void setInter_date(Date inter_date) {
		this.inter_date = inter_date;
	}
	public String getInter_result() {
		return inter_result;
	}
	public void setInter_result(String inter_result) {
		this.inter_result = inter_result;
	}
	public int getOffer_state() {
		return offer_state;
	}
	public void setOffer_state(int offer_state) {
		this.offer_state = offer_state;
	}
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	public String getReading_state() {
		return reading_state;
	}
	public void setReading_state(String reading_state) {
		this.reading_state = reading_state;
	}
	public int getOffer_no() {
		return offer_no;
	}
	public void setOffer_no(int offer_no) {
		this.offer_no = offer_no;
	}
	public int getJpo_no() {
		return jpo_no;
	}
	public void setJpo_no(int jpo_no) {
		this.jpo_no = jpo_no;
	}
	public String getJpo_title() {
		return jpo_title;
	}
	public void setJpo_title(String jpo_title) {
		this.jpo_title = jpo_title;
	}
	public int getGradeCnt() {
		return gradeCnt;
	}
	public void setGradeCnt(int gradeCnt) {
		this.gradeCnt = gradeCnt;
	}
	public double getInter_grade_avg() {
		return inter_grade_avg;
	}
	public void setInter_grade_avg(double inter_grade_avg) {
		this.inter_grade_avg = inter_grade_avg;
	}
	public double getSt_score_avg() {
		return st_score_avg;
	}
	public void setSt_score_avg(double st_score_avg) {
		this.st_score_avg = st_score_avg;
	}
	public String getRe_title() {
		return re_title;
	}
	public void setRe_title(String re_title) {
		this.re_title = re_title;
	}
	public String getCl_gender() {
		return cl_gender;
	}
	public void setCl_gender(String cl_gender) {
		this.cl_gender = cl_gender;
	}
	public int getCl_age() {
		return cl_age;
	}
	public void setCl_age(int cl_age) {
		this.cl_age = cl_age;
	}
	public int getInter_grade() {
		return inter_grade;
	}
	public void setInter_grade(int inter_grade) {
		this.inter_grade = inter_grade;
	}
	public int getSt_score() {
		return st_score;
	}
	public void setSt_score(int st_score) {
		this.st_score = st_score;
	}
	public Date getOffer_date() {
		return offer_date;
	}
	public void setOffer_date(Date offer_date) {
		this.offer_date = offer_date;
	}
	public String getCl_photo() {
		return cl_photo;
	}
	public void setCl_photo(String cl_photo) {
		this.cl_photo = cl_photo;
	}
	public int getInter_no() {
		return inter_no;
	}
	public void setInter_no(int inter_no) {
		this.inter_no = inter_no;
	}
	public String getInter_comment() {
		return inter_comment;
	}
	public void setInter_comment(String inter_comment) {
		this.inter_comment = inter_comment;
	}
	
	
	
	
	
}
