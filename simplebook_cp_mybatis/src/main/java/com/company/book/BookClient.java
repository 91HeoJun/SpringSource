package com.company.book;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.BookVO;
import com.company.service.BookService;

public class BookClient {

	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("book_config2.xml");
		BookService service = (BookService) ctx.getBean("service");
		
		// 입력 - 확인 완료
		BookVO insertBook = new BookVO(7745, "당나귀", "띠용", 22000);
		if (service.insertBook(insertBook)) {
			System.out.println("입력 성공");
		} else {
			System.out.println("입력 실패");
		}

		// 전체 리스트 - 확인 완료
		List<BookVO> list = service.getList();
		for (BookVO vo : list) {
			System.out.println(vo);
		}
		
		// 수정 - 확인 완료
//		BookVO ModiBook = new BookVO();
//		
//		ModiBook.setCode(9952);
//		ModiBook.setPrice(10);
//		
//		if (service.updateBook(ModiBook)) {
//			System.out.println("수정 성공");
//		} else {
//			System.out.println("수정 실패");
//		}
		
		// 삭제 - 확인 완료
//		int DelBook = 9952;
//		
//		if (service.deleteBook(DelBook)) {
//			System.out.println("삭제 성공");
//		} else {
//			System.out.println("삭제 실패");
//		}
		
		// 개별조회 - 확인 완료
//		BookVO seletBook = service.getRow(129);
//		System.out.println("단일조회 : " + seletBook.toString());
		
	}
}
