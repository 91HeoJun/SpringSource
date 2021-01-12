package com.company.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.company.domian.AuthVO;

public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// [http://localhost:8080/member.changePwd]를 바로 요청 했을 때, 세션값이 없으면 로그인 페이지로 보내기
		HttpSession session = request.getSession();
		
		if (session != null) {
			AuthVO auth = (AuthVO) session.getAttribute("auth");
			if (auth != null) {
				return true;	// 세션 값이 있다면 사용자가 요청한 컨트롤러로 진입
			}
		
		} 
		
		// 세션 값이 없으면 로그인 폼 페이지로 이동
		response.sendRedirect("/member/signin");
		return false;
	}
}
