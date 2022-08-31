package com.signal.jobposting.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.signal.all.dto.InterviewDTO;
import com.signal.all.dto.JobPostingDTO;
import com.signal.all.dto.PageMakerDTO;
import com.signal.all.dto.ResumeDTO;
import com.signal.enter.controller.Criteria;
import com.signal.jobposting.dao.JobPostingDAO;
import com.signal.jobposting.service.JobPostingService;
import com.signal.resume.service.ResumeService;


@Controller
public class JobPostingController {

	// By 진희, 채용공고 및 기업정보와 관련
	@Autowired JobPostingService service;
	@Autowired ResumeService reService;
	@Autowired JobPostingDAO dao;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 기업정보 페이지로 이동 
	// 비로그인 시 url 접근과 기업회원 외 회원은 접근금지 처리
	   @RequestMapping(value = "/companyInfo.go")
		public String companyInfo(Model model, HttpSession session) {
		   
		   String page= "";
		   if(session.getAttribute("loginId")!=null && session.getAttribute("isCompany")!= null) {
				String id = (String) session.getAttribute("loginId");
				logger.info(id+"의 기업정보 유무확인 및 불러오기");

				String ceo = service.ComDetail(id);
				String no = service.file(id);
				
				logger.info("대표자명: "+ceo);
				logger.info("기업번호: "+no);

				if(ceo != null) {
					JobPostingDTO dto = service.ComInfoDetail(model, id,ceo,no);
					model.addAttribute("dto",dto);
					} page="companyInfo2";
				}else {
					page="main";
					model.addAttribute("msg","기업회원만 접근 가능합니다.");
				}			
			return page;
		}
	
	// 기업정보 등록페이지 이동(+기업명 자동 입력)
	@RequestMapping(value = "/companyInfoWrite.go")
	public String infoWritePage(Model model, HttpSession session) {
		String page= "";
		if(session.getAttribute("loginId")!=null && session.getAttribute("isCompany")!= null) {
			String id = (String) session.getAttribute("loginId");
			
			logger.info("로그인 아이디: "+id+" / "+"기업정보 등록페이지로 이동");
			
			JobPostingDTO dto = service.infoCom(id);
			logger.info("기업명과 아이디? "+dto);
			model.addAttribute("dto",dto);
			page="companyInfoWrite";
		}else {
			page="main";
			model.addAttribute("msg","기업회원만 접근 가능합니다.");
		}		
		return page;
	}
	
	// 기업정보 등록(+사진 저장)
	@RequestMapping(value = "/companyInfoWrite.do")
	public String infoWrite(MultipartFile[] ci_photo, @RequestParam HashMap<String, String> params, 
			HttpServletRequest session,  Model model) {
		
		logger.info("기업로고사진 = " + ci_photo);
		logger.info("param : {}",params);

		int sum = service.infoWrite(ci_photo,params);
		String msg = "기업정보 등록에 실패하였습니다.";
		if(sum > 0) {
			msg = "기업정보 등록이 완료되었습니다.";
		}
		model.addAttribute("msg",msg);
		model.addAttribute("url","/companyInfo.go");
		
		return "alert";
	}

	// 기업정보 수정하기 페이지로 이동
	@RequestMapping(value = "/companyInfoUpdate.go")
	   public String comInfoUpdate(Model model,HttpSession session) {
			
		String page= "";
		if(session.getAttribute("loginId")!=null && session.getAttribute("isCompany")!= null) {
			String id = (String) session.getAttribute("loginId");
			logger.info(id+"수정 상세보기 페이지 이동");
			JobPostingDTO dto = service.comUpdate(id);
			model.addAttribute("dto",dto);
			logger.info("담겻?"+dto);
			page="companyInfoDetail";
		}else {
			page="main";
			model.addAttribute("msg","기업회원만 접근 가능합니다.");
		}
	      return page;
	   }

