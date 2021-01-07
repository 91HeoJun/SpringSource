package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller //@Component, @Service, @Repository
@RequestMapping("/sample/*") // http://localhost:8080/ 이후로 붙여내는 경로
public class SampleController {
	
	// @RequestMapping = Model2의 cmd.equals(list.do)와 같은 작업
	
	@RequestMapping("/basic") // http://localhost:8080/sample/basic
	public void basic() {
		log.info("basic....");
	}
	
	@RequestMapping("/test") // http://localhost:8080/sample/test
	public String test() {	
		log.info("Test....");
		return "default";	// default => view 리졸버
	}
	

}
