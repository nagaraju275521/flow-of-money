package com.vktechnology.naagu.service;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.servlet.ModelAndView;

import com.vktechnology.naagu.dao.SourceDao;
import com.vktechnology.naagu.models.Source;
import com.vktechnology.naagu.models.SourceRecord;

public class SourceServiceImpl implements SourceService {
	
	@Autowired
	private SourceDao sourceDao;
	
	@Override
	//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@Secured({ "ROLE_USER" })
	public List<Source> ListOfSource(String user){
		return sourceDao.ListOfSource(user);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public ModelAndView showSource(){
		System.out.println("in show source service :"+sourceDao.showSource());
		return sourceDao.showSource();
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public String saveSource(Source source){
		System.out.println("in save source service");
		return sourceDao.saveSource(source);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public List<String> sourceNamesList(String user){
		System.out.println("in sourceNamesList source service");
		return sourceDao.sourceNamesList(user);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public List<String> sourceListOnly(String user){
		return sourceDao.sourceListOnly(user);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public BigDecimal sourceBalance(String user, String SourceName){
		return sourceDao.sourceBalance(user, SourceName);
	}

	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public String saveSourceRecord(Source source){
		return sourceDao.saveSourceRecord(source);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public List<SourceRecord> recordOfSource(String user){
		return sourceDao.recordOfSource(user);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public String sourceListExcel(XSSFWorkbook workbook) throws FileNotFoundException{
		return sourceDao.sourceListExcel(workbook);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public String sourceRecordExcel(XSSFWorkbook workbook, String fromDate, String toDate) throws FileNotFoundException{
		return sourceDao.sourceRecordExcel(workbook, fromDate, toDate);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public List<SourceRecord> recordSourTwoDates(String user, String fromDate, String toDate){
		return sourceDao.recordSourTwoDates(user, fromDate, toDate);
	}
}
