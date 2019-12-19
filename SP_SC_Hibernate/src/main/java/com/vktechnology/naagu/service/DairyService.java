package com.vktechnology.naagu.service;

import java.util.List;

import com.vktechnology.naagu.models.Dairy;

public interface DairyService {

	public String showDairy();
	public String saveDairy(Dairy dairy);
	public List<Dairy> resultOfDairy(String user);
}
