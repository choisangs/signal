package com.signal.jobposting.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.signal.all.dto.InterviewDTO;
import com.signal.all.dto.JobPostingDTO;
import com.signal.enter.controller.Criteria;
import com.signal.jobposting.dao.JobPostingDAO;

@Service
public class JobPostingService {
	
	@Autowired JobPostingDAO dao;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	/* 로그인 관련
	public String login(String com_id, String com_pw) {
		logger.info("로그인이 잘 들어왔나?");
		return dao.login(com_id,com_pw);
	}
	*/
	
	// 대표자명 받아오기
	public String ComDetail(String id) {
		logger.info("대표자명 요청");
		return dao.ComDetail(id);
		}
	
	//
	public JobPostingDTO infoCom(String id) {
		JobPostingDTO dto =null;
		logger.info("기업정보 등록 시 기업명 가져오기");
		logger.info("받아온 아이디: "+id);
		dto = dao.infoCom(id);
		return dto;
	}
	
	// 기업정보 상세보기
	@Transactional(isolation = Isolation.DEFAULT)
	public JobPostingDTO ComInfoDetail(Model model,String id, String ceo,String no) {
		logger.info("기업정보 상세보기 요청");
		JobPostingDTO dto =null;
		logger.info(id+" 기업의 대표자: "+ceo+" / "+no);
		dto = dao.ComInfoDetail(id,ceo,no);
		
		  ArrayList<JobPostingDTO>list=dao.photoList(id);
		  model.addAttribute("list",list);
	      logger.info("상세보기 아이디?" + id);
	      
		return dto;
	}
	
	/*
	 	public JobPostingDTO ComInfoDetail(String id, String ceo) {
		JobPostingDTO dto =null;
		logger.info(id+" 기업의 대표자: "+ceo);
		dto = dao.ComInfoDetail(id,ceo);
		return dto;
	}
	  
	 * */
	
	@Transactional(isolation = Isolation.DEFAULT)
	public int infoWrite(MultipartFile[] ci_photo, HashMap<String, String> params) {
		JobPostingDTO dto = new JobPostingDTO();
		logger.info("사진: "+ci_photo);
		
		for (MultipartFile photo : ci_photo) {
			String photo_original = photo.getOriginalFilename(); //3-1파일명 추출
            logger.info("photo name: " + photo.getOriginalFilename());
            if(!photo.getOriginalFilename().equals("")) {
               logger.info("업로드 진행");
               String ext = photo_original.substring(photo_original.lastIndexOf(".")).toLowerCase();
               String photo_copy = System.currentTimeMillis() + ext;
               logger.info(photo_original + photo_copy );   
               try {
                  byte[] arr =photo.getBytes();
                  Path path = Paths.get("C:\\upload/" + photo_copy);
                  Files.write(path, arr);
                  logger.info(photo_copy + " 저장 완료");
               } catch (IOException e) {
                     e.printStackTrace();
               }
               dto.setCi_photo(photo_copy);
               logger.info(photo_copy + " 저장 완료");   
            }  
         }
		dto.setCom_id(params.get("com_id"));
		dto.setCi_ceo(params.get("ci_ceo"));
		dto.setCi_web(params.get("ci_web"));
		dto.setCi_intro(params.get("ci_intro"));
		dto.setCi_pass_intro(params.get("ci_pass_intro"));
		dto.setCi_emp(params.get("ci_emp"));
		
		return dao.infoWrite(dto);

	}

	// 기업정보 번호 받아오기
	public String file(String no) {
		logger.info("기업정보 번호 요청");
		return dao.file(no);
	}

	public JobPostingDTO Detail(String id, String ceo, String no) {
		JobPostingDTO dto =null;
		logger.info(id+" 기업의 대표자: "+ceo+" / "+no);
		dto = dao.Detail(id,ceo,no);
		return dto;
	}

	public JobPostingDTO comUpdate(String id) {
		// ArrayList<JobPostingDTO> list = dao.file(id);
		JobPostingDTO dto =new JobPostingDTO();
		dto = dao.comUpdate(id);
		logger.info(id+" 기업정보 수정페이지 요청");
		logger.info("대표자명만 ?"+dto.getCi_ceo());
		
		return dto;
	}

