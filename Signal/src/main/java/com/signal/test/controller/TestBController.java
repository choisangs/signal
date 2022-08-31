package com.signal.test.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.signal.test.service.TestService;

@RestController
@RequestMapping(value="/rest")
public class TestBController {
	
	@Autowired TestService service;
	
	@RequestMapping(value="/stReg")
	@ResponseBody
	public HashMap<String, Object> stReg(@RequestBody HashMap<String, Object> param) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		
		boolean success=service.stReg(param);
		
		map.put("success", success);
		
		return map;
	}
	
	@RequestMapping(value="/stUp")
	@ResponseBody
	public HashMap<String, Object> stUp(@RequestBody HashMap<String, Object> param) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		
		boolean success=service.stUp(param);
		
		map.put("success", success);
		
		return map;
	}
	
	
}
