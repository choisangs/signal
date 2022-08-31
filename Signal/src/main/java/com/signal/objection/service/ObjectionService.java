package com.signal.objection.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.signal.all.dto.InterviewDTO;
import com.signal.all.dto.ObjectionDTO;
import com.signal.enter.controller.Criteria;
import com.signal.interview.dao.InterviewDAO;
import com.signal.objection.dao.ObjectionDAO;

@Service
public class ObjectionService {
@Autowired ObjectionDAO dao;
Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public ArrayList<ObjectionDTO> clientObjectionList(HashMap<String, Object> params) {
		
		return dao.clientObjectionList(params);
	}
	
	public ArrayList<ObjectionDTO> comObjectionList(HashMap<String, Object> params) {
		
		return dao.comObjectionList(params);
	}
	
	public ArrayList<ObjectionDTO> adminObjectionList(Criteria cri) {
	
		return dao.adminObjectionList(cri);
	}
	
	public ArrayList<ObjectionDTO> adminBlindList(Criteria cri) {
		
		return dao.adminBlindList(cri);
	}
	
	public void clientDbjectionRegDo(HashMap<String, String> params) {
		
		int success = dao.clientDbjectionRegDo(params);
		logger.info("수정된 데이터 수 : "+ success);
	}

	public ObjectionDTO comObjectionUpdate(String obj_no) {
		
		return dao.comObjectionUpdate(obj_no);
	}

	public void comObjectionUpdateDo(HashMap<String, String> params) {
		int row =dao.comObjectionUpdateDo(params);
		
	}

	public void adminBlind(String inter_no) {
		
		dao.adminBlind(inter_no);
	}

	public void adminBlindCancel(String inter_no) {
	
		dao.adminBlindCancel(inter_no);
	}

	public int clientObjectionListTotal(HashMap<String, Object> params) {
		logger.info("게시글 수 가져오기 서비스 요청");
		return dao.clientObjectionListTotal(params);
	}

	public int comObjectionListTotal(HashMap<String, Object> params) {
		logger.info("게시글 수 가져오기 서비스 요청");
		return dao.comObjectionTotal(params);
	}
	//이의제기관리(관리자) -페이징 토탈
	public int adminObjectionTotal() {
		
		return dao.adminObjectionTotal();
	}
	
	

	public ArrayList<InterviewDTO> adminObjectionSearch(String searchOption, String search, int skip) {
		
		return dao.adminObjectionSearch(searchOption,search,skip);
	}

	public int adminObjectionTotal2(String searchOption, String search) {
		
		return dao.adminObjectionTotal2(searchOption,search);
	}

	public int adminBlindTotal() {
		
		return  dao.adminBlindTotal();
	}

	public ArrayList<InterviewDTO> adminBlindSearch(String searchOption, String search, int skip) {
	
		return dao.adminBlindSearch(searchOption,search,skip);
	}

	public int adminBlindTotal2(String searchOption, String search) {
		
		return dao.adminBlindTotal2(searchOption,search);
	}





}