	@Transactional(isolation = Isolation.DEFAULT)
	public int update(MultipartFile[] ci_photo, HashMap<String, String> params, String id) {
		
		params.put("com_id", id);
		
		JobPostingDTO dto = new JobPostingDTO();
		logger.info("사진: "+ci_photo);
		
		for (MultipartFile photo : ci_photo) {
			String photo_original = photo.getOriginalFilename(); //3-1파일명 추출
            logger.info("photo name: " + photo.getOriginalFilename());
            if(!photo.getOriginalFilename().equals("")) {
               logger.info("업로드 진행");
               String ext = photo_original.substring(photo_original.lastIndexOf(".")).toLowerCase();
               String photo_copy = System.currentTimeMillis() + ext;
               logger.info(photo_original + photo_copy );   
               try {
                  byte[] arr =photo.getBytes();
                  Path path = Paths.get("C:\\upload/" + photo_copy);
                  Files.write(path, arr);
                  logger.info(photo_copy + " 저장 완료");
               } catch (IOException e) {
                     e.printStackTrace();
               }
               dto.setCi_photo(photo_copy);
               logger.info(photo_copy + " 저장 완료");   
            }  
         }
		dto.setCom_name(params.get("com_name"));
		dto.setCom_id(params.get("com_id"));
		dto.setCi_ceo(params.get("ci_ceo"));
		dto.setCi_web(params.get("ci_web"));
		dto.setCi_intro(params.get("ci_intro"));
		dto.setCi_pass_intro(params.get("ci_pass_intro"));
		dto.setCi_emp(params.get("ci_emp"));
			
		logger.info("기업정보 수정 서비스 요청");
		logger.info("param : {}",params);

		return dao.update(dto);
		   }

	public JobPostingDTO infoList(String id,Model model) {
		JobPostingDTO dto =null;
		logger.info("채용공고 리스트에서 기업정보 보여주기");
		logger.info("받아온 아이디: "+id);
		dto = dao.infoList(id);
		  ArrayList<JobPostingDTO>list=dao.photoList(id);
		  model.addAttribute("list",list);
	      logger.info("상세보기 아이디?" + id);
		
		return dto;
	}
	
	public JobPostingDTO posting(String id) {
		JobPostingDTO dto =new JobPostingDTO();
		dto = dao.posting(id);
		logger.info("기업정보 등록 시 기업명 가져오기");
		logger.info("받아온 아이디: "+id);
		
		return dto;
	}
	
	
	@Transactional(isolation = Isolation.DEFAULT)
	public int postingWrite(MultipartFile[] jpo_photo, HashMap<String, String> params) {
		JobPostingDTO dto = new JobPostingDTO();
		logger.info("공고 사진: "+jpo_photo);
		
		for (MultipartFile photo : jpo_photo) {
			String photo_original = photo.getOriginalFilename(); //3-1파일명 추출
            logger.info("photo name: " + photo.getOriginalFilename());
            if(!photo.getOriginalFilename().equals("")) {
               logger.info("공고 사진 업로드 진행");
               String ext = photo_original.substring(photo_original.lastIndexOf(".")).toLowerCase();
               String photo_copy = System.currentTimeMillis() + ext;
               logger.info(photo_original + photo_copy );   
               try {
                  byte[] arr =photo.getBytes();
                  Path path = Paths.get("C:\\upload/" + photo_copy);
                  Files.write(path, arr);
                  logger.info(photo_copy + " 저장 완료");
               } catch (IOException e) {
                     e.printStackTrace();
               }
               dto.setJpo_photo(photo_copy);
               logger.info(photo_copy + " 저장 완료");   
            }  
         }
		dto.setJpo_no(params.get("jpo_no"));
		dto.setCom_id(params.get("com_id"));
		dto.setJpo_title(params.get("jpo_title"));
		dto.setJpo_type(params.get("jpo_type"));
		dto.setJpo_region(params.get("jpo_region"));
		dto.setJpo_field(params.get("jpo_field"));
		dto.setJpo_start(params.get("jpo_start"));
		dto.setJpo_deadline(params.get("jpo_deadline"));
		dto.setJpo_education(params.get("jpo_education"));
		dto.setJpo_salary(params.get("jpo_salary"));
		dto.setJpo_contact(params.get("jpo_contact"));
		dto.setJpo_name(params.get("jpo_name"));
		dto.setJpo_state(params.get("jpo_state"));
		dto.setJp_no(params.get("jp_no"));
		dto.setJc_no(params.get("jc_no"));
		dto.setJpo_welfare(params.get("jpo_welfare"));
		
		return dao.postingWrite(dto);
	}

