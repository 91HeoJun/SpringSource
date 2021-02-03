package com.company.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.BoardVO;
import com.company.domain.Criteria;
import com.company.domain.FileAttach;
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
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/register")
	public void register() {
		log.info("---- 입력 페이지로 이동중 .... ----");
	}
	
	// 게시글 작성
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/register")
	public String registerPost(BoardVO board, RedirectAttributes rttr) {
		log.info("---- 삽입 작업 실행중 .... ----" + board);

		// 파일 첨부 확인
//		if (board.getAttachList() != null) {
//			board.getAttachList().forEach(attach -> log.info(""+attach));
//		}
		
		if (service.regist(board)) {
			log.info("게시글 번호 : " + board.getBno());
			// 등록성공 메시지를 Modal에 띄우기 위해 삽입한 글번호 보내기
			rttr.addFlashAttribute("result", board.getBno());
			return "redirect:list";
			
		}
		
		log.info("---- !!!! 삽입 실패 !!!! ---- ");
		return "register";
	}
	
	// 리스트 가져오기
	@GetMapping("/list")
	public void allBoard(Model model, Criteria cri) {
		log.info("---- 전체 리스트 가져오기 ----");
		List<BoardVO> list = service.getList(cri);
		
		// 전체 게시물 수 요청
		int total = service.getTotalCnt(cri);
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
	@PreAuthorize("#board.writer == principal.username") // 작성자와 로그인한 정보가 같은지 재확인
	public String modify(BoardVO board, Criteria cri, RedirectAttributes rttr) {
		log.info("---- 수정 실행중 .... ----" + board);
		log.info("board : " + board);
		log.info("Criteria : " + cri);
		
		// 파일 첨부 확인
		if (board.getAttachList() != null) {
			board.getAttachList().forEach(attach -> log.info(""+attach));
		}

		service.modify(board);
		rttr.addFlashAttribute("result", "success");
		rttr.addAttribute("pageNum", cri.getPageNum());	// 여기서 가능한 이유는 같은 주소에서 ModelAttribute로 유지시킬 경우 사용 가능
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
				
		return "redirect:list";
	}
	

	@PostMapping("/remove")
	@PreAuthorize("#writer == principal.username") // 작성자와 로그인한 정보가 같은지 재확인(String writer는 modify.jsp에서 가져옴)
	public String removePost(int bno, Criteria cri, RedirectAttributes rttr, String writer) {
		log.info("---- 게시물 삭제중 .... ----");

		// 게시물 번호에 해당하는 첨부파일 삭제(서버 폴더 내 파일 삭제  & DB에서 내용 삭제 )
		
		// 서버 폴더 안 파일 삭제하기
		// 1. bno에 해당하는 첨부물 목록 알아내기
		List<FileAttach> attachList = service.getAttachList(bno);

		//성공하면 리스트 보여주기
		if (service.remove(bno)) {		// 2. DB 내 파일 삭제
			// 3. 파일 삭제
			deleteFiles(attachList);
			rttr.addFlashAttribute("result", "success");
		}
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:list";
	}
	
	private void deleteFiles(List<FileAttach> attachList) {
		log.info("---- 첨부물 삭제중 ... ---- : " + attachList);

		if (attachList== null || attachList.size()<= 0) {
			return;
		}
		for (FileAttach attach : attachList) {
			Path path = Paths.get("c:\\upload\\", attach.getUploadPath() + "\\" + attach.getUuid() + "_" + attach.getFileName());
			
			// 일반파일, 이미지 원본 파일 삭제
			try {
				Files.deleteIfExists(path);
				if (Files.probeContentType(path).startsWith("image")) {
					Path thumb = Paths.get("c:\\upload\\", attach.getUploadPath() + "\\s_" +
							attach.getUuid() + "_" + attach.getFileName());
				
				Files.delete(thumb);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//첨부물 가져오기
	@GetMapping(value = "/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<FileAttach>> getAttachList (int bno){
		log.info("---- 첨부물 가져오는중 ----" + bno);
		
		return new ResponseEntity<List<FileAttach>>(service.getAttachList(bno), HttpStatus.OK);
	}
	
	
}
