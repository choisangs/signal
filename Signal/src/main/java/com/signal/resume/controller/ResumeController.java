package com.signal.resume.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.signal.all.dto.MemberDTO;
import com.signal.all.dto.PageMakerDTO;
import com.signal.all.dto.ResumeDTO;
import com.signal.all.dto.TestDTO;
import com.signal.enter.controller.Criteria;
import com.signal.resume.service.ResumeService;
import com.signal.test.service.TestService;

@Controller
public class ResumeController {

	@Autowired ResumeService service;
	@Autowired TestService testService;
	Logger logger = LoggerFactory.getLogger(this.getClass());

	// 이력서 리스트 불러오기
	// cl_id 세션값으로 해당 id에 맞는 리스트만 불러오기
	// 우선 메인페이지 제작 전 전체 리스트 호출로 수정 진행
	@RequestMapping(value = "resumeList.go", method = RequestMethod.GET)
	public String resumeList(Model model, HttpSession session) {		
		
		logger.info("이력서 리스트 요청");
		String id = (String) session.getAttribute("loginId");
		logger.info("id=" + id);
		// * 추후에 아이디 값 가져가기		
		
		ArrayList<ResumeDTO> list = service.list(id);
		logger.info("결과 확인 : 리스트 개수=" +list.size());
		model.addAttribute("list", list);
		
		return "./resume/resumeList";
	}
	
	
	// 인재현황 리스트 불러오기
	
	@RequestMapping(value = "personList.go", method = RequestMethod.GET)
	public String personList(Model model, HttpSession session, Criteria cri) {		
		
		logger.info("인재현황 리스트 요청");
		
		//리스트 페이징 처리하기 위해 리스트를 보여주는 서비스를 보내는데 그안에 cri를 담는다.
   		//service의 최종 목적지 mapper에는 10개씩 보여달라는 쿼리문이 작성되어 있어 10개 이상이어도 10개까지만 보여준다.
	  ArrayList<ResumeDTO> list = service.personList(cri);
	  logger.info("결과 확인 : 리스트 개수=" +list.size()); //mapper에 limit 10개로 해놓았으므로 당연히 10개초과는 안나온다.
	  model.addAttribute("list",list);
  		int pageNum = cri.getPageNum();
	  		
  		//pageNum 이동을 하기위해 담아서 jsp에 보내준다.
  		model.addAttribute("pageNum",pageNum);
  		
  		//페이징 처리를 위한 게시글의 총 개수를 구해온다.
  		int total = service.personListTotal();
  		// PageMakerDTO 라는 것에 총개수와 몇개씩 보여줄 것인지에 대한 내용을 담는다.
  		// 단순히 총인원 리스트를 pageMaker에 맞게 잘라서 표현한 것일 뿐 이동에 대한 내용은 없다.
  		PageMakerDTO pageMaker = new PageMakerDTO(cri, total);
  		model.addAttribute("pageMaker",pageMaker);
		logger.info("총 인재는?? : "+total);
		
		String chk=(String) session.getAttribute("isCompany");
		model.addAttribute("comChk", chk);
		return "./resume/personList";
	}
	
	//개인회원 리스트 검색기능 + 페이징 처리
   	@RequestMapping(value="/personListSearch.do")
   	public String personListSearch(Model model,HttpSession session,
   			@RequestParam String searchOption, String searchEndAge, String searchStartAge, int pageNum) {
   	
   		logger.info("옵션 확인: "+searchOption+" / "+searchStartAge+" / "+searchEndAge+" / "+pageNum);
   		
   		model.addAttribute("searchOption",searchOption);
   		model.addAttribute("searchStartAge",searchStartAge);
   		model.addAttribute("searchEndAge",searchEndAge);
   		
   		//검색한 내용 페이징 처리하기
   		int skip=(pageNum-1) * 10;
   		
   		ArrayList<MemberDTO> dto = service.personListSearch(searchOption,searchStartAge,searchEndAge,skip);
   		// 전체 리스트에서 넣었던 것을 검색을 통해 가져온 값을 바꿔넣어준다.
   		model.addAttribute("list",dto);
   		
   		// 검색해서 나온 결과의 갯수를 세어본다.
   		int personSearchTotal = service.personSearchTotal(searchOption,searchStartAge,searchEndAge);
   		model.addAttribute("pageNum",pageNum);
   		
   		// 검색 결과를 어떻게 나눌것인지 페이지 메이커 변수에 담아준다.
   		PageMakerDTO pageMaker = new PageMakerDTO(pageNum, personSearchTotal);
   		// 검색 결과숫자와 페이지 갯수를 처리한 변수를 담아서 jsp에 보내준다.
   		model.addAttribute("pageMaker",pageMaker);
   		
   		   		
   		return "./resume/personList";
   	}
	
