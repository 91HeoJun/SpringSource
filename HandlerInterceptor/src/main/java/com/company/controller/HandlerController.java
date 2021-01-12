package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HandlerController {
	
	@GetMapping("/doA")
	public void doA() {
		log.info("doA 요청 ....");
	}

	@GetMapping("/doB")
	public void doB() throws Exception {
		log.info("doB 요청 ....");
		
		throw new Exception("--- 강제 발생 ---");
	}
	
}
