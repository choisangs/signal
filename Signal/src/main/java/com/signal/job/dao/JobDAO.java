package com.signal.job.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.signal.all.dto.ResumeDTO;

public interface JobDAO {

	int jpReg(HashMap<String, Object> params);

	int jcReg(HashMap<String, Object> params);

	void jpHiddenUp(String jp_no, String jp_hidden);

	void jpJcHiddenUp(String jp_no, String jp_hidden);

	void jcHiddenUp(String jc_no, String jc_hidden);

	int hiddenGet(String jp_no);

	ArrayList<ResumeDTO> jpAdminList();

	ArrayList<ResumeDTO> jcAdminList(String jp_no);	

}
