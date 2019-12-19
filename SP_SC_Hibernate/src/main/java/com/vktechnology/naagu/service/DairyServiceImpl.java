package com.vktechnology.naagu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import com.vktechnology.naagu.dao.DairyDao;
import com.vktechnology.naagu.models.Dairy;

public class DairyServiceImpl implements DairyService{
	
	@Autowired
	private DairyDao dairyDao;
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public String showDairy(){
		return dairyDao.showDairy();
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public String saveDairy(Dairy dairy){
		return dairyDao.saveDairy(dairy);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public List<Dairy> resultOfDairy(String user){
		return dairyDao.resultOfDairy(user);
	}
}
