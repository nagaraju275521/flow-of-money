package com.vktechnology.naagu.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.vktechnology.naagu.models.Credit;
import com.vktechnology.naagu.models.CreditClearRecord;
import com.vktechnology.naagu.models.CreditRecord;

public interface CreditDao {
	
	public String showCredit();
	public List<Credit> listOfCredit(String user);
	public String saveCredit(Credit credit);
	public List<String> crediterList(String user);
	public void addCreditMoney(String creditName, BigDecimal Amount, String user);
	public BigDecimal crediterAmount(String user, String Crediter);
	public void deductCredit(String C_name, String C_User, BigDecimal C_Amount);
	public String deductCredit_AC(String C_name, String C_User, BigDecimal C_Amount);
	public String saveCreditRecord(Credit credit); 
	public BigDecimal creditAvailBalance(String user, String Source);
	public BigDecimal creditCurBalance(String user, String Source);
	public List<CreditRecord> recordOfCredit(String user);
	public String clearLentAmountS(String DebitName, String CreditName, BigDecimal Diff_amount, BigDecimal Remaining, String user);
	public String clearLentAmountDynamic(String CreditName, String source, BigDecimal amount, String user);
	public String buildExcelDocument(XSSFWorkbook workbook, String fromDate, String toDate) throws FileNotFoundException;
	public String sendMail(String ToMail, String FromMail, String Password, String Attachfile, String Subject, String Message) 
			throws MessagingException, IOException;
	public String creditRecordExcel(XSSFWorkbook workbook, String fromDate, String toDate) throws FileNotFoundException;
	public String mailSuccessMessage(String Message, String ToMail, String FromMail, String Password, String Attachfile, String Subject, String MailMessage) 
			throws MessagingException, IOException;
	public String saveCreditClearRecord(CreditClearRecord creditClearRecord);
	public List<CreditClearRecord> recordOfClearRecord(String user);
	public List<Credit> listOfCreditTwoDates(String user, String fromDate, String toDate);
	public String gettingFilePath();
	public List<CreditRecord> CreditRecordTwoDates(String user, String fromDate, String toDate);
	public String creditClearReExcel(XSSFWorkbook workbook, String fromDate, String toDate) throws FileNotFoundException;
	public List<CreditClearRecord> recordCreditClearTD(String user, String fromDate, String toDate);
}