	// 이력서 상세보기
	@RequestMapping(value = "resumeDetail.do", method = RequestMethod.GET)
	public String resumeDetail(HttpSession session ,Model model, @RequestParam String re_no) {
		String cl_id=(String) session.getAttribute("loginId");
		String id=(String) session.getAttribute("loginId");
		ResumeDTO dto = service.resumeDetail(re_no);
		ArrayList<ResumeDTO> careerDto = service.careerDetail(re_no);
		ArrayList<ResumeDTO> socialDto = service.socialDetail(re_no);
		ArrayList<ResumeDTO> licenseDto = service.licenseDetail(re_no);
		ArrayList<ResumeDTO> recommendDto = service.recommendDetail(re_no);
		String selfTest1=testService.selfTestDetail1(cl_id);
		String selfTest2=testService.selfTestDetail2(cl_id);
		String selfTest3=testService.selfTestDetail3(cl_id);
		String selfTest4=testService.selfTestDetail4(cl_id);
		String selfTest5=testService.selfTestDetail5(cl_id);
		String selfTest6=testService.selfTestDetail6(cl_id);
		String selfComment=testService.selfComment(id);
		ArrayList<TestDTO> interviewTestDto1 = testService.interviewTestDetail1(cl_id);
		ArrayList<TestDTO> interviewTestDto2 = testService.interviewTestDetail2(cl_id);
		ArrayList<TestDTO> interviewTestDto3 = testService.interviewTestDetail3(cl_id);
		ArrayList<TestDTO> interviewTestDto4 = testService.interviewTestDetail4(cl_id);
		ArrayList<TestDTO> interviewTestDto5 = testService.interviewTestDetail5(cl_id);
		String interviewTestDto6 = testService.interviewTestDetail6(cl_id);
		String interviewTestDto7 = testService.interviewTestDetail7(cl_id);
		ArrayList<TestDTO> interviewComment = testService.interviewTestComment(cl_id);
		
		String page = "./resume/resumeDetail";
		
		model.addAttribute("re_no", re_no);
		model.addAttribute("dto", dto);
		model.addAttribute("careerDto", careerDto);
		model.addAttribute("socialDto", socialDto);
		model.addAttribute("licenseDto", licenseDto);
		model.addAttribute("recommendDto", recommendDto);
		model.addAttribute("selfTest1", selfTest1);
		model.addAttribute("selfTest2", selfTest2);
		model.addAttribute("selfTest3", selfTest3);
		model.addAttribute("selfTest4", selfTest4);
		model.addAttribute("selfTest5", selfTest5);
		model.addAttribute("selfTest6", selfTest6);
		model.addAttribute("selfComment", selfComment);
		model.addAttribute("interviewTestDto1", interviewTestDto1);
		model.addAttribute("interviewTestDto2", interviewTestDto2);
		model.addAttribute("interviewTestDto3", interviewTestDto3);
		model.addAttribute("interviewTestDto4", interviewTestDto4);
		model.addAttribute("interviewTestDto5", interviewTestDto5);
		model.addAttribute("interviewTestDto6", interviewTestDto6);
		model.addAttribute("interviewTestDto7", interviewTestDto7);
		model.addAttribute("interviewComment", interviewComment);
		/* model.addAttribute("interviewTestDto", interviewTestDto); */
		
		return page;
	}
	
