package com.company.member;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.MemberVO;
import com.company.service.MemberService;

public class MemberClient {

	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("Member_config.xml");
		MemberService service = (MemberService) ctx.getBean("service");

		// 전체조회
		List<MemberVO> list = service.getMemberList();		
		for (MemberVO vo : list) {
			System.out.println(vo);
		}
		
		// 삽입
//		MemberVO insertMember = new MemberVO();
//		insertMember.setUserid("TTTTest123456");
//		insertMember.setPassword("Test123456!");
//		insertMember.setName("닭다리");
//		insertMember.setGender("닭");
//		insertMember.setEmail("chic@naver.com");
//		
//		if (service.MemberInsert(insertMember)) {
//			System.out.println("삽입 성공");
//		} else {
//			System.out.println("삽입 실패");
//		}
		
		// 수정
//		MemberVO ModifyMember = new MemberVO();
//		ModifyMember.setUserid("zzxx123");
//		ModifyMember.setPassword("Test123456@");
//		
//		int Modify = service.MemberUpdate(ModifyMember);
//		
//		if (Modify > 0) {
//			System.out.println("수정 성공");
//		} else {
//			System.out.println("수정 실패");
//		}
		
		// 삭제
		
		
		// 개별조회
		
		
		
	}

}
