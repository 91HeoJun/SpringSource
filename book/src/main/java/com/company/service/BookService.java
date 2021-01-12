package com.company.service;

import java.util.List;

import com.company.domain.BookVO;

public interface BookService {

	//입력, 삭제, 수정, 전체조회, 특정조회
	public boolean insertBook(BookVO book);
	public boolean deleteBook(int code);
	public boolean updateBook(int code, int price);
	
	public List<BookVO> booklist();
	public List<BookVO> bookInfo(String criteria, String keyword);
}
