package com.vktechnology.naagu.service;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

import com.vktechnology.naagu.dao.ExpensesDao;
import com.vktechnology.naagu.models.Expenses;

public class ExpensesServiceImpl implements ExpensesService{
	
	@Autowired
	private ExpensesDao expensesDao;
	
	@Override
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String showExpenses(){
		return expensesDao.showExpenses();
	}
	
	@Override
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public List<Expenses> listOfExpenses1(String user){
		System.out.println("in service layer: "+expensesDao.listOfExpenses(user));
		return expensesDao.listOfExpenses(user);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public String saveExpenses(Expenses expenses){
		return expensesDao.saveExpenses(expenses);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public List<String> holderMoney_ListOnly(String user){
		return expensesDao.holderMoney_ListOnly(user);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public List<Expenses> listOFCurrentDay(String user){
		return expensesDao.listOFCurrentDay(user);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public List<Expenses> listOfDate(String user, String date){
		return expensesDao.listOfDate(user, date);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public List<Expenses> listOfTwoDate(String user, String fromdate, String todate){
		return expensesDao.listOfTwoDate(user, fromdate, todate);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public  String availablityCheck(String user, String sourcce, BigDecimal amount){
		return expensesDao.availablityCheck(user, sourcce, amount);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public String deleteExpensesRow(String id, String amount, String source, String user){
		return expensesDao.deleteExpensesRow(id, amount, source, user);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public String generateExpensesExcel(XSSFWorkbook workbook, String fromDate, String toDate) throws FileNotFoundException{
		return expensesDao.generateExpensesExcel(workbook, fromDate, toDate);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public List<Expenses> listExpensesTwoDates(String user, String fromDate, String toDate){
		return expensesDao.listExpensesTwoDates(user, fromDate, toDate);
	}
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public List<String> listByOnlineType(String onlineType){
		return expensesDao.listByOnlineType(onlineType);
	}

}
