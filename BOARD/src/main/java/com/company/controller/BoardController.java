package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.domain.BoardVO;
import com.company.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Autowired
	private BoardService service;
	
	// 게시글 작성폼으로 이동
	@GetMapping("/register")
	public void register() {
		log.info("---- 입력 페이지로 이동중 .... ----");
	}
	
	@PostMapping("/register")
	public String registerPost(BoardVO board) {
		log.info("---- 삽입작업 실행중 .... ----" + board);
		
		if (service.regist(board)) {
			return "redirect:list";
		} else {
			return "redirect:/";
		}
	}
	
	@GetMapping("/list")
	public void allBoard(Model model) {
		log.info("---- 전체 리스트 가져오기 ----");
		
		List<BoardVO> list = service.getList();
		model.addAttribute("list", list);
	}
	
	@GetMapping({"/read", "/modify"})
	public void get(int bno, Model model) {
		log.info("---- 단일 조회 실행중 .... ----");
		
		BoardVO getBoard = service.getRow(bno);
		model.addAttribute("getBoard", getBoard);
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board) {
		log.info("---- 수정 실행중 .... ----");
		
		return "redirect:list";
	}
	
	@PostMapping("/remove")
	public String removePost(int bno) {
		log.info("---- 게시물 삭제중 .... ----");
		service.remove(bno);
		
		return "redirect:list";
	}
	
	
}
