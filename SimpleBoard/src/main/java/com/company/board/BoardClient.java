package com.company.board;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.BoardVO;
import com.company.service.BoardService;

public class BoardClient {

	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("BoardConfig.xml");

		// 서비스 호출
		BoardService service = (BoardService) ctx.getBean("service");
		BoardVO vo = new BoardVO();
		vo.setTitle("스프링 컨테이너");
		vo.setContent("이제 곧 점심시간이니라 음훼훼후헤");
		vo.setWriter("짐이다");

		// 삽입
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

		// 게시글 하나 가져오기
		BoardVO voinfo = service.getRow(3);
		System.out.println(voinfo.toString());

		// 게시글 하나 삭제하기
		BoardVO delRow = new BoardVO();
		delRow.setBno(11);
		int Dresult = service.DeleteBoard(delRow);

		if (Dresult > 0) {
			System.out.println("삭제 성공");	
			} else {
			System.out.println("삭제 실패");
		}

		// 게시글 수정하기
		BoardVO ModiRow = new BoardVO();

		ModiRow.setBno(2);
		ModiRow.setTitle("2021 새해");
		ModiRow.setContent("실감이 안남");

		int Modify = service.UpdateBoard(ModiRow);
		if (Modify > 0) {
			System.out.println("수정 성공");
		} else {
			System.out.println("수정 실패");
		}
	}
}