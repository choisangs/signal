package com.signal.recommend.controller;

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

import com.signal.recommend.service.RecommendService;

@Controller
public class RecommendController {
	
	@Autowired RecommendService service;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "recommendMePop.go", method = RequestMethod.GET)
	public String recommendGo(HttpSession session ,Model model) {		
		String cl_id=(String) session.getAttribute("loginId");
		logger.info(cl_id + "의 추천요청");
		model.addAttribute("cl_id", cl_id);
		return "./resume/recommendMePop";
	}
	
	// 요청아이디 유효성 검사
		@RequestMapping(value = "/overlay.ajax")
		@ResponseBody
		public HashMap<String, Object> overlay(@RequestParam String chkId) {
			logger.info("요청할 아이디 : " + chkId);
			return service.overlay(chkId);
		}
	
	// 추천 요청하기
		@RequestMapping(value="recommendReg.ajax")
		@ResponseBody
		public HashMap<String, Object> recommendReg(@RequestParam HashMap<String, Object>params){
			logger.info("추천요청 : {}",params);
			return service.recommendReg(params);
		}
		
	
	// 추천 응답하기
		@RequestMapping(value = "recommendYouPop.go", method = RequestMethod.GET)
		public String recommendYouGo(Model model, @RequestParam String reco_no) {		
			
			logger.info(reco_no + "의 추천응답하기");
			String cl_id=service.recommendU(reco_no);
			String reco_state=service.recommendUb(reco_no);
			model.addAttribute("reco_no", reco_no);
			model.addAttribute("cl_id",cl_id);
			model.addAttribute("reco_state",reco_state);
			return "./resume/recommendYouPop";
		}
		
		@RequestMapping(value = "/recommendUReg.do")
		public String recommendUReg(@RequestParam HashMap<String, String> params, 
				HttpSession session, Model model) {
				
				String page="./resume/recommendYouPop";
				boolean success=false;
				logger.info("params : {}",params);
				int result = service.recommendUReg(params);
				if(result>0) {
					success = true;
				}
				model.addAttribute("success", success);
			return page;
		}
		
		
	
	
	
}
