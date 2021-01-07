package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.domain.BoardVO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/board/*")
public class BoardController {

	@GetMapping("/insert")
	public void insertForm() {
		log.info("Insert.....");
	}	
	
	@GetMapping("/select")
	public void selectForm() {
		log.info("Select.....");
	}
	
	// 사용자가 입력한 게시물 가져오기
//	@PostMapping("/insert")
//	public String boardView(@ModelAttribute("words") BoardVO boardInfo) {
//		log.info(" ------------ boardView Execute.... ------------" );
//	
//	// 정보 확인하기(Log)
//		log.info("writer : " + boardInfo.getWriter());
//		log.info("title : " + boardInfo.getTitle());
//		log.info("content : " + boardInfo.getContent());
//		log.info("password : " + boardInfo.getPassword());
//		
//	// 페이지 이동 -> view.jsp
//	return "board/view";
//	}
	
	@PostMapping("/view")
	public void view(@ModelAttribute("words") BoardVO boardInfo) {
		log.info("view....");
	}
	
}
