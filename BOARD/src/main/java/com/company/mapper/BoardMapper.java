package com.company.mapper;

import java.util.List;

import com.company.domain.BoardVO;
import com.company.domain.Criteria;

public interface BoardMapper {
	
	public int insert(BoardVO board);
	public int delete(int bno);
	public int update(BoardVO board);
	
	public List<BoardVO> list(Criteria cri);
	public BoardVO read(int bno);
	
	public int totalCnt();
	

}
