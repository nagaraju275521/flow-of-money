package com.vktechnology.naagu.service;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.servlet.ModelAndView;

import com.vktechnology.naagu.models.DebitRecord;
import com.vktechnology.naagu.models.Source;
import com.vktechnology.naagu.models.SourceRecord;

public interface SourceService {
	
	public ModelAndView showSource();
	public List<Source> ListOfSource(String user);
	public String saveSource(Source source);
	public List<String> sourceNamesList(String user);
	public List<String> sourceListOnly(String user);
	public BigDecimal sourceBalance(String user, String SourceName);
	public String saveSourceRecord(Source source);
	public List<SourceRecord> recordOfSource(String user);
	public String sourceListExcel(XSSFWorkbook workbook) throws FileNotFoundException;
	public String sourceRecordExcel(XSSFWorkbook workbook, String fromDate, String toDate) throws FileNotFoundException;
	public List<SourceRecord> recordSourTwoDates(String user, String fromDate, String toDate);
}
