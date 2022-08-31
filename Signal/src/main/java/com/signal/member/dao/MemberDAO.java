package com.signal.member.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

import com.signal.all.dto.MemberDTO;
import com.signal.enter.controller.Criteria;

public interface MemberDAO {
	
	

	// 개인회원 아이디 중복체크
	String overlayClientId(String cl_id);

	// 개인회원 이메일 중복체크
	String overlayEmail(String cl_email);

	
	
	//회원가입시 파일업로드랑 params dto로 담아넣는 방법 ***
	//int clientJoin(MemberDTO dto);
	int clientJoin(HashMap<String, String> params);
	
	/* 로그인 int로 받는 경우
	//개인회원 로그인
	int clientMember(String id, String pw, String state);

	//기업회원 로그인
	int companyMember(String id, String pw, String state);
	*/
	
	//개인회원 로그인
	String clientLogin(String id, String pw);

	MemberDTO clientdto(String clientLogin);

	//기업회원 로그인
	String companyLogin(String id, String pw);

	MemberDTO companydto(String companyLogin);
	
	//관리자 로그인
	String adminLogin(String id, String pw);

	MemberDTO admindto(String adminLogin);
	

	//기업회원 id 중복체크
	String overlayCompanyId(String com_id);

	//기업회원 email 중복체크
	String overlayComEmail(String com_email);
	
	//기업회원 사업자 번호 중복체크
	String overlayNumber(String com_business_no);

	// 기업회원 가입 ajax
	int joinCompany(HashMap<String, String> params);
	
	// 기업회원 사업자등록증 사진 등록
	void fileWrite2(String newFileName, String id);

	// 개인회원 가입 ajax
	int joinClient(HashMap<String, String> params);
	
	// 개인회원 사진 등록
	void fileWrite(String newFileName, String id);


	// 개인회원 Id찾기
	String findClientId(String name, String email);

	// 기업회원 Id찾기
	String findCompanyId(String email, String number);

	// 개인회원 Pw찾기
	int findClientPw(String id, String name, String email);

	void clientPwChange(String id, String cl_pw);

	// 기업회원 Pw찾기
	int findCompanyPw(String id, String email, String number);

	void companyPwChange(String id, String com_pw);
	
	//개인회원 개인정보관리 페이지 이동 및 리스트 보여주기
	MemberDTO clientInfoManagement(String cl_id);

	//개인회원 비밀번호 DB에서 확인(암호화 때문)
	String passwordConfirm(String cl_pw);

	/*
	//개인회원 정보 수정하기
	void clientInfoUpdate(HashMap<String, String> params);
	*/
	
	//개인회원 정보 수정하기
	int clientInfoUpdate(HashMap<String, String> params);

	//기업회원 기업회원정보관리 페이지 이동 및 리스트 보여주기
	MemberDTO companyInfoManagement(String com_id);

	//기업회원 비밀번호 DB에서 확인(암호화 때문)
	String passwordConfirm2(String com_pw);

	/*
	//기업회원 회원정보 수정하기
	void companyMemberInfoUpdate(HashMap<String, String> params);
	*/
	
	//기업회원 회원정보 수정하기
	int companyMemberInfoUpdate(HashMap<String, String> params);

	HashMap<String, Object> passwordConfirm3(String memberPw);

	//개인회원 탈퇴가 되었는지 true,false 반환
	boolean clientDelete(String cl_id);

	//개인회원 탈퇴가 true이면 개인회원 관리 테이블에 파라미터 인서트 요청
	void clientManagement(HashMap<String, String> params);

	//기업회원 탈퇴 되었는지 true false 반환
	boolean companyDelete(String com_id);

	//기업회원 탈퇴가 true이면 기업회원 관리 테이블에 파라미터 인서트 요청
	void companyManagement(HashMap<String, String> params);

	
	//관리자 리스트 호출
	ArrayList<MemberDTO> adminInfoManagement();

	//관리자 아이디 중복체크
	String overlayAdminId(String ad_id);
	
	//관리자 이메일 중복체크
	String overlayAdminEmail(String ad_email);
	
	//관리자 계정등록(회원가입)
	int joinAdmin(HashMap<String, Object> params);
	
	//관리자 상태 수정 페이지 이동 및 항목 호출
	MemberDTO adminStateChangePopup(String ad_id);

	//관리자 상태 수정 요청
	void adminStateUpdate(HashMap<String, String> params);

	// 개인회원 관리 리스트 호출 요청
	ArrayList<MemberDTO> clientManagementList(Criteria cri);

	// 개인회원 리스트 총 인원수 확인
	int clientListTotal();

	// 개인회원 검색하기
	ArrayList<MemberDTO> clientListSearch(String searchOption, String search, int skip);

	// 개인회원 검색 결과 수 가져오기
	int clientSearchTotal(String searchOption, String search);

	// 개인회원 상태변경 팝업창 이동 및 리스트 호출
	MemberDTO clientStateChangePopup(String cl_id);

	// 개인회원 상태 변경
	void clientStateUpdate(HashMap<String, String> params);

	// 기업회원 관리 리스트 호출 요청
	ArrayList<MemberDTO> companyManagementList(Criteria cri);

	// 기업회원 리스트 총 갯수 확인
	int companyListTotal();

	// 기업회원 검색하기
	ArrayList<MemberDTO> companyListSearch(String searchOption, String search, int skip);

	// 기업회원 검색 결과 수 가져오기
	int companySearchTotal(String searchOption, String search);

	// 기업회원 상태 수정 팝업 이동 및 항목 보기
	MemberDTO companyStateChangePopup(String com_id);

	// 기업회원 상태 변경
	void companyStateChange(HashMap<String, String> params);





	
	
	// 개인회원 상태 변경 사유
	//void clientCommentUpdate(HashMap<String, String> params);



	//파일 업로드 메서드
	//void fileWrite(String oriFileName, String newFileName, String clId);



	// 개인회원 가입 dao ajax
	//int clientJoin(HashMap<String, Object> params);

}
