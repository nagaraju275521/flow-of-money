package com.vktechnology.naagu.dao;


import com.google.gson.Gson;
import com.vktechnology.naagu.models.Credit;
import com.vktechnology.naagu.models.Expenses;
import com.vktechnology.naagu.models.Holder;
import com.vktechnology.naagu.models.Source;
import com.vktechnology.naagu.service.CreditService;
import com.vktechnology.naagu.service.DebitService;
import com.vktechnology.naagu.service.ExpensesService;
import com.vktechnology.naagu.service.HolderService;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.*;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

/**
 * Created by HOME on 11/23/2015.
 */
public class ExpensesDaoImpl implements ExpensesDao{
   
    
    @Autowired
    private HolderService holderService;
    
    @Autowired
    private CreditService creditService;
    
    @Autowired
    private DebitService debitService;
    
    @Autowired
    private ExpensesService expensesService;
    
    @Autowired
    private SessionFactory sessionFactory;

    public ExpensesDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public String showExpenses(){
    	String showpath = "redirect:/htmlpages/Expenses.html";
    	return showpath;
    }

    @SuppressWarnings("unchecked")
	@Override
    @Transactional
    public List<Expenses> listOfExpenses(String user){

       Criteria cr = sessionFactory.getCurrentSession().createCriteria(Expenses.class);
                cr.add(Restrictions.eq("user", user)).addOrder( Order.desc("date"));
                List<Expenses> listExpenses = cr.list();
        return listExpenses;

    }

    @SuppressWarnings("unchecked")
	@Override
    @Transactional
    public List<Expenses> listExpensesTwoDates(String user, String fromDate, String toDate){
    	Criteria cr = sessionFactory.getCurrentSession().createCriteria(Expenses.class);
                cr.add(Restrictions.eq("user", user)).addOrder( Order.desc("date"));
                cr.add(Restrictions.between("date", fromDate, toDate));
                List<Expenses> listExpenses = cr.list();
        return listExpenses;
    }
    
    @Override
    @Transactional
    public String saveExpenses(Expenses expenses){
    	String Message = null;
    	BigDecimal Zero1 = new BigDecimal("0");
        System.out.println(expenses.getSource_Name()+"--we r in save expenses--: "+expenses.getAmount());
        String ouruser = expenses.getUser();
        String sourcename = expenses.getSource_Name();
        System.out.println("source name :"+sourcename);
        if(availablityCheck(expenses.getUser(), expenses.getSource_Name(), expenses.getAmount()).equalsIgnoreCase("Error") ){
        	Message = "ErrorAmountLess";
        }else{
        
        	//if(expenses.getUnit_price() == 0){
        	if(expenses.getUnit_price().compareTo(Zero1) == 0){
        		//expenses.setUnit_price(0);
        		expenses.setUnit_price(new BigDecimal("0"));
        	}
        //deduct from source
        if(sourcename.indexOf("Credit") > -1){
        	System.out.println("--we are IN index of : add credit");
        	creditService.addCreditMoney(expenses.getSource_Name(), expenses.getAmount(), ouruser);
        	//float SouC_bal = debitService.debitRecordPurpose(expenses.getSource_Name(), getPrincipal());
        	BigDecimal SouC_bal = debitService.debitRecordPurpose(expenses.getSource_Name(), getPrincipal());
        	//float SouC_balUpdate = SouC_bal + expenses.getAmount();
        	BigDecimal SouC_balUpdate = SouC_bal.add(expenses.getAmount());
        	expenses.setSource_bal(SouC_balUpdate);
        	sessionFactory.getCurrentSession().save(expenses);
        	Message = "SuccessCredit";
        }else{
        	
        	System.out.println("--we are not indexOf : deduct from money holder");
        	//holderDa.deductHolderMoney(expenses.getSource_Name(), expenses.getAmount(), ouruser);
        	holderService.deductHolderMoney(expenses.getSource_Name(), expenses.getAmount(), ouruser);
        	// save
        	//float Sou_Bal = debitService.debitRecordPurpose(expenses.getSource_Name(), getPrincipal());
        	BigDecimal Sou_Bal = debitService.debitRecordPurpose(expenses.getSource_Name(), getPrincipal());
        	//float Sou_BalUpdate = Sou_Bal - expenses.getAmount();
        	BigDecimal Sou_BalUpdate = Sou_Bal.subtract(expenses.getAmount());
        	expenses.setSource_bal(Sou_BalUpdate);
        	sessionFactory.getCurrentSession().save(expenses);
        	Message = "SuccessHolder";
        }
    	
        }
        return Message;
    }

