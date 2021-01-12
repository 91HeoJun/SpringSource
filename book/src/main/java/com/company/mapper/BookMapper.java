package com.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.domain.BookVO;

public interface BookMapper {
	public int insert(BookVO book);
	public int delete(int code);
	public int update(@Param("code") int code, @Param("price") int price);
	
	public List<BookVO> allBook();
	public List<BookVO> bookInfo(@Param("criteria") String criteria, @Param("keyword") String keyword);
}