	// 기업정보 수정하기
	@RequestMapping(value = "/companyInfoUpdate.do")
	   public String update(MultipartFile[] ci_photo, Model model, @RequestParam HashMap<String, String> params,
			   HttpSession session) {

			String id = (String) session.getAttribute("loginId");
			logger.info(id+": 아이디");
		    logger.info("param : {}",params);

		    int num = service.update(ci_photo, params,id);
		    if(num>0) {
		         model.addAttribute("msg", "수정에 성공하였습니다.");
		         model.addAttribute("url", "/companyInfo.go");
		      }else {
		    	  model.addAttribute("msg", "수정에 실패하였습니다.");
		    	  model.addAttribute("url", "/companyInfo.go");
		      }	    
	      return "alert";
	}
	
	// 기업 페이지 - 채용공고관리 리스트 이동
	@RequestMapping(value = "/jobPostingList.go")
	public String jobPostingGo(Model model, HttpSession session,Criteria cri,
			@RequestParam HashMap<String, Object> params) {
		
		logger.info("기업 채용공고 페이지 이동");
		String page= "";
		if(session.getAttribute("loginId")!=null && session.getAttribute("isCompany")!= null) {
			String id = (String) session.getAttribute("loginId");
			logger.info(id+"의 기업 채용공고 리스트 불러오기");
			
			// 모집마감일이 오늘날짜보다 전이라면 jpo_state를 '마감'으로 업데이트 하는 기능
			service.close();
			logger.info("모집여부 마감 요청");
			
			JobPostingDTO dto = service.infoList(id,model);
			logger.info("기업정보: "+dto);
			
			
			int pageNum = 1;
			if(params.get("pageNum") != null) {
				pageNum = (int) Integer.parseInt(String.valueOf(params.get("pageNum")));
			}
			int skip = (pageNum -1) * 10;

			params.put("skip", skip);
			params.put("com_id", id);
			// 페이징 리스트
			ArrayList<JobPostingDTO> jpoList = service.postingList(params);
			logger.info("리스트 갯수: "+jpoList.size());
			model.addAttribute("jpoList",jpoList);
			model.addAttribute("pageNum",pageNum);
			
			//페이징 위한 게시글 토탈수
			int total=service.comPostingPasingTotal(params);
			PageMakerDTO pageMaker =new PageMakerDTO(cri, total);
			model.addAttribute("pageMaker",pageMaker);
			
			model.addAttribute("dto",dto);	
			page="jobPostingCom";
			
		}else {
			page="main";
			model.addAttribute("msg","기업회원만 접근 가능합니다.");
		}
		return page;
	}	
	
	// 채용공고 검색 페이징
		@RequestMapping(value="/jobPostingList.do")
		public String adminObjectionSearch(Model model,HttpSession session,@RequestParam String searchOption, int pageNum ) {
				logger.info("옵션 확인: "+searchOption);
				
				String id = (String) session.getAttribute("loginId");
				
				model.addAttribute("searchOption",searchOption);
				
				JobPostingDTO dto = service.infoList(id,model);
				logger.info("기업정보: "+dto);
				model.addAttribute("dto",dto);
				
				//옵션 페이징처리
				int skip=(pageNum-1) * 10;
				ArrayList<InterviewDTO> dto1 = service.jobPostingSearch(searchOption, skip,id);
				model.addAttribute("jpoList",dto1);
				
				int comPostingPasingTotal=service.jobPostingListTotal2(searchOption,id);
				model.addAttribute("pageNum",pageNum);
				
				PageMakerDTO pageMake2= new PageMakerDTO(pageNum, comPostingPasingTotal);
				model.addAttribute("pageMaker", pageMake2);
				
			return "jobPostingCom";
		}
		
		
	// 기업 페이지 - 채용공고 상세보기
	@RequestMapping(value = "/jobPostingDetail.do")
	public String jobPostingDetail(Model model, @RequestParam String jpo_no, HttpSession session) {
		String page= "";
		if(session.getAttribute("loginId")!=null && session.getAttribute("isCompany")!= null) {
			logger.info("상세보기 요청: " + jpo_no);
		      
		    String id = (String) session.getAttribute("loginId"); 
			// 모집마감일이 오늘날짜보다 전이라면 jpo_state를 '마감'으로 업데이트 하는 기능
		    	service.close();
			logger.info("모집여부 마감 요청");
			
		    //상세정보 가져오기 기능
		    service.jobPostingDetail(model,jpo_no); 
		  	page="jobPostingDetail";
		}else {
			page="main";
			model.addAttribute("msg","기업회원만 접근 가능합니다.");
		}
		return page;
	}	