	public void upHit(String jpo_no) {
		logger.info(jpo_no + " 번 채용공고 조회수 +1");
		dao.upHit(jpo_no);
	}

	public void jobPostingDetail(Model model, String jpo_no) {
	      logger.info("상세보기 서비스 요청");
	      
	      JobPostingDTO dto1 = dao.jobPostingDetail(jpo_no);

	      logger.info("상세보기 번호?" + jpo_no);
	      
	      model.addAttribute("dto1", dto1);
	   
	   }
	
	public JobPostingDTO UpdatePage(String id, String jpo_no) {
		JobPostingDTO dto =new JobPostingDTO();
		dto = dao.UpdatePage(id,jpo_no);
		logger.info(id+" 채용공고 "+jpo_no+ "번 수정페이지 요청");
		
		return dto;
	}

	@Transactional(isolation = Isolation.DEFAULT)
	public int postingUpdate(MultipartFile[] jpo_photo, String id, String jpo_no, HashMap<String, String> params) {

	params.put("com_id", id);
	params.put("jpo_no", jpo_no);
	
	JobPostingDTO dto = new JobPostingDTO();
	logger.info("사진: "+jpo_photo);
	
	for (MultipartFile photo : jpo_photo) {
		String photo_original = photo.getOriginalFilename(); //3-1파일명 추출
        logger.info("photo name: " + photo.getOriginalFilename());
        if(!photo.getOriginalFilename().equals("")) {
           logger.info("업로드 진행");
           String ext = photo_original.substring(photo_original.lastIndexOf(".")).toLowerCase();
           String photo_copy = System.currentTimeMillis() + ext;
           logger.info(photo_original + photo_copy );   
           try {
              byte[] arr =photo.getBytes();
              Path path = Paths.get("C:\\upload/" + photo_copy);
              Files.write(path, arr);
              logger.info(photo_copy + " 저장 완료");
           } catch (IOException e) {
                 e.printStackTrace();
           }
           dto.setJpo_photo(photo_copy);
           logger.info(photo_copy + " 저장 완료");   
        }  
     }
	dto.setJpo_no(params.get("jpo_no"));
	dto.setCom_id(params.get("com_id"));
	dto.setJpo_title(params.get("jpo_title"));
	dto.setJpo_type(params.get("jpo_type"));
	dto.setJpo_region(params.get("jpo_region"));
	dto.setJpo_field(params.get("jpo_field"));
	dto.setJpo_start(params.get("jpo_start"));
	dto.setJpo_deadline(params.get("jpo_deadline"));
	dto.setJpo_education(params.get("jpo_education"));
	dto.setJpo_salary(params.get("jpo_salary"));
	dto.setJpo_contact(params.get("jpo_contact"));
	dto.setJpo_name(params.get("jpo_name"));
	dto.setJpo_state(params.get("jpo_state"));
	dto.setJp_no(params.get("jp_no"));
	dto.setJc_no(params.get("jc_no"));
	dto.setJpo_welfare(params.get("jpo_welfare"));
		
	logger.info("기업정보 수정 서비스 요청");
	logger.info("param : {}",params);

	return dao.postingUpdate(dto);
	   }

	public void close() {
		logger.info("모집여부 마감 서비스 요청");
		int result = dao.close();
		logger.info("모집여부 수정 데이터 수 : " + result);
	}

	public ArrayList<JobPostingDTO> postingList(HashMap<String, Object> params) {
		return dao.postingList(params);
	}

	public int comPostingPasingTotal(HashMap<String, Object> params) {
		logger.info("게시글 수 가져오기 서비스 요청");
		return dao.comPostingPasingTotal(params);
	}

