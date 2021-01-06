package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.BoardVO;
import com.company.mapper.BoardMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service("service")//@Service : 빈 생성
public class BoardServiceImpl implements BoardService {
	
	@Autowired // @Autowired : 이미 생성된 빈을 주입
	private BoardMapper mapper;
	
	@Override
	public int InsertBoard(BoardVO vo) {
		log.info("Insert Service 실행");
		return mapper.insert(vo);
	}

	@Override
	public int UpdateBoard(BoardVO vo) {
		log.info("Update Service 실행");
		return mapper.update(vo);
	}

	@Override
	public int DeleteBoard(BoardVO vo) {
		log.info("Delete Service 실행");
		return mapper.delete(vo);
	}

	@Override
	public BoardVO getRow(int bno) {
		return mapper.select(bno);
	}

	@Override
	public List<BoardVO> getList() {
		return mapper.selectAll();
	}

}