	@RequestMapping(value = "/download/{oriName}/{newName}")
	public void portfolioDownload(HttpServletResponse resp,
			@PathVariable String oriName, @PathVariable String newName) {
		logger.info(newName + "=>" + oriName);
		service.portfolioDownload(oriName,newName,resp);
	}
	
	@RequestMapping(value = "resumeReg.go", method = RequestMethod.GET)
	public String resumeReg(HttpSession session, Model model) {		
		
		String id = (String) session.getAttribute("loginId");		
		logger.info(id + " 의 이력서 등록페이지 이동");
		ResumeDTO dto = service.resumeRegDetail(id);
		model.addAttribute("cl_id", id);	
		model.addAttribute("dto", dto);
		
		return "./resume/resumeReg";
	}
	
	@RequestMapping(value = "/resumeReg.ajax")
	@ResponseBody
	public HashMap<String, Object> resumeReg(
			@RequestParam(value = "re_title") String re_title,
			@RequestParam(value = "cl_id") String cl_id,
			@RequestParam(value = "reco_no") String reco_no,
			@RequestParam(value = "re_fn_status") String re_fn_status,
			@RequestParam(value = "jp_no", required=false) String jp_no,
			@RequestParam(value = "jc_no", required=false) String jc_no,
			@RequestParam(value = "re_sch_name", required=false) String re_sch_name,
			@RequestParam(value = "enterYear") String enterYear,
			@RequestParam(value = "enterMonth") String enterMonth,
			@RequestParam(value = "outYear") String outYear,
			@RequestParam(value = "outMonth") String outMonth,
			@RequestParam(value = "re_major", required=false) String re_major,
			@RequestParam(value = "re_average", required=false) String re_average,
			@RequestParam(value = "re_total", required=false) String re_total,
			@RequestParam(value = "re_register") String re_register,
			@RequestParam(value = "re_intro", required=false) String re_intro,
			@RequestParam(value = "re_portfolio", required=false) MultipartFile re_portfolio) {
		
			logger.info("이력서 등록 페이지");
			HashMap<String, Object> map = new HashMap<String, Object>();
			HashMap<String, String> params = new HashMap<String, String>();
			
			String re_sch_period = enterYear+'.'+enterMonth+'~'+outYear+'.'+outMonth;
			
			params.put("re_title",re_title);
			params.put("cl_id",cl_id);
			params.put("reco_no",reco_no);
			params.put("re_fn_status",re_fn_status);
			params.put("jp_no",jp_no);
			params.put("jc_no",jc_no);
			params.put("re_sch_name",re_sch_name);
			params.put("re_sch_period",re_sch_period);
			params.put("re_major",re_major);
			params.put("re_average",re_average);
			params.put("re_total",re_total);
			params.put("re_register",re_register);
			params.put("re_intro",re_intro);
			
			logger.info("jp_no="+jp_no+'/'+"re_portfolio=" +re_portfolio);
			
			int re_no = service.resumeReg(params);
			
			if(re_portfolio != null) {
			service.portfolioUp(re_no,re_portfolio);
			}
			map.put("re_no", re_no);
			
			
		return map;
	}
	
	
	
	@RequestMapping(value = "jobClassPop.go", method = RequestMethod.GET)
	public String jobClassPopGo(Model model) {		
		
		ArrayList<ResumeDTO> list = service.jpList();
		model.addAttribute("jpList", list);
		return "./resume/jobClassPop";
	}
	
	@RequestMapping(value = "jcList.go", method = RequestMethod.GET)
	public String jcList(Model model, @RequestParam String jp_no) {		
		
		ArrayList<ResumeDTO> jplist = service.jpList();
		ArrayList<ResumeDTO> list = service.jcList(jp_no);
		model.addAttribute("jp_no", jp_no);
		model.addAttribute("jcList", list);
		model.addAttribute("jpList", jplist);
		return "./resume/jobClassPop";
	}
	
