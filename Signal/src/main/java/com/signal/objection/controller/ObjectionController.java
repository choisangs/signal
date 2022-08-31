package com.signal.objection.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.signal.all.dto.InterviewDTO;
import com.signal.all.dto.ObjectionDTO;
import com.signal.all.dto.PageMakerDTO;
import com.signal.enter.controller.Criteria;
import com.signal.objection.service.ObjectionService;

@Controller
public class ObjectionController {
	@Autowired ObjectionService service;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//이의제기관리(사용자)리스트
	@RequestMapping(value = "/clientObjectionList.go", method = RequestMethod.GET)
	public String clientObjectionList(Model model,Criteria cri,@RequestParam HashMap<String, Object> params,HttpSession session) {	
		
			
				String page ="clientObjectionList";
				String cl_id = (String) session.getAttribute("loginId");
				//params.put("com_id", com_id);
				 if(cl_id==null) {
			        	model.addAttribute("msg","개인회원 서비스입니다.");
			        	page = "main";
			        }
				int pageNum = 1;
				if(params.get("pageNum") != null) {
					pageNum = (int) Integer.parseInt(String.valueOf(params.get("pageNum")));
				}
				
				int skip = (pageNum -1) * 10;
				
				
				params.put("cl_id", cl_id);
				params.put("skip", skip);
				
				//페이지 리스트 수
				ArrayList<ObjectionDTO>clientObjectionList =service.clientObjectionList(params);
				model.addAttribute("clientObjectionList",clientObjectionList);
				
				//페이징처리 토탈개수
				int total = service.clientObjectionListTotal(params);
				
				PageMakerDTO pageMaker = new PageMakerDTO(cri, total);
				model.addAttribute("pageMaker", pageMaker);

		
		return page;
	}
	//이의제기등록(사용자)
	@RequestMapping(value = "/clientDbjectionReg.do")
	public ModelAndView clientDbjectionRegDo(HttpSession session,Model model,@RequestParam HashMap<String, String>params){		
	
		ModelAndView mav =new ModelAndView();
		String pclose="pclose";
		String page="clientObjectionReg";
		service.clientDbjectionRegDo(params);
		
		mav.setViewName(page);
		mav.addObject("pclose",pclose);
		
		return mav;
	}
	
	//이의제기관리(기업)리스트
	@RequestMapping(value = "/comObjectionList.go", method = RequestMethod.GET)
	public String comObjectionList(Model model,Criteria cri,@RequestParam HashMap<String, Object> params,HttpSession session) {
		
		
		String com_id = (String) session.getAttribute("loginId");
		String page ="comObjectionList";
		 if(com_id==null) {
	        	model.addAttribute("msg","기업회원 서비스입니다.");
	        	page = "main";
	        }
		
		int pageNum = 1;
		if(params.get("pageNum") != null) {
			pageNum = (int) Integer.parseInt(String.valueOf(params.get("pageNum")));
		}
		
		int skip = (pageNum -1) * 10;
		
		
		params.put("com_id", com_id);
		params.put("skip", skip);
		
		//페이지 리스트 수
		ArrayList<ObjectionDTO>comObjectionList =service.comObjectionList(params);
		model.addAttribute("comObjectionList",comObjectionList);
		
		//페이징처리 토탈개수
		int total = service.comObjectionListTotal(params);
		
		PageMakerDTO pageMaker = new PageMakerDTO(cri, total);
		model.addAttribute("pageMaker", pageMaker);
		
		return page;
	}
	
	//이의제기 처리페이지(기업)
	@RequestMapping(value = "/comObjectionUpdate.go", method = RequestMethod.GET)
	public String comObjectionUpdate(Model model,@RequestParam String obj_no) {
		
		ObjectionDTO dto =service.comObjectionUpdate(obj_no);
		model.addAttribute("dto",dto);
	
		return "comObjectionUpdate";
	}
	//이의제기 처리페이지(기업) -업데이트
		@RequestMapping(value = "/comObjectionUpdate.do")
		public ModelAndView comObjectionUpdateDo(HttpSession session,Model model
				,@RequestParam HashMap<String, String>params){
			ModelAndView mav =new ModelAndView();
			String pclose="pclose";
			String page="comObjectionUpdate";

			service.comObjectionUpdateDo(params);
			mav.setViewName(page);
			mav.addObject("pclose",pclose);
			
			return mav;
		}
	
