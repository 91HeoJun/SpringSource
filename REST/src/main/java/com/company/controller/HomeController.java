package com.company.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.domain.SampleVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	
	// @ResponseBody : jsp를 찾는 주소값이 아닌 return 값 그대로 토스
	@ResponseBody	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		log.info("Welcome home!");
		
		return "home";
	}
	
	@ResponseBody
	@GetMapping("/test")
	public SampleVO test() {
		log.info("test 요청");
		
		SampleVO sample = new SampleVO();
		sample.setMno("12345");
		sample.setFirstName("go");
		sample.setLastName("gildong");
		
		return sample;
	}
	
	@GetMapping("/book")
	public void book() {
		log.info("book.jsp 요청 ...");
	}
	
}
