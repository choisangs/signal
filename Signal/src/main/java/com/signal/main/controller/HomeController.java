package com.signal.main.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.signal.all.dto.CallendarDTO;
import com.signal.main.dao.MainDAO;
import com.signal.main.service.MainService;


@Controller
public class HomeController {
	
	@Autowired MainService service;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/")
	public String home() {
		logger.info("메인페이지 이동");
		
		return "main";
	}
	
	@RequestMapping(value="callendar.ajax")
	@ResponseBody
	public HashMap<String, Object> callendar(@RequestBody HashMap<String,Object> params){
		
		logger.info("params:"+params);		
		
		HashMap<String, Object> values = (HashMap<String, Object>) params.get("values");
		ArrayList<String> callendar=(ArrayList<String>) values.get("arr");
		
		logger.info("values:"+values);
		logger.info("callendar:"+callendar);
		
		String day1 = callendar.get(0);
		String day2 = callendar.get(1);
		String day3 = callendar.get(2);
		String day4 = callendar.get(3);
		String day5 = callendar.get(4);
		String day6 = callendar.get(5);
		String day7 = callendar.get(6);
		
		
		ArrayList<CallendarDTO> startPost1 = service.startPost1(day1);		
		ArrayList<CallendarDTO> endPost1 = service.endPost1(day1);
		ArrayList<CallendarDTO> startPost2 = service.startPost2(day2);
		ArrayList<CallendarDTO> endPost2 = service.endPost2(day2);
		ArrayList<CallendarDTO> startPost3 = service.startPost3(day3);
		ArrayList<CallendarDTO> endPost3 = service.endPost3(day3);
		ArrayList<CallendarDTO> startPost4 = service.startPost4(day4);
		ArrayList<CallendarDTO> endPost4 = service.endPost4(day4);
		ArrayList<CallendarDTO> startPost5 = service.startPost5(day5);
		ArrayList<CallendarDTO> endPost5 = service.endPost5(day5);
		ArrayList<CallendarDTO> startPost6 = service.startPost6(day6);
		ArrayList<CallendarDTO> endPost6 = service.endPost6(day6);
		ArrayList<CallendarDTO> startPost7 = service.startPost7(day7);
		ArrayList<CallendarDTO> endPost7 = service.endPost7(day7);
		
		ArrayList<CallendarDTO> size1 = service.startPostCnt1(day1);	
		ArrayList<CallendarDTO> size2 = service.startPostCnt2(day2);
		ArrayList<CallendarDTO> size3 = service.startPostCnt3(day3);
		ArrayList<CallendarDTO> size4 = service.startPostCnt4(day4);
		ArrayList<CallendarDTO> size5 = service.startPostCnt5(day5);
		ArrayList<CallendarDTO> size6 = service.startPostCnt6(day6);
		ArrayList<CallendarDTO> size7 = service.startPostCnt7(day7);		
		ArrayList<CallendarDTO> size8 = service.endPostCnt1(day1);		
		ArrayList<CallendarDTO> size9 = service.endPostCnt2(day2);		
		ArrayList<CallendarDTO> size10 = service.endPostCnt3(day3);		
		ArrayList<CallendarDTO> size11 = service.endPostCnt4(day4);		
		ArrayList<CallendarDTO> size12 = service.endPostCnt5(day5);		
		ArrayList<CallendarDTO> size13 = service.endPostCnt6(day6);		
		ArrayList<CallendarDTO> size14 = service.endPostCnt7(day7);
		
		params.put("startPost1", startPost1);		
		params.put("startPost2", startPost2);
		params.put("startPost3", startPost3);
		params.put("startPost4", startPost4);
		params.put("startPost5", startPost5);
		params.put("startPost6", startPost6);
		params.put("startPost7", startPost7);
		params.put("endPost1", endPost1);
		params.put("endPost2", endPost2);
		params.put("endPost3", endPost3);
		params.put("endPost4", endPost4);
		params.put("endPost5", endPost5);
		params.put("endPost6", endPost6);
		params.put("endPost7", endPost7);
		params.put("size1", size1.size());
		params.put("size2", size2.size());
		params.put("size3", size3.size());
		params.put("size4", size4.size());
		params.put("size5", size5.size());
		params.put("size6", size6.size());
		params.put("size7", size7.size());
		params.put("size8", size8.size());
		params.put("size9", size9.size());
		params.put("size10", size10.size());
		params.put("size11", size11.size());
		params.put("size12", size12.size());
		params.put("size13", size13.size());
		params.put("size14", size14.size());		
		
		String msg="하하";
		params.put("msg", msg);
		return params;
		
	}
	
	
	@RequestMapping(value = "/mainPop.go")
	public String mainPop(@RequestParam String chkDay, @RequestParam String chkState,
			Model model) {
		logger.info("채용달력 자세히 보기 이동" + chkDay + '/' + chkState);
		ArrayList<CallendarDTO> list =service.postPop(chkDay,chkState);
		model.addAttribute("list", list);
		return "mainPop";
	}
	
	
	@RequestMapping(value = "/main2.go")
	public String mainCss() {
		logger.info("css페이지 이동");
		
		return "main2";
	}
	
}
