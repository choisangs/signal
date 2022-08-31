package com.signal.interview.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.signal.all.dto.EnterDTO;
import com.signal.all.dto.InterviewDTO;
import com.signal.all.dto.JobPostingDTO;
import com.signal.enter.controller.Criteria;

public interface InterviewDAO {

	ArrayList<InterviewDTO> interviewList(String cl_id);

	InterviewDTO clientObjectionReg(String inter_no);

	InterviewDTO interviewDetail(String inter_no);

	ArrayList<InterviewDTO> comInterviewList(Criteria cri, String com_id);

	int comInterviewDateDo(HashMap<String, String> params);

	ArrayList<InterviewDTO> interviewDetailResultList(String inter_no);

	int avgGrade(String cl_id);

	int countComment(String cl_id);

	InterviewDTO comInterviewUpdate(String inter_no);

	//ArrayList<InterviewDTO> queList();

	ArrayList<InterviewDTO> comInterviewUpdateQue(String inter_no);



	int comInterviewUpdateState(HashMap<String, String> params);

	int getTotal(String com_id);

	int comSearchTotal(String searchOption, String search);

	ArrayList<InterviewDTO> comSearchList(String searchOption, String search, int skip);



	ArrayList<EnterDTO> companyApplyList(String com_id, int skip, int amount);

	int getCompanyApplyTotal(String com_id);

	// by태섭, 기업이 지원자 면접 상태 변경_2022_08_18
	boolean interviewSave(int inter_no, String inter_date, String inter_result);

	// by태섭, 면접 결과 리스트_2022_08_18
	//String interResult(String inter_no);

	EnterDTO interResultList(String inter_no);

	// by태섭, 채용공고 리스트 호출
	ArrayList<JobPostingDTO> jobPostingList(String com_id);

	// by태섭, 선택된 채용공고에 대한 지원자 리스트 호출
	ArrayList<EnterDTO> jobPostingApplyList(String com_id, String jpo_no, int skip, int amount);

	//  by태섭, 선택된 채용공고 지원자 총 수
	int getJobPostingApplyTotal(String com_id, String jpo_no);

	void comUp2(HashMap<String, Object> map);

	void comUp1(String interno, String inter_comment, String inter_result);

	InterviewDTO comInterviewRegState(String inter_no);

	ArrayList<InterviewDTO> comInterviewRegQue();

	void comReg1(String interno, String inter_comment, String inter_result);

	void comReg2(HashMap<String, Object> map);

	InterviewDTO adminInterviewDetail(String inter_no);

	ArrayList<InterviewDTO> adminInterviewListDetail(String inter_no);

	int close2();

	void upHit2(String jpo_no);

	JobPostingDTO PostingDetailMainPage2(String jpo_no, String com_id);

	ArrayList<InterviewDTO> interviewDetailResultList2(String inter_no);

	InterviewDTO interviewDetail2(String inter_no);

	

}