	public ArrayList<InterviewDTO> jobPostingSearch(String searchOption, int skip, String id) {
		return dao.jobPostingSearch(searchOption,skip,id);
	}

	public int jobPostingListTotal2(String searchOption, String id) {
		return dao.jobPostingListTotal2(searchOption,id);
	}

	public ArrayList<JobPostingDTO> mainPostingList(Criteria cri) {
		return dao.mainPostingList(cri);
	}

	public int mainPostingPasingTotal() {
		logger.info("채용공고 갯수 가져오기 서비스 요청");
		return dao.mainPostingPasingTotal();
	}

	public ArrayList<JobPostingDTO> jobPostingMainSearch(String jpo_region, String jp_no, String search, int skip) {
		return dao.jobPostingMainSearch(jpo_region, jp_no, search, skip);
	}

	public int jobPostingMainTotal(String jpo_region, String jp_no, String search) {
		return dao.jobPostingMainTotal(jpo_region, jp_no, search);
	}

	public void PostingDetailMainPage(Model model, String jpo_no, String com_id) {
	          
		JobPostingDTO dto = dao.PostingDetailMainPage(jpo_no,com_id);
	      logger.info("상세보기 번호?" + jpo_no +" / "+ com_id);
	      
	      model.addAttribute("dto", dto);
	   
	   }


	public ArrayList<JobPostingDTO> mainList(String start, String end) {
		logger.info("리스트 요청 서비스");
		return dao.mainList(start,end);
	}

	//메인 공고 리스트에서 직무 대분류 리스트 호출
	public ArrayList<JobPostingDTO> jobBigList() {
		logger.info("직무 대분류 리스트 호출 서비스");
		return dao.jobBigList();
	}

	
	public ArrayList<JobPostingDTO> jobMidList() {
		logger.info("직무 중분류 리스트 호출 서비스");
		return dao.jobMidList();
	}

	public ArrayList<JobPostingDTO> jobMidList2(String jp_no) {
		logger.info("직무 중분류 리스트 호출 서비스");
		return dao.jobMidList2(jp_no);
	}
	
	
	
	
	/*
	public ArrayList<InterviewDTO> jobPostingMainSearch(String searchOption, String searchOption1, String searchOption2, String search, int skip) {
		return dao.jobPostingMainSearch(searchOption,searchOption1,searchOption2,search,skip);
	}

	public int jobPostingMainTotal(String searchOption, String searchOption1, String searchOption2, String search) {
		return dao.jobPostingMainTotal(searchOption,searchOption1,searchOption2,search);
	}
	*/



	
	
	
	

	/* 
	public int jobPostingUpdate(MultipartFile[] jpo_photo, HashMap<String, String> params) {
	      
		logger.info("채용공고 수정하기 서비스 요청");  
		
	      int jpo_no = Integer.parseInt(params.get("jpo_no"));
	      logger.info(jpo_no+"번 채용공고 수정 요청 서비스 시작");
	      int row = dao.jobPostingUpdate(params);
	      logger.info("수정하는 번호" + jpo_no);
	      if(row>0) {
	    	  jopPhoto(jpo_no,jpo_photo);
	    	  
	      }
	      logger.info("수정된 데이터 수 : " + row);
	      return row;
	   }
	   */	
	
	/*
	public int update(MultipartFile[] ci_photo, HashMap<String, String> params, String id) {
		
		params.put("com_id", id);
		logger.info("기업정보 수정 서비스 요청");
		logger.info("param : {}",params);
		int row = dao.update(params);
		logger.info("row? "+row);
		
		if(row<0) {    
			infoWrite(ci_photo, params);        
      }
		return row;
		   }
		   */
	
	
	/*
	 * public void comUpdate(String no) { logger.info("기업정보 수정하기 페이지 요청");
	 * 
	 * JobPostingDTO dto = dao.comUpdate(no); ArrayList<JobPostingDTO>list
	 * =dao.Detail(no); logger.info("상세보기 번호?" + no);
	 * 
	 * model.addAttribute("comInfo", dto); model.addAttribute("list", list);
	 * 
	 * }
	 */
	
	
	

}




