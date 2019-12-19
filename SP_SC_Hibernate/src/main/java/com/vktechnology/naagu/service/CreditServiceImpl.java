package com.vktechnology.naagu.service;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

import com.vktechnology.naagu.dao.CreditDao;
import com.vktechnology.naagu.models.Credit;
import com.vktechnology.naagu.models.CreditClearRecord;
import com.vktechnology.naagu.models.CreditRecord;

public class CreditServiceImpl implements CreditService {
	
	@Autowired
	private CreditDao creditDao;
	
	@Override
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String showCredit(){
		return creditDao.showCredit();
	}
	
	@Override
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public List<Credit> listOfCredit(String user){
		return creditDao.listOfCredit(user);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public String saveCredit(Credit credit){
		return creditDao.saveCredit(credit);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public List<String> crediterList(String user){
		return creditDao.crediterList(user);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public void addCreditMoney(String creditName, BigDecimal Amount, String user){
		creditDao.addCreditMoney(creditName, Amount, user);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public BigDecimal crediterAmount(String user, String Crediter){
		return creditDao.crediterAmount(user, Crediter);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public void deductCredit(String C_name, String C_User, BigDecimal C_Amount){
		creditDao.deductCredit(C_name, C_User, C_Amount);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public BigDecimal creditAvailBalance(String user, String Source){
		return creditDao.creditAvailBalance(user, Source);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public BigDecimal creditCurBalance(String user, String Source){
		return creditDao.creditCurBalance(user, Source);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public String saveCreditRecord(Credit credit){
		return creditDao.saveCreditRecord(credit);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public List<CreditRecord> recordOfCredit(String user){
		return creditDao.recordOfCredit(user);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public String deductCredit_AC(String C_name, String C_User, BigDecimal C_Amount){
		return creditDao.deductCredit_AC(C_name, C_User, C_Amount);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public String clearLentAmountS(String DebitName, String CreditName, BigDecimal Diff_amount, BigDecimal Remaining, String user){
		return creditDao.clearLentAmountS(DebitName, CreditName, Diff_amount, Remaining, user);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public String clearLentAmountDynamic(String CreditName, String source, BigDecimal amount, String user){
		return creditDao.clearLentAmountDynamic(CreditName, source, amount, user);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public String buildExcelDocument(XSSFWorkbook workbook, String fromDate, String toDate) throws FileNotFoundException{
		return creditDao.buildExcelDocument(workbook, fromDate, toDate);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public String sendMail(String ToMail, String FromMail, String Password, String Attachfile, String Subject, String Message) 
			throws MessagingException, IOException{
		return creditDao.sendMail(ToMail, FromMail, Password, Attachfile, Subject, Message);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public String creditRecordExcel(XSSFWorkbook workbook, String fromDate, String toDate) throws FileNotFoundException{
		return creditDao.creditRecordExcel(workbook, fromDate, toDate);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public String mailSuccessMessage(String Message, String ToMail, String FromMail, String Password, String Attachfile, String Subject, String MailMessage) throws MessagingException, IOException{
		return creditDao.mailSuccessMessage(Message, ToMail, FromMail, Password, Attachfile, Subject, MailMessage);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public String saveCreditClearRecord(CreditClearRecord creditClearRecord){
		return creditDao.saveCreditClearRecord(creditClearRecord);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public List<CreditClearRecord> recordOfClearRecord(String user){
		return creditDao.recordOfClearRecord(user);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public List<Credit> listOfCreditTwoDates(String user, String fromDate, String toDate){
		return creditDao.listOfCreditTwoDates(user, fromDate, toDate);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public String gettingFilePath(){
		return creditDao.gettingFilePath();
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public List<CreditRecord> CreditRecordTwoDates(String user, String fromDate, String toDate){
		return creditDao.CreditRecordTwoDates(user, fromDate, toDate);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public String creditClearReExcel(XSSFWorkbook workbook, String fromDate, String toDate) throws FileNotFoundException{
		return creditDao.creditClearReExcel(workbook, fromDate, toDate);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public List<CreditClearRecord> recordCreditClearTD(String user, String fromDate, String toDate){
		return creditDao.recordCreditClearTD(user, fromDate, toDate);
	}
}
