package com.signal.objection.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.signal.all.dto.InterviewDTO;
import com.signal.all.dto.ObjectionDTO;
import com.signal.enter.controller.Criteria;

public interface ObjectionDAO {

	ArrayList<ObjectionDTO> clientObjectionList(HashMap<String, Object> params);

	ArrayList<ObjectionDTO> comObjectionList(HashMap<String, Object> params);

	ArrayList<ObjectionDTO> adminObjectionList(Criteria cri);

	ArrayList<ObjectionDTO> adminBlindList(Criteria cri);

	int clientDbjectionRegDo(HashMap<String, String> params);

	ObjectionDTO comObjectionUpdate(String obj_no);

	int comObjectionUpdateDo(HashMap<String, String> params);

	void adminBlind(String inter_no);

	void adminBlindCancel(String inter_no);

	int comObjectionTotal(HashMap<String, Object> params);

	int adminObjectionTotal();

	ArrayList<InterviewDTO> adminObjectionSearch(String searchOption, String search, int skip);

	int adminObjectionTotal2(String searchOption, String search);

	int adminBlindTotal();

	ArrayList<InterviewDTO> adminBlindSearch(String searchOption, String search, int skip);

	int adminBlindTotal2(String searchOption, String search);

	int clientObjectionListTotal(HashMap<String, Object> params);

}
