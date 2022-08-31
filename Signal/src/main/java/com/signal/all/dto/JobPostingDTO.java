package com.signal.all.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class JobPostingDTO {
	
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
	private int ci_no;
	private String ci_ceo;
	private String ci_emp;
	private String ci_web;
	private String ci_intro;
	private String ci_pass_intro;
	private String ci_photo;
	private Float comment_a;
	private int apply_no;
	private int comment;
	private int apply;
	private String jpo_no;
	private String jpo_title;
	private String jpo_type;
	private String jpo_region;
	private String jpo_field;
	private String jpo_start;
	private String jpo_deadline;
	private String jpo_education;
	private String jpo_salary;
	private String jpo_contact;
	private String jpo_name;
	private String jpo_welfare;
	private String jpo_photo;
	private String jpo_state;
	private int jpo_views;
	private String jp_name;
	private String jc_name;
	private String jp_no;
	private String jc_no;
	private String re_no;	
	private boolean jp_hidden;
	private boolean jc_hidden;
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	

	public int getApply_no() {
		return apply_no;
	}
	public boolean isJp_hidden() {
		return jp_hidden;
	}
	public void setJp_hidden(boolean jp_hidden) {
		this.jp_hidden = jp_hidden;
	}
	public boolean isJc_hidden() {
		return jc_hidden;
	}
	public void setJc_hidden(boolean jc_hidden) {
		this.jc_hidden = jc_hidden;
	}
	public String getRe_no() {
		return re_no;
	}
	public void setRe_no(String re_no) {
		this.re_no = re_no;
	}
	public void setApply_no(int apply_no) {
		this.apply_no = apply_no;
	}
	public String getJc_no() {
		return jc_no;
	}
	public void setJc_no(String jc_no) {
		this.jc_no = jc_no;
	}
	public String getJp_no() {
		return jp_no;
	}
	public void setJp_no(String jp_no) {
		this.jp_no = jp_no;
	}
	public String getJpo_no() {
		return jpo_no;
	}
	public void setJpo_no(String jpo_no) {
		this.jpo_no = jpo_no;
	}

	public int getJpo_views() {
		return jpo_views;
	}
	public void setJpo_views(int jpo_views) {
		this.jpo_views = jpo_views;
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
	public String getJpo_title() {
		return jpo_title;
	}
	public void setJpo_title(String jpo_title) {
		this.jpo_title = jpo_title;
	}
	public String getJpo_type() {
		return jpo_type;
	}
	public void setJpo_type(String jpo_type) {
		this.jpo_type = jpo_type;
	}
	public String getJpo_region() {
		return jpo_region;
	}
	public void setJpo_region(String jpo_region) {
		this.jpo_region = jpo_region;
	}
	public String getJpo_field() {
		return jpo_field;
	}
	public void setJpo_field(String jpo_field) {
		this.jpo_field = jpo_field;
	}

	public String getJpo_start() {
		return jpo_start;
	}
	public void setJpo_start(String jpo_start) {
		this.jpo_start = jpo_start;
	}
	public String getJpo_deadline() {
		return jpo_deadline;
	}
	public void setJpo_deadline(String jpo_deadline) {
		this.jpo_deadline = jpo_deadline;
	}
	public String getJpo_education() {
		return jpo_education;
	}
	public void setJpo_education(String jpo_education) {
		this.jpo_education = jpo_education;
	}
	public String getJpo_salary() {
		return jpo_salary;
	}
	public void setJpo_salary(String jpo_salary) {
		this.jpo_salary = jpo_salary;
	}
	public String getJpo_contact() {
		return jpo_contact;
	}
	public void setJpo_contact(String jpo_contact) {
		this.jpo_contact = jpo_contact;
	}
	public String getJpo_name() {
		return jpo_name;
	}
	public void setJpo_name(String jpo_name) {
		this.jpo_name = jpo_name;
	}
	public String getJpo_welfare() {
		return jpo_welfare;
	}
	public void setJpo_welfare(String jpo_welfare) {
		this.jpo_welfare = jpo_welfare;
	}
	public String getJpo_photo() {
		return jpo_photo;
	}
	public void setJpo_photo(String jpo_photo) {
		this.jpo_photo = jpo_photo;
	}
	public String getJpo_state() {
		return jpo_state;
	}
	public void setJpo_state(String jpo_state) {
		this.jpo_state = jpo_state;
	}
	
	public int getApply() {
		return apply;
	}
	public void setApply(int apply) {
		this.apply = apply;
	}
	public Float getComment_a() {
		return comment_a;
	}
	public void setComment_a(Float comment_a) {
		this.comment_a = comment_a;
	}
	public int getComment() {
		return comment;
	}
	public void setComment(int comment) {
		this.comment = comment;
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
	public int getCi_no() {
		return ci_no;
	}
	public void setCi_no(int ci_no) {
		this.ci_no = ci_no;
	}
	public String getCi_ceo() {
		return ci_ceo;
	}
	public void setCi_ceo(String ci_ceo) {
		this.ci_ceo = ci_ceo;
	}
	public String getCi_emp() {
		return ci_emp;
	}
	
	public void setCi_emp(String ci_emp) {
		this.ci_emp = ci_emp;
	}
	public String getCi_web() {
		return ci_web;
	}
	public void setCi_web(String ci_web) {
		this.ci_web = ci_web;
	}
	public String getCi_intro() {
		return ci_intro;
	}
	public void setCi_intro(String ci_intro) {
		this.ci_intro = ci_intro;
	}
	public String getCi_pass_intro() {
		return ci_pass_intro;
	}
	public void setCi_pass_intro(String ci_pass_intro) {
		this.ci_pass_intro = ci_pass_intro;
	}
	public String getCi_photo() {
		return ci_photo;
	}
	public void setCi_photo(String ci_photo) {
		this.ci_photo = ci_photo;
	}



	
	
	
	
	
	
}
