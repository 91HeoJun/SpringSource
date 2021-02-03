package com.company.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.security.core.Authentication;
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
		log.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@GetMapping("/accessError")
	public String accessError(Authentication auth) {
		log.info("접근제한" + auth);
	
		/*  
		 * Authentication 객체 안에 담아오는 내용들
		 *org.springframework.security.authentication.UsernamePasswordAuthenticationToken@ff740c75:
		 * Principal: org.springframework.security.core.userdetails.User@bfc28a9a: Username: member; Password: [PROTECTED];
		 *  Enabled: true; AccountNonExpired: true; credentialsNonExpired: true; AccountNonLocked: true; Granted Authorities: ROLE_MEMBER; 
		 *  Credentials: [PROTECTED]; Authenticated: true; Details: org.springframework.security.web.authentication.WebAuthenticationDetails@fffbcba8: 
		 *  RemoteIpAddress: 0:0:0:0:0:0:0:1; SessionId: 1FE5AB00B2950C784223F2E20701C030; Granted Authorities: ROLE_MEMBER 
		 * 
		 */
		
		
		return "/security/accessError";
	}
	
	
}
