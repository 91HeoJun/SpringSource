package com.company.service;

import java.util.List;
import com.company.domain.MemberVO;

public interface MemberService {
	
	public boolean MemberInsert(MemberVO member);
	public boolean MemberUpdate(MemberVO member);
	public boolean MemberDelete(MemberVO member);
	
	public MemberVO getMember(MemberVO member);
	public List<MemberVO> getMemberList();

}