	// 채용공고 수정하기
	@RequestMapping(value = "/jobPostingUpdate.do")
	   public String PostingUpdate(MultipartFile[] jpo_photo,Model model,HttpSession session,
			   @RequestParam String jpo_no,@RequestParam String jpo_state,@RequestParam HashMap<String, String> params) {
			
			String id = (String) session.getAttribute("loginId");
			logger.info(jpo_no+" 번 채용공고 수정하기 서비스 요청");
			logger.info("param : {}",params);
			String url = "/jobPostingDetail.do?jpo_no="+jpo_no+"&&jpo_state="+jpo_state;
			int num = service.postingUpdate(jpo_photo,id,jpo_no,params);
			
		    if(num>0) {
		         model.addAttribute("msg", "채용공고 수정에 성공하였습니다.");
		         model.addAttribute("url", url);
		      }else {
		    	  model.addAttribute("msg", "채용공고 수정에 실패하였습니다.");
		    	  model.addAttribute("url", url);
		      }
			      return "alert";
			}
	
	// 신규 채용공고 등록 페이지로 이동
	@RequestMapping(value = "/jobPostingWrite.go")
	public String jobPostingWrite(Model model, HttpSession session) {
			logger.info("기업 채용공고 등록 페이지로 이동");
			String page= "";
			if(session.getAttribute("loginId")!=null && session.getAttribute("isCompany")!= null) {
				String id = (String) session.getAttribute("loginId");
				logger.info(id+"의 기업 채용공고 등록하기");
				
				//직무 대분류 리스트 호출
				ArrayList<ResumeDTO> jpList = reService.jpList();
				model.addAttribute("jpList", jpList);
						
				//직무 중분류 리스트 호출
				ArrayList<JobPostingDTO> jobMidList = service.jobMidList();
				model.addAttribute("jobMidList", jobMidList);
				
				JobPostingDTO dto = service.posting(id);
				logger.info("채용공고 등록 아이디: "+id);	
				model.addAttribute("dto",dto);
				page="jobPostingWrite";
			}else {
				page="main";
				model.addAttribute("msg","기업회원만 접근 가능합니다.");
			}
			return page;
		}	
	
	// 채용공고 등록(+사진 저장)하기
	@RequestMapping(value = "/jobPostingWrite.do")
	public String postingWrite(MultipartFile[] jpo_photo, @RequestParam HashMap<String, String> params, 
			HttpSession session,  Model model) {
		
		logger.info("채용공고 사진 = " + jpo_photo);
		logger.info("param : {}",params);

		int sum = service.postingWrite(jpo_photo,params);
		String msg = "채용공고 등록에 실패하였습니다.";
		if(sum > 0) {
			msg = "채용공고 등록이 완료되었습니다.";
		}
		model.addAttribute("msg",msg);
		model.addAttribute("url","/jobPostingList.go");
		
		return "alert";
	}

