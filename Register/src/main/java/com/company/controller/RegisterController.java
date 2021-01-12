package com.company.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domian.AuthVO;
import com.company.domian.LogInVO;
import com.company.domian.RegisterVO;
import com.company.domian.changeVO;
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
	public String loginInfo(LogInVO logVO, HttpSession session) {
		log.info("로그인 정보 : " + logVO);
		AuthVO auth = service.isLogin(logVO);
		
		log.info("authVO에 들은 값 : " + auth);
		if (auth != null) {
			session.setAttribute("auth", auth);
			return "redirect:/";
			
		} else {	// userid, password가 틀려서 로그인을 못한 경우
			return "redirect:/signin";
		}
	}
	
	// 로그아웃 - session 해제 후 index로 이동
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		log.info("로그아웃 진행중....");
		session.invalidate(); // 세션에 있는 정보 모두 날리기
//		session.removeAttribute("auth"); // 특정 세션 날리기
		
		return "redirect:/";
	}
	
	// 회원탈퇴 폼 보여주기
	@GetMapping("/leave")
	public void leaveGet() {
		log.info("회원탈퇴 폼으로 이동중....");
	}
	
	
	// 회원탈퇴 - 회원삭제 + 세션해제 후 index로 이동 
	@PostMapping("/leave")
	public String leavePost(LogInVO logVO, HttpSession session) {
		log.info("회원삭제 진행중 ....");
		
		if (service.leave(logVO)) {
			session.invalidate();
			
			return "redirect:/";

		} else {	// 비밀번호가 틀린 경우
			return "redirect:leave";
		}
	}
	
	// 회원정보 수정 폼 보여주기
	@GetMapping("/changePwd")
	public void changeInfo() {
		log.info("회원정보 수정 폼 이동중 ....");
	}
	
	// 회원정보 수정하기
	@PostMapping("/changePwd")
	public String chageNewPassword(changeVO chanVO, @SessionAttribute AuthVO auth, HttpSession session, RedirectAttributes rttr) {
		log.info("비밀번호 수정 실행 .... 이동중인 값 : " + chanVO);
		
		// userid : 세션에서 가져와서 change에 담기
//		AuthVO auth = (AuthVO) session.getAttribute("auth");	->	해당코드 대신으로 chageNewPassword메소드 상단에서 받을떄 @SessionAttribute AuthVO auth 으로 받을 수 있음.
		chanVO.setUserid(auth.getUserid());
		
		// 성공 => 세션해제 후 로그인 페이지로 이동
		if (service.updatePwd(chanVO)) {
			log.info("비밀번호 수정 성공. 로그인 페이지로 이동");
			session.invalidate();;

			return "redirect:signin";
		
		// 실패 => 비밀번호 변경 폼 보여주기로 이동
		} else {
			rttr.addFlashAttribute("error", "비밀번호를 확인해주세요");
			return "redirect:/member/changePwd";
		}
		

		
	}
	
}
