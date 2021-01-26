package com.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.domain.Criteria;
import com.company.domain.ReplyPageVO;
import com.company.domain.ReplyVO;
import com.company.mapper.BoardMapper;
import com.company.mapper.ReplyMapper;

@Service
public class ReplyServieceImpl implements ReplyService {
	
	@Autowired
	private ReplyMapper reMapper;
	
	@Autowired
	private BoardMapper boMapper;
	
	@Transactional
	@Override
	public boolean addReply(ReplyVO reVO) {
		// 게시글의 댓글 수 수정
		boMapper.updateReplyCnt(reVO.getBno(), 1);
		return reMapper.insert(reVO)>0?true:false;
	}
	
	@Override
	public boolean updateReply(ReplyVO reVO) {
		
		return reMapper.update(reVO)>0?true:false;
	}
	
	@Transactional
	@Override
	public boolean deleteReply(int rno) {
		// 게시글의 댓글 수 수정
		ReplyVO reVO = reMapper.read(rno);
		boMapper.updateReplyCnt(reVO.getBno(), -1);
		return reMapper.delete(rno)>0?true:false;
	}
	
	@Override
	public ReplyVO get(int rno) {

		return reMapper.read(rno);
	}

	@Override
	public ReplyPageVO getList(Criteria cri, int bno) {

		return new ReplyPageVO(reMapper.countBno(bno), reMapper.list(cri, bno));
	}
}
