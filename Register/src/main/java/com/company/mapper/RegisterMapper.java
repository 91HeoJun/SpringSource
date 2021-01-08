package com.company.mapper;

import com.company.domian.AuthVO;
import com.company.domian.LogInVO;
import com.company.domian.RegisterVO;

public interface RegisterMapper {
	
	// RegisterMapper.xml과 연결되어있음
	// 태그 [id]는 메소드명과 동일하게 매칭시킬 것.
	// 받아 올 값이 있을 경우(select) 는 [resultType]과 데이터 타입과 을 맞출것
	// <select id="selectById" resultType="com.company.domian.RegisterVO">

	public RegisterVO selectById(String userid);
	public int insert(RegisterVO regVO);
	public AuthVO selectByMember(LogInVO logVO);

}