	@RequestMapping(value ="jcGet.ajax", method =RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> jcGet(@RequestParam HashMap<String, Object> param){
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		String jp_no= (String) param.get("jp_no");
		logger.info("jp_no="+jp_no);		
		ArrayList<ResumeDTO> jcList = service.jcList(jp_no);
		map.put("success", true);
		map.put("jcList", jcList);
		
		return map;
	}
	
	
	
	@RequestMapping(value = "jcCheck.go", method = RequestMethod.GET)
	public String jcCheck(Model model, @RequestParam String jc_no) {		
		
		ArrayList<ResumeDTO> list = service.jcCheck(jc_no);
		model.addAttribute("finList", list);
		boolean success=true;
		model.addAttribute("success", success);
		return "./resume/jobClassPop";
	}
	
	@RequestMapping(value = "resumeAddReg.go", method = RequestMethod.GET)
	public String resumeAddReg(Model model, @RequestParam String re_no) {		
		
		String id = "aaaabbbb";
		logger.info(re_no + "번 이력서 추가정보 등록 요청");
		
		ArrayList<ResumeDTO> careerDto = service.careerDetail(re_no);
		ArrayList<ResumeDTO> socialDto = service.socialDetail(re_no);
		ArrayList<ResumeDTO> licenseDto = service.licenseDetail(re_no);
		ArrayList<ResumeDTO> recommendDto = service.recommendDetail(re_no);
		
		model.addAttribute("cl_id", id);	
		model.addAttribute("re_no", re_no);
		model.addAttribute("careerDto", careerDto);
		model.addAttribute("socialDto", socialDto);
		model.addAttribute("licenseDto", licenseDto);
		model.addAttribute("recommendDto", recommendDto);
		return "./resume/resumeAddReg";
	}
	
	@RequestMapping(value = "careerReg.go", method = RequestMethod.GET)
	public String careerReg(Model model, @RequestParam String re_no) {		
		
		logger.info(re_no + "번 이력서 경력사항 등록 요청");
		model.addAttribute("re_no", re_no);
		return "./resume/careerRegPop";
	}
	
	@RequestMapping(value = "careerUp.go", method = RequestMethod.GET)
	public String careerUp(Model model, @RequestParam String re_no) {		
		
		logger.info(re_no + "번 이력서 경력사항 수정 요청");
		
		ArrayList<ResumeDTO> careerDto = service.careerDetail(re_no);
		
		model.addAttribute("re_no", re_no);
		model.addAttribute("careerDto", careerDto);
		
		return "./resume/careerUpPop";
	}
	
	@RequestMapping(value = "socialReg.go", method = RequestMethod.GET)
	public String socialReg(Model model, @RequestParam String re_no) {		
		
		logger.info(re_no + "번 이력서 그 외 활동 등록 요청");
		model.addAttribute("re_no", re_no);
		return "./resume/socialRegPop";
	}
	
	@RequestMapping(value = "socialUp.go", method = RequestMethod.GET)
	public String socialUp(Model model, @RequestParam String re_no) {		
		
		logger.info(re_no + "번 이력서 그 외 활동 수정 요청");
		
		ArrayList<ResumeDTO> socialDto = service.socialDetail(re_no);
		
		model.addAttribute("re_no", re_no);
		model.addAttribute("socialDto", socialDto);
		
		return "./resume/socialUpPop";
	}
	
	@RequestMapping(value = "licenseReg.go", method = RequestMethod.GET)
	public String licenseReg(Model model, @RequestParam String re_no) {		
		
		logger.info(re_no + "번 이력서 자격증,수상내역 등록 요청");
		model.addAttribute("re_no", re_no);
		return "./resume/licenseRegPop";
	}
	
	@RequestMapping(value = "licenseUp.go", method = RequestMethod.GET)
	public String licenseUp(Model model, @RequestParam String re_no) {		
		
		logger.info(re_no + "번 이력서 자격증,수상내역 수정 요청");
		
		ArrayList<ResumeDTO> licenseDto = service.licenseDetail(re_no);
		
		model.addAttribute("re_no", re_no);
		model.addAttribute("licenseDto", licenseDto);
		
		return "./resume/licenseUpPop";
	}
	
	@RequestMapping(value = "recommendUp.go", method = RequestMethod.GET)
	public String recommendUp(Model model, HttpSession session,@RequestParam String re_no, @RequestParam String reco_no) {		
		
		logger.info(re_no + "번 이력서 추천내역 수정 요청");
		String id = (String) session.getAttribute("loginId");	
		
		ArrayList<ResumeDTO> recommendDto = service.recommendUp(id);	
		
		model.addAttribute("cl_id", id);	
		model.addAttribute("re_no", re_no);
		model.addAttribute("reco_no", reco_no);
		model.addAttribute("recommendDto", recommendDto);
		
		return "./resume/recommendUp";
	}
	
	@RequestMapping(value = "resumeUpdate.go", method = RequestMethod.GET)
	public String resumeUpdate(HttpSession session, Model model, @RequestParam String re_no) {		
		
		String cl_id=(String) session.getAttribute("loginId");
		
		logger.info(re_no + "번 이력서 수정 요청");
		
		ResumeDTO dto = service.resumeDetail(re_no);
		ArrayList<ResumeDTO> careerDto = service.careerDetail(re_no);
		ArrayList<ResumeDTO> socialDto = service.socialDetail(re_no);
		ArrayList<ResumeDTO> licenseDto = service.licenseDetail(re_no);		
		String page = "./resume/resumeUpdate";
		
		model.addAttribute("cl_id", cl_id);
		model.addAttribute("dto", dto);
		model.addAttribute("careerDto", careerDto);
		model.addAttribute("socialDto", socialDto);
		model.addAttribute("licenseDto", licenseDto);
		model.addAttribute("re_no", re_no);
		return page;
		
	}
	
	@RequestMapping(value = "/resumeUp.ajax")
	@ResponseBody
	public HashMap<String, Object> resumeUp(
			@RequestParam(value = "old_re_no") String old_re_no,
			@RequestParam(value = "reco_no", required=false) String reco_no,
			@RequestParam(value = "re_title") String re_title,
			@RequestParam(value = "cl_id") String cl_id,
			@RequestParam(value = "re_fn_status") String re_fn_status,
			@RequestParam(value = "jp_no", required=false) String jp_no,
			@RequestParam(value = "jc_no", required=false) String jc_no,
			@RequestParam(value = "re_sch_name", required=false) String re_sch_name,
			@RequestParam(value = "enterYear") String enterYear,
			@RequestParam(value = "enterMonth") String enterMonth,
			@RequestParam(value = "outYear") String outYear,
			@RequestParam(value = "outMonth") String outMonth,
			@RequestParam(value = "re_major", required=false) String re_major,
			@RequestParam(value = "re_average", required=false) String re_average,
			@RequestParam(value = "re_total", required=false) String re_total,
			@RequestParam(value = "re_register") String re_register,
			@RequestParam(value = "re_intro", required=false) String re_intro,
			@RequestParam(value = "re_portfolio", required=false) MultipartFile re_portfolio) {
		
			logger.info("이력서 등록 페이지");
			HashMap<String, Object> map = new HashMap<String, Object>();
			HashMap<String, String> params = new HashMap<String, String>();			
			
			String re_sch_period = enterYear+'.'+enterMonth+'~'+outYear+'.'+outMonth;
			
			params.put("old_re_no", old_re_no);
			params.put("reco_no", reco_no);
			params.put("re_title",re_title);
			params.put("cl_id",cl_id);
			params.put("re_fn_status",re_fn_status);
			params.put("jp_no",jp_no);
			params.put("jc_no",jc_no);
			params.put("re_sch_name",re_sch_name);
			params.put("re_sch_period",re_sch_period);
			params.put("re_major",re_major);
			params.put("re_average",re_average);
			params.put("re_total",re_total);
			params.put("re_register",re_register);
			params.put("re_intro",re_intro);
			
			logger.info("jp_no="+jp_no+'/'+"re_portfolio=" +re_portfolio);
			
			int re_no = service.resumeReg(params);
			
			service.resumeAddUp(old_re_no, re_no, reco_no);
			
			if(re_portfolio != null) {
			service.portfolioUp(re_no,re_portfolio);
			}
			map.put("re_no", re_no);
			
			
		return map;
	}
	
	@RequestMapping(value = "resumeDelete.go", method = RequestMethod.GET)
	public String resumeDelete(Model model, @RequestParam String re_no, RedirectAttributes rattr) {		
		
		logger.info(re_no + "번 이력서 삭제 요청");
		
		boolean success=service.resumeDelete(re_no);
		
		rattr.addFlashAttribute("success", success);
		return "redirect:/resumeList.go";
	}
	
	@RequestMapping(value = "/careerReg.do")
	public ModelAndView careerReg(@RequestParam HashMap<String, String> params, 
			HttpSession session, Model model) {
		
		logger.info("params : {}",params);	
		
		return service.careerReg(params);
	}
	
	@RequestMapping(value = "/socialReg.do")
	public ModelAndView socialReg(@RequestParam HashMap<String, String> params, 
			HttpSession session, Model model) {
		
		logger.info("params : {}",params);	
		
		return service.socialReg(params);
	}
	
	@RequestMapping(value = "/licenseReg.do")
	public ModelAndView licenseReg(@RequestParam HashMap<String, String> params, 
			HttpSession session, Model model) {
		
		logger.info("params : {}",params);	
		
		return service.licenseReg(params);
	}
	
	@RequestMapping(value = "/recommendUp.do")
	public ModelAndView recommendReg(@RequestParam HashMap<String, String> params, 
			HttpSession session, Model model) {
		
		logger.info("params : {}",params);	
		
		return service.recommendReg(params);
	}
	
	@RequestMapping(value = "recommendMe.go", method = RequestMethod.GET)
	public String recommendMeGo(HttpSession session, Model model) {		
		
		String id = (String) session.getAttribute("loginId");		
		logger.info(id + " 의 추천현황 페이지 이동");
		ArrayList<ResumeDTO> list = service.recommendMe(id);
		/* ArrayList<ResumeDTO> listB = service.recommendYou(id); */
		model.addAttribute("cl_id", id);	
		model.addAttribute("list", list);
		/* model.addAttribute("listB", listB); */
		
		return "./resume/recommendMe";
	}
	
	@RequestMapping(value="/recommendYou.ajax")
	@ResponseBody
	public HashMap<String, Object> recommendUlist(HttpSession session) {
		logger.info("리스트 요청");
		HashMap<String, Object> map = new HashMap<String, Object>();		
			String id=(String) session.getAttribute("loginId");
			ArrayList<ResumeDTO> list = service.recommendUlist(id);
			map.put("list", list);		
		
		return map;
	}
	
	
	@RequestMapping(value = "/careerUp.do")
	public ModelAndView careerUp(@RequestParam HashMap<String, String> params, 
			HttpSession session, Model model) {
		
		logger.info("params : {}",params);	
		
		return service.careerUp(params);
	}
	
	@RequestMapping(value = "/socialUp.do")
	public ModelAndView socialUp(@RequestParam HashMap<String, String> params, 
			HttpSession session, Model model) {
		
		logger.info("params : {}",params);	
		
		return service.socialUp(params);
	}
	
	@RequestMapping(value = "/licenseUp.do")
	public ModelAndView licenseUp(@RequestParam HashMap<String, String> params, 
			HttpSession session, Model model) {
		
		logger.info("params : {}",params);	
		
		return service.licenseUp(params);
	}
	
	
	@RequestMapping(value = "/careerDelete.do")
	public ModelAndView careerDelete(@RequestParam String ca_no,@RequestParam String re_no,
			HttpSession session, Model model) {		
		
		return service.careerDelete(ca_no,re_no);
	}
	
	@RequestMapping(value = "/socialDelete.do")
	public ModelAndView socialDelete(@RequestParam String soc_no,@RequestParam String re_no,
			HttpSession session, Model model) {		
		
		return service.socialDelete(soc_no,re_no);
	}
	
	@RequestMapping(value = "/licenseDelete.do")
	public ModelAndView licenseDelete(@RequestParam String li_no,@RequestParam String re_no,
			HttpSession session, Model model) {		
		
		return service.licenseDelete(li_no,re_no);
	}
	
	
}
