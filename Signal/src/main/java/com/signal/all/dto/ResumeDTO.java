package com.signal.all.dto;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;

@Alias("resume")
public class ResumeDTO {

	private String cl_id;
	private String cl_name;
	private String cl_call;
	private String cl_address;
	private Date cl_birth;
	private String cl_email;
	private String cl_photo;
	
	private int cl_age;
	private String cl_gender;
	private int inter_grade;
	private String inter_comment;
	private int st_score;
	private int re_no;
	private String re_title;
	private String re_fn_status;
	private int re_hidden;
	private String re_sch_name;
	private String re_sch_period;
	private String re_major;
	private String re_average;
	private String re_total;
	private String re_register;
	private String re_intro;
	private String re_portfolio;
	private String re_portfolio_ori;
	private Date re_regDate;
	
	public Date getRe_regDate() {
		return re_regDate;
	}
	public void setRe_regDate(Date re_regDate) {
		this.re_regDate = re_regDate;
	}
	public String getRe_portfolio_ori() {
		return re_portfolio_ori;
	}
	public void setRe_portfolio_ori(String re_portfolio_ori) {
		this.re_portfolio_ori = re_portfolio_ori;
	}
	private int jp_no;
	private String jp_name;
	private int jp_hidden;
	private int jc_no;
	private String jc_name;
	private int jc_hidden;
	private String li_field;
	
	private int li_no;
	private String li_date;
	private String li_org;
	private String li_name;
	private int ca_no;
	private String ca_com_name;
	private String ca_period;
	private String ca_job;
	private String ca_content;
	private int soc_no;
	private String soc_field;
	private String soc_name;
	private String soc_content;
	private String soc_period;
	private int reco_no;
	private String reco_cl_id;
	private String reco_req_memo;
	private String reco_state;
	private String reco_relation;
	private String reco_content;
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date reco_date;
	
	private double avr_inter_grade;
	private int cnt_inter;
	private double avr_st_score;
	
	//by 태섭, 필요한 컬럼 추가
	private int jpo_no;
	
