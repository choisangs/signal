package com.signal.all.dto;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

@Alias("callendar")
public class CallendarDTO {
	private int rankCom;
	public int getRankCom() {
		return rankCom;
	}
	public void setRankCom(int rankCom) {
		this.rankCom = rankCom;
	}
	private int jpo_no;
	private String com_id;
	private String jpo_title;
	public String getJpo_title() {
		return jpo_title;
	}
	public void setJpo_title(String jpo_title) {
		this.jpo_title = jpo_title;
	}
	private Date jpo_start;
	private Date jpo_deadline;
	private String jpo_state;
	private String com_name;
	
	private int jp_no;
	private int jc_no;
	private String jp_name;
	private String jc_name;
	
	
	public int getJp_no() {
		return jp_no;
	}
	public void setJp_no(int jp_no) {
		this.jp_no = jp_no;
	}
	public int getJc_no() {
		return jc_no;
	}
	public void setJc_no(int jc_no) {
		this.jc_no = jc_no;
	}
	public String getJp_name() {
		return jp_name;
	}
	public void setJp_name(String jp_name) {
		this.jp_name = jp_name;
	}
	public String getJc_name() {
		return jc_name;
	}
	public void setJc_name(String jc_name) {
		this.jc_name = jc_name;
	}
	public int getJpo_no() {
		return jpo_no;
	}
	public void setJpo_no(int jpo_no) {
		this.jpo_no = jpo_no;
	}
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	public Date getJpo_start() {
		return jpo_start;
	}
	public void setJpo_start(Date jpo_start) {
		this.jpo_start = jpo_start;
	}
	public Date getJpo_deadline() {
		return jpo_deadline;
	}
	public void setJpo_deadline(Date jpo_deadline) {
		this.jpo_deadline = jpo_deadline;
	}
	public String getJpo_state() {
		return jpo_state;
	}
	public void setJpo_state(String jpo_state) {
		this.jpo_state = jpo_state;
	}
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	
}
