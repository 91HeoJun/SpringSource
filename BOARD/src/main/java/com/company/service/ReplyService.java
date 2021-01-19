package com.company.service;

import com.company.domain.Criteria;
import com.company.domain.ReplyPageVO;
import com.company.domain.ReplyVO;

public interface ReplyService {
	
	public boolean addReply(ReplyVO reVO);
	public boolean updateReply(ReplyVO reVO);
	public boolean deleteReply(int rno);
	
	public ReplyVO get(int rno);
	public ReplyPageVO getList(Criteria cri, int bno);
	
	

}
