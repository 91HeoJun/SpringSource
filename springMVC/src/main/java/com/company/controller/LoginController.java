package com.company.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.domain.LoginVO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class LoginController {

	// get => login.jsp | post => 사용자가 입력한 데이터를 가져와서 DB작업
	//@RequestMapping(value = "/login", method=RequestMethod.GET) // http://localhost:8080/login
	@GetMapping("/login")
	public void loginGet() {
		log.info("Login GET...."); // login =>view 리졸버
	}

	
	// ===== 1. 기존 방법대로 사용자 입력값 가져오기 - 잘 사용되지 않음 =====
//	@RequestMapping(value = "/login", method=RequestMethod.POST) // http://localhost:8080/login
//	@PostMapping("/login")
//	public void loginPost(HttpServletRequest request) {
//		log.info("Login POST...."); // login =>view 리졸버
//		log.info("userid : " + request.getParameter("userid"));
//		log.info("password :" + request.getParameter("password"));
//	}
	
	// ===== 2. 파라미터 처리(단, name 맞추기) - 단점 : 받을 데이터가 많을경우 문제가 됨 =====
	@PostMapping("/login")
	public String loginPost(@RequestParam("userid") String userid, String password, Model model) {
		log.info("Login POST...."); // login =>view 리졸버
		log.info("userid : " + userid);
		log.info("password :" + password);
		
		model.addAttribute("login", new LoginVO(userid, password));

		return "logout";
	}
	
//	@PostMapping("/login")
//	public String loginPost(LoginVO vo) {
//		log.info("Login POST....");
//		log.info("userid : " + vo.getUserid());
//		log.info("password :" + vo.getPassword());
//		
//		return "logout"; // logout => view 리졸버
//	}
	
}
