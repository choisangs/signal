package com.signal.interview.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mariadb.jdbc.internal.logging.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.signal.all.dto.EnterDTO;
import com.signal.all.dto.InterviewDTO;
import com.signal.all.dto.JobPostingDTO;
import com.signal.enter.controller.Criteria;
import com.signal.interview.dao.InterviewDAO;

@Service
public class InterviewService {
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
	@Autowired InterviewDAO dao;

	public ArrayList<InterviewDTO> interviewList(String cl_id) {
	
		return dao.interviewList(cl_id);
	}

	public InterviewDTO clientObjectionReg(String inter_no) {
		
		return dao.clientObjectionReg(inter_no);
	}

	public InterviewDTO interviewDetail(String inter_no) {
	
		return dao.interviewDetail(inter_no);
	}

	public ArrayList<InterviewDTO> comInterviewList(Criteria cri, String com_id) {
		
		return dao.comInterviewList(cri,com_id);
	}

	public void comInterviewDateDo(HashMap<String, String> params) {
		int row =dao.comInterviewDateDo(params);
		
	}

	public ArrayList<InterviewDTO> interviewDetailResultList(String inter_no) {
		
		return dao.interviewDetailResultList(inter_no);
	}

	public float avgGrade(String cl_id) {
		
		return dao.avgGrade(cl_id);
	}

	public int countComment(String cl_id) {
	
		return dao.countComment(cl_id);

	}

	public InterviewDTO comInterviewUpdate(String inter_no) {
		
		return dao.comInterviewUpdate(inter_no);
	}

	//public ArrayList<InterviewDTO> queList() {
		
		//return dao.queList();
	//}

	public ArrayList<InterviewDTO> comInterviewUpdateQue(String inter_no) {
		
		return dao.comInterviewUpdateQue(inter_no);
	}

	public void comInterviewUpdateDo(HashMap<String, String> params) {
		
	
		
		int row =dao.comInterviewUpdateState(params);
		
 		

		
		
	}

	public int getTotal(String com_id) {
		logger.info("????????????  ????????? ??????");
		return dao.getTotal(com_id);
	}

	public ArrayList<InterviewDTO> comSearchList(String searchOption, String search, int skip) {
		
		return dao.comSearchList(searchOption,search,skip);
	}

	public int comSearchTotal(String searchOption, String search) {
	
		return dao.comSearchTotal(searchOption, search);
	}

	// by??????, ?????? ??????????????? ??????????????????_2022_08_17
		public ArrayList<EnterDTO> companyApplyList(String com_id, int skip, int amount) {
			logger.info("?????? ??????????????? ?????????????????? ????????? ????????? ??????");
			return dao.companyApplyList(com_id, skip, amount);
		}

		// by??????, ?????? ??????????????? ???????????? ??? ??????_2022_08_17
		public int getCompanyApplyTotal(String com_id) {
			return dao.getCompanyApplyTotal(com_id);
		}

		// by??????, ?????? ??????????????? ?????????????????? ?????? ?????? ????????????_2022_08_18
		public boolean interviewSave(int inter_no, String inter_date, String inter_result) {
			return dao.interviewSave(inter_no, inter_date, inter_result);		
		}

		// by??????, ?????? ??????
		 /*
		public String interResult(String inter_no) {
			logger.info("?????? ?????? ????????? ?????? ?????????");
			return dao.interResult(inter_no);
		}*/

		public EnterDTO interResultList(String inter_no) {
			logger.info("?????? ?????? ????????? ?????? ?????????");
			return dao.interResultList(inter_no);
		}

		// by??????, ???????????? ????????? ????????????
		public ArrayList<JobPostingDTO> jobPostingList(String com_id) {
			logger.info("?????? ???????????? ????????? ??????");
			return dao.jobPostingList(com_id);
		}

		public ArrayList<EnterDTO> jobPostingApplyList(String com_id, String jpo_no, int skip, int amount) {
			logger.info("????????? ???????????? ????????? ??????");
			return dao.jobPostingApplyList(com_id,jpo_no,skip,amount);
		}