    @Override
    @Transactional
    public String  deleteExpensesRow(String id, String amount, String source, String user){
    	System.out.println("--delete expense in dao--");
        String Message = "";
        	System.out.println("--delete expense in dao  --");
            Expenses expens = new Expenses();
            expens.setId(Long.parseLong(id));
            sessionFactory.getCurrentSession().delete(expens);
            
            if(source.indexOf("_Credit") > -1){
            	System.out.println("--delete SuccessDeductFrCredit  --");
            	//creditService.deductCredit_AC(source, user, Float.parseFloat(amount));
            	creditService.deductCredit_AC(source, user, new BigDecimal(amount));
            Message = "SuccessDeductFrCredit";
            }else{
            	System.out.println("--delete SuccessAddToHolder  --");
            	//holderService.AmountAddToHolder(source, Float.parseFloat(amount), user);
            	holderService.AmountAddToHolder(source, new BigDecimal(amount), user);
            	Message = "SuccessAddToHolder";
            }
            System.out.println("delete--:");
        return Message;
     }
    

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<String> holderMoney_ListOnly(String user){

        List<String> listOne  = sessionFactory.getCurrentSession().createQuery("select holder_Name FROM Holder where holder_User ='"+user+"' ").list();
        
        List<String> listThree = sessionFactory.getCurrentSession().createQuery("select credit_Name FROM Credit where user ='"+user+"' ").list();

        List<String> listTwo = sessionFactory.getCurrentSession().createQuery("select sourceName FROM Source where user ='" + user + "' and sourceType = '" + "Credit_Person" + "' ").list();


        Gson gson = new Gson();
        String js = gson.toJson(listTwo);
        System.out.println("----xxxx-----:"+js);


        List<String> list123 = new ArrayList<String>();
        list123.addAll(listOne);
        list123.addAll(listTwo);
        list123.addAll(listThree);
        System.out.println("-=-=-=-:"+list123);

        return list123;

    }

    @SuppressWarnings("unchecked")
	@Override
	@Transactional
    public List<Expenses> listOFCurrentDay(String user){
    	
    	Date date1 = new Date();
    	 SimpleDateFormat ft = 
    		      new SimpleDateFormat ("MM/dd/yyyy");
    	 System.out.println(ft.format(date1)+"---curent day---");
    	 String dateCD = ft.format(date1).toString();

       Criteria cr = sessionFactory.getCurrentSession().createCriteria(Expenses.class);
               Criterion user1 = Restrictions.eq("user", user);
               Criterion date2 = Restrictions.eq("date", ft.format(date1));
                
                LogicalExpression andExp = Restrictions.and(user1, date2);
                cr.add( andExp );
                
                List<Expenses> currentDay = cr.list();
         return currentDay;
    }
    
    @SuppressWarnings("unchecked")
	@Override
    @Transactional
    public List<Expenses> listOfDate(String user, String date){    	
    	System.out.println(user+"ddddddddddd"+date);
    	Criteria cr = sessionFactory.getCurrentSession().createCriteria(Expenses.class);
               	Criterion user1 = Restrictions.eq("user", user);
                Criterion date2 = Restrictions.eq("date", date);                
                LogicalExpression andExp = Restrictions.and(user1, date2);
                cr.add(andExp);                
                List<Expenses> currentDay = cr.list();
        System.out.println(currentDay+"ddddddddddd");
        return currentDay;
    }

    @SuppressWarnings("unchecked")
	@Override
    @Transactional
    public List<Expenses> listOfTwoDate(String user, String fromdate, String todate){

       Criteria cr = sessionFactory.getCurrentSession().createCriteria(Expenses.class);
               	Criterion user1 = Restrictions.eq("user", user);
                Criterion date3 = Restrictions.between("date", fromdate, todate);
                
                LogicalExpression andExp = Restrictions.and(user1, date3);
                cr.add(andExp);
                
                List<Expenses> betweenDates = cr.list();

        System.out.println(betweenDates+"ddddddddddd");
         return betweenDates;

    }
    
