package com.signal.interview.controller;



import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.signal.interview.service.InterviewService;
@RestController
@RequestMapping(value="/rest")
public class InterviewController2 {
	@Autowired InterviewService service;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@RequestMapping(value="/comReg")
	@ResponseBody
	public HashMap<String, Object> comReg(@RequestBody HashMap<String, Object> param) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		
		boolean success=service.comReg(param);
		
		map.put("success", success);
		
		return map;
	}

	
	
	@RequestMapping(value="/comUp")
	@ResponseBody
	public HashMap<String, Object> stReg(@RequestBody HashMap<String, Object> param) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		
		boolean success=service.comUp(param);
		
		map.put("success", success);
		
		return map;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