		public int getJobPostingApplyTotal(String com_id, String jpo_no) {
			logger.info("????????? ?????? ??? ???????????? ????????? ??????");
			return dao.getJobPostingApplyTotal(com_id, jpo_no);
		}

		public boolean comUp(HashMap<String, Object> param) {
			boolean success=false;
			
			HashMap<String, Object> values = (HashMap<String, Object>) param.get("values");
			ArrayList<String> inter_no = (ArrayList<String>) values.get("inter_no");
			ArrayList<String> it_no	= (ArrayList<String>) values.get("it_no");
			ArrayList<String> inter_score2 = (ArrayList<String>) values.get("inter_score2");
			System.out.println("inter_no="+inter_no+"it_no="+it_no	+"inter_score2 ="+inter_score2 +inter_no.size());		
			
			String inter_comment = (String) values.get("inter_comment");
			String inter_result = (String) values.get("inter_result");
			String interno=inter_no.get(0);
			
			dao.comUp1(interno,inter_comment,inter_result);
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			for (int i = 0; i < inter_no.size(); i++) {
				map.put("inter_no", inter_no.get(i));
				map.put("it_no", it_no.get(i));
				map.put("inter_score", inter_score2.get(i));
				dao.comUp2(map);
			}	
			
			return false;

		}
		public boolean comReg(HashMap<String, Object> param) {
			HashMap<String, Object> values = (HashMap<String, Object>) param.get("values");
			ArrayList<String> inter_no = (ArrayList<String>) values.get("inter_no");
			ArrayList<String> it_no	= (ArrayList<String>) values.get("it_no");
			ArrayList<String> inter_score2 = (ArrayList<String>) values.get("inter_score2");
			System.out.println("inter_no="+inter_no+"it_no="+it_no	+"inter_score2 ="+inter_score2 +inter_no.size());		
			
			String inter_comment = (String) values.get("inter_comment");
			String inter_result = (String) values.get("inter_result");
			String interno=inter_no.get(0);
			
			dao.comReg1(interno,inter_comment,inter_result);
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			for (int i = 0; i < inter_no.size(); i++) {
				map.put("inter_no", inter_no.get(i));
				map.put("it_no", it_no.get(i));
				map.put("inter_score", inter_score2.get(i));
				dao.comReg2(map);
			}	
			
			return false;
		}
		
		
		
		
		
		
		public InterviewDTO comInterviewRegState(String inter_no) {
			
			return dao.comInterviewRegState(inter_no);
		}

		public ArrayList<InterviewDTO> comInterviewRegQue() {
			
			return dao.comInterviewRegQue();
		}

		public InterviewDTO adminInterviewDetail(String inter_no) {
			
			return dao.adminInterviewDetail(inter_no);
		}

		public ArrayList<InterviewDTO> adminInterviewListDetail(String inter_no) {
			
			return dao.adminInterviewListDetail(inter_no);
		}

		public void close2() {
			logger.info("???????????? ?????? ????????? ??????");
			int result = dao.close2();
			logger.info("???????????? ?????? ????????? ??? : " + result);
			
		}

		public void upHit2(String jpo_no) {
			logger.info(jpo_no + " ??? ???????????? ????????? +1");
			dao.upHit2(jpo_no);
			
		}

		public void PostingDetailMainPage2(Model model, String jpo_no, String com_id) {
			JobPostingDTO dto = dao.PostingDetailMainPage2(jpo_no,com_id);
		      logger.info("???????????? ???????" + jpo_no +" / "+ com_id);
		      
		      model.addAttribute("dto", dto);
		   
			
		}

		public InterviewDTO interviewDetail2(String inter_no) {
			
			return dao.interviewDetail2(inter_no);
		}

		public ArrayList<InterviewDTO> interviewDetailResultList2(String inter_no) {
			
			return dao.interviewDetailResultList2(inter_no);
		}




	
}