    @SuppressWarnings("unchecked")
	@Override
    @Transactional
    public String availablityCheck(String user, String source, BigDecimal amount){
    	String message = "";
    	BigDecimal Zero = new BigDecimal("0");
    	if(source.indexOf("Credit") > -1){
    		Criteria cr = sessionFactory.getCurrentSession().createCriteria(Credit.class);
    	       Criterion query1 = Restrictions.eq("user", user);
               Criterion query2 = Restrictions.eq("credit_Name", source);
               		LogicalExpression andExp = Restrictions.and(query1, query2);
               		cr.add(andExp);
               		List<Credit> betweenDates = cr.list();
               			for(Credit ss: betweenDates){
               				//if(0 < ss.getCredit_Amount() ){
               				if(ss.getCredit_Amount().compareTo(Zero) == 1 ){
               					message =  "SuccessCredit";
               				}
               				else {
               					message = "Error";
               				}
               			}
    		}else{
    		
    		String SourceName = "";
            List<String> Source12 = sessionFactory.getCurrentSession().createQuery("select holder_Name FROM Holder where holder_User "
            		+ "='" + user + "' and holder_Name = '" + source + "' ").list();
            for(String aa: Source12){
            	SourceName = aa;
            }
          
            if(SourceName.isEmpty()){
            	System.out.println("check from source");	
            	Criteria cr = sessionFactory.getCurrentSession().createCriteria(Source.class);
	       			Criterion query5 = Restrictions.eq("user", user);
	       			Criterion query6 = Restrictions.eq("sourceName", source);
	       				LogicalExpression andExp3 = Restrictions.and(query5, query6);
	       				cr.add(andExp3);
	       				List<Source> checkSource = cr.list();
	       			for(Source sss: checkSource){
	       				//if(amount > sss.getSourceAmount()){
	       				if(amount.compareTo(sss.getSourceAmount()) == 1){
	       					message =  "Error";
	       				}
	       				else {
	       					message = "Success";
	       				}
	       			}
            }else{
            	System.out.println("check from holder");
            	Criteria cr = sessionFactory.getCurrentSession().createCriteria(Holder.class);
 	       			Criterion query3 = Restrictions.eq("holder_User", user);
 	       			Criterion query4 = Restrictions.eq("holder_Name", source);
 	       				LogicalExpression andExp2 = Restrictions.and(query3, query4);
 	       				cr.add(andExp2);
 	       				List<Holder> checkHolder = cr.list();
 	       					for(Holder sss: checkHolder){
 	       						//if(amount > sss.getHolder_Amount()){
 	       						if(amount.compareTo(sss.getHolder_Amount()) == 1){	
 	       							message =  "Error";
 	       						}
 	       						else {
 	       							message = "Success";
 	       						}
 	       					}
            } 
    	}
    	return message;
    }
    
@SuppressWarnings("unchecked")
@Override
@Transactional
public List<String> listByOnlineType(String onlineType){
	
	String userName = getPrincipal();
	if(onlineType.equalsIgnoreCase("Cash")){
		String holdertype = "Pocket";
		List<String> load12  = sessionFactory.getCurrentSession().createQuery("select holder_Name FROM Holder where holder_Type = '"+holdertype+"' "
    			+ "and holder_User ='"+userName+"' ").list();
        List<String> list_bank1 = new ArrayList<String>();
        list_bank1.addAll(load12);
        System.out.println("--pocket list" + list_bank1);
        return list_bank1;
		
	}else if(onlineType.equalsIgnoreCase("Debit_Card") || onlineType.equalsIgnoreCase("Netbanking") || onlineType.equalsIgnoreCase("Check")){
		String holdertype = "bank";
		List<String> load2  = sessionFactory.getCurrentSession().createQuery("select holder_Name FROM Holder where holder_Type = '"+holdertype+"' "
    			+ "and holder_User ='"+userName+"' ").list();
        List<String> list_bank = new ArrayList<String>();
        list_bank.addAll(load2);
        System.out.println("--pocket list" + list_bank);
        return list_bank;
		
	}else if(onlineType.equalsIgnoreCase("Credit_Card")){
		List<String> load3  = sessionFactory.getCurrentSession().createQuery("select credit_Name FROM Credit where user ='"+userName+"' ").list();
        List<String> list_bank3 = new ArrayList<String>();
        list_bank3.addAll(load3);
        System.out.println("--pocket list" + list_bank3);
        return list_bank3;
	}else if(onlineType.equalsIgnoreCase("Wallet")){
		
		return null;
	}else{
		return null;
	}
    	
    	
}
    
    
    
