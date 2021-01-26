package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.domain.BoardVO;
import com.company.domain.Criteria;
import com.company.domain.FileAttach;
import com.company.mapper.AttachMapper;
import com.company.mapper.BoardMapper;

@Service("service")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper mapper;
	
	@Autowired
	private AttachMapper attachMapper;
	
	@Transactional
	@Override
	public boolean regist(BoardVO board) {

		// 작성글
		boolean result = mapper.insert(board)>0?true:false;
		
		// 첨부파일이 Null이거나 사이즈가 0일 경우, board.getAttachList().forEach(~~) 작업 미실시
		if (board.getAttachList() == null || board.getAttachList().size() <= 0) {
			return result;
		}
		
		board.getAttachList().forEach(attach -> {
			attach.setBno(board.getBno());
			attachMapper.insert(attach);
		});
		return result;
	}

	@Transactional
	@Override
	public boolean remove(int bno) {
		
		// 첨부물 삭제
		// (외래키로 테이블이 다중으로 걸려있을 경우, 하위로 걸려있는 레코드의 내용 삭제 후 상위 레코드 삭제할 것. 안그럼 500뜸)
		attachMapper.delete(bno);
		
		return mapper.delete(bno)>0?true:false;
	}

	@Override
	public boolean modify(BoardVO board) {
		// 첨부파일 전체 삭제
		attachMapper.delete(board.getBno());
		
		// 게심ㄹ 수정
		boolean result = mapper.update(board)>0?true:false;
		
		// 첨부파일 전체 삭제 후 - 첨부파일이 Null이거나 사이즈가 0일 경우, board.getAttachList().forEach(~~) 작업 미실시
		if (board.getAttachList() == null || board.getAttachList().size() <= 0) {
			return result;
		}
		
		// 첨부파일 (재)삽입
		board.getAttachList().forEach(attach -> {
			attach.setBno(board.getBno());
			attachMapper.insert(attach);
		});
		
		return result;
	}

	@Override
	public List<BoardVO> getList(Criteria cri) {
		return mapper.list(cri);
	}

	@Override
	public BoardVO getRow(int bno) {
		return mapper.read(bno);
	}

	@Override
	public int getTotalCnt(Criteria cri) {
		return mapper.totalCnt(cri);
	}

	@Override
	public List<FileAttach> getAttachList(int bno) {
		return mapper.attachList(bno);
	}
	
}
