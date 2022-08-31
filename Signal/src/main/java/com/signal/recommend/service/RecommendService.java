package com.signal.recommend.service;

import java.sql.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.signal.all.dto.ResumeDTO;
import com.signal.recommend.dao.RecommendDAO;

@Service
public class RecommendService {

	@Autowired RecommendDAO dao;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public HashMap<String, Object> overlay(String chkId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String overId = dao.overlay(chkId);
		logger.info("아이디가 있나? "+overId);
		boolean over = overId==null ? false : true; 
		logger.info("가능한 아이디?" + over);
		map.put("overlay", over);
		
		return map;
	}

	public HashMap<String, Object> recommendReg(HashMap<String, Object> params) {
		logger.info("추천요청 등록하기 서비스 요청");
		
		HashMap<String, Object> recoResult = new HashMap<String, Object>();
		
		int result=dao.recommendReg(params);
		boolean success=false;
		if(result>0) {
			success=true;
		}
		recoResult.put("success", success);
		return recoResult;
	}

	public String recommendU(String reco_no) {
		return dao.recommendU(reco_no);
	}
	
	public String recommendUb(String reco_no) {
		return dao.recommendUb(reco_no);
	}

	public int recommendUReg(HashMap<String, String> params) {
		int row=dao.recommendUReg(params);
		return row;
	}

	

}
