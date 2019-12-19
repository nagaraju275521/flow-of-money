package com.vktechnology.naagu.dao;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.vktechnology.naagu.models.Credit;
import com.vktechnology.naagu.models.Debit;
import com.vktechnology.naagu.models.DebitClearRecord;
import com.vktechnology.naagu.models.DebitRecord;

public interface DebitDao {

	public String showDebit();
	public List<Debit> listOfDebit(String user);
	public List<String> debiterList(String user);
	public String saveDebit(Debit debit);
	public List<Debit> existOrNotDebit(String user, String debitName);
	public List<DebitRecord> recordOfDebit(String user);
	public String saveDebitRecord(Debit debit);
	public BigDecimal debitBalance(String user, String Source);
	public String debitListExcel(XSSFWorkbook workbook) throws FileNotFoundException;
	public String debitRecordExcel(XSSFWorkbook workbook, String fromDate, String toDate) throws FileNotFoundException;
	public String securityForClintSide();
	public BigDecimal debitRecordPurpose(String De_Source, String DE_User);
	public String debitClearPage();
	public String debitClearDynamic(String CDname, BigDecimal CDamount, String CDsource, String CDuser);
	public List<DebitRecord> recordOfDebTwoDates(String user, String fromDate, String toDate);
	public String saveDebitClearRecord(DebitClearRecord DCRecord);
	public String showdebitClearRpage();
	public List<DebitClearRecord> debitClearRecord(String user);
	public String debitClearRecordExcel(XSSFWorkbook workbook, String fromDate, String toDate) throws FileNotFoundException;
	public List<DebitClearRecord> reOfDebClearTwoDates(String user, String fromDate, String toDate);
}