	//이의제기관리(관리자)리스트 페이징
	@RequestMapping(value = "/adminObjectionList.go", method = RequestMethod.GET)
	public String adminObjectionList(Model model,Criteria cri,HttpSession session) {
		String page = "adminObjectionList";
  		String isAdmin = (String) session.getAttribute("isAdmin");
  		
  		if(isAdmin==null) {
  			model.addAttribute("msg","관리자 전용 페이지입니다.");
  			page = "main";
  		}
		//페이징 리스트
		ArrayList<ObjectionDTO>adminObjectionList =service.adminObjectionList(cri);
		model.addAttribute("adminObjectionList",adminObjectionList);
		int pageNum=cri.getPageNum();
		model.addAttribute("pageNum",pageNum);
		
		//페이징 위한 게시글 토탈수
		int total=service.adminObjectionTotal();
		PageMakerDTO pageMaker =new PageMakerDTO(cri, total);
		model.addAttribute("pageMaker",pageMaker);
		
		
		
		return page;
	}
	//이의제기관리(관리자)검색 페이징
	@RequestMapping(value="/adminObjectionList.do")
	public String adminObjectionSearch(Model model,HttpSession session,@RequestParam String searchOption, String search, int pageNum ) {
			String page = "adminObjectionList";
	  		String isAdmin = (String) session.getAttribute("isAdmin");
	  		
	  		if(isAdmin==null) {
	  			model.addAttribute("msg","관리자 페이지입니다.");
	  			page = "main";
	  		}
		
			logger.info("옵션 확인: "+searchOption+search);
		
			model.addAttribute("searchOption",searchOption);
			model.addAttribute("search",search);
			//옵션 페이징처리
			int skip=(pageNum-1) * 10;
			ArrayList<InterviewDTO>dto = service.adminObjectionSearch(searchOption, search,skip);
			model.addAttribute("adminObjectionList",dto);
			
			int adminObjectionTotal=service.adminObjectionTotal2(searchOption, search);
			model.addAttribute("pageNum",pageNum);
			
			PageMakerDTO pageMake2= new PageMakerDTO(pageNum, adminObjectionTotal);
			model.addAttribute("pageMaker", pageMake2);
			
		return page;
	}
	//이의제기관리(관리자)-블라인드기능
	@RequestMapping(value = "/adminBlind")
	public String adminBlind(Model model, @RequestParam String inter_no) {

		
			service.adminBlind(inter_no);
	
		return "redirect:adminObjectionList.go";
	}
	

		//블라인드관리(관리자)리스트 -페이징
		@RequestMapping(value = "/adminBlindList.go", method = RequestMethod.GET)
		public String adminBlindList(Model model,Criteria cri,HttpSession session) {
			String page = "adminBlindList";
	  		String isAdmin = (String) session.getAttribute("isAdmin");
	  		
	  		if(isAdmin==null) {
	  			model.addAttribute("msg","관리자 페이지입니다.");
	  			page = "main";
	  		}
			
			
			//페이징 리스트
			ArrayList<ObjectionDTO>adminBlindList =service.adminBlindList(cri);
			model.addAttribute("adminBlindList",adminBlindList);
		
			
			int pageNum=cri.getPageNum();
			model.addAttribute("pageNum",pageNum);
			
			//페이징 위한 게시글 토탈수
			int total=service.adminBlindTotal();
			PageMakerDTO pageMaker =new PageMakerDTO(cri, total);
			model.addAttribute("pageMaker",pageMaker);
			

			return page;
		}
		//블라인드관리(관리자)검색 -페이징
		@RequestMapping(value="/adminBlindList.do")
		public String adminBlindSearch(Model model,HttpSession session,@RequestParam String searchOption, String search, int pageNum ) {
				String page = "adminBlindList";
		  		String isAdmin = (String) session.getAttribute("isAdmin");
		  		
		  		if(isAdmin==null) {
		  			model.addAttribute("msg","관리자 페이지입니다.");
		  			page = "main";
		  		}
			
				logger.info("옵션 확인: "+searchOption+search);
			
				model.addAttribute("searchOption",searchOption);
				model.addAttribute("search",search);
				//옵션 페이징처리
				int skip=(pageNum-1) * 10;
				ArrayList<InterviewDTO>dto = service.adminBlindSearch(searchOption, search,skip);
				model.addAttribute("adminBlindList",dto);
				
				int adminBlindTotal=service.adminBlindTotal2(searchOption, search);
				model.addAttribute("pageNum",pageNum);
				
				PageMakerDTO pageMake2= new PageMakerDTO(pageNum, adminBlindTotal);
				model.addAttribute("pageMaker", pageMake2);
				
			return page;
		}
		
		
		
		
		
		
	//블라인드관리(관리자)-블라인드취소기능
	@RequestMapping(value = "/adminBlindCancel")
	public String adminBlindCancel(Model model, @RequestParam String inter_no) {

			service.adminBlindCancel(inter_no);
	
		return "redirect:adminBlindList.go";
	}
	
	
}
