package com.company.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domian.AuthVO;
import com.company.domian.LogInVO;
import com.company.domian.RegisterVO;
import com.company.service.RegisterService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member/*")
public class RegisterController {

	@Autowired
	private RegisterService service;
	
	// Step1으로 이동
	@GetMapping("/step1")
	public void step1Get() {
		log.info("Step1으로 이동중 ...");
	}

	// Step2로 이동
	@PostMapping("/step2")
	public String step2Get(boolean agree, RedirectAttributes rttr) {
		log.info("Step2으로 이동중 ...");
		log.info("동의여부 : " + agree);

		if (agree != true) {
			rttr.addFlashAttribute("check", "false");
			return "redirect:/member/step1";

		}
		return "/member/step2";
	}

	@PostMapping("/step3")
	public String step3Post(@ModelAttribute("member") RegisterVO regVO) {
		// step2.jsp에서 넘긴 값 가져오기 & 로그출력
		log.info("Step3로 이동중 ...");
		log.info("userid : " + regVO.getUserid());
		log.info("password : " + regVO.getPassword());
		log.info("confirm_password : " + regVO.getConfirm_password());
		log.info("name : " + regVO.getName());
		log.info("gender : " + regVO.getGender());
		log.info("email : " + regVO.getEmail());
		
		// 두개의 비밀번호가 일치하지 않는다면 step2로 돌려보내기
		if (regVO.isPasswordEqualToConfirmPassword()) {
			// step3로 이동(두개의 비밀번호가 일치할 경우)
			log.info("일치하여 통과");
			service.registerAdd(regVO);
			return "/member/step3";

		} else {
			log.info("일치하지 않아 step2로 복귀");
			return "/member/step2";
		}
	}
	
	// /member/step2 & /member/step3
	// get으로 요청하는 핸들러
	@GetMapping(value= {"/step2", "step3"})
	public String handleStep2_3() {
		log.info("/step2 & /step3 직접 요청");
		
		return "redirect:step1";
	}
	
	// 중복 아이디 체크
	// ********** @ResponseBody : 보내는 리턴값을 실제 값으로 보냄 / 주소값과 상관없이 해당 어노테이션이 붙으면 Ajax 세상이야기 ************
	@ResponseBody	
	@PostMapping("/checkId")
	public String checkId(String userid) {
		log.info("중복아이디 확인 요청중.... 받은 아이디 : " + userid);
		RegisterVO dupId = service.selectById(userid);
		
		if (dupId != null) {
			log.info("사용못함");
			return "false";
		} 
		
		return "true";
	}
	
	// 로그인 - signin 보여주기
	@GetMapping("/signin")
	public void login() {
		log.info("로그인 요청중....");
	}
	
	//로그인 정보(아이디, 비밀번호)를 가져오는 컨트롤러 작성
	@PostMapping("/signin")
	public void loginInfo(LogInVO logVO) {
		log.info("로그인 정보 : " + logVO);
		AuthVO auth = service.isLogin(logVO);
		
		log.info("authVO에 들은 값 : " + auth);
//월욜	HttpSession session;
		
	}
	
	
	
	
	
}
