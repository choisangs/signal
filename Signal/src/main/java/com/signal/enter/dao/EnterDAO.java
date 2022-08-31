package com.signal.enter.dao;

import java.util.ArrayList;

import com.signal.all.dto.EnterDTO;
import com.signal.all.dto.JobPostingDTO;
import com.signal.all.dto.ResumeDTO;
import com.signal.enter.controller.Criteria;

public interface EnterDAO {

	//by태섭, 기업이 입사제안한 리스트 호출
	ArrayList<EnterDTO> offerList(String com_id, int skip, int amount);
	
	//by태섭, 기업 입사제안 페이징 처리를 위한 제안 총 갯수
	int getComOfferTotal(String com_id);

	//by태섭, 해당 기업의 채용 공고 리스트
	ArrayList<JobPostingDTO> jobPostingList(String com_id);

	//by태섭, 해당 이력서에 대한 회원 정보 
	ArrayList<ResumeDTO> personInfo(String cl_id);

	//by태섭, 입사제안 
	int offer(String cl_id, int jpo_no);

	//by태섭, 개인 회원이 받은 입사 제안 리스트
	ArrayList<EnterDTO> clientOfferList(String cl_id, int skip, int amount);
	
	//by태섭, 개인 회원 입사 제안 페이징 처리를 위한 제안 총 갯수
	public int getOfferTotal(String cl_id);

	//by태섭, 받은 입사 제안 삭제하기
	boolean deleteOffer(String[] chkArr);

	//by태섭, 채용공고 열람 시 열람 여부 변경
	void upHit(int offer_no);

	//by태섭, 개인회원 입사지원 현황 리스트 호출
	ArrayList<EnterDTO> clientApplyList(String cl_id, int skip, int amount);

	//by태섭, 개인 회원 입사지원 현황 리스트 페이징 처리를 위한 지원 총 갯수
	public int getApplyTotal(String cl_id);

	ArrayList<EnterDTO> companyApplyList(String com_id, int skip, int amount);

	int getCompanyApplyTotal(String com_id);

	// by태섭, 기업이 지원자 면접 상태 변경_2022_08_18
	boolean interviewSave(int inter_no, String inter_date, String inter_result);

	// by태섭, 면접 결과 리스트_2022_08_18
	//String interResult(String inter_no);

	EnterDTO interResultList(String inter_no);

	// by태섭, 입사제안 리스트에서 채용공고 상세보기
	JobPostingDTO clientOfferJobpostingDetail(String jpo_no);

	// by태섭, 개인회원 입사제안에서 지원하기
	int jobPostingApplyOne(String cl_id, String jpo_no, String re_no, String com_id);

	// by태섭, 채용공고 리스트에서 지원하기
	int jobPostingApplyTwo(String cl_id, String jpo_no, String re_no, String com_id);


	

	

}
