package com.vktechnology.naagu.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.vktechnology.naagu.dao.SignupDao;
import com.vktechnology.naagu.models.Signup;

public class SignupServiceImpl implements SignupService{
	
	@Autowired
	private SignupDao signupDao;
	
	@Override
	public void saveUser(Signup signup){
		signupDao.saveUser(signup);
	}

}