	public int getJpo_no() {
		return jpo_no;
	}
	public void setJpo_no(int jpo_no) {
		this.jpo_no = jpo_no;
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
	public String getCl_photo() {
		return cl_photo;
	}
	public void setCl_photo(String cl_photo) {
		this.cl_photo = cl_photo;
	}
	
	public double getAvr_inter_grade() {
		return avr_inter_grade;
	}
	public void setAvr_inter_grade(double avr_inter_grade) {
		this.avr_inter_grade = avr_inter_grade;
	}
	public int getCnt_inter() {
		return cnt_inter;
	}
	public void setCnt_inter(int cnt_inter) {
		this.cnt_inter = cnt_inter;
	}
	public double getAvr_st_score() {
		return avr_st_score;
	}
	public void setAvr_st_score(double avr_st_score) {
		this.avr_st_score = avr_st_score;
	}
	public int getCl_age() {
		return cl_age;
	}
	public void setCl_age(int cl_age) {
		this.cl_age = cl_age;
	}
	public String getCl_gender() {
		return cl_gender;
	}
	public void setCl_gender(String cl_gender) {
		this.cl_gender = cl_gender;
	}
	public int getInter_grade() {
		return inter_grade;
	}
	public void setInter_grade(int inter_grade) {
		this.inter_grade = inter_grade;
	}
	public String getInter_comment() {
		return inter_comment;
	}
	public void setInter_comment(String inter_comment) {
		this.inter_comment = inter_comment;
	}
	public int getSt_score() {
		return st_score;
	}
	public void setSt_score(int st_score) {
		this.st_score = st_score;
	}
	public int getRe_no() {
		return re_no;
	}
	public void setRe_no(int re_no) {
		this.re_no = re_no;
	}
	public String getCl_id() {
		return cl_id;
	}
	public void setCl_id(String cl_id) {
		this.cl_id = cl_id;
	}
	public String getRe_title() {
		return re_title;
	}
	public void setRe_title(String re_title) {
		this.re_title = re_title;
	}
	public String getRe_fn_status() {
		return re_fn_status;
	}
	public void setRe_fn_status(String re_fn_status) {
		this.re_fn_status = re_fn_status;
	}
	public int getRe_hidden() {
		return re_hidden;
	}
	public void setRe_hidden(int re_hidden) {
		this.re_hidden = re_hidden;
	}
	public String getRe_sch_name() {
		return re_sch_name;
	}
	public void setRe_sch_name(String re_sch_name) {
		this.re_sch_name = re_sch_name;
	}
	public String getRe_sch_period() {
		return re_sch_period;
	}
	public void setRe_sch_period(String re_sch_period) {
		this.re_sch_period = re_sch_period;
	}
	public String getRe_major() {
		return re_major;
	}
	public void setRe_major(String re_major) {
		this.re_major = re_major;
	}
	public String getRe_average() {
		return re_average;
	}
	public void setRe_average(String re_average) {
		this.re_average = re_average;
	}
	public String getRe_total() {
		return re_total;
	}
	public void setRe_total(String re_total) {
		this.re_total = re_total;
	}
	public String getRe_register() {
		return re_register;
	}
	public void setRe_register(String re_register) {
		this.re_register = re_register;
	}
	public String getRe_intro() {
		return re_intro;
	}
	public void setRe_intro(String re_intro) {
		this.re_intro = re_intro;
	}
	public String getRe_portfolio() {
		return re_portfolio;
	}
	public void setRe_portfolio(String re_portfolio) {
		this.re_portfolio = re_portfolio;
	}
	public int getJp_no() {
		return jp_no;
	}
	public void setJp_no(int jp_no) {
		this.jp_no = jp_no;
	}
	public String getJp_name() {
		return jp_name;
	}
	public void setJp_name(String jp_name) {
		this.jp_name = jp_name;
	}
	public int getJp_hidden() {
		return jp_hidden;
	}
	public void setJp_hidden(int jp_hidden) {
		this.jp_hidden = jp_hidden;
	}
	public int getJc_no() {
		return jc_no;
	}
	public void setJc_no(int jc_no) {
		this.jc_no = jc_no;
	}
	public String getJc_name() {
		return jc_name;
	}
	public void setJc_name(String jc_name) {
		this.jc_name = jc_name;
	}
	public int getJc_hidden() {
		return jc_hidden;
	}
	public void setJc_hidden(int jc_hidden) {
		this.jc_hidden = jc_hidden;
	}
	public String getLi_field() {
		return li_field;
	}
	public void setLi_field(String li_field) {
		this.li_field = li_field;
	}
	public int getLi_no() {
		return li_no;
	}
	public void setLi_no(int li_no) {
		this.li_no = li_no;
	}
	public String getLi_date() {
		return li_date;
	}
	public void setLi_date(String li_date) {
		this.li_date = li_date;
	}
	public String getLi_org() {
		return li_org;
	}
	public void setLi_org(String li_org) {
		this.li_org = li_org;
	}
	public String getLi_name() {
		return li_name;
	}
	public void setLi_name(String li_name) {
		this.li_name = li_name;
	}
	public int getCa_no() {
		return ca_no;
	}
	public void setCa_no(int ca_no) {
		this.ca_no = ca_no;
	}
	public String getCa_com_name() {
		return ca_com_name;
	}
	public void setCa_com_name(String ca_com_name) {
		this.ca_com_name = ca_com_name;
	}
	public String getCa_period() {
		return ca_period;
	}
	public void setCa_period(String ca_period) {
		this.ca_period = ca_period;
	}
	public String getCa_job() {
		return ca_job;
	}
	public void setCa_job(String ca_job) {
		this.ca_job = ca_job;
	}
	public String getCa_content() {
		return ca_content;
	}
	public void setCa_content(String ca_content) {
		this.ca_content = ca_content;
	}
	public int getSoc_no() {
		return soc_no;
	}
	public void setSoc_no(int soc_no) {
		this.soc_no = soc_no;
	}
	public String getSoc_field() {
		return soc_field;
	}
	public void setSoc_field(String soc_field) {
		this.soc_field = soc_field;
	}
	public String getSoc_name() {
		return soc_name;
	}
	public void setSoc_name(String soc_name) {
		this.soc_name = soc_name;
	}
	public String getSoc_content() {
		return soc_content;
	}
	public void setSoc_content(String soc_content) {
		this.soc_content = soc_content;
	}
	public String getSoc_period() {
		return soc_period;
	}
	public void setSoc_period(String soc_period) {
		this.soc_period = soc_period;
	}
	public int getReco_no() {
		return reco_no;
	}
	public void setReco_no(int reco_no) {
		this.reco_no = reco_no;
	}
	public String getReco_cl_id() {
		return reco_cl_id;
	}
	public void setReco_cl_id(String reco_cl_id) {
		this.reco_cl_id = reco_cl_id;
	}
	public String getReco_req_memo() {
		return reco_req_memo;
	}
	public void setReco_req_memo(String reco_req_memo) {
		this.reco_req_memo = reco_req_memo;
	}
	public String getReco_state() {
		return reco_state;
	}
	public void setReco_state(String reco_state) {
		this.reco_state = reco_state;
	}
	public String getReco_relation() {
		return reco_relation;
	}
	public void setReco_relation(String reco_relation) {
		this.reco_relation = reco_relation;
	}
	public Date getReco_date() {
		return reco_date;
	}
	public void setReco_date(Date reco_date) {
		this.reco_date = reco_date;
	}
	public String getReco_content() {
		return reco_content;
	}
	public void setReco_content(String reco_content) {
		this.reco_content = reco_content;
	}
	
	
	
}
