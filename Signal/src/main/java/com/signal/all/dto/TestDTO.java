package com.signal.all.dto;

import org.apache.ibatis.type.Alias;

@Alias("test")
public class TestDTO {
	private String cl_id;
	private int st_no;
	private int st_score;
	 private int scoreCnt;
	public int getScoreCnt() {
		return scoreCnt;
	}
	public void setScoreCnt(int scoreCnt) {
		this.scoreCnt = scoreCnt;
	}
	private int cntStScore;
	public int getCntStScore() {
		return cntStScore;
	}
	public void setCntStScore(int cntStScore) {
		this.cntStScore = cntStScore;
	}
	private String st_que;
	private int st_hidden;
	private String st_keyword;
	private int it_no;
	private String it_que;
	private int it_hidden;
	private String it_keyword;
	private int inter_no;
	private String inter_comment;
	public String getInter_comment() {
		return inter_comment;
	}
	public void setInter_comment(String inter_comment) {
		this.inter_comment = inter_comment;
	}
	public int getInter_no() {
		return inter_no;
	}
	public void setInter_no(int inter_no) {
		this.inter_no = inter_no;
	}
	public int getInter_score() {
		return inter_score;
	}
	public void setInter_score(int inter_score) {
		this.inter_score = inter_score;
	}
	private int inter_score;
	public int getIt_no() {
		return it_no;
	}
	public void setIt_no(int it_no) {
		this.it_no = it_no;
	}
	public String getIt_que() {
		return it_que;
	}
	public void setIt_que(String it_que) {
		this.it_que = it_que;
	}
	public int getIt_hidden() {
		return it_hidden;
	}
	public void setIt_hidden(int it_hidden) {
		this.it_hidden = it_hidden;
	}
	public String getIt_keyword() {
		return it_keyword;
	}
	public void setIt_keyword(String it_keyword) {
		this.it_keyword = it_keyword;
	}
	public String getCl_id() {
		return cl_id;
	}
	public void setCl_id(String cl_id) {
		this.cl_id = cl_id;
	}
	public int getSt_no() {
		return st_no;
	}
	public void setSt_no(int st_no) {
		this.st_no = st_no;
	}
	public int getSt_score() {
		return st_score;
	}
	public void setSt_score(int st_score) {
		this.st_score = st_score;
	}
	public String getSt_que() {
		return st_que;
	}
	public void setSt_que(String st_que) {
		this.st_que = st_que;
	}
	public int getSt_hidden() {
		return st_hidden;
	}
	public void setSt_hidden(int st_hidden) {
		this.st_hidden = st_hidden;
	}
	public String getSt_keyword() {
		return st_keyword;
	}
	public void setSt_keyword(String st_keyword) {
		this.st_keyword = st_keyword;
	}
	
	
}
