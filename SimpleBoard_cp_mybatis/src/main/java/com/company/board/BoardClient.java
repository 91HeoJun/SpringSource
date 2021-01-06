package com.company.board;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.BoardVO;
import com.company.service.BoardService;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class BoardClient {

	public static void main(String[] args) {
		
		log.info("BoardClient 실행");

		// 서비스 호출
		ApplicationContext ctx = new ClassPathXmlApplicationContext("BoardConfig2.xml");
		BoardService service = (BoardService) ctx.getBean("service");

		// 삽입
		BoardVO vo = new BoardVO();
		vo.setTitle("점심머먹지");
		vo.setContent("국물있는거");
		vo.setWriter("나");
		int result = service.InsertBoard(vo);
		if (result > 0) {
			System.out.println("삽입 성공");
		} else {
			System.out.println("삽입 실패");
		}

		// 전체 리스트
		List<BoardVO> list = service.getList();
		for (BoardVO vo1 : list) {
			System.out.println(vo1.toString());
		}
//
//		// 게시글 하나 가져오기
//		BoardVO voinfo = service.getRow(16);
//		System.out.println(voinfo.toString());

//		// 게시글 하나 삭제하기
//		BoardVO delRow = new BoardVO();
//		delRow.setBno(3);
//		int Dresult = service.DeleteBoard(delRow);
//
//		if (Dresult > 0) {
//			System.out.println("삭제 성공");	
//			} else {
//			System.out.println("삭제 실패");
//		}
//
		// 게시글 수정하기
//		BoardVO ModiRow = new BoardVO();
//
//		ModiRow.setBno(16);
//		ModiRow.setTitle("설렁탕먹음");
//		ModiRow.setContent("후추많이");
//
//		int Modify = service.UpdateBoard(ModiRow);
//		if (Modify > 0) {
//			System.out.println("수정 성공");
//		} else {
//			System.out.println("수정 실패");
//		}
	}
}