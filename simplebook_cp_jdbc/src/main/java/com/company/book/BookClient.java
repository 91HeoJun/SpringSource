package com.company.book;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.BookVO;
import com.company.service.BookService;

public class BookClient {

	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("book_config.xml");
		BookService service = (BookService) ctx.getBean("service");
		
		// 입력 - 확인 완료
//		BookVO voin = new BookVO();
//		voin.setCode(9952);
//		voin.setTitle("확인사실");
//		voin.setWriter("확인");
//		voin.setPrice(30000);
//		if (service.insertBook(voin)) {
//			System.out.println("입력 성공");
//		} else {
//			System.out.println("입력 실패");
//		}

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
//		if (service.deleteBook(2)) {
//			System.out.println("삭제 성공");
//		} else {
//			System.out.println("삭제 실패");
//		}
		
		// 개별조회 - 확인 완료
		BookVO bookinfo = service.getRow(2021);
		System.out.println(bookinfo.toString());
		
	}
}
