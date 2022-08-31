package com.signal.member.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.signal.all.dto.MemberDTO;
import com.signal.enter.controller.Criteria;
import com.signal.member.dao.MemberDAO;

@Service
public class MemberService {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired MemberDAO dao;
	@Value("#{config['superUser.super.id']}") String adminId;
	@Value("#{config['superUser.super.pw']}") String adminPw;
	
	// 개인회원 아이디 중복확인
	public HashMap<String, Object> overlayClientId(String cl_id) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		String overclId = dao.overlayClientId(cl_id);
		logger.info("중복된 아이디 인가요? : "+overclId);
		//중복된 아이디이면 중복 아이디:(중복아이디)가 보여짐 -> 사용불가 아이디
		//사용가능한 아이디이면 중복 아이디:(null)이 보여짐 ->사용가능 아이디
		boolean over = overclId == null?false:true;
		map.put("overlayClientId", over);
		
		return map;
	}
	

	// 개인회원 이메일 중복확인
	public HashMap<String, Object> overlayEmail(String cl_email) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		String overClientEmail=dao.overlayEmail(cl_email);
		logger.info("중복된 이메일 인가요? : "+overClientEmail);
		//중복된 이메일이면 중복 이메일:(중복이메일)이 보여짐 ->사용불가 이메일
		//사용가능한 이메일이면 중복 이메일:(null)이 보여짐 ->사용가능 이메일
		boolean over = overClientEmail ==null?false:true;
		map.put("overlayEmail", over);
		
		return map;
	}


	/* 개인회원 가입서비스 form 방식
	public int clientJoin(HashMap<String, String> params, MultipartFile cl_photo) {
		logger.info("회원가입 서비스 요청");
		return dao.clientJoin(params);		
	}
	*/
	
	

	/* 개인회원 회원가입 서비스 ajax 파일업로드 불가
	public HashMap<String, Object> joinClient(HashMap<String, Object> params) {
		logger.info("개인회원 가입 서비스이동");
		HashMap<String, Object> map = new HashMap<String, Object>();
		int row = dao.joinClient(params);
		boolean success = row>0?true:false;
		map.put("success", success);
		return map;	
	}
	*/

	

	// 개인회원 회원가입 서비스 ajax
	public boolean joinClient(HashMap<String, String> params) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		
		int row = dao.joinClient(params);
		boolean success = false;
		
		
		if(row>0) {
			success = true;
		}
		result.put("success", success);
		
		return success;
	}
	
	
	// 개인회원 가입 성공시 파일 저장 + 새로운 파일명
	public void fileSave(MultipartFile file, String cl_id) {
		String oriFileName = file.getOriginalFilename(); // 원본 파일명
		
		logger.info("원본파일명 : "+oriFileName);
		
		if(!oriFileName.equals("")) {
			String ext = oriFileName.substring(oriFileName.lastIndexOf(".")).toLowerCase();
			String newFileName = System.currentTimeMillis()+ext;
		
			try {
				byte[] arr = file.getBytes();
				Path path = Paths.get("C:/upload/"+newFileName);
				Files.write(path, arr);
				logger.info(newFileName+" save ok");
				//4. 업로드 후 photo 테이블에 데이터 입력	
				dao.fileWrite(newFileName,cl_id);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
		
		
	}


	/*
	private void fileSave(MultipartFile file) {
		String ba_org_name= file.getOriginalFilename();//파일명 추출
		
		if(!ba_org_name.equals("")) {
			logger.info("업로드 진행");
			//확장자 분리
			String ba_ext = ba_org_name
					.substring(ba_org_name.lastIndexOf(".")).toLowerCase();			
			// 새 이름 만들기
			String ba_new_name = System.currentTimeMillis()+ba_ext;
			
			logger.info(ba_org_name+" => "+ba_new_name);
							
			//파일 받아서 저장하기
			try {
				byte[] arr = file.getBytes();
				Path path = Paths.get("C:/upload/"+ba_new_name);
				Files.write(path, arr);
				logger.info(ba_new_name+" save ok");
				dao.fileWrite(ba_new_name);
				
			} catch (IOException e) {
				e.printStackTrace();
			}				
		}
	}
	*/
	
	
	
	// 개인회원 로그인서비스
	public String clientLogin(String id, String pw) {
		return dao.clientLogin(id,pw);
	}


	// 개인회원 로그인서비스
	public MemberDTO clientdto(String clientLogin) {
		return dao.clientdto(clientLogin);
	}


	// 기업회원 로그인서비스
	public String companyLogin(String id, String pw) {
		return dao.companyLogin(id,pw);
	}


	// 기업회원 로그인서비스
	public MemberDTO companydto(String companyLogin) {
		return dao.companydto(companyLogin);
	}
	
	
	// 관리자 로그인서비스
	public String adminLogin(String id, String pw) {
		return dao.adminLogin(id,pw);
	}
	
	
	// 관리자 로그인서비스
	public MemberDTO admindto(String adminLogin) {
		return dao.admindto(adminLogin);
	}
	
	
	/* int로 요청 보내는 경우
	// 개인회원 로그인서비스
	public int clientMember(String id, String pw, String state) {
		logger.info("개인회원 로그인 서비스 요청");
		return dao.clientMember(id,pw,state);
	}


	// 기업회원 로그인서비스
	public int companyMember(String id, String pw, String state) {
		logger.info("기업회원 로그인 서비스 요청");
		return dao.companyMember(id,pw,state);
	}


	//관리자 로그인 서비스
	public int adminMember(String id, String pw, String state) {
		// TODO Auto-generated method stub
		return 0;
	}
	*/
	
	
	/* 사진 업로드 포함 회원가입 방법 *************
	public String clientJoin(HashMap<String, String> params, MultipartFile cl_photo) {
		logger.info("회원가입 서비스 요청");
		
		MemberDTO dto = new MemberDTO();
		dto.setCl_id(params.get("cl_id"));
		dto.setCl_pw(params.get("cl_pw"));
		dto.setCl_age(params.get("cl_age"));
		dto.setCl_address(params.get("cl_address"));
		dto.setCl_call(params.get("cl_call"));
		dto.setCl_gender(params.get("gender"));
		dto.setCl_email(params.get("cl_email"));
		dto.setCl_state(params.get("cl_state"));
		dto.setCl_birth(params.get("cl_birth"));
		
		int success = dao.clientJoin(dto);
		String clId = dto.getCl_id();
		if(success>0) {
			fileSave(cl_photo,clId);
		}
		return "login";
	}


	private void fileSave(MultipartFile cl_photo, String clId) {
		String oriFileName= cl_photo.getOriginalFilename(); // 파일명 추출
		if(!oriFileName.equals("")) {
			logger.info("업로드 진행");
			//3-2. 확장자 분리
			String ext = oriFileName
					.substring(oriFileName.lastIndexOf(".")).toLowerCase();			
			//3-3. 새 이름 만들기
			String newFileName = System.currentTimeMillis()+ext;
			
			logger.info(oriFileName+" => "+newFileName);
			
			//3-4. 파일 받아서 저장하기
			try {
				byte[] arr = cl_photo.getBytes();
				Path path = Paths.get("C:/upload/"+newFileName);
				Files.write(path, arr);
				logger.info(newFileName+" save ok");
				//4. 업로드 후 photo 테이블에 데이터 입력	
				dao.fileWrite(oriFileName,newFileName,clId);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	*/

	/*
	//개인회원 가입서비스 요청 및 파일업로드 form 방식
	public int clientJoin(HashMap<String, String> params,MultipartFile cl_photo) {
		logger.info("회원가입 서비스 요청");
		return dao.clientJoin(params,cl_photo);
	}
	
	public void fileWrite(MultipartFile cl_photo) {
		logger.info("사진파일 등록 서비스 요청");
		return dao.fileWrite();
	}
	*/

	
	/*
	// 개인회원 가입서비스 요청 ajax
	public HashMap<String, Object> clientJoin(HashMap<String, Object> params) {
		
		logger.info("회원가입 서비스 요청");
		HashMap<String, Object> result = new HashMap<String, Object>();
		int row = dao.clientJoin(params);
		boolean success = false;
		
		if(row > 0) {
			success = true;
		}
		
		result.put("success", success);
		
		return result;
	}
	*/
	
	
	/*
	public ModelAndView fileUpload(MultipartFile file, HttpSession session) {

		ModelAndView mav = new ModelAndView("clientPhotoUploadForm");
		
		//1. 파일명 추출하기
		String fileName = file.getOriginalFilename();
		
		//2. 신규 파일명 생성하기
		String ext = fileName.substring(fileName.lastIndexOf("."));
		String newFileName = System.currentTimeMillis()+ext;
		
		try {
			byte[] bytes = file.getBytes(); //3. 파일 받아오기
			//4. 파일 저장(Java nio 사용)
			Path filePath = Paths.get("C:/upload/"+newFileName);
			Files.write(filePath, bytes);
			
			//5. DB 에 저장(불가능 하다 - 아직 글을 쓰지도 않았으니까)
			//그래서 세션에 임시 저장한다.
			
			HashMap<String, String> map = (HashMap<String, String>) session.getAttribute("fileList");
			if(map == null) {
				map = new HashMap<String, String>();				
			}
			
			map.put(newFileName, fileName);
			logger.info("업로드 된 파일 수 : "+map.size());
			session.setAttribute("fileList", map);
			
			//6. 이미지 url 전달
			mav.addObject("path", "/photo/"+newFileName);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return mav;
		
	}
	*/

	/*
	//파일 삭제, 세션에서 지우기
	public HashMap<String, Object> fileDelete(String fileName, HttpSession session) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		boolean success = delFile(fileName);
		
		if(success) {
			HashMap<String, String> fileList = (HashMap<String, String>) session.getAttribute("fileList");
			fileList.remove(fileName);
			logger.info("삭제 후 남은 파일 수 : "+fileList.size());
			session.setAttribute("fileList", fileList);
			
		}
		
		result.put("success", success);
		
		return result;
	}
	*/
	
	/*
	//delFile 메서드 생성후 삭제 성공 유무를 통해 삭제 완료 여부 확인
	private boolean delFile(String fileName) {
		
		File file = new File("C:/upload/"+fileName);
		boolean success = false;
		if(file.exists()) {
			success = file.delete();
		}
		return success;
	}
	*/
	
	
	//기업회원 아이디 중복체크
	public HashMap<String, Object> overlayCompanyId(String com_id) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		String overcomId = dao.overlayCompanyId(com_id);
		logger.info("중복된 아이디 인가요? : "+overcomId);
		//중복된 아이디이면 중복 아이디:(중복아이디)가 보여짐 -> 사용불가 아이디
		//사용가능한 아이디이면 중복 아이디:(null)이 보여짐 ->사용가능 아이디
		boolean over = overcomId == null?false:true;
		map.put("overlayCompanyId", over);
		
		return map;
	}


	// 기업회원 이메일 중복확인
	public HashMap<String, Object> overlayComEmail(String com_email) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		String overlayComEmail=dao.overlayComEmail(com_email);
		logger.info("중복된 이메일 인가요? : "+overlayComEmail);
		//중복된 이메일이면 중복 이메일:(중복이메일)이 보여짐 ->사용불가 이메일
		//사용가능한 이메일이면 중복 이메일:(null)이 보여짐 ->사용가능 이메일
		boolean over = overlayComEmail ==null?false:true;
		map.put("overlayComEmail", over);
		
		return map;
	}
	
	
	// 기업회원 사업자 번호 중복확인
	public HashMap<String, Object> overlayNumber(String com_business_no) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		String overlayNumber=dao.overlayNumber(com_business_no);
		logger.info("중복된 사업자번호 인가요? : "+overlayNumber);
		//중복된 이메일이면 중복 이메일:(중복이메일)이 보여짐 ->사용불가 이메일
		//사용가능한 이메일이면 중복 이메일:(null)이 보여짐 ->사용가능 이메일
		boolean over = overlayNumber ==null?false:true;
		map.put("overlayNumber", over);
		
		return map;
	}


	// 기업회원 회원가입 서비스 ajax
	public boolean joinCompany(HashMap<String, String> params) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		int row = dao.joinCompany(params);
		boolean success = false;
		
		if(row>0) {
		success = true;
		}
		result.put("success", success);
		
		return success;
	}
		
		
	// 기업회원 가입 성공시 파일 저장 + 새로운 파일명
	public void fileSave2(MultipartFile file, String com_id) {
		String oriFileName = file.getOriginalFilename(); // 원본 파일명
		
		if(!oriFileName.equals("")) {
			String ext = oriFileName.substring(oriFileName.lastIndexOf(".")).toLowerCase();
			String newFileName = System.currentTimeMillis()+ext;
		
			try {
				byte[] arr = file.getBytes();
				Path path = Paths.get("C:/upload/"+newFileName);
				Files.write(path, arr);
				logger.info(newFileName+" save ok");
				//4. 업로드 후 photo 테이블에 데이터 입력	
				dao.fileWrite2(newFileName,com_id);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
		
		
	}
	


	// 개인회원 아이디 찾기 서비스 form 방식
	public String findClientId(String name, String email) {
		logger.info("개인회원 Id찾기 서비스 도착");
		//서비스에서는 단순히 매퍼와 연결해주는 것이므로 해줄 것이 없다. dao에 받아온 변수를 담아 보내주자
		return dao.findClientId(name,email);
	}


	// 기업회원 아이디 찾기 서비스 form 방식
	public String findCompanyId(String email, String number) {
		logger.info("기업회원 Id찾기 서비스 도착");
		//서비스에서는 단순히 매퍼와 연결해주는 것이므로 해줄 것이 없다. dao에 받아온 변수를 담아 보내주자
		return dao.findCompanyId(email,number);
	}

	
	// 개인회원 비밀번호 재설정과 관련된 정보입력 확인(새 비밀번호 설정 페이지로 넘어가기 위함)
	public int findClientPw(String id, String name, String email) {
		//서비스에서는 단순히 매퍼와 연결해주는 것이므로 해줄 것이 없다. dao에 받아온 변수를 담아 보내주자
		return dao.findClientPw(id,name,email);
	}


	// 개인회원 새 비밀번호 설정 서비스(뒤에 파라미터는 직접 바꿔주었음 String parameter -> String cl_pw)
	public void clientPwChange(String id, String cl_pw) {
		dao.clientPwChange(id,cl_pw);
	}


	// 기업회원 비밀번호 재설정과 관련된 정보입력 확인(새 비밀번호 설정 페이지로 넘어가기 위함)
	public int findCompanyPw(String id, String email, String number) {
		//서비스에서는 단순히 매퍼와 연결해주는 것이므로 해줄 것이 없다. dao에 받아온 변수를 담아 보내주자
		return dao.findCompanyPw(id,email,number);
	}

	
	// 기업회원 새 비밀번호 설정 서비스(뒤에 파라미터는 직접 바꿔주었음 String parameter -> String com_pw)
	public void companyPwChange(String id, String com_pw) {
		dao.companyPwChange(id,com_pw);	
	}


	// 개인회원 정보관리 페이지 이동 및 리스트 서비스 요청 + 개인회원 수정페이지 이동하여 리스트 보여주는 것도 가능하다
	public MemberDTO clientInfoManagement(String loginId) {
		logger.info("개인회원 개인정보관리 페이지 및 리스트 서비스 도착");
		return dao.clientInfoManagement(loginId);
	}


	// 개인회원 정보수정 요청전 현재 비밀번호 확인
	public HashMap<String, Object> passwordConfirm(String cl_pw) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		
		String pw = dao.passwordConfirm(cl_pw);
		logger.info("비밀번호 ? : "+pw);
		//중복된 이메일이면 중복 이메일:(중복이메일)이 보여짐 ->사용불가 이메일
		//사용가능한 이메일이면 중복 이메일:(null)이 보여짐 ->사용가능 이메일
		boolean over = pw ==null?false:true;
		map.put("pw", over);
		
		return map;
	}


	/*
	// 개인회원 정보수정 요청 서비스
	public void clientInfoUpdate(HashMap<String, String> params) {
		dao.clientInfoUpdate(params);
	}
	*/
	
	// 개인회원 정보수정 요청 서비스
	public String clientInfoUpdate(HashMap<String, String> params,MultipartFile file) {
		String id = params.get("cl_id");
		
		int row = dao.clientInfoUpdate(params);
		
		if(row>0) {
			fileSave(file,id);
		}
		
		return "redirect:/clientInfoManagement.do";
	}


	
	// 기업회원 정보관리 페이지 이동 및 리스트 서비스 요청 + 기업회원 수정페이지 이동하여 리스트 보여주는 것도 가능하다.
	public MemberDTO companyInfoManagement(String loginId) {
		logger.info("기업회원 기업회원정보관리 페이지 및 리스트 서비스 도착");
		return dao.companyInfoManagement(loginId);
	}


	// 기업회원 회원정보수정 요청전 현재 비밀번호 확인
	public HashMap<String, Object> passwordConfirm2(String com_pw) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		
		String pw = dao.passwordConfirm2(com_pw);
		logger.info("비밀번호 ? : "+pw);
		//중복된 이메일이면 중복 이메일:(중복이메일)이 보여짐 ->사용불가 이메일
		//사용가능한 이메일이면 중복 이메일:(null)이 보여짐 ->사용가능 이메일
		boolean over = pw ==null?false:true;
		map.put("pw", over);
		
		return map;
	}


	/*
	// 기업회원 회원정보수정 요청
	public void companyMemberInfoUpdate(HashMap<String, String> params) {
		dao.companyMemberInfoUpdate(params);
	}
	*/
	
	// 기업회원 정보수정 요청 서비스
	public String companyMemberInfoUpdate(HashMap<String, String> params,MultipartFile file) {
		String id = params.get("com_id");
		
		int row = dao.companyMemberInfoUpdate(params);
		
		if(row>0) {
			fileSave2(file,id);
		}
		
		return "redirect:/companyInfoManagement.do";
	}
	


	// 개인회원 탈퇴 요청
	public boolean clientDelete(String cl_id) {
		logger.info("개인회원 탈퇴 서비스 요청");
		return dao.clientDelete(cl_id);
	}

	// 개인회원 관리 인서트 요청
	public void clientManagement(HashMap<String, String> params) {
		dao.clientManagement(params);
	}
	
	
	// 기업회원 탈퇴 요청
	public boolean companyDelete(String com_id) {
		logger.info("기업회원 탈퇴 서비스 요청");
		return dao.companyDelete(com_id);
	}

	// 기업회원 관리 인서트 요청
	public void companyManagement(HashMap<String, String> params) {
		dao.companyManagement(params);	
	}


	// 관리자 상태관리 리스트 서비스 요청
	public ArrayList<MemberDTO> adminInfoManagement() {
		logger.info("관리자 계정관리 리스트 호출 서비스 도착");
		return dao.adminInfoManagement();
	}
	
	
	// 관리자 아이디 중복체크 서비스
	public HashMap<String, Object> overlayAdminId(String ad_id) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		String overAdminId = dao.overlayAdminId(ad_id);
		logger.info("중복된 아이디 인가요? : "+overAdminId);
		//중복된 아이디이면 중복 아이디:(중복아이디)가 보여짐 -> 사용불가 아이디
		//사용가능한 아이디이면 중복 아이디:(null)이 보여짐 ->사용가능 아이디
		boolean over = overAdminId == null?false:true;
		map.put("overlayAdminId", over);
		
		return map;
	}
	
	
	// 관리자 이메일 중복확인
	public HashMap<String, Object> overlayAdminEmail(String  ad_email) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		String overAdminEmail=dao.overlayAdminEmail(ad_email);
		logger.info("중복된 이메일 인가요? : "+overAdminEmail);
		//중복된 이메일이면 중복 이메일:(중복이메일)이 보여짐 ->사용불가 이메일
		//사용가능한 이메일이면 중복 이메일:(null)이 보여짐 ->사용가능 이메일
		boolean over = overAdminEmail ==null?false:true;
		map.put("overlayAdminEmail", over);
		
		return map;
	}
	
	
	// 관리자 계정등록(회원가입) 서비스 ajax
	public HashMap<String, Object> joinAdmin(HashMap<String, Object> params) {
		logger.info("관리자 계정등록 서비스이동");
		HashMap<String, Object> map = new HashMap<String, Object>();
		int row = dao.joinAdmin(params);
		boolean success = row>0?true:false;
		map.put("success", success);
		return map;	
	}
	


	// 관리자 상태 수정 페이지 및 항목보기 서비스 요청
	public MemberDTO adminStateChangePopup(String ad_id) {
		logger.info("관리자 상태 수정 페이지 서비스 도착");
		return dao.adminStateChangePopup(ad_id);
	}


	//관리자 상태 수정 요청 서비스
	public void adminStateUpdate(HashMap<String, String> params) {
		dao.adminStateUpdate(params);
	}


	// 개인회원 관리 페이지 이동 및 리스트 호출 서비스
	public ArrayList<MemberDTO> clientManagementList(Criteria cri) {
		logger.info("개인회원 관리 및 리스트 호출 서비스 도착");
		return dao.clientManagementList(cri);
	}


	// 개인회원 숫자 확인
	public int clientListTotal() {
		return dao.clientListTotal();
	}


	// 개인회원 검색하기
	public ArrayList<MemberDTO> clientListSearch(String searchOption, String search, int skip) {
		return dao.clientListSearch(searchOption,search,skip);
	}


	// 개인회원 검색개수 가져오기
	public int clientSearchTotal(String searchOption, String search) {
		return dao.clientSearchTotal(searchOption,search);
	}


	// 개인회원 상태 수정 페이지 및 항목보기 서비스 요청
	public MemberDTO clientStateChangePopup(String cl_id) {
		logger.info("개인회원 상태 수정 페이지 서비스 도착");
		return dao.clientStateChangePopup(cl_id);
	}


	// 개인회원 상태 및 사유 변경 서비스 요청
	public void clientStateUpdate(HashMap<String, String> params) {
		dao.clientStateUpdate(params);
		//dao.clientCommentUpdate(params);
	}


	// 기업회원 관리 페이지 이동 및 리스트 호출 서비스
	public ArrayList<MemberDTO> companyManagementList(Criteria cri) {
		logger.info("기업회원 관리 및 리스트 호출 서비스 도착");
		return dao.companyManagementList(cri);
	}


	// 기업회원 수 세기
	public int companyListTotal() {
		return dao.companyListTotal();
	}


	// 기업회원 검색하기
	public ArrayList<MemberDTO> companyListSearch(String searchOption, String search, int skip) {
		return dao.companyListSearch(searchOption,search,skip);
	}


	// 기업회원 검색 개수 가져오기
	public int companySearchTotal(String searchOption, String search) {
		return dao.companySearchTotal(searchOption,search);
	}


	// 기업회원 상태 수정 페이지 및 항목보기 서비스 요청
	public MemberDTO companyStateChangePopup(String com_id) {
		logger.info("기업회원 상태 수정 페이지 서비스 도착");
		return dao.companyStateChangePopup(com_id);
	}


	// 기업회원 상태 및 사유 변경 서비스 요청
	public void companyStateChange(HashMap<String, String> params) {
		dao.companyStateChange(params);
	}


	public boolean superLogin(String superPw) {
		boolean success = false;
		logger.info(superPw+"=?"+adminPw);
		if(superPw.equals(adminPw)) {
			success = true;
		}
		
		return success;
	}











	




	












	




}
