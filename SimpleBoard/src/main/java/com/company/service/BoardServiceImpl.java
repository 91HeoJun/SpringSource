package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.BoardVO;
import com.company.persistence.BoardDAO;

//@Service : 빈 생성
@Service("service")
public class BoardServiceImpl implements BoardService {
	
	@Autowired // @Autowired : 이미 생성된 빈을 주입
	private BoardDAO dao;
	
	

	@Override
	public int InsertBoard(BoardVO vo) {

		return dao.insert(vo);
	}

	@Override
	public int UpdateBoard(BoardVO vo) {

		return dao.UpdateBoard(vo);
	}

	@Override
	public int DeleteBoard(BoardVO vo) {

		return dao.DeleteBoard(vo);
	}

	@Override
	public BoardVO getRow(int bno) {

		return dao.getRow(bno);
	}

	@Override
	public List<BoardVO> getList() {

		return dao.getList();
	}

}
