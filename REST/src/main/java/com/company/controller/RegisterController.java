package com.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.domain.LogInVO;
import com.company.domain.RegisterVO;
import com.company.domain.changeVO;
import com.company.service.RegisterService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RegisterController {

	@Autowired
	private RegisterService service;
	
	@PostMapping("/regist")
	public ResponseEntity<String> regist(@RequestBody RegisterVO regVO) {
		log.info("회원가입 요청중 ...." + regVO);
		
		return service.registerAdd(regVO)?new ResponseEntity<String>("Add Success", HttpStatus.OK):
			new ResponseEntity<String>("Add Fail", HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/pwdchange")
	public ResponseEntity<String> changePWD(@RequestBody changeVO chanVO) {
		log.info("비밀번호 변경중 ..." + chanVO);

		return service.updatePwd(chanVO)?new ResponseEntity<String>("PWD Changeing is Success", HttpStatus.OK):
			new ResponseEntity<String>("PWD Changeing is Fail", HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/remove")
	public ResponseEntity<String> removePost(@RequestBody LogInVO logVO) {
		log.info("삭제중 ..." + logVO);
		return service.leave(logVO)?new ResponseEntity<String>("success", HttpStatus.OK):
			new ResponseEntity<String>("Delete Fail", HttpStatus.BAD_REQUEST);
	}
}
