package com.signal.test.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.signal.all.dto.TestDTO;
import com.signal.test.dao.TestDAO;

@Service
public class TestService {

	@Autowired TestDAO dao;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public ModelAndView selfInsert(String id) {
		ModelAndView mav = new ModelAndView("./selfTest/selfInsert");		
		boolean result = false;
		ArrayList<TestDTO> selfInsert = dao.selfInsert(id);
		String st_comment = dao.selfComment(id);
		if(selfInsert.size()>0) {			
			result = true;
			mav.addObject("list", selfInsert);
		}
		logger.info("사이즈="+selfInsert.size()+'/'+result);
		mav.addObject("result", result);
		mav.addObject("cl_id", id);
		mav.addObject("st_comment", st_comment);
		return mav;
	}

	public ArrayList<TestDTO> selfRegGo() {
		
		return dao.selfRegGo();
	}

	public boolean selfReg(HashMap<String, Object> params) {
		int cnt = dao.selfReg(params);
		boolean success=false;
		if(cnt>0) {
			String st_comment=(String) params.get("st_comment");
			String cl_id=(String)params.get("cl_id");
			int row = dao.selfContentReg(st_comment,cl_id);
			if(row>0) {
			success=true;
			}
		}
		return success;
	}

	public boolean scoreReg(HashMap<String, Object> params) {
		int cnt = dao.scoreReg(params);
		boolean success=false;
		if(cnt>0) {
			success=true;
		}
		logger.info("결과는? " + success);
		return success;
	}

	

	public ArrayList<TestDTO> selfQueList() {

		return dao.selfQueList();
	}
	
	public ArrayList<TestDTO> selfQueListA() {

		return dao.selfQueListA();
	}
	
	public ArrayList<TestDTO> selfQueListB() {

		return dao.selfQueListB();
	}

	public ArrayList<TestDTO> interviewQueList() {

		return dao.interviewQueList();
	}
	
	public ArrayList<TestDTO> interviewQueListA() {

		return dao.interviewQueListA();
	}
	
	public ArrayList<TestDTO> interviewQueListB() {

		return dao.interviewQueListB();
	}

	public void stHiddenUp(String st_no, String st_hidden) {
		dao.stHiddenUp(st_no, st_hidden);
		
	}
	
	public void itHiddenUp(String it_no, String it_hidden) {
		dao.itHiddenUp(it_no, it_hidden);
		
	}

	public boolean stReg(HashMap<String, Object> param) {
		boolean success=false;
		
		HashMap<String, Object> values = (HashMap<String, Object>) param.get("values");
		ArrayList<String> cl_id = (ArrayList<String>) values.get("cl_id");
		ArrayList<String> st_no = (ArrayList<String>) values.get("st_no");
		ArrayList<String> st_score = (ArrayList<String>) values.get("st_score");
		System.out.println("cl_id="+cl_id+"st_no="+st_no+"st_score="+st_score+cl_id.size());		
		
		String st_comment = (String) values.get("st_comment");
		String id=cl_id.get(0);
		dao.selfContentReg(id,st_comment);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		for (int i = 0; i < cl_id.size(); i++) {
			map.put("cl_id", cl_id.get(i));
			map.put("st_no", st_no.get(i));
			map.put("st_score", st_score.get(i));
			dao.stReg(map);
		}	
		
		return success;
	}

	public boolean stUp(HashMap<String, Object> param) {
		boolean success=false;
		
		HashMap<String, Object> values = (HashMap<String, Object>) param.get("values");
		ArrayList<String> cl_id = (ArrayList<String>) values.get("cl_id");
		ArrayList<String> st_no = (ArrayList<String>) values.get("st_no");
		ArrayList<String> st_score = (ArrayList<String>) values.get("st_score");
		System.out.println("cl_id="+cl_id+"st_no="+st_no+"st_score="+st_score+cl_id.size());		
		
		String st_comment = (String) values.get("st_comment");
		String id=cl_id.get(0);
		dao.selfContentReg(id,st_comment);
		
		logger.info("cl_id="+cl_id.get(0));
		logger.info("st_no="+st_no.get(0));
		logger.info("st_score="+st_score.get(0));
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		for (int i = 0; i < cl_id.size(); i++) {
			map.put("cl_id", cl_id.get(i));			
			map.put("st_no", st_no.get(i));
			map.put("st_score", st_score.get(i));
			dao.stUp(map);
		}	
		
		return success;
	}
	
	public boolean selfQueReg(HashMap<String, Object> params) {
		
		boolean success=false;
		int result = dao.selfQueReg(params);
		
		if(result > 0) {
			success=true;
		}
				
		return success;
	}

	public boolean interQueReg(HashMap<String, Object> params) {
		boolean success=false;
		int result = dao.interQueReg(params);
		
		if(result > 0) {
			success=true;
		}
				
		return success;
	}

	public String selfTestDetail1(String cl_id) {
		return dao.selfTestDetail1(cl_id);
	}
	public String selfTestDetail2(String cl_id) {
		return dao.selfTestDetail2(cl_id);
	}
	public String selfTestDetail3(String cl_id) {
		return dao.selfTestDetail3(cl_id);
	}
	public String selfTestDetail4(String cl_id) {
		return dao.selfTestDetail4(cl_id);
	}
	public String selfTestDetail5(String cl_id) {
		return dao.selfTestDetail5(cl_id);
	}
	public String selfTestDetail6(String cl_id) {
		return dao.selfTestDetail6(cl_id);
	}

	public String selfComment(String id) {
		return dao.selfComment(id);
	}

	
	  public ArrayList<TestDTO> interviewTestDetail1(String cl_id) { 
		  return dao.interviewTestDetail1(cl_id); 
		  } 
	  
	  public ArrayList<TestDTO> interviewTestDetail2(String cl_id) { 
		  return dao.interviewTestDetail2(cl_id); 
		  } 
	  
	  public ArrayList<TestDTO> interviewTestDetail3(String cl_id) { 
		  return dao.interviewTestDetail3(cl_id); 
		  } 
	  
	  public ArrayList<TestDTO> interviewTestDetail4(String cl_id) { 
		  return dao.interviewTestDetail4(cl_id); 
		  } 
	  
	  public ArrayList<TestDTO> interviewTestDetail5(String cl_id) { 
		  return dao.interviewTestDetail5(cl_id); 
		  }

	public ArrayList<TestDTO> interviewTestComment(String cl_id) {
		return dao.interviewTestComment(cl_id);
	}

	public String interviewTestDetail6(String cl_id) {
		return dao.interviewTestDetail6(cl_id); 
	}

	public String interviewTestDetail7(String cl_id) {
		return dao.interviewTestDetail7(cl_id); 
	}

	public ArrayList<TestDTO> selfUpGo(String cl_id) {
		
		return dao.selfUpGo(cl_id);
	} 

	

}
