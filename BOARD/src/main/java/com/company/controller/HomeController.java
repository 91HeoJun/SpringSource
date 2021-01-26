package com.company.controller;

//import java.text.DateFormat;
//import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		log.info("---- Index로 이동 .... ----");

		return "index";
	}
	
	@GetMapping("/except")
	public void exceptTest(int no) {
		log.info("예외 테스트");
	}
	
}