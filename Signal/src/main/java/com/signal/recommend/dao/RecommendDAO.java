package com.signal.recommend.dao;

import java.util.HashMap;

import com.signal.all.dto.ResumeDTO;

public interface RecommendDAO {

	String overlay(String chkId);

	int recommendReg(HashMap<String, Object> params);

	String recommendU(String reco_no);	

	/* int recommendUReg(ResumeDTO dto); */

	int recommendUReg(HashMap<String, String> params);

	String recommendUb(String reco_no);

}
