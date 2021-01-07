package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.domain.NumVO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/add/*")	// http://localhost:8080/add/
public class AddController {

	@GetMapping("/sum")
	public void addForm() { // http://localhost:8080/add/sum
		log.info("Add Form 요청...");	// add/sum => view 리졸버
	}
	
	
//	@PostMapping("/sum")
//	public void addpost() { // http://localhost:8080/add/sum
//		log.info("Add Post 요청...");	// add/sum => view 리졸버
//	}
	
	
//	@PostMapping("/sum")
//	public void addpost(@RequestParam("num1") int num, int num2) { // 원하는 데이터타입으로 받아올 수 있는 장점이 있음!
//		log.info("Add Post 요청...");	// add/sum => view 리졸버
//		log.info("num1 : " + num);
//		log.info("num2 : " + num2);
//	}

//	@PostMapping("/sum")	// http://localhost:8080/add/sum
//	public String addpost(NumVO vo, Model model) {
//		log.info("Add Post 요청...");	
//		log.info("num1 : " + vo.getNum1());
//		log.info("num2 : " + vo.getNum2());
//
//		// [request.setAttribute]와 같은 역할
//		model.addAttribute("result", vo.getNum1()+vo.getNum2());
//		return "add/result"; // 보여주는 페이지(이대로 view 리졸버에 들어감)
//	}

	@PostMapping("/sum")	// http://localhost:8080/add/sum
	public String addpost(@ModelAttribute("Nvo") NumVO vo, Model model) {
		log.info("Add Post 요청...");	
		log.info("num1 : " + vo.getNum1());
		log.info("num2 : " + vo.getNum2());

		// [request.setAttribute]와 같은 역할
		model.addAttribute("result", vo.getNum1()+vo.getNum2());
		return "add/result"; // 보여주는 페이지(이대로 view 리졸버에 들어감)
	}
	
}
