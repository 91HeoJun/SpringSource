package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.BookVO;
import com.company.mapper.BookMapper;

@Service
public class BookServiceImple implements BookService {

	@Autowired
	public BookMapper mpr;
	
	@Override
	public boolean insertBook(BookVO book) {
		return mpr.insert(book)>0?true:false;
	}

	@Override
	public boolean deleteBook(int code) {
		return mpr.delete(code)>0?true:false;
	}

	@Override
	public boolean updateBook(int code, int price) {
		return mpr.update(code, price)>0?true:false;
	}

	@Override
	public List<BookVO> booklist() {
		return mpr.allBook();
	}

	@Override
	public List<BookVO> bookInfo(String criteria, String keyword) {
		return mpr.bookInfo(criteria, keyword);
	}
}