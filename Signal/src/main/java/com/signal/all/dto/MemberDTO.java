package com.signal.all.dto;

import java.security.Timestamp;
import java.sql.Date;

public class MemberDTO {

	private String cl_id;
	private String cl_pw;
	private String cl_name;
	private String cl_call;
	private String cl_address;
	private Date cl_birth;
	private String cl_email;
	private String cl_gender;
	private Timestamp cl_join_date;
	private String cl_state;
	private String cl_photo;
	private String st_comment;
	private int cl_age;
	
	private String com_id;
	private String com_pw;
	private String com_name;
	private String com_business_no;
	private String com_address;
	private String com_call;
	private String com_email;
	private Date com_join_date;
	private String com_state;
	private String com_photo;
	
	private String ad_id;
	private String ad_pw;
	private String ad_name;
	private String ad_call;
	private String ad_email;
	private String ad_state;
	private Date ad_date;
	private String ad_comment;
	
	// 개인회원 관리 관련 DTO
	private int cl_no;
	private Date cl_break_date;
	private String cl_break_re;
	private String cl_admin_re;
	
	// 기업회원 관리 관련 DTO
	private int com_no;
	private Date com_break_date;
	private String com_break_re;
	private String com_admin_re;
	
	// 상태변경 날짜
	private Date com_update_date;
	private Date cl_update_date;
	
	
	
	public Date getCl_update_date() {
		return cl_update_date;
	}
	public void setCl_update_date(Date cl_update_date) {
		this.cl_update_date = cl_update_date;
	}
	public Date getCom_update_date() {
		return com_update_date;
	}
	public void setCom_update_date(Date com_update_date) {
		this.com_update_date = com_update_date;
	}
	public int getCom_no() {
		return com_no;
	}
	public void setCom_no(int com_no) {
		this.com_no = com_no;
	}
	public Date getCom_break_date() {
		return com_break_date;
	}
	public void setCom_break_date(Date com_break_date) {
		this.com_break_date = com_break_date;
	}
	public String getCom_break_re() {
		return com_break_re;
	}
	public void setCom_break_re(String com_break_re) {
		this.com_break_re = com_break_re;
	}
	public String getCom_admin_re() {
		return com_admin_re;
	}
	public void setCom_admin_re(String com_admin_re) {
		this.com_admin_re = com_admin_re;
	}
	public int getCl_no() {
		return cl_no;
	}
	public void setCl_no(int cl_no) {
		this.cl_no = cl_no;
	}
	public Date getCl_break_date() {
		return cl_break_date;
	}
	public void setCl_break_date(Date cl_break_date) {
		this.cl_break_date = cl_break_date;
	}
	public String getCl_break_re() {
		return cl_break_re;
	}
	public void setCl_break_re(String cl_break_re) {
		this.cl_break_re = cl_break_re;
	}
	public String getCl_admin_re() {
		return cl_admin_re;
	}
	public void setCl_admin_re(String cl_admin_re) {
		this.cl_admin_re = cl_admin_re;
	}
	public String getAd_id() {
		return ad_id;
	}
	public void setAd_id(String ad_id) {
		this.ad_id = ad_id;
	}
	public String getAd_pw() {
		return ad_pw;
	}
	public void setAd_pw(String ad_pw) {
		this.ad_pw = ad_pw;
	}
	public String getAd_name() {
		return ad_name;
	}
	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
	}
	public String getAd_call() {
		return ad_call;
	}
	public void setAd_call(String ad_call) {
		this.ad_call = ad_call;
	}
	public String getAd_email() {
		return ad_email;
	}
	public void setAd_email(String ad_email) {
		this.ad_email = ad_email;
	}
	public String getAd_state() {
		return ad_state;
	}
	public void setAd_state(String ad_state) {
		this.ad_state = ad_state;
	}
	public Date getAd_date() {
		return ad_date;
	}
	public void setAd_date(Date ad_date) {
		this.ad_date = ad_date;
	}
	public String getAd_comment() {
		return ad_comment;
	}
	public void setAd_comment(String ad_comment) {
		this.ad_comment = ad_comment;
	}
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	public String getCom_pw() {
		return com_pw;
	}
	public void setCom_pw(String com_pw) {
		this.com_pw = com_pw;
	}
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	public String getCom_business_no() {
		return com_business_no;
	}
	public void setCom_business_no(String com_business_no) {
		this.com_business_no = com_business_no;
	}
	public String getCom_address() {
		return com_address;
	}
	public void setCom_address(String com_address) {
		this.com_address = com_address;
	}
	public String getCom_call() {
		return com_call;
	}
	public void setCom_call(String com_call) {
		this.com_call = com_call;
	}
	public String getCom_email() {
		return com_email;
	}
	public void setCom_email(String com_email) {
		this.com_email = com_email;
	}
	public Date getCom_join_date() {
		return com_join_date;
	}
	public void setCom_join_date(Date com_join_date) {
		this.com_join_date = com_join_date;
	}
	public String getCom_state() {
		return com_state;
	}
	public void setCom_state(String com_state) {
		this.com_state = com_state;
	}
	public String getCom_photo() {
		return com_photo;
	}
	public void setCom_photo(String com_photo) {
		this.com_photo = com_photo;
	}
	public String getCl_id() {
		return cl_id;
	}
	public void setCl_id(String cl_id) {
		this.cl_id = cl_id;
	}
	public String getCl_pw() {
		return cl_pw;
	}
	public void setCl_pw(String cl_pw) {
		this.cl_pw = cl_pw;
	}
	public String getCl_name() {
		return cl_name;
	}
	public void setCl_name(String cl_name) {
		this.cl_name = cl_name;
	}
	public String getCl_call() {
		return cl_call;
	}
	public void setCl_call(String cl_call) {
		this.cl_call = cl_call;
	}
	public String getCl_address() {
		return cl_address;
	}
	public void setCl_address(String cl_address) {
		this.cl_address = cl_address;
	}
	public Date getCl_birth() {
		return cl_birth;
	}
	public void setCl_birth(Date cl_birth) {
		this.cl_birth = cl_birth;
	}
	public String getCl_email() {
		return cl_email;
	}
	public void setCl_email(String cl_email) {
		this.cl_email = cl_email;
	}
	public String getCl_gender() {
		return cl_gender;
	}
	public void setCl_gender(String cl_gender) {
		this.cl_gender = cl_gender;
	}
	public Timestamp getCl_join_date() {
		return cl_join_date;
	}
	public void setCl_join_date(Timestamp cl_join_date) {
		this.cl_join_date = cl_join_date;
	}
	public String getCl_state() {
		return cl_state;
	}
	public void setCl_state(String cl_state) {
		this.cl_state = cl_state;
	}
	public String getCl_photo() {
		return cl_photo;
	}
	public void setCl_photo(String cl_photo) {
		this.cl_photo = cl_photo;
	}
	public String getSt_comment() {
		return st_comment;
	}
	public void setSt_comment(String st_comment) {
		this.st_comment = st_comment;
	}
	public int getCl_age() {
		return cl_age;
	}
	public void setCl_age(int cl_age) {
		this.cl_age = cl_age;
	}
	
	
}
