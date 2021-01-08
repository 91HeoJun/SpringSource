package com.company.service;

import com.company.domian.AuthVO;
import com.company.domian.LogInVO;
import com.company.domian.RegisterVO;


public interface RegisterService {

	// 아이디 중복 
	public RegisterVO selectById(String userid);
	
	// 회원가입
	public boolean registerAdd(RegisterVO regVO);
	
	// 로그인 => 로그인 성공 시 유저아이디, 이름만 세션에 담기
	public AuthVO isLogin(LogInVO logVO);
}
