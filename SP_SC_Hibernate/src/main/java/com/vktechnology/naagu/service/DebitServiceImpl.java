package com.vktechnology.naagu.service;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

import com.vktechnology.naagu.dao.CreditDao;
import com.vktechnology.naagu.dao.DebitDao;
import com.vktechnology.naagu.models.Debit;
import com.vktechnology.naagu.models.DebitClearRecord;
import com.vktechnology.naagu.models.DebitRecord;

public class DebitServiceImpl implements DebitService{
	
	@Autowired
	private DebitDao debitDao;
	
	@Override
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String showDebit(){
		return debitDao.showDebit();
	}
	
	@Override
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String debitClearPage(){
		return debitDao.debitClearPage();
	}
	
	@Override
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public List<Debit> listOfDebit(String user){
		return debitDao.listOfDebit(user);
	}
	
	@Override
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public List<String> debiterList(String user){
		return debitDao.debiterList(user);
	}
	
	@Override
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String saveDebitData(Debit debit){
		return debitDao.saveDebit(debit);
	}
	
	@Override
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public List<Debit> existOrNotDebit(String user, String debitName){
		return debitDao.existOrNotDebit(user, debitName);
	}
	
	@Override
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public List<DebitRecord> recordOfDebit(String user){
		return debitDao.recordOfDebit(user);
	}
	
	@Override
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String saveDebitRecord(Debit debit){
		return debitDao.saveDebitRecord(debit);
	}
	
	@Override
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public BigDecimal debitBalance(String user, String Source){
		return debitDao.debitBalance(user, Source);
	}
	
	@Override
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String debitRecordExcel(XSSFWorkbook workbook, String fromDate, String toDate) throws FileNotFoundException{
		return debitDao.debitRecordExcel(workbook, fromDate, toDate);
	}
	
	@Override
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String debitListExcel(XSSFWorkbook workbook) throws FileNotFoundException{
		return debitDao.debitListExcel(workbook);
		
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public String securityForClintSide(){
		return debitDao.securityForClintSide();
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public BigDecimal debitRecordPurpose(String De_Source, String DE_User){
		return debitDao.debitRecordPurpose(De_Source, DE_User);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public String debitClearDynamic(String CDname, BigDecimal CDamount, String CDsource, String CDuser){
		return debitDao.debitClearDynamic(CDname, CDamount, CDsource, CDuser);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public List<DebitRecord> recordOfDebTwoDates(String user, String fromDate, String toDate){
		return debitDao.recordOfDebTwoDates(user, fromDate, toDate);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public String saveDebitClearRecord(DebitClearRecord DCRecord){
		return debitDao.saveDebitClearRecord(DCRecord);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public String showdebitClearRpage(){
		return debitDao.showdebitClearRpage();
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public List<DebitClearRecord> debitClearRecord(String user){
		return debitDao.debitClearRecord(user);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public String debitClearRecordExcel(XSSFWorkbook workbook, String fromDate, String toDate) throws FileNotFoundException{
		return debitDao.debitClearRecordExcel(workbook, fromDate, toDate);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public List<DebitClearRecord> reOfDebClearTwoDates(String user, String fromDate, String toDate){
		return debitDao.reOfDebClearTwoDates(user, fromDate, toDate);
	}
}
