package com.vktechnology.naagu.dao;

import java.util.List;

import com.vktechnology.naagu.models.Dairy;

public interface DairyDao {
	
	public String showDairy();
	public String saveDairy(Dairy dairy);
	public List<Dairy> resultOfDairy(String user);
}
