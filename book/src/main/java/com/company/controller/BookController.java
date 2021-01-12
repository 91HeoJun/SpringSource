package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.BookVO;
import com.company.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BookController {

	@Autowired
	private BookService service;

	@PostMapping("/insert")
	public String insert(BookVO book, RedirectAttributes rttr) {
		log.info("---- Insert 작업중 ... ----" + book);

		try {

			if (service.insertBook(book)) {
				return "redirect:/select";

			} else {
				return "redirect:/";
			}
		} catch (Exception e) {
			rttr.addFlashAttribute("tab", "insert");
			return "redirect:/";
		}
	}

	@GetMapping("/select")
	public String allBook(Model model) {
		log.info("---- 전체 리스트 가져오기 ----");

		List<BookVO> list = service.booklist();
		model.addAttribute("list", list);

		return "book_selectAll";
	}

	@PostMapping("/delete")
	public String delete(int code, RedirectAttributes rttr) {
		log.info("---- 삭제 진행중 ----");

		if (service.deleteBook(code)) {
			return "redirect:select";

		} else {
			rttr.addFlashAttribute("tab", "delete");
			return "redirect:/";
		}
	}

	@PostMapping("/modify")
	public String update(int code, int price, RedirectAttributes rttr) {
		log.info("---- 가격수정 진행중 .... ----");
		if (service.updateBook(code, price)) {
			return "redirect:select";

		} else {
			rttr.addFlashAttribute("tab", "modify");
			return "redirect:/";
		}
	}

	@PostMapping("/search")
	public String search(String criteria, String keyword, RedirectAttributes rttr, Model model) {
		// 성공하면 searchAll
		log.info("---- 도서정보 확인중 .... ----" + criteria + "," + keyword);

		List<BookVO> list = service.bookInfo(criteria, keyword);

		if (!list.isEmpty()) {
			model.addAttribute("list", list);
			return "book_searchAll";

		} else {
			rttr.addFlashAttribute("tab", "search");
			return "redirect:/";
		}
	}
	
	@GetMapping("/search")
	public String searchGet(RedirectAttributes rttr) {
		log.info("search Form 요청");
		rttr.addFlashAttribute("tab", "search");
		
		return "redirect:/";
		
	}
}