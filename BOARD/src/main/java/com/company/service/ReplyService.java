package com.company.service;

import java.util.List;

import com.company.domain.Criteria;
import com.company.domain.ReplyVO;

public interface ReplyService {
	
	public boolean addReply(ReplyVO reVO);
	public boolean updateReply(ReplyVO reVO);
	
	public ReplyVO get(int rno);
	public List<ReplyVO> getList(Criteria cri, int bno);
	
	

}
