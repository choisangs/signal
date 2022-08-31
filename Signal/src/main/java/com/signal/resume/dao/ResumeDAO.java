package com.signal.resume.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.signal.all.dto.MemberDTO;
import com.signal.all.dto.ResumeDTO;
import com.signal.enter.controller.Criteria;

public interface ResumeDAO {

	ArrayList<ResumeDTO> list(String id);

	ArrayList<ResumeDTO> personList(Criteria cri);

	ResumeDTO resumeDetail(String re_no);

	ArrayList<ResumeDTO> careerDetail(String re_no);

	ArrayList<ResumeDTO> socialDetail(String re_no);

	ArrayList<ResumeDTO> licenseDetail(String re_no);
	
	ArrayList<ResumeDTO> recommendDetail(String re_no);

	int allCount(HashMap<String, Object> searchResult);

	ArrayList<ResumeDTO> personList2(HashMap<String, Object> searchResult);

	ResumeDTO resumeRegDetail(String id);	

	int resumeRegB(ResumeDTO dto);

	int resumeReg(int jp_no, int jc_no, int re_no);

	int careerReg(HashMap<String, String> params);

	int socialReg(HashMap<String, String> params);

	int licenseReg(HashMap<String, String> params);

	ArrayList<ResumeDTO> recommendMe(String id);

	ArrayList<ResumeDTO> recommendYou(String id);

	ArrayList<ResumeDTO> recommendUlist(String id);

	ArrayList<ResumeDTO> jpList();

	ArrayList<ResumeDTO> jcList(String jp_no);

	ArrayList<ResumeDTO> jcCheck(String jc_no);

	ArrayList<ResumeDTO> recommendUp(String id);

	int recommendReg(HashMap<String, String> params);

	int careerUp(HashMap<String, String> params);

	int socialUp(HashMap<String, String> params);

	int licenseUp(HashMap<String, String> params);

	int careerDelete(String ca_no);

	int socialDelete(String soc_no);

	int licenseDelete(String li_no);

	void portfolioUp(int re_no, String newFileName, String oriFileName);

	int resumeDelete(String re_no);

	void careerAddUp(String old_re_no, int re_no);

	void socialAddUp(String old_re_no, int re_no);

	void licenseAddUp(String old_re_no, int re_no);

	void recommendSelect(String reco_no, int re_no);

	void recoReg(int re_no, int reco_no);

	int personListTotal();

	ArrayList<MemberDTO> personListSearch(String searchOption, int startAge, int endAge, int skip);

	int personSearchTotal(int option, int startAge, int endAge);

	ArrayList<MemberDTO> personListSearch(int option, int startAge, int endAge, int skip);

	

	

}