	// 메인 채용공고 페이지로 이동
	@RequestMapping(value = "/jobPostingMain.go")
	public String jobPostingListMain(Model model, HttpSession session,Criteria cri) {
		
		logger.info("메인 채용공고 페이지 이동");
		String page= "";

		int pageNum = cri.getPageNum();
		
		//직무 대분류 리스트 호출
		ArrayList<JobPostingDTO> jobBigList = service.jobBigList();
		model.addAttribute("jobBigList", jobBigList);
				
		//직무 중분류 리스트 호출
		ArrayList<JobPostingDTO> jobMidList = service.jobMidList();
		model.addAttribute("jobMidList", jobMidList);

		// 페이징 리스트
		ArrayList<JobPostingDTO> mainJpoList = service.mainPostingList(cri);
		logger.info("리스트 갯수: "+mainJpoList.size());
		
		model.addAttribute("mainJpoList",mainJpoList);
		model.addAttribute("pageNum",pageNum);
		
		//페이징 위한 게시글 토탈수
		int total=service.mainPostingPasingTotal();
		PageMakerDTO pageMaker =new PageMakerDTO(cri, total);
		model.addAttribute("pageMaker",pageMaker);

		page="jobPostingMain";
		
		return page;
	}	
	
	// 채용공고 검색 페이징
	@RequestMapping(value="/jobPostingMain.do")
	public String jobPostingMain(Model model,HttpSession session, int pageNum, 
		@RequestParam String jpo_region, String jp_no, String jc_no, String search) {
		logger.info("옵션 확인: "+jpo_region+" / "+jp_no+" / "+jc_no+" / "+search);		
		model.addAttribute("jpo_region",jpo_region);
		model.addAttribute("jp_no",jp_no);
		model.addAttribute("jc_no",jc_no);
		model.addAttribute("search",search);
		
		//직무 대분류 리스트 호출
		ArrayList<JobPostingDTO> jobBigList = service.jobBigList();
		model.addAttribute("jobBigList", jobBigList);
	
		//직무 중분류 리스트 호출
		ArrayList<JobPostingDTO> jobMidList2 = service.jobMidList2(jp_no);
		model.addAttribute("jobMidList", jobMidList2);
		
		//옵션 페이징처리
		int skip=(pageNum-1) * 10;
		ArrayList<JobPostingDTO> dto = service.jobPostingMainSearch(jpo_region,jp_no, search,skip);
		model.addAttribute("mainJpoList",dto);
		
		int mainPostingPasingTotal=service.jobPostingMainTotal(jpo_region,jp_no, search);
		model.addAttribute("pageNum",pageNum);
		
		PageMakerDTO pageMake= new PageMakerDTO(pageNum, mainPostingPasingTotal);
		model.addAttribute("pageMaker", pageMake);
		
		return "jobPostingMain";
	}
	

	
	
	// 메인 채용공고 상세보기
	@RequestMapping(value = "/PostingDetailMain.go")
	public String PostingDetailMainPage(Model model, @RequestParam String jpo_no,
			@RequestParam String com_id, @RequestParam int curState, HttpSession session) {
		
			logger.info( jpo_no+"상세보기 요청: " + com_id);
		      
		    String id = (String) session.getAttribute("loginId"); 
			// 모집마감일이 오늘날짜보다 전이라면 jpo_state를 '마감'으로 업데이트 하는 기능
			service.close();
			logger.info("모집여부 마감 요청");
			if(session.getAttribute("isClient")!= null || session.getAttribute("loginId") == null) {
				service.upHit(jpo_no);
			};
		    service.PostingDetailMainPage(model,jpo_no,com_id); 
		  	model.addAttribute("curState", curState);
		return "PostingDetailMain";
	}	
	
	// 채용달력
	@RequestMapping(value = "/main.go")
	public String Main(Model model, HttpSession session) {		
		
		String start = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		String end = LocalDate.now().plusDays(6).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		logger.info(start+" | "+end);
		ArrayList<JobPostingDTO> mainList = service.mainList(start,end);
		logger.info("리스트 갯수: "+mainList.size());
		String page = "main";
		model.addAttribute("mainList",mainList);
		model.addAttribute("start",start);
		model.addAttribute("end",end);
		
		return page;	
	}	
	
	
	
	
	
	
	
	
	
	
}
