package com.signal.member.controller;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.signal.all.dto.MemberDTO;
import com.signal.all.dto.PageMakerDTO;
import com.signal.enter.controller.Criteria;
import com.signal.member.service.MemberService;

@Controller
public class MemberController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired MemberService service;
	
	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
	public String home(Model model) {
		
		//임시로 보낸것임 바꿔주어야함 주소
		return "main";
	}
	
	
	// login 팝업창 띄우기
	@RequestMapping(value = "/loginPopup.go")
	public String loginPopup() {

		return "loginPopup";
	}
	
	
	// 회원가입 회원선택 페이지 이동
	@RequestMapping(value = "/joinSelect.go")
	public String joinSelect() {
		logger.info("회원가입 선택페이지 이동");
		return "joinSelect";
	}
	
	
	// 개인회원 가입페이지 이동
	@RequestMapping(value = "/joinFormClient.go")
	public String joinFormClient() {

		return "joinFormClient";
	}
	
	
	// 개인회원 아이디 중복체크
	@RequestMapping("/overlayClientId.ajax")
	@ResponseBody
	public HashMap<String, Object>overlayClientId(@RequestParam String chkclId) {
		logger.info("아이디 중복 체크 : "+chkclId);
		return service.overlayClientId(chkclId);
	}
	
	
	// 개인회원 이메일 중복체크
	@RequestMapping("/overlayEmail.ajax")
	@ResponseBody
	public HashMap<String, Object>overlayEmail(@RequestParam String chkEmail) {
		logger.info("이메일 중복 체크 : "+chkEmail);
		return service.overlayEmail(chkEmail);
	}
	
	
	/*
	// 개인회원 회원가입 요청 및 파일업로드
	@RequestMapping(value="clientJoin.do")
	public String clientJoin(Model model,@RequestParam HashMap<String, String>params, MultipartFile cl_photo) {
		
		//1. 파일명 추출하기
		String fileName = cl_photo.getOriginalFilename();
		String ext = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();//확장자
		
		//2. 새 파일명으로 바꿔주기(중복 방지)
		String newFileName = System.currentTimeMillis()+ext;
		logger.info(fileName+" -> "+newFileName);
		
		//3. 새 파일명으로 저장하기
		try {
			byte[] arr = cl_photo.getBytes();//바이트 추출
			Path filePath = Paths.get("C:/upload/"+newFileName);// 어디에 저장할지 지정
			Files.write(filePath, arr);// 파일 저장
			service.fileWrite(cl_photo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		logger.info("회원가입 요청정보 : "+params);

		int success=service.clientJoin(params,cl_photo);
		String msg = "회원가입에 실패하였습니다.";
		
		if(success>0) {
			msg="회원가입에 성공하셨습니다.";
		}
		
		// 회원가입 성공 여부 출력창 스크립트에 달아주기 위함
		model.addAttribute("msg",msg);
		// 임시 페이지 다른 곳으로 주소 바꿀것!
		 return "login";
	}
	*/
	
	
	/*
	// 개인회원 회원가입 요청 및 파일업로드(아직 불가) (form 방식)
	@RequestMapping(value="clientJoin.do")
	public String clientJoin(Model model,@RequestParam HashMap<String, String>params, MultipartFile cl_photo) {			
		logger.info("회원가입 요청하려는 입력정보 : "+params);
		int success = service.clientJoin(params,cl_photo);
		String msg = "회원가입에 실패 했습니다.";
		if(success>0) {
			msg="회원가입에 성공 했습니다.";
		}
		model.addAttribute("msg",msg);
		return "login";
	}
	*/
	
	/* 파일 업로드 불가 
	// 개인회원 회원가입 요청 ajax 버전
	@RequestMapping("/joinClient.ajax")
	@ResponseBody
	public HashMap<String, Object> joinClient(@RequestParam HashMap<String, Object> params){
		logger.info("개인회원 가입요청 : "+params);
		HashMap<String, Object> map = new HashMap<String, Object>();
		service.joinClient(params);
		map.put("success", 1);
		return map;

	}
	*/

	
	// 개인회원 회원가입 요청 ajax 버전 + 파일 저장
	@RequestMapping("/joinClient.ajax")
	@ResponseBody
	public HashMap<String, Object> joinClient(
			@RequestParam(value="cl_id") String cl_id, @RequestParam(value="cl_pw") String cl_pw,
			@RequestParam(value="cl_name") String cl_name, @RequestParam(value="cl_birth") String cl_birth,
			@RequestParam(value="cl_age") String cl_age,@RequestParam(value="cl_gender") String cl_gender,
			@RequestParam(value="cl_address") String cl_address,@RequestParam(value="cl_call") String cl_call,
			@RequestParam(value="cl_email") String cl_email,@RequestParam(value="cl_state") String cl_state,
			@RequestParam(value="file",required=false) MultipartFile file){
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, String> params = new HashMap<String, String>();
		
		params.put("cl_id", cl_id);
		params.put("cl_pw",cl_pw);
		params.put("cl_name",cl_name);
		params.put("cl_birth",cl_birth);
		params.put("cl_age",cl_age);
		params.put("cl_gender",cl_gender);
		params.put("cl_address",cl_address);
		params.put("cl_call",cl_call);
		params.put("cl_email",cl_email);
		params.put("cl_state",cl_state);
		
		logger.info("params : {}"+params);
		logger.info("어떤 사진 파일을 보내시나요? :"+file);
		
		boolean joinClient = service.joinClient(params);
		if(file!=null) {
			service.fileSave(file,cl_id);
		}
		map.put("success", joinClient);
		
		
		return map;
	}
	
	
	/*
	//파일 업로드 팝업창 띄우기
	@RequestMapping(value = "/clientPhotoUploadForm.go")
	public String uploadForm() {

		return "clientPhotoUploadForm";
	}
	*/
	
	/*
	//파입 업로드 요청 보내기
	@RequestMapping(value = "/clientPhotoUpload.do")
	public ModelAndView clientPhotoUpload(MultipartFile file, HttpSession session) {
		logger.info("개인회원 사진 upload 요청");
		return service.fileUpload(file,session);
	}
	*/
	
	/*
	//파일 삭제 , 세션에서 지우기
	@RequestMapping(value = "/fileDelete")
	@ResponseBody
	public HashMap<String,Object> fileDelete(@RequestParam String fileName, HttpSession session) {
		logger.info(fileName+" 삭제 요청");
		return service.fileDelete(fileName,session);
	}
	*/
	
		
	
	
	
	// 개인회원,기업회원,관리자 로그인 (String으로 받아옴)
	@RequestMapping(value = "/login.do", method= RequestMethod.POST)
	public String login(Model model,HttpServletRequest request) throws Exception{
		logger.info("로그인 요청!");
		
		String pclose = "pclose";
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String state = request.getParameter("memberSelect");
		
		// 개인회원 로그인
		String clientLogin = service.clientLogin(id,pw);
		MemberDTO client = service.clientdto(clientLogin);
		
		// 기업회원 로그인
		String companyLogin = service.companyLogin(id,pw);
		MemberDTO company = service.companydto(companyLogin);
		
		// 관리자 로그인
		String adminLogin = service.adminLogin(id,pw);
		MemberDTO admin = service.admindto(adminLogin);
		
		
		logger.info("개인회원 로그인 시도 아이디 : "+clientLogin);
		logger.info("기업회원 로그인 시도 아이디 : "+companyLogin);
		logger.info("관리자 로그인 시도 아이디 : "+adminLogin);
		logger.info(state);
		
		String page = "loginPopup";
		String msg = "로그인 실패!";
		HttpSession session = request.getSession();
		if(clientLogin !=null && companyLogin==null && adminLogin==null) {
			if(client.getCl_state().equals("탈퇴회원")) {
				msg = "탈퇴된 회원입니다.";
				model.addAttribute("msg",msg);
			}else if(state.equals("개인회원")){				
				msg = "개인회원 로그인에 성공하였습니다!";
				model.addAttribute("pclose",pclose);
				model.addAttribute("msg",msg);
				session.setAttribute("loginId", clientLogin);
				session.setAttribute("isClient", "true");
			}else {
				msg ="아이디 / 비밀번호 또는 회원상태를 확인해주세요.";
				model.addAttribute("msg",msg);
			}
		}else if(companyLogin !=null && clientLogin==null && adminLogin==null) {
			if(company.getCom_state().equals("탈퇴회원")){
				msg = "탈퇴된 회원입니다.";
				model.addAttribute("msg",msg);
			}else if(state.equals("기업회원")){
			msg = "기업회원 로그인에 성공하였습니다!";
			model.addAttribute("pclose",pclose);
			model.addAttribute("msg",msg);
			session.setAttribute("loginId", companyLogin);
			session.setAttribute("isCompany", "true");
			}else {
				msg ="아이디 / 비밀번호 또는 회원상태를 확인해주세요.";
				model.addAttribute("msg",msg);
				page = "loginPopup";
			}
		}else if(adminLogin !=null && companyLogin==null && clientLogin==null) {
			if(admin.getAd_state().equals("탈퇴회원")) {
				msg = "탈퇴된 회원입니다.";
				model.addAttribute("msg",msg);
			}else if(state==null) {
				msg = "관리자 로그인에 성공하였습니다!";
				model.addAttribute("pclose",pclose);
				model.addAttribute("msg",msg);
				session.setAttribute("loginId", adminLogin);
				session.setAttribute("isAdmin", "true");
				session.setAttribute("isSuperAdmin", "true");
			}else {
				msg ="아이디 / 비밀번호 또는 회원상태를 확인해주세요.";
				model.addAttribute("msg",msg);
			}
		}else {
			msg ="아이디 / 비밀번호 또는 회원상태를 확인해주세요.";
			model.addAttribute("msg",msg);
		}
		
		return page;
	}
	
	/*
	// 개인회원,기업회원,관리자 로그인 (String으로 받아옴)
	@RequestMapping(value = "/login.do", method= RequestMethod.POST)
	public String login(Model model,HttpServletRequest request) throws Exception{
		logger.info("로그인 요청!");
		
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String state = request.getParameter("memberSelect");
		
		// 개인회원 로그인
		String clientLogin = service.clientLogin(id,pw);
		MemberDTO client = service.clientdto(clientLogin);
		
		// 기업회원 로그인
		String companyLogin = service.companyLogin(id,pw);
		MemberDTO company = service.companydto(companyLogin);
		
		// 관리자 로그인
		String adminLogin = service.adminLogin(id,pw);
		MemberDTO admin = service.admindto(adminLogin);
		
		
		logger.info("개인회원 로그인 시도 아이디 : "+clientLogin);
		logger.info("기업회원 로그인 시도 아이디 : "+companyLogin);
		logger.info("관리자 로그인 시도 아이디 : "+adminLogin);
		logger.info(state);
		
		String page = "main";
		String msg = "로그인 실패!";
		HttpSession session = request.getSession();
		if(clientLogin !=null && companyLogin==null && adminLogin==null) {
			if(client.getCl_state().equals("탈퇴회원")) {
				msg = "탈퇴된 회원입니다.";
				model.addAttribute("msg",msg);
				page = "loginPopup";
			}else if(state.equals("개인회원")){				
				msg = "개인회원 로그인에 성공하였습니다!";
				model.addAttribute("msg",msg);
				session.setAttribute("loginId", clientLogin);
				session.setAttribute("isClient", "true");
			}else {
				msg ="아이디 / 비밀번호 또는 회원상태를 확인해주세요.";
				model.addAttribute("msg",msg);
				page = "loginPopup";
			}
		}else if(companyLogin !=null && clientLogin==null && adminLogin==null) {
			if(company.getCom_state().equals("탈퇴회원")){
				msg = "탈퇴된 회원입니다.";
				model.addAttribute("msg",msg);
				page = "loginPopup";
			}else if(state.equals("기업회원")){
			msg = "기업회원 로그인에 성공하였습니다!";
			model.addAttribute("msg",msg);
			session.setAttribute("loginId", companyLogin);
			session.setAttribute("isCompany", "true");
			}else {
				msg ="아이디 / 비밀번호 또는 회원상태를 확인해주세요.";
				model.addAttribute("msg",msg);
				page = "loginPopup";
			}
		}else if(adminLogin !=null && companyLogin==null && clientLogin==null) {
			if(admin.getAd_state().equals("탈퇴회원")) {
				msg = "탈퇴된 회원입니다.";
				model.addAttribute("msg",msg);
				page = "loginPopup";
			}else if(state==null) {
				msg = "관리자 로그인에 성공하였습니다!";
				model.addAttribute("msg",msg);
				session.setAttribute("loginId", adminLogin);
				session.setAttribute("isAdmin", "true");
			}else {
				msg ="아이디 / 비밀번호 또는 회원상태를 확인해주세요.";
				model.addAttribute("msg",msg);
				page = "loginPopup";
			}
		}else {
			msg ="아이디 / 비밀번호 또는 회원상태를 확인해주세요.";
			model.addAttribute("msg",msg);
			page = "loginPopup";
		}
		
		return page;
	}
	*/
		
		
		
		
		
		
		
	/*  
	// 개인회원,기업회원,관리자 로그인 (id,pw 있는것 개수 count 해서 int에 담는 방법)
	@RequestMapping(value = "/login.do", method= RequestMethod.POST)
	public String login(Model model,HttpSession session,@RequestParam HashMap<String,String> params ) {
		logger.info("로그인 요청!! : "+params);
		
		String id = params.get("id");
		String pw = params.get("pw");
		String state = params.get("memberSelect");
		String page = "main";
		String msg = "로그인 실패!";
		int cnt1 = service.clientMember(id,pw,state);
		int cnt2 = service.companyMember(id,pw,state);
		int cnt3 = service.adminMember(id,pw,state);
		
		//로그인이 null이 아닌경우로 가져와서 로그인 다시할 것 제일 중요함 비밀번호 틀려도 접속됌 현재
		// ==아니고 equals를 사용해야 인식한다! 매우매우 중요함
		//매퍼에서 아이디와 비밀번호 맞는것의 갯수를 구해온다. -> 아이디가 기본키 이므로 한개이다.
		//0보다 크면 아이디와 비밀번호와 회원상태가 맞는것이 있다는 뜻이므로 로그인이 가능하다.
		if(cnt1>0) {
			//가져온 아이디와 비밀번호가 있으면 이제 회원상태를 확인한다.
			if(state.equals("개인회원")) {
				msg="개인회원 로그인에 성공하셨습니다!";
				model.addAttribute("msg",msg);
				session.setAttribute("loginId", id);
				//개인,기업,관리자에 따라 마이페이지가 다르게 보여야 하므로 세션을 추가해 준다.
				session.setAttribute("isClient", "true");
			}
		}else if(cnt2>0) { //기업회원 테이블에서 로그인할 아이디와 비밀번호가 있는경우
			//가져온 아이디와 비밀번호가 있으면 이제 회원상태를 확인한다.
			if(state.equals("기업회원")) {
				msg="기업회원 로그인에 성공하셨습니다!";
				model.addAttribute("msg",msg);
				session.setAttribute("loginId", id);
				//개인,기업,관리자에 따라 마이페이지가 다르게 보여야 하므로 세션을 추가해 준다.
				session.setAttribute("isCompany", "true");
			}
		}else if(cnt3>0) { //관리자 테이블에서 로그인할 아이디와 비밀번호가 있는경우
			//가져온 아이디와 비밀번호가 있으면 회원상태를 확인한다.
			if(!state.equals("개인회원") || !state.equals("기업회원") || !state.equals("탈퇴회원")) { // 관리자 회원도 탈퇴일 수 있으므로 조건을 준다.
				msg="관리자 로그인에 성공하셨습니다!";
				model.addAttribute("msg",msg);
				session.setAttribute("loginId", id);
				//개인,기업,관리자에 따라 마이페이지가 다르게 보여야 하므로 세션을 추가해 준다.
				session.setAttribute("isAdmin", "true");
			}
		}else if((cnt1<1 || cnt2<1 || cnt3<1) && state.equals("탈퇴회원")) {
				msg="탈퇴된 회원입니다!";
				model.addAttribute("msg",msg);
				page = "loginPopup";
		}else {
			msg= "아이디 또는 비밀번호 또는 회원상태를 확인해주세요.";
			model.addAttribute("msg",msg);
			page = "loginPopup";
		}
		
		return page;
	}
	*/
	
	
	//로그아웃 기능 구현
	@RequestMapping(value = "/logout.do")
	public String logout(RedirectAttributes attr,HttpSession session) {
		
		//위에 이름 날리기
		session.removeAttribute("loginId");
		
		// 개인회원 세션 날리기
        session.removeAttribute("isClient");
        // 기업회원 세션 날리기
        session.removeAttribute("isCompany");
        // 관리자 세션 날리기 
        session.removeAttribute("isAdmin");
        
        //추가되어야 할 것 관리자 로그아웃
        
		attr.addFlashAttribute("msg", "로그아웃 되었습니다.");
		//페이지는 임시
		return "redirect:/main.do";
		}
	
	
	//기업회원 가입페이지 이동 요청
	@RequestMapping(value = "/joinFormCompany.go")
	public String joinFormCompany() {

		return "joinFormCompany";
	}
	
	
	// 기업회원 아이디 중복체크
	@RequestMapping("/overlayCompanyId.ajax")
	@ResponseBody
	public HashMap<String, Object>overlayCompanyId(@RequestParam String chkComId) {
		logger.info("아이디 중복 체크 : "+chkComId);
		return service.overlayCompanyId(chkComId);
	}
	
	
	// 기업회원 이메일 중복체크
	@RequestMapping("/overlayComEmail.ajax")
	@ResponseBody
	public HashMap<String, Object>overlayComEmail(@RequestParam String chkComEmail) {
		logger.info("이메일 중복 체크 : "+chkComEmail);
		return service.overlayComEmail(chkComEmail);
	}
	
	
	// 기업회원 사업자 번호 중복체크
	@RequestMapping("/overlayNumber.ajax")
	@ResponseBody
	public HashMap<String, Object>overlayNumber(@RequestParam String chkNumber) {
		logger.info("사업자 번호 중복 체크 : "+chkNumber);
		return service.overlayNumber(chkNumber);
	}
	
	
	// 기업회원 회원가입 요청 ajax 버전 + 파일 저장
	@RequestMapping("/joinCompany.ajax")
	@ResponseBody
	public HashMap<String, Object> joinCompany(
			@RequestParam(value="com_id") String com_id, @RequestParam(value="com_pw") String com_pw,
			@RequestParam(value="com_business_no") String com_business_no, @RequestParam(value="com_name") String com_name,
			@RequestParam(value="com_address") String com_address, @RequestParam(value="com_call") String com_call,
			@RequestParam(value="com_email") String com_email, @RequestParam(value="com_state") String com_state,
			@RequestParam(value="file",required=false) MultipartFile file){
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, String> params = new HashMap<String, String>();
		
		params.put("com_id", com_id);
		params.put("com_pw",com_pw);
		params.put("com_business_no",com_business_no);
		params.put("com_name",com_name);
		params.put("com_address",com_address);
		params.put("com_call",com_call);
		params.put("com_email", com_email);
		params.put("com_state", com_state);
		
		logger.info("params : {}"+params);
		logger.info("어떤 사진 파일을 보내시나요? :"+file);
		
		boolean joinCompany = service.joinCompany(params);
		if(file!=null) {
			service.fileSave2(file,com_id);
		}
		map.put("joinCompany", joinCompany);
		return map;
	}

	
	// 개인회원 아이디 찾기 페이지 이동요청
	@RequestMapping(value = "/findClientId.go")
	public String findClientIdForm() {
		logger.info("개인회원 ID찾기 페이지 이동");
		return "findClientId";
	}
	
	
	// 기업회원 아이디 찾기 페이지 이동요청
	@RequestMapping(value = "/findCompanyId.go")
	public String findCompanyIdForm() {
		logger.info("기업회원 ID찾기 페이지 이동");
		return "findCompanyId";
	}
	
	
	// 개인회원 아이디 찾기 요청! 
	// HttpServletRequest : jsp 파일에서 파라미터들의 내용을 가져오는 역할
	@RequestMapping(value = "/findClientId.do", method= RequestMethod.POST)
	public String findClientId(Model model, HttpServletRequest req) {
		logger.info("개인회원 ID찾기 요청");
		// HttpServletRequest로 파라미터를 가져오고 그 파라미터들을 꺼낼때는 getParameter로 파라미터값을 하나씩 꺼낸다.
		// 이때 getParameter는 모두 String 타입이다 *** 중요
		String name = req.getParameter("cl_name");
		String email = req.getParameter("cl_email");
		String msg = "일치하는 정보가 없습니다.";
		String page = "findClientId";
		
		
		logger.info(name+" , "+email+"을 가진 아이디가 있는지 확인");
		// 서비스에 아이디 찾기 요청을 보내는데 name과 email변수를 담아 보낸다.
		// 서비스에서 dao를 거쳐 mapper에서 DB를 확인해서 아이디가 있는 경우 그 값을 id라는 변수에 담는다.
		String id = service.findClientId(name,email);
		
		// db에서 맞는 Id가 있는지 확인하고 돌아와서 값이 있는경우 아래 구문을 실행한다.
		if(id !=null) {
			msg = "아이디는 "+id+" 입니다.";
			page = "main";
		}
		
		model.addAttribute("msg",msg);
		
		return page;
	}
	
	
	// 기업회원 아이디 찾기 요청! 
	// HttpServletRequest : jsp 파일에서 파라미터들의 내용을 가져오는 역할
	@RequestMapping(value = "/findCompanyId.do", method= RequestMethod.POST)
	public String findCompanyId(Model model, HttpServletRequest req) {
		logger.info("개인회원 ID찾기 요청");
		// HttpServletRequest로 파라미터를 가져오고 그 파라미터들을 꺼낼때는 getParameter로 파라미터값을 하나씩 꺼낸다.
		// 이때 getParameter는 모두 String 타입이다 *** 중요
		String email = req.getParameter("com_email");
		String number = req.getParameter("com_business_no");
		String msg = "일치하는 정보가 없습니다.";
		String page = "findCompanyId";
		
		
		logger.info(email+" , "+number+"을 가진 아이디가 있는지 확인");
		// 서비스에 아이디 찾기 요청을 보내는데 name과 email변수를 담아 보낸다.
		// 서비스에서 dao를 거쳐 mapper에서 DB를 확인해서 아이디가 있는 경우 그 값을 id라는 변수에 담는다.
		String id = service.findCompanyId(email,number);
		
		// db에서 맞는 Id가 있는지 확인하고 돌아와서 값이 있는경우 아래 구문을 실행한다.
		if(id !=null) {
			msg = "아이디는 "+id+" 입니다.";
			page = "main";
		}
		
		model.addAttribute("msg",msg);
		
		return page;
	}
	
	
	// 개인회원 비밀번호 찾기 페이지 이동요청
	@RequestMapping(value = "/findClientPw.go")
	public String findClientPwForm() {
		logger.info("개인회원 ID찾기 페이지 이동");
		return "findClientPw";
	}
	
	
	// 기업회원 비밀번호 찾기 페이지 이동요청
	@RequestMapping(value = "/findCompanyPw.go")
	public String findCompanyPwForm() {
		logger.info("기업회원 ID찾기 페이지 이동");
		return "findCompanyPw";
	}
	
	
	// 개인회원 비밀번호 찾기 요청! 
	// HttpServletRequest : jsp 파일에서 파라미터들의 내용을 가져오는 역할
	@RequestMapping(value = "/findClientPw.do", method= RequestMethod.POST)
	public String findClientPw(Model model, HttpServletRequest req,HttpSession session) {
		logger.info("개인회원 PW찾기 요청");
		// HttpServletRequest로 파라미터를 가져오고 그 파라미터들을 꺼낼때는 getParameter로 파라미터값을 하나씩 꺼낸다.
		// 이때 getParameter는 모두 String 타입이다 *** 중요
		String id = req.getParameter("cl_id");
		String name = req.getParameter("cl_name");
		String email = req.getParameter("cl_email");
		
		
		logger.info(id+" , "+name+" , "+email+"을 가진 데이터가 DB에 있는지 확인");
		// 서비스에 비밀번호 찾기 요청을 보내는데 Id와 name과 email변수를 담아 보낸다.
		// 서비스에서 dao를 거쳐 mapper에서 DB를 확인해서 아이디가 있는 경우 그 값을 row라는 변수에 담는다.
		// 해당 변수 3개와 일치하는 아이디를 카운트해오는 것이므로 int 타입이다.
		int row = service.findClientPw(id,name,email);
		
		// db에서 맞는 Id가 있는지 확인하고 돌아와서 값이 있는경우 아래 구문을 실행한다.
		if(row>0) {
			model.addAttribute("cl_id", id);
			//어떤 아이디에 비밀번호를 변경할 것인지에 대한 세션 저장
			session.setAttribute("findId", id);
			//새 비밀번호 설정 페이지 이동
			return "clientPwChangeForm";
		}else {
			model.addAttribute("msg","일치하는 정보가 없습니다.");
			return "findClientPw";
			
		}
	}
	
	//개인회원 새 비밀번호 설정 페이지 이동
    @RequestMapping(value="/clientPwChange.go")
    public String clientPwChangeForm(HttpSession session,Model model) {
        logger.info("새 비밀번호 설정 페이지 이동");
        String id = (String) session.getAttribute("findId");
        String page = "clientPwChangeForm";
        logger.info("어떤 아이디 비밀번호 재설정? : "+id);
        if(id==null) {
        	model.addAttribute("msg","비밀번호 찾기 먼저 진행해 주세요.");
        	page = "main";
        }
        
        return page;
    }
  
    
    //개인회원 새 비밀번호 설정 요청
    @RequestMapping(value="/clientPwChange.do")
    public String clientPwChange(Model model,HttpServletRequest req, HttpSession session) {
		
    	//세션을 캐스팅해준다.
    	String id = (String) session.getAttribute("findId");
    	String page = "clientPwChangeForm";
    	//세션의 값이 null이 아닌경우 하단 구문을 실행한다.
    	if(id !=null) {
    		//입력한 비밀번호를 HttpServletRequest를 통해 service로 보내준다.
    		service.clientPwChange(id, req.getParameter("cl_pw"));
    		page = "main";
    		model.addAttribute("msg", "비밀번호 변경에 성공하였습니다.");
    	}
    	
    	//비밀번호를 변경하였으면 다시 로그인하기 위해 세션을 지워준다.
    	session.removeAttribute("findId");
    	
    	return page;
    }
    
    
	 // 기업회원 비밀번호 찾기 요청! 
	// HttpServletRequest : jsp 파일에서 파라미터들의 내용을 가져오는 역할
	@RequestMapping(value = "/findCompanyPw.do", method= RequestMethod.POST)
	public String findCompanyPw(Model model, HttpServletRequest req,HttpSession session) {
		logger.info("기업회원 PW찾기 요청");
		// HttpServletRequest로 파라미터를 가져오고 그 파라미터들을 꺼낼때는 getParameter로 파라미터값을 하나씩 꺼낸다.
		// 이때 getParameter는 모두 String 타입이다 *** 중요
		String id = req.getParameter("com_id");
		String email = req.getParameter("com_email");
		String number = req.getParameter("com_business_no");
		
		
		logger.info(id+" , "+email+" , "+number+"을 가진 데이터가 DB에 있는지 확인");
		// 서비스에 비밀번호 찾기 요청을 보내는데 Id와 name과 email변수를 담아 보낸다.
		// 서비스에서 dao를 거쳐 mapper에서 DB를 확인해서 아이디가 있는 경우 그 값을 row라는 변수에 담는다.
		// 해당 변수 3개와 일치하는 아이디를 카운트해오는 것이므로 int 타입이다.
		int row = service.findCompanyPw(id,email,number);
		
		// db에서 맞는 Id가 있는지 확인하고 돌아와서 값이 있는경우 아래 구문을 실행한다.
		if(row>0) {
			model.addAttribute("com_id", id);
			//어떤 아이디에 비밀번호를 변경할 것인지에 대한 세션 저장
			session.setAttribute("findId", id);
			//새 비밀번호 설정 페이지 이동
			return "companyPwChangeForm";
		}else {
			model.addAttribute("msg","일치하는 정보가 없습니다.");
			return "findCompanyPw";
			
		}
	}
	
	
	//기업회원 새 비밀번호 설정 요청
    @RequestMapping(value="/companyPwChange.do")
    public String companyPwChange(Model model,HttpServletRequest req, HttpSession session) {
		
    	//세션을 캐스팅해준다.
    	String id = (String) session.getAttribute("findId");
    	String page = "companyPwChangeForm";
    	//세션의 값이 null이 아닌경우 하단 구문을 실행한다.
    	if(id !=null) {
    		//입력한 비밀번호를 HttpServletRequest를 통해 service로 보내준다.
    		service.companyPwChange(id, req.getParameter("com_pw"));
    		page = "main";
    		model.addAttribute("msg", "비밀번호 변경에 성공하였습니다.");
    	}
    	
    	//비밀번호를 변경하였으면 다시 로그인하기 위해 세션을 지워준다.
    	session.removeAttribute("findId");
    	
    	return page;
    }
    
    
    //개인회원 개인정보관리 페이지 이동 요청 및 리스트 뿌려주기 + 정보수정 페이지 리스트까지 같이 보여줌
    @RequestMapping(value="/clientInfoManagement.do")
    public String clientInfoManagement(HttpSession session, Model model) {
        logger.info("개인회원 개인정보관리 페이지 이동 및 리스트 보여주기 요청");
        
        //dto에 등록된 값들을 뿌려주기 위해 해당 로그인된 세션을 저장한 값을 dto에 담아준다.
        //loginId는 String 타입이므로 service에 String으로 보내야 한다. 그래서 세션을 형변환 해준다.
        MemberDTO dto = service.clientInfoManagement((String) session.getAttribute("loginId"));
        String id = (String) session.getAttribute("loginId");
        String page = "clientInfoManagement";
        
        if(id==null) {
        	model.addAttribute("msg","개인회원 전용 서비스입니다.");
        	page = "main";
        }
        
        //받아온 dto들을 model에 담아 jsp에 뿌려준다.
        model.addAttribute("clientInfo",dto);
        
        return page;
    }
    
    
    // 개인회원 정보수정 페이지 이동요청, 기존 개인정보 관리페이지에서 서비스에 요청한 값들 model에 담기
 	@RequestMapping(value = "/clientInfoUpdateForm.go")
 	public String clientInfoUpdateForm(HttpSession session, Model model) {
 		logger.info("개인회원 개인정보수정 페이지 이동 및 리스트 보여주기 요청");
        String page = "clientInfoUpdateForm";
 		String isClient = (String) session.getAttribute("isClient");
        //dto에 등록된 값들을 뿌려주기 위해 해당 로그인된 세션을 저장한 값을 dto에 담아준다.
        //loginId는 String 타입이므로 service에 String으로 보내야 한다. 그래서 세션을 형변환 해준다.
        MemberDTO dto = service.clientInfoManagement((String) session.getAttribute("loginId"));
        
        if(isClient==null) {
        	model.addAttribute("msg","개인회원 전용 서비스입니다.");
        	page = "main";
        }
        
        
        //받아온 dto들을 model에 담아 jsp에 뿌려준다.
        model.addAttribute("clientInfo",dto);
        
        return page;
 	}
 	
 	
 	// 개인회원 정보수정 요청시 현재 비밀번호 암호화 DB와 확인
 	@RequestMapping(value="/passwordConfirm.ajax")
 	@ResponseBody
 	public HashMap<String, Object> passwordConfirm(@RequestParam String cl_pw){
		logger.info("현재 비밀번호 확인 : "+cl_pw);
		return service.passwordConfirm(cl_pw);
	}
 	
 	/*
 	// 개인회원 정보수정 하기 요청
 	@RequestMapping(value="/clientInfoUpdate.do")
 	public String clientInfoUpdate(RedirectAttributes redirectAttr,HttpSession session, @RequestParam HashMap<String, String> params) {
 		
 		// 어떤 아이디의 정보를 수정할 것인지 확인하기 위해 로그인 세션 가져와서 담아줌 + 세션을 String으로 형변환
 		String loginId = (String) session.getAttribute("loginId");
 		
 		params.put("cl_id", loginId);
 		logger.info("params : {}"+params);
 		
 		//서비스에 어떤 값을 수정할 것인지 담아서 요청을 보낸다.
 		service.clientInfoUpdate(params);
 		
 		// ※중요 redirect 전에 경고창 출력할 때는 RedirectAttributes 를 이용해서 출력한다.
 		String msg = "수정이 완료되었습니다.";
 		redirectAttr.addFlashAttribute("msg",msg);
 		
 		return "redirect:/clientInfoManagement.do";
 	}
 	*/
 	
 	// 개인회원 정보수정 하기 요청
  	@RequestMapping(value="/clientInfoUpdate.do")
  	public String clientInfoUpdate(RedirectAttributes rttr,@RequestParam HashMap<String, String> params,MultipartFile file) {


  		logger.info("수정 요청 : "+params);
  		rttr.addFlashAttribute("msg","수정이 완료되었습니다.");

  		return service.clientInfoUpdate(params,file);
  	}
 	
 	
 	
 	
 	//기업회원 기업회원정보관리 페이지 이동 요청 및 리스트 뿌려주기 + 정보수정 페이지 리스트까지 같이 보여줌
    @RequestMapping(value="/companyInfoManagement.do")
    public String companyInfoManagement(HttpSession session, Model model) {
        logger.info("기업회원 기업회원정보관리 페이지 이동 및 리스트 보여주기 요청");
        
        //dto에 등록된 값들을 뿌려주기 위해 해당 로그인된 세션을 저장한 값을 dto에 담아준다.
        //loginId는 String 타입이므로 service에 String으로 보내야 한다. 그래서 세션을 형변환 해준다.
        MemberDTO dto = service.companyInfoManagement((String) session.getAttribute("loginId"));
        String id = (String) session.getAttribute("loginId");
        String page = "companyInfoManagement";
        
        if(id==null) {
        	model.addAttribute("msg","기업회원 전용 서비스입니다.");
        	page = "main";
        }
        
        
        //받아온 dto들을 model에 담아 jsp에 뿌려준다.
        model.addAttribute("companyInfo",dto);
        
        return page;
    }
 	
 	
    // 기업회원 회원정보수정 페이지 이동요청, 기존 기업회원정보 관리페이지에서 서비스에 요청한 값들 model에 담기
 	@RequestMapping(value = "/companyInfoUpdateForm.go")
 	public String companyInfoUpdateForm(HttpSession session, Model model) {
 		logger.info("기업회원 기업회원정보수정 페이지 이동 및 리스트 보여주기 요청");
 		String page = "companyInfoUpdateForm";
  		String isCompany = (String) session.getAttribute("isCompany");
  		
        //dto에 등록된 값들을 뿌려주기 위해 해당 로그인된 세션을 저장한 값을 dto에 담아준다.
        //loginId는 String 타입이므로 service에 String으로 보내야 한다. 그래서 세션을 형변환 해준다.
        MemberDTO dto = service.companyInfoManagement((String) session.getAttribute("loginId"));
        
        if(isCompany==null) {
        	model.addAttribute("msg","기업회원 전용서비스 입니다.");
        	page = "main";
        }
        
        //받아온 dto들을 model에 담아 jsp에 뿌려준다.
        model.addAttribute("companyInfo",dto);
        
        return page;
 	}
 	
 	
 	// 기업회원 회원정보수정 요청시 현재 비밀번호 암호화 DB와 확인
  	@RequestMapping(value="/passwordConfirm2.ajax")
  	@ResponseBody
  	public HashMap<String, Object> passwordConfirm2(@RequestParam String com_pw){
 		logger.info("현재 비밀번호 확인 : "+com_pw);
 		return service.passwordConfirm2(com_pw);
 	}
  	
  	
  	// 기업회원 정보수정 하기 요청
   	@RequestMapping(value="/companyMemberInfoUpdate.do")
   	public String companyMemberInfoUpdate(RedirectAttributes rttr,@RequestParam HashMap<String, String> params,MultipartFile file) {


   		logger.info("수정 요청 : "+params);
   		rttr.addFlashAttribute("msg","수정이 완료되었습니다.");

   		return service.companyMemberInfoUpdate(params,file);
   	}
  	
  	
  	// 개인회원 탈퇴 페이지 이동 요청
  	@RequestMapping(value="/clientBreakFormPopup.go")
  	public String clientBreakFormPopup(HttpSession session,Model model){
 		logger.info("회원탈퇴 페이지 이동");
 		String page = "clientBreakFormPopup";
  		String isClient = (String) session.getAttribute("isClient");
  		
  		if(isClient==null) {
  			model.addAttribute("msg","회원전용 서비스입니다.");
  			page = "main";
  		}
  		
 		
 		return page;
 	}
  	
  	
  	// 개인회원 탈퇴 요청
  	@RequestMapping(value="/clientDelete.do")
  	public String clientDelete(Model model, HttpSession session, @RequestParam HashMap<String, String> params) {
  	String cl_id = (String) session.getAttribute("loginId");
  	
  	String pclose = "pclose";
  	String page = "main";
  	params.put("cl_id", cl_id);
  	//회원 탈퇴가 완료된 경우 조건을 걸어서 회원관리 테이블에 인서트하는 요청을 보낸다.
  	if(service.clientDelete(cl_id)==true) {
  		// 기타를 선택한경우 조건을 걸어 기타에 대한 내용을 인서트한다.
  		if(params.get("mg_content").equals("기타")) {
            params.put("mg_content", "기타(" + params.get("mg_content_other") + ")");
        }
  		
  		service.clientManagement(params);
  		String msg = "탈퇴가 완료되었습니다.";
  		model.addAttribute("msg",msg);
  		model.addAttribute("pclose",pclose);
  		page = "clientBreakFormPopup";
  	}
  	
  	session.removeAttribute("loginId");
  	session.removeAttribute("isClient");
  	
  	return page;
  	}
  	
  	
  	// 기업회원 탈퇴 페이지 이동 요청
   	@RequestMapping(value="/companyBreakFormPopup.go")
   	public String companyBreakForm(HttpSession session,Model model){
  		logger.info("회원탈퇴 페이지 이동");
  		String page = "companyBreakFormPopup";
  		String isCompany = (String) session.getAttribute("isCompany");
  		
  		if(isCompany==null) {
  			model.addAttribute("msg","회원전용 서비스입니다.");
  			page = "main";
  		}
  		
  		return page;
  	}
  
   	
  	// 기업회원 탈퇴 요청
   	@RequestMapping(value="/companyDelete.do")
   	public String companyDelete(Model model, HttpSession session, @RequestParam HashMap<String, String> params) {
	String com_id = (String) session.getAttribute("loginId");
	
	String pclose = "pclose";
  	String page = "main";
	
	
   	params.put("com_id", com_id);
   	//회원 탈퇴가 완료된 경우 조건을 걸어서 회원관리 테이블에 인서트하는 요청을 보낸다.
   	if(service.companyDelete(com_id)==true) {
   		// 기타를 선택한경우 조건을 걸어 기타에 대한 내용을 인서트한다.
   		if(params.get("mg_content").equals("기타")) {
             params.put("mg_content", "기타(" + params.get("mg_content_other") + ")");
         }
   		
   		service.companyManagement(params);
   		String msg = "탈퇴가 완료되었습니다.";
   		model.addAttribute("msg",msg);
   		model.addAttribute("pclose",pclose);
   		page = "companyBreakFormPopup";
   	}
   	
   	session.removeAttribute("loginId");
   	session.removeAttribute("isCompany");
   	
   	return page;
   	}
  	
  	
   	// 관리자 관리 페이지 이동 및 리스트 보여주기 요청
   	
   	@RequestMapping(value="/adminManagementList.go")
   	public String adminManageGo() {   		
   		return "superPw";
   	}
   	
   	
   	@RequestMapping(value="/adminManagementList.do")
   	public String adminInfoManagement(Model model,HttpSession session,@RequestParam String superPw){
   		
   		boolean success=false;
   		boolean result=service.superLogin(superPw);
   		logger.info("결과는?"+result);
   		if(result) {
   			session.setAttribute("superPw", superPw);
   			success=true;
   		}   		
   		logger.info("보낼값은?"+success);
   		model.addAttribute("success", success);
   		return "superPw";   		
   	}
   	
   	@RequestMapping(value="/adminManagementListReal.Go")
   	public String adminManagementListReal(Model model,HttpSession session){   		
   		
  		logger.info("관리자 계정관리 페이지 이동");
  		String page = "adminManagementList";
  		String superPw = (String) session.getAttribute("superPw");
  		
  		if(superPw==null) {
  			model.addAttribute("msg","관리자 전용 패스워드를 확인해주세요.");
  			page = "main";
  		}
  		
  		ArrayList<MemberDTO> adminList = service.adminInfoManagement();
  		logger.info("list 갯수 : "+adminList.size());
  		model.addAttribute("adminList",adminList);  		
  		session.removeAttribute("superPw");
  		return page;
  	}
   	
   	
   	// 관리자 회원가입페이지 이동
 	@RequestMapping(value = "/joinFormAdmin.go")
 	public String joinFormAdmin(Model model, HttpSession session) {
 		String isAdmin = (String) session.getAttribute("isAdmin");
 		String page = "joinFormAdmin";
 		
 		if(isAdmin==null) {
 			model.addAttribute("msg","관리자 전용 페이지 입니다.");
 			page = "main";
 		}
 		
 		
 		return page;
 	}
 	
 	
 	// 관리자 아이디 중복체크
 	@RequestMapping("/overlayAdminId.ajax")
 	@ResponseBody
 	public HashMap<String, Object>overlayAdminId(@RequestParam String chkadminId) {
 		logger.info("아이디 중복 체크 : "+chkadminId);
 		return service.overlayAdminId(chkadminId);
 	}
 	
 	
 	// 관리자 이메일 중복체크
 	@RequestMapping("/overlayAdminEmail.ajax")
 	@ResponseBody
 	public HashMap<String, Object>overlayAdminEmail(@RequestParam String chkAdminEmail) {
 		logger.info("이메일 중복 체크 : "+chkAdminEmail);
 		return service.overlayAdminEmail(chkAdminEmail);
 	}
 	
 	
 	// 관리자 계정등록(회원가입) 요청 ajax 버전
 	@RequestMapping("/joinAdmin.ajax")
 	@ResponseBody
 	public HashMap<String, Object> joinAdmin(@RequestParam HashMap<String, Object> params){
 		logger.info("관리자 계정등록 요청 : "+params);
 		HashMap<String, Object> map = new HashMap<String, Object>();
 		service.joinAdmin(params);
 		map.put("success", 1);
 		return map;
 	}
 		
   	
   	// 관리자 상태 변경 팝업 페이지 이동 및 항목 보여주기 요청
   	@RequestMapping(value="/adminStateChangePopup.go")
   	public String adminStateChangePopup(HttpSession session,Model model, @RequestParam String ad_id) {
   		logger.info("어떤 아이디의 상태를 변경할건가? : "+ad_id);
   		MemberDTO dto = service.adminStateChangePopup(ad_id);

   		String page = "adminStateChangePopup";
  		String isAdmin = (String) session.getAttribute("isAdmin");
  		
  		if(isAdmin==null) {
  			model.addAttribute("msg","관리자 전용 페이지입니다.");
  			page = "main";
  		}
   		
   		
   		model.addAttribute("adminState",dto);
   		
   		
   		
   		return page;
   	}
   	
   	
   	// 관리자 상태수정 요청
   	@RequestMapping(value="/adminStateChange.do")
   	public String adminStateChange(Model model,@RequestParam HashMap<String, String> params) {
   		
   		String pclose = "pclose";
   		
   		logger.info("params : {}"+params);
   		
   		service.adminStateUpdate(params);
   		
   		model.addAttribute("pclose",pclose);
   		model.addAttribute("msg","수정에 성공하였습니다.");
   		
   		return "adminStateChangePopup";
   	}
   	
   	
   	// 관리자 상태 상세보기 팝업 요청
   	@RequestMapping(value="/adminStateDetailPopup.do")
   	public String adminStateDetailPopup(HttpSession session,Model model,@RequestParam String ad_id) {
   	
   		logger.info("어떤 아이디의 상태를 확인할건가? : "+ad_id);
   		MemberDTO dto = service.adminStateChangePopup(ad_id);
   		
   		String page = "adminStateDetailPopup";
  		String isAdmin = (String) session.getAttribute("isAdmin");
  		
  		if(isAdmin==null) {
  			model.addAttribute("msg","관리자 전용 페이지입니다.");
  			page = "main";
  		}
   		
   		model.addAttribute("adminState",dto);
   	
   		return page;
   			
   	}
   	
   	
   	// 개인회원 관리 페이지 이동 및 리스트 호출 요청 + 페이징 처리 (전체리스트)
   	@RequestMapping(value="/clientManagementList.do")
   	public String clientManagementList(HttpSession session,Model model,Criteria cri) {
   		
   		//리스트 페이징 처리하기 위해 리스트를 보여주는 서비스를 보내는데 그안에 cri를 담는다.
   		//service의 최종 목적지 mapper에는 10개씩 보여달라는 쿼리문이 작성되어 있어 10개 이상이어도 10개까지만 보여준다.
   		ArrayList<MemberDTO> clientList = service.clientManagementList(cri);
  		logger.info("list 갯수 : "+clientList.size()); //mapper에 limit 10개로 해놓았으므로 당연히 10개초과는 안나온다.
  		model.addAttribute("clientList",clientList);
  		int pageNum = cri.getPageNum();
  		
  		String page = "clientManagementList";
  		String isAdmin = (String) session.getAttribute("isAdmin");
  		
  		if(isAdmin==null) {
  			model.addAttribute("msg","관리자 전용 페이지입니다.");
  			page = "main";
  		}
  		
  		//pageNum 이동을 하기위해 담아서 jsp에 보내준다.
  		model.addAttribute("pageNum",pageNum);
  		
  		//페이징 처리를 위한 게시글의 총 개수를 구해온다.
  		int total = service.clientListTotal();
  		// PageMakerDTO 라는 것에 총개수와 몇개씩 보여줄 것인지에 대한 내용을 담는다.
  		// 단순히 총인원 리스트를 pageMaker에 맞게 잘라서 표현한 것일 뿐 이동에 대한 내용은 없다.
  		PageMakerDTO pageMaker = new PageMakerDTO(cri, total);
  		model.addAttribute("pageMaker",pageMaker);
		logger.info("개인회원의 총 인원은?? : "+total);

  		
  		return page;
   	}
   	
   	
   	//개인회원 리스트 검색기능 + 페이징 처리
   	@RequestMapping(value="/clientListSearch.do")
   	public String clientListSearch(Model model,HttpSession session,@RequestParam String searchOption, String search, int pageNum) {
   	
   		logger.info("옵션 확인: "+searchOption+" / "+search+" / "+pageNum);
   		
   		model.addAttribute("searchOption",searchOption);
   		model.addAttribute("search",search);
   		
   		String page = "clientManagementList";
  		String isAdmin = (String) session.getAttribute("isAdmin");
  		
  		if(isAdmin==null) {
  			model.addAttribute("msg","관리자 전용 페이지입니다.");
  			page = "main";
  		}
   		
   		
   		//검색한 내용 페이징 처리하기
   		int skip=(pageNum-1) * 10;
   		
   		ArrayList<MemberDTO> dto = service.clientListSearch(searchOption,search,skip);
   		// 전체 리스트에서 넣었던 것을 검색을 통해 가져온 값을 바꿔넣어준다.
   		model.addAttribute("clientList",dto);
   		
   		// 검색해서 나온 결과의 갯수를 세어본다.
   		int clientSearchTotal = service.clientSearchTotal(searchOption,search);
   		model.addAttribute("pageNum",pageNum);
   		
   		// 검색 결과를 어떻게 나눌것인지 페이지 메이커 변수에 담아준다.
   		PageMakerDTO pageMaker = new PageMakerDTO(pageNum, clientSearchTotal);
   		// 검색 결과숫자와 페이지 갯수를 처리한 변수를 담아서 jsp에 보내준다.
   		model.addAttribute("pageMaker",pageMaker);
   		
   		   		
   		return page;
   	}
   	
   	
   	// 개인회원 상태 변경 팝업 페이지 이동 및 항목 보여주기 요청
   	@RequestMapping(value="/clientStateChangePopup.go")
   	public String clientStateChangePopup(HttpSession session,Model model, @RequestParam String cl_id) {
   		logger.info("어떤 아이디의 상태를 변경할건가? : "+cl_id);
   		MemberDTO dto = service.clientStateChangePopup(cl_id);

   		String page = "clientStateChangePopup";
  		String isAdmin = (String) session.getAttribute("isAdmin");
  		
  		if(isAdmin==null) {
  			model.addAttribute("msg","관리자 전용 페이지입니다.");
  			page = "main";
  		}
   		
   		model.addAttribute("clientState",dto);
   		
   		
   		
   		return page;
   	}
   	
   	
   	// 개인회원 상태수정 요청
   	@RequestMapping(value="/clientStateChange.do")
   	public String clientStateChange(Model model,@RequestParam HashMap<String, String> params) {
   		logger.info("params : {}"+params);
   		
   		String pclose = "pclose";
   		
   		service.clientStateUpdate(params);
   		
   		model.addAttribute("pclose",pclose);
   		model.addAttribute("msg","수정에 성공하였습니다.");
   		
   		return "clientStateChangePopup";
   	}
   	
   	
   	// 개인회원 상태 상세보기 팝업 요청
   	@RequestMapping(value="/clientStateDetailPopup.do")
   	public String clientStateDetailPopup(HttpSession session,Model model,@RequestParam String cl_id) {
   	
   		logger.info("어떤 아이디의 상태를 확인할건가? : "+cl_id);
   		MemberDTO dto = service.clientStateChangePopup(cl_id);
   		
   		String page = "clientStateDetailPopup";
  		String isAdmin = (String) session.getAttribute("isAdmin");
  		
  		if(isAdmin==null) {
  			model.addAttribute("msg","관리자 전용 페이지입니다.");
  			page = "main";
  		}
   		
   		model.addAttribute("clientState",dto);
   	
   		return page;
   			
   	}
   	
   	
   	// 기업회원 관리 페이지 이동 및 리스트 호출 요청 + 페이징 처리 (전체리스트)
   	@RequestMapping(value="/companyManagementList.do")
   	public String companyManagementList(HttpSession session,Model model,Criteria cri) {
   		
   		//리스트 페이징 처리하기 위해 리스트를 보여주는 서비스를 보내는데 그안에 cri를 담는다.
   		//service의 최종 목적지 mapper에는 10개씩 보여달라는 쿼리문이 작성되어 있어 10개 이상이어도 10개까지만 보여준다.
   		ArrayList<MemberDTO> companyList = service.companyManagementList(cri);
  		logger.info("list 갯수 : "+companyList.size()); //mapper에 limit 10개로 해놓았으므로 당연히 10개초과는 안나온다.
  		model.addAttribute("companyList",companyList);
  		int pageNum = cri.getPageNum();
  		
  		String page = "companyManagementList";
  		String isAdmin = (String) session.getAttribute("isAdmin");
  		
  		if(isAdmin==null) {
  			model.addAttribute("msg","관리자 전용 페이지입니다.");
  			page = "main";
  		}
  		
  		//pageNum 이동을 하기위해 담아서 jsp에 보내준다.
  		model.addAttribute("pageNum",pageNum);
  		
  		//페이징 처리를 위한 게시글의 총 개수를 구해온다.
  		int total = service.companyListTotal();
  		// PageMakerDTO 라는 것에 총개수와 몇개씩 보여줄 것인지에 대한 내용을 담는다.
  		// 단순히 총인원 리스트를 pageMaker에 맞게 잘라서 표현한 것일 뿐 이동에 대한 내용은 없다.
  		PageMakerDTO pageMaker = new PageMakerDTO(cri, total);
  		model.addAttribute("pageMaker",pageMaker);
		logger.info("기업회원의 총 갯수는?? : "+total);

  		
  		return page;
   	}
   	
   	
   	//기업회원 리스트 검색기능 + 페이징 처리
   	@RequestMapping(value="/companyListSearch.do")
   	public String companyListSearch(Model model,HttpSession session,@RequestParam String searchOption, String search, int pageNum) {
   	
   		logger.info("옵션 확인: "+searchOption+" / "+search+" / "+pageNum);
   		
   		model.addAttribute("searchOption",searchOption);
   		model.addAttribute("search",search);
   		
   		String page = "companyManagementList";
  		String isAdmin = (String) session.getAttribute("isAdmin");
  		
  		if(isAdmin==null) {
  			model.addAttribute("msg","관리자 전용 페이지입니다.");
  			page = "main";
  		}
   		
   		//검색한 내용 페이징 처리하기
   		int skip=(pageNum-1) * 10;
   		
   		ArrayList<MemberDTO> dto = service.companyListSearch(searchOption,search,skip);
   		// 전체 리스트에서 넣었던 것을 검색을 통해 가져온 값을 바꿔넣어준다.
   		model.addAttribute("companyList",dto);
   		
   		// 검색해서 나온 결과의 갯수를 세어본다.
   		int companySearchTotal = service.companySearchTotal(searchOption,search);
   		model.addAttribute("pageNum",pageNum);
   		
   		// 검색 결과를 어떻게 나눌것인지 페이지 메이커 변수에 담아준다.
   		PageMakerDTO pageMaker = new PageMakerDTO(pageNum, companySearchTotal);
   		// 검색 결과숫자와 페이지 갯수를 처리한 변수를 담아서 jsp에 보내준다.
   		model.addAttribute("pageMaker",pageMaker);
   		
   		   		
   		return page;
   	}
   	
   	  	
   	// 기업회원 상태 변경 팝업 페이지 이동 및 항목 보여주기 요청
   	@RequestMapping(value="/companyStateChangePopup.go")
   	public String companyStateChangePopup(HttpSession session,Model model, @RequestParam String com_id) {
   		logger.info("어떤 아이디의 상태를 변경할건가? : "+com_id);
   		MemberDTO dto = service.companyStateChangePopup(com_id);

   		String page = "companyStateChangePopup";
  		String isAdmin = (String) session.getAttribute("isAdmin");
  		
  		if(isAdmin==null) {
  			model.addAttribute("msg","관리자 전용 페이지입니다.");
  			page = "main";
  		}
   		
   		model.addAttribute("companyState",dto);
   		
   		
   		
   		return page;
   	}
   	
   	
   	// 기업회원 상태수정 요청
   	@RequestMapping(value="/companyStateChange.do")
   	public String companyStateChange(Model model,@RequestParam HashMap<String, String> params) {
   		
   		logger.info("params : {}"+params);
   		
   		String pclose = "pclose";
   		
   		service.companyStateChange(params);
   		
   		model.addAttribute("pclose",pclose);
   		model.addAttribute("msg","수정에 성공하였습니다.");
   		
   		return "companyStateChangePopup";
   	}
   	
   	
   	// 개인회원 상태 상세보기 팝업 요청
   	@RequestMapping(value="/companyStateDetailPopup.do")
   	public String companyStateDetailPopup(HttpSession session,Model model,@RequestParam String com_id) {
   	
   		logger.info("어떤 아이디의 상태를 확인할건가? : "+com_id);
   		MemberDTO dto = service.companyStateChangePopup(com_id);
   		
   		String page = "companyStateDetailPopup";
  		String isAdmin = (String) session.getAttribute("isAdmin");
  		
  		if(isAdmin==null) {
  			model.addAttribute("msg","관리자 전용 페이지입니다.");
  			page = "main";
  		}
   		
   		model.addAttribute("companyState",dto);
   	
   		return page;
   			
   	}
   	
   	
   	

   	
   	
}