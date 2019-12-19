package com.vktechnology.naagu.dao;

import com.vktechnology.naagu.models.Source;
import com.vktechnology.naagu.models.SourceRecord;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by HOME on 12/15/2015.
 */
public interface SourceDao {
	
	public ModelAndView showSource();
    public List<Source> ListOfSource(String user);
    public String saveSource(Source source);
    public List<String> sourceListOnly(String user);
    public List<String> sourceNamesList(String user);
    public String saveSourceRecord(Source source);
    public BigDecimal sourceBalance(String user, String SourceName);
    public List<SourceRecord> recordOfSource(String user);
    public String sourceListExcel(XSSFWorkbook workbook) throws FileNotFoundException;
    public String sourceRecordExcel(XSSFWorkbook workbook, String fromDate, String toDate) throws FileNotFoundException;
    public List<SourceRecord> recordSourTwoDates(String user, String fromDate, String toDate);
}
