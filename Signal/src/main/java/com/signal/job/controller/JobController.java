package com.signal.job.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.signal.all.dto.ResumeDTO;
import com.signal.job.service.JobService;
import com.signal.resume.service.ResumeService;

@Controller
public class JobController {

	@Autowired ResumeService service;
	@Autowired JobService jobService;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "jobClassReg.go", method = RequestMethod.GET)
	public String jobClassPopGo(Model model) {		
		
		ArrayList<ResumeDTO> list = jobService.jpAdminList();
		model.addAttribute("jpList", list);
		return "jobClassReg";
	}
	
	@RequestMapping(value = "jobchList.go", method = RequestMethod.GET)
	public String recommendGo(Model model, @RequestParam String jp_no) {		
		logger.info("jp_no = "+jp_no);
		ArrayList<ResumeDTO> jplist = jobService.jpAdminList();
		ArrayList<ResumeDTO> list = jobService.jcAdminList(jp_no);
		model.addAttribute("jp_no", jp_no);
		model.addAttribute("jcList", list);
		model.addAttribute("jpList", jplist);
		return "jobClassReg";
	}
	
	@RequestMapping(value = "jpReg.go", method = RequestMethod.GET)
	public String jpRegGo() {		
		return "jpReg";
	}
	
	@RequestMapping(value = "jpReg.do", method = RequestMethod.POST)
	public String interQueReg(Model model, @RequestParam HashMap<String, Object>params) {	
		logger.info("직무대분류 등록");
		logger.info("params : {}",params);
		boolean result = jobService.jpReg(params);
		
		model.addAttribute("success", result);
		
		return "jpReg";
	}
	
	@RequestMapping(value = "jcReg.go", method = RequestMethod.GET)
	public String jcRegGo(Model model,@RequestParam String jp_no) {		
		model.addAttribute("jp_no", jp_no);
		return "jcReg";
	}
	
	@RequestMapping(value = "jcReg.do", method = RequestMethod.POST)
	public String jcReg(Model model, @RequestParam HashMap<String, Object>params) {	
		logger.info("직무중분류 등록");
		logger.info("params : {}",params);
		boolean result = jobService.jcReg(params);
		
		model.addAttribute("success", result);
		
		return "jpReg";
	}
	
	@RequestMapping(value = "jp_hiddenUp.do", method = RequestMethod.GET)
	public String jp_hiddenUp(HttpSession session, Model model,
			@RequestParam String jp_no, @RequestParam String jp_hidden) {		
		jobService.jpHiddenUp(jp_no, jp_hidden);
		ArrayList<ResumeDTO> list;		
		list = service.jpList();
		
		model.addAttribute("list", list);
		return "redirect:/jobClassReg.go";
	}
	
	@RequestMapping(value = "jc_hiddenUp.do", method = RequestMethod.GET)
	public String jc_hiddenUp(HttpSession session, Model model, RedirectAttributes rAttr,
			@RequestParam String jc_no, @RequestParam String jc_hidden, @RequestParam String jp_no) {	
		logger.info("jp_no = "+jp_no);		
		int hiddenGet=jobService.hiddenGet(jp_no);
		logger.info("hidden= " + hiddenGet);
		if(hiddenGet>0) {
		jobService.jcHiddenUp(jc_no, jc_hidden);		
		} else {			
			rAttr.addFlashAttribute("success", true);
		}		
		rAttr.addAttribute("jp_no", jp_no);
		return "redirect:/jobchList.go";
	}
	
}
