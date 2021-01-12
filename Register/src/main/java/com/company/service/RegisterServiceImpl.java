package com.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domian.AuthVO;
import com.company.domian.LogInVO;
import com.company.domian.RegisterVO;
import com.company.domian.changeVO;
import com.company.mapper.RegisterMapper;

@Service
public class RegisterServiceImpl implements RegisterService {
	
	@Autowired
	private RegisterMapper mapper;

	@Override
	public RegisterVO selectById(String userid) {
		return mapper.selectById(userid);
	}

	@Override
	public boolean registerAdd(RegisterVO regVO) {
		return mapper.insert(regVO)>0?true:false;
	}

	@Override
	public boolean leave(LogInVO logVO) {
		return mapper.leaveMember(logVO)>0?true:false;
	}

	@Override
	public AuthVO isLogin(LogInVO logVO) {
		return mapper.selectByMember(logVO);
	}

	@Override
	public boolean updatePwd(changeVO chanVO) {
		return mapper.newPwd(chanVO)>0?true:false;
	}
	
	

}
