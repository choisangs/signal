package com.signal.enter.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.signal.all.dto.EnterDTO;
import com.signal.all.dto.JobPostingDTO;
import com.signal.all.dto.PageMakerDTO;
import com.signal.all.dto.ResumeDTO;
import com.signal.enter.service.EnterService;
import com.signal.resume.service.ResumeService;

//by태섭, 관리자 마이페이지 입사제안관리 기능 2022년 8월 8일

@Controller
public class EnterController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	// by태섭, service 객체를 한 번만 선언하고 계속 사용한다.
	@Autowired EnterService service;
	@Autowired ResumeService reService;
	
	// by태섭, 입사제안현황 페이지로 이동 시 리스트 호출_2022_08_13
	@RequestMapping(value = "/companyOfferList.do", method = RequestMethod.GET)
	public String companyOfferList(Model model, HttpSession session, Criteria cri) {
		logger.info("입사제안현황 리스트 요청");
		// by태섭, 해당 기업 id를 인자값으로 넣어주기 변수에 세션 값 넣어주기
		String com_id = (String) session.getAttribute("loginId");
		
		// by태섭, 페이징 처리를 Criteria 클래스에서 skip, amount 변수 가져오기
		int skip = cri.getSkip();
		int amount = cri.getAmount();
		
		// by태섭, 페이징처리를 적용한 기업이 입사제안한 리스트 호출
		ArrayList<EnterDTO> offerList = service.offerList(com_id,skip,amount);
		model.addAttribute("offerList", offerList);
		
		// by태섭, 페이징 인터페이스 처리 부분
		int total = service.getComOfferTotal(com_id);
		PageMakerDTO pageMake = new PageMakerDTO(cri, total);
		//PageMaker 데이터를 view로 보내기 위함
		model.addAttribute("pageMaker", pageMake);
		return "companyOfferList2"; 
	}
	
	// by태섭, 기업이 일반회원에게 입사제안 페이지 이동_2022_08_10 
	// 팝업창 떠야 한다.
	@RequestMapping(value = "/offer.go")
	public String offer(Model model, @RequestParam String cl_id, HttpSession session) {
		logger.info("입사제안 요청 ID : "+cl_id);
		logger.info("채용공고 리스트 호출");
		//by태섭, 해당 기업 id를 인자값으로 넣어주기 변수에 세션 값 넣어주기
		String com_id = (String) session.getAttribute("loginId");
		
		// by태섭, 기업 채용공고 리스트 호출
		ArrayList<JobPostingDTO> jobPostingList = service.jobPostingList(com_id);
		model.addAttribute("jobPostingList", jobPostingList);
		model.addAttribute("cl_id",cl_id);
		// by태섭, 해당 이력서에 대한 회원 정보 불러오기
		ArrayList<ResumeDTO> personInfo = service.personInfo(cl_id);
		model.addAttribute("personInfo", personInfo);
		return "personOffer";
	}
	
	// by태섭, 기업이 일반회원에게 입사제안_2022_08_10 
	@RequestMapping(value = "/offer.do")
	public String offerDo(Model model, @RequestParam String cl_id, int jpo_no ) {
		logger.info("입사제안 요청 아이디 : "+cl_id);		
		logger.info("입사제안 요청 공고 번호 : "+jpo_no);
		// by태섭, 팝업창 닫기 위해 변수 선언
		String popupClose = "popupClose";
		
		// by태섭, 제안 테이블에 넣기 위해 해당 이력서 번호와 채용공고 번호를 인자값으로 넣는다.
		service.offer(cl_id, jpo_no);

		model.addAttribute("popupClose", popupClose);

		return "personOffer";
	}
	
	
	// by태섭, 기업 채용공고 리스트 호출_2022_08_09 
	/* 입사제안 팝업창 시도 코드 
	@RequestMapping(value = "/jobPostingList.go") 
	public String jobPostingList(Model model) { 
		logger.info("채용공고 리스트 호출"); // 나중에 해당 기업이 올린 공고리스트만 보여줘야 한다. 인자값으로 com_id 
		ArrayList<JobPostingDTO> jobPostingList = service.jobPostingList(); 
		model.addAttribute("jobPostingList", jobPostingList); 
		return "comOfferJobPostingList"; 
	}
	*/
	
	
	// by태섭, 개인 마이페이지 입사제안현황 기능_2022_08_11
	@RequestMapping(value = "/clientOfferList.go")
	public String clientOfferList(Model model, Criteria cri, HttpSession session) {
		logger.info("입사제안 리스트 호출");
		
		// by태섭, 세션에서 회원 아이디 값 가져오기
		String cl_id = (String) session.getAttribute("loginId");
		// by태섭, 페이징 처리에 skip, amount 변수
		int skip = cri.getSkip();
		int amount = cri.getAmount();
		
		//  by태섭, 페이징 처리한 리스트 호출
		ArrayList<EnterDTO> clientOfferList = service.clientOfferList(cl_id,skip,amount);
		model.addAttribute("clientOfferList", clientOfferList);
		
		// by태섭, 페이징 인터페이스 처리 부분
		int total = service.getOfferTotal(cl_id);
        PageMakerDTO pageMake = new PageMakerDTO(cri, total);
        // by태섭, PageMaker 데이터를 view로 보내기 위함
        model.addAttribute("pageMaker", pageMake);
		
		return "clientOfferList";
	}
	
	// by태섭, 개인 입사제안 선택 후 삭제 기능_2022_08_11	
	@RequestMapping(value = "/deleteOffer.do", method = RequestMethod.POST)
	public String delete(Model model, @RequestParam String[] chkArr) {//같은 이름으로 여러 개 올 경우 이렇게 받아줘야 한다.
		logger.info("입사제안 삭제 요청");
		
		// by태섭, 제안 삭제 요청 chkArr에 offer_no 담아서 삭제 요청
		boolean success = service.deleteOffer(chkArr);
		logger.info("삭제 성공 여부 : "+success);
		// by태섭, 삭제 후 다시 입사제안현황 페이지로 보내주자
		return "redirect:/clientOfferList.go";
	}
	
	//by태섭, 입사제안현황에서 공고제목 눌렀을 때 채용공고 보여주고 열람여부 변경_2022_08_12
	@RequestMapping(value = "/jobPostingDetail.go")
	public String jobPostingDetail(Model model, @RequestParam int offer_no, String jpo_no, String com_id, int curState) {
		logger.info("채용공고 상세보기 요청");
		// by태섭, 채용공고 상세보기
		
		service.jobPostingState(offer_no);
		// by태섭, 임시로 personOffer로 보내준다. 채용공고 완성 시 채용공고로 변경
		return "redirect:/PostingDetailMain.go?jpo_no="+jpo_no+"&com_id="+com_id+"&curState="+curState;
	}
	
	//by태섭, 개인 마이페이지 입사지원 현황 리스트 호출_2022_08_11
	@RequestMapping(value = "/clientApplyList.go")
	public String clientApplyList(Model model, Criteria cri, HttpSession session) {
		logger.info("입사지원 리스트 호출");
		
		// by태섭, 세션에서 회원 아이디 값 가져오기
		String cl_id = (String) session.getAttribute("loginId");
		
		// by태섭, 페이징 처리에 skip, amount 변수
		int skip = cri.getSkip();
		int amount = cri.getAmount();
		
		//  by태섭, 페이징 처리한 리스트 호출
		ArrayList<EnterDTO> clientApplyList = service.clientApplyList(cl_id,skip,amount);
		model.addAttribute("clientApplyList", clientApplyList);
		
		// by태섭, 페이징 인터페이스 처리 부분
		int total = service.getApplyTotal(cl_id);
		PageMakerDTO pageMake = new PageMakerDTO(cri, total);
        //PageMaker 데이터를 view로 보내기 위함
        model.addAttribute("pageMaker", pageMake);
        
		return "clientApplyList";
	}
	
	// by태섭, 기업 마이페이지 입사지원 관리 리스트 호출_2022_08_17
	/*
	@RequestMapping(value = "/companyApplyList.go")
	public String companyApplyList(Model model, Criteria cri, HttpSession session) {
		logger.info("기업 입사지원 리스트 호출");
		
		// by태섭, 세션에서 회원 아이디 값 가져오기
		String com_id = (String) session.getAttribute("loginId");
		
		// by태섭, 페이징 처리에 skip, amount 변수
		int skip = cri.getSkip();
		int amount = cri.getAmount();
		
		//  by태섭, 페이징 처리한 리스트 호출
		ArrayList<EnterDTO> companyApplyList = service.companyApplyList(com_id,skip,amount);
		model.addAttribute("companyApplyList", companyApplyList);
		
		// by태섭, 페이징 인터페이스 처리 부분
		int total = service.getCompanyApplyTotal(com_id);
		PageMakerDTO pageMake = new PageMakerDTO(cri, total);
        //PageMaker 데이터를 view로 보내기 위함
        model.addAttribute("pageMaker", pageMake);
        
		return "companyApplyList";
	}*/
	
	// by태섭, 기업 마이페이지 입사지원 관리 리스트에서 면접 상태 선택 팝업창_2022_08_17
	/*
	@RequestMapping(value = "/companyApplyPopup.go")
	public String companyApplyPopup(Model model, @RequestParam String inter_no) {
		logger.info("팝업창 띄우기");
        logger.info("면접 번호 : "+inter_no);
        //String interResult = service.interResult(inter_no);
        EnterDTO dto = service.interResultList(inter_no);
        model.addAttribute("interResult", dto);
		return "interviewResultPopup";
	}*/
	
	// by태섭, 기업 마이페이지 입사지원 관리 팝업창에서 면접 상태 저장하기_2022_08_17
	/*
	@RequestMapping(value = "/interviewSave.do", method = RequestMethod.GET)
	public String interviewSave(Model model, @RequestParam int inter_no, String inter_date, String inter_result) {
		logger.info("면접 번호 : "+inter_no);
		
		// by태섭, 팝업창 닫기 위해 변수 선언
		String popupClose = "popupClose";

		//String page = "redirect:/companyApplyList.go";
		
		// by태섭, 수정이 성공했는지 확인하기 위해 
		boolean success = service.interviewSave(inter_no, inter_date, inter_result);
		logger.info("상태 변경 성공 여부 : "+success);
		
		model.addAttribute("popupClose", popupClose);		
		
		return "interviewResultPopup";
	}*/
	
	
	// by태섭, 개인회원이 채용공고 보고 지원하기_2022_08_18
	@RequestMapping(value = "/applyOne.do")
	public String applyOne(Model model, HttpSession session, @RequestParam String jpo_no, String re_no, String com_id) {
		
		// by태섭, 세션에서 회원 아이디 값 가져오기
		String cl_id = (String) session.getAttribute("loginId");
		
		int success = service.jobPostingApplyOne(cl_id, jpo_no, re_no, com_id);
		if(success > 0) {
			logger.info("지원 성공");
		}
		return "redirect:/clientOfferList.go";
	}
	
	@RequestMapping(value = "/applyTwo.go")
	public String applyGoo(Model model, HttpSession session, @RequestParam String jpo_no, String com_id) {
		
		// by태섭, 세션에서 회원 아이디 값 가져오기
		String cl_id = (String) session.getAttribute("loginId");
		
		ArrayList<ResumeDTO> list = reService.list(cl_id);
		logger.info("결과 확인 : 리스트 개수=" +list.size());
		model.addAttribute("list", list);		
		
		model.addAttribute("cl_id", cl_id);
		model.addAttribute("jpo_no", jpo_no);
		model.addAttribute("com_id", com_id);
		
		return "jobPostEnterPop";
	}
	
	
	@RequestMapping(value = "/applyTwo.do")
	public String applyTwo(Model model, HttpSession session, 
			@RequestParam String jpo_no, String re_no, String com_id,
			RedirectAttributes rattr) {
		
		boolean result = false;
		// by태섭, 세션에서 회원 아이디 값 가져오기
		String cl_id = (String) session.getAttribute("loginId");
		int success = service.jobPostingApplyTwo(cl_id, jpo_no, re_no, com_id);
		if(success > 0) {
			logger.info("지원 성공");
			result = true;
		}
		
		rattr.addFlashAttribute("success", result);
		return "redirect:/applyTwo.go?jpo_no="+jpo_no+"&com_id="+com_id;
	}
	
	
	
	
	
}
