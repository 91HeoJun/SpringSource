package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.Criteria;
import com.company.domain.ReplyVO;
import com.company.mapper.ReplyMapper;

@Service
public class ReplyServieceImpl implements ReplyService {
	
	@Autowired
	private ReplyMapper reMapper;
	
	@Override
	public boolean addReply(ReplyVO reVO) {

		return reMapper.insert(reVO)>0?true:false;
	}
	
	@Override
	public boolean updateReply(ReplyVO reVO) {
		
		return reMapper.update(reVO)>0?true:false;
	}
	
	
	
	
	

	@Override
	public ReplyVO get(int rno) {

		return reMapper.read(rno);
	}

	@Override
	public List<ReplyVO> getList(Criteria cri, int bno) {

		return reMapper.list(cri, bno);
	}


	
	

}
