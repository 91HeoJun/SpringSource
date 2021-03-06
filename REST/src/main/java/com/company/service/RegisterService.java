package com.company.service;

import com.company.domain.AuthVO;
import com.company.domain.LogInVO;
import com.company.domain.RegisterVO;
import com.company.domain.changeVO;

public interface RegisterService {

	// 아이디 중복 
	public RegisterVO selectById(String userid);
	
	// 회원가입
	public boolean registerAdd(RegisterVO regVO);
	
	// 로그인 => 로그인 성공 시 유저아이디, 이름만 세션에 담기
	public AuthVO isLogin(LogInVO logVO);
	
	// 회원삭제
	public boolean leave(LogInVO logVO);
	
	// 비밀번호 변경
	public boolean updatePwd(changeVO chanVO);
}
