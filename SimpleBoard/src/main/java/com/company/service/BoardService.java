package com.company.service;

import java.util.List;

import com.company.domain.BoardVO;

public interface BoardService {
	
	int InsertBoard(BoardVO vo);
	int UpdateBoard(BoardVO vo);
	int DeleteBoard(BoardVO vo);
	
	BoardVO getRow(int bno);
	List<BoardVO> getList();
}
