package com.company.member;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.MemberVO;
import com.company.service.MemberService;

public class MemberClient {

	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("MemberConfig.xml");
		MemberService service = (MemberService) ctx.getBean("service");

		// 전체조회 - 확인 완료
		List<MemberVO> list = service.getMemberList();		
		for (MemberVO vo : list) {
			System.out.println(vo);
		}
		
		// 삽입 - 확인 완료
		MemberVO insertMember = new MemberVO();
		insertMember.setUserid("DDDDiner");
		insertMember.setPassword("Test123456!");
		insertMember.setName("고기");
		insertMember.setGender("육");
		insertMember.setEmail("GOGI@naver.com");
		
		if (service.MemberInsert(insertMember)) {
			System.out.println("삽입 성공");
		} else {
			System.out.println("삽입 실패");
		}
		
		// 수정 - 확인 완료
//		MemberVO ModifyMember = new MemberVO();
//		ModifyMember.setUserid("RRRRunch");
//		ModifyMember.setPassword("kimchi@");
//		
//		if (service.MemberUpdate(ModifyMember)) {
//			System.out.println("수정 성공");
//		} else {
//			System.out.println("수정 실패");
//		}
		
		// 삭제 - 확인 완료
//		MemberVO deleteMember = new MemberVO();
//		deleteMember.setUserid("gjwns1116");
//		deleteMember.setPassword("ddssaa321!");
//		
//		if (service.MemberDelete(deleteMember)) {
//			System.out.println("삭제 성공");
//		} else {
//			System.out.println("삭제 실패");
//		}

		// 개별조회 - 확인 완료
//		MemberVO selectMember = new MemberVO();
//		selectMember.setUserid("zzxx123");
//		selectMember.setPassword("aassddad123!");
//		System.out.println("단일조회 : " + service.getMember(selectMember));
	}

}
