package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.BoardVO;
import com.company.domain.Criteria;
import com.company.domain.PageVO;
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
	public String registerPost(BoardVO board, RedirectAttributes rttr) {
		log.info("---- 삽입 작업 실행중 .... ----" + board);
		
		if (service.regist(board)) {
			log.info("게시글 번호 : " + board.getBno());
			// 등록성공 메시지를 Modal에 띄우기 위해 삽입한 글번호 보내기
			rttr.addFlashAttribute("result", board.getBno());
			return "redirect:list";
			
		} else {
			log.info("---- !!!! 삽입 실패 !!!! ---- ");
			return "redirect:/";
		}
	}
	
	@GetMapping("/list")
	public void allBoard(Model model, Criteria cri) {
		log.info("---- 전체 리스트 가져오기 ----");
		List<BoardVO> list = service.getList(cri);
		
		// 전체 게시물 수 요청
		int total = service.getTotalCnt();
		model.addAttribute("list", list);
		model.addAttribute("pageVO", new PageVO(cri, total));
	}
	
	@GetMapping({"/read", "/modify"})
	public void get(int bno, @ModelAttribute("cri")Criteria cri, Model model) {
		log.info("---- 단일 조회 실행중 .... ----" + bno);
		log.info("Criteria : " + cri);
		BoardVO getBoard = service.getRow(bno);
		
		model.addAttribute("getBoard", getBoard);
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board, Criteria cri, RedirectAttributes rttr) {
		log.info("---- 수정 실행중 .... ----" + board);
		service.modify(board);
		
		rttr.addFlashAttribute("result", "success");
		rttr.addAttribute("pageNum", cri.getPageNum());	// 여기서 가능한 이유는 같은 주소에서 ModelAttribute로 유지시킬 경우 사용 가능
		rttr.addAttribute("amount", cri.getAmount());
		
		return "redirect:list";
	}
	
	@PostMapping("/remove")
	public String removePost(int bno, Criteria cri, RedirectAttributes rttr) {
		log.info("---- 게시물 삭제중 .... ----");
		service.remove(bno);
		
		rttr.addFlashAttribute("result", "success");
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		
		return "redirect:list";
	}
	
	
}