    @Override
    @Transactional
    public String generateExpensesExcel(XSSFWorkbook workbook, String fromDate, String toDate) throws FileNotFoundException{
    	System.out.println("-------------++download DaoImpl Expenses++------------");
        String SuccessMes = "none";
        List<Expenses> list = expensesService.listExpensesTwoDates(getPrincipal(), fromDate, toDate);

        XSSFSheet sheet = workbook.createSheet("sheet 1");
        sheet.setDefaultColumnWidth(30);
       
        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
         
        // create header row
        XSSFRow header = sheet.createRow(0);
         
        header.createCell(0).setCellValue("Id");
        header.getCell(0).setCellStyle(style);
         
        header.createCell(1).setCellValue("Date");
        header.getCell(1).setCellStyle(style);
         
        header.createCell(2).setCellValue("Unit_Type");
        header.getCell(2).setCellStyle(style);
         
        header.createCell(3).setCellValue("Unit_Name");
        header.getCell(3).setCellStyle(style);
         
        header.createCell(4).setCellValue("Unit_Tag");
        header.getCell(4).setCellStyle(style);
        
        header.createCell(5).setCellValue("Unit_price");
        header.getCell(5).setCellStyle(style);
        
        header.createCell(6).setCellValue("Quantity");
        header.getCell(6).setCellStyle(style);
        
        header.createCell(7).setCellValue("Amount");
        header.getCell(7).setCellStyle(style);
        
        header.createCell(8).setCellValue("Source_Name");
        header.getCell(8).setCellStyle(style);
        
        header.createCell(9).setCellValue("Source_Name");
        header.getCell(9).setCellStyle(style);
        
        header.createCell(10).setCellValue("Description");
        header.getCell(10).setCellStyle(style);
        
        header.createCell(11).setCellValue("Sold_company");
        header.getCell(11).setCellStyle(style);
        
        header.createCell(12).setCellValue("Area");
        header.getCell(12).setCellStyle(style);
        
        header.createCell(13).setCellValue("Whome_to");
        header.getCell(13).setCellStyle(style);
        // create data rows
        int rowCount = 1;
        DecimalFormat dfff = new DecimalFormat("#.00"); 
        for (Expenses aBook : list) {
            XSSFRow aRow = sheet.createRow(rowCount++);
            System.out.println(aBook.getId());
            aRow.createCell(0).setCellValue(aBook.getId());
            aRow.createCell(1).setCellValue(aBook.getDate());
            aRow.createCell(2).setCellValue(aBook.getUnit_Type());
            aRow.createCell(3).setCellValue(aBook.getUnit_Name());
            aRow.createCell(4).setCellValue(aBook.getUnit_Tag());
            //aRow.createCell(5).setCellValue(aBook.getUnit_price());
            aRow.createCell(5).setCellValue(dfff.format(aBook.getUnit_price()));
            aRow.createCell(6).setCellValue(aBook.getQuantity());
            aRow.createCell(7).setCellValue(dfff.format(aBook.getAmount()));
            aRow.createCell(8).setCellValue(aBook.getSource_Name());
            aRow.createCell(9).setCellValue(dfff.format(aBook.getSource_bal()));
            aRow.createCell(10).setCellValue(aBook.getDescription());
            aRow.createCell(11).setCellValue(aBook.getSold_company());
            aRow.createCell(12).setCellValue(aBook.getArea());
            aRow.createCell(13).setCellValue(aBook.getWhome_to());
        }
        
        
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Save file");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
          System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
          System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
        
        String filename = chooser.getSelectedFile().toString();
        
        String  from_Date = fromDate.replaceAll("/", "-");
        String  to_Date = toDate.replaceAll("/", "-");
        String fnnn = "["+from_Date+","+to_Date+"].xlsx";

        String FilePath = filename.concat("\\Expenses").concat(fnnn);
        System.out.println("---sss--"+FilePath);
        //Write the workbook in file system
        FileOutputStream out = null;
		
			out = new FileOutputStream(new File(FilePath));
		
			try {
				workbook.write(out);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		SuccessMes = "Success";
        } else {
            System.out.println("No Selection ");
            SuccessMes = "FileNotSelect";		// write here apple else to whole
          }
		
        System.out.println("Writesheet.xlsx written successfully" );
        return SuccessMes;
    }    
    
    
	private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
