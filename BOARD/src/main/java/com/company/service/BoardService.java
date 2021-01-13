package com.company.service;

import java.util.List;

import com.company.domain.BoardVO;
import com.company.domain.Criteria;

public interface BoardService {
	
	// 게시글 등록/삭제/수정
	public boolean regist(BoardVO board);
	public boolean remove(int bno);
	public boolean modify(BoardVO board);

	// 게시글 조회 - 전체/단일
	public List<BoardVO> getList(Criteria cri);
	public BoardVO getRow(int bno);
	
	// 전체 게시물 수
	public int getTotalCnt();
}