package com.vktechnology.naagu.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JFileChooser;
import javax.transaction.Transactional;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.vktechnology.naagu.models.CreditClearRecord;
import com.vktechnology.naagu.models.CreditRecord;
import com.vktechnology.naagu.models.Debit;
import com.vktechnology.naagu.models.DebitClearRecord;
import com.vktechnology.naagu.models.DebitRecord;
import com.vktechnology.naagu.service.CreditService;
import com.vktechnology.naagu.service.DebitService;
import com.vktechnology.naagu.service.ExpensesService;
import com.vktechnology.naagu.service.HolderService;
import com.vktechnology.naagu.service.SourceService;

public class DebitDaoImpl implements DebitDao{
	
	@Autowired
    private SessionFactory sessionFactory;
  
    public DebitDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Autowired
    private ExpensesService expensesService;
    
    @Autowired
    private HolderService holderService;
	
    @Autowired
    private CreditService creditService;
    
    @Autowired
    private DebitService debitService;
    
    @Autowired
    private SourceService sourceService;
    
    
    
    private String Debit_existName = "";
	//private float Debit_existAmount = 0.0f;
    BigDecimal Debit_existAmount = new BigDecimal("0");
    @Override
    @Transactional
	public String showDebit(){
		String show_debit = "redirect:/htmlpages/debit.html";
    	return show_debit;
	}
    
    public String debitClearPage(){
    	String clear_page = "redirect:/htmlpages/debitClear.html";
    	return clear_page;
    }
    
    public String showdebitClearRpage(){
    	String clearDRpage = "debitClearRecord";
    	return clearDRpage;
    }
    
    @SuppressWarnings("unchecked")
   	@Override
    @Transactional
    public List<Debit> listOfDebit(String user){

          Criteria cr = sessionFactory.getCurrentSession().createCriteria(Debit.class);
                   cr.add(Restrictions.eq("user", user)).addOrder( Order.desc("debit_Date"));
                   List<Debit> listDebit = cr.list();
           return listDebit;

       }
    
    @SuppressWarnings("unchecked")
   	@Override
    @Transactional
    public List<String> debiterList(String user){
        List<String> listTwo123 = sessionFactory.getCurrentSession().createQuery("select debit_Name FROM Debit where user ='" + user + "' ").list();

        List<String> list23 = new ArrayList<String>();
        list23.addAll(listTwo123);
        return list23;

    }
    
    @Override
    @Transactional
    public String saveDebit(Debit debit){
    	String Message = "none";
    	
    	List<Debit> NameList = debitService.existOrNotDebit(getPrincipal(), debit.getDebit_Name());
    	for(Debit debit_name: NameList){
    		Debit_existName = debit_name.getDebit_Name();
    		Debit_existAmount = debit_name.getDebit_Amount();
    	}
    	
    	//float current = Debit_existAmount + debit.getDebit_Amount();
    	BigDecimal current = Debit_existAmount.add(debit.getDebit_Amount());
    	System.out.println(Debit_existName+"-0-:"+debit.getDebit_Name());
    	System.out.println(current+"-0-:"+debit.getDebit_Amount());
    	System.out.println("-0-:"+debit.getDebit_Source());
    	
    	if(expensesService.availablityCheck(getPrincipal(), debit.getDebit_Source(), debit.getDebit_Amount()).equalsIgnoreCase("Error") ){
        	Message = "ErrorAmountLess";
        }else if(debit.getDebit_Name().equals(Debit_existName)){
        
        System.out.println("-------update--------");
        	if(debit.getDebit_Source().indexOf("Credit") > -1){
        		System.out.println("-- we are IN  credit");
        		
        		creditService.deductCredit(debit.getDebit_Source(), getPrincipal(), debit.getDebit_Amount());
        		
        		Query query1 = sessionFactory.getCurrentSession().createQuery("update Debit set debit_Amount=:debitAmount where debit_Name=:debitName and user= :userName");
                query1.setBigDecimal("debitAmount", current);
                query1.setString("debitName", debit.getDebit_Name());
                query1.setString("userName", getPrincipal());
                query1.executeUpdate();
                Message = "SuccessCreditDeductUpdate";
        	}else{
        	
        		System.out.println("--we are not indexOf : deduct from money holder");
        		holderService.deductHolderMoney(debit.getDebit_Source(), debit.getDebit_Amount(), getPrincipal());
        		// update
        		Query query1 = sessionFactory.getCurrentSession().createQuery("update Debit set debit_Amount=:debitAmount where debit_Name=:debitName and user= :userName");
                query1.setBigDecimal("debitAmount", current);
                query1.setString("debitName", debit.getDebit_Name());
                query1.setString("userName", getPrincipal());
                query1.executeUpdate();
        		Message = "SuccessDeductHolderUpdate";
        	}
        	
    	
        }else{
        	if(debit.getDebit_Source().indexOf("Credit") > -1){
            	System.out.println("-- we are IN  credit");
            	creditService.deductCredit(debit.getDebit_Source(), getPrincipal(), debit.getDebit_Amount());
            	sessionFactory.getCurrentSession().save(debit);
            	Message = "SuccessCreditDeductNew";
            }else{
            	
            	System.out.println("--we are not indexOf : deduct from money holder");
            	holderService.deductHolderMoney(debit.getDebit_Source(), debit.getDebit_Amount(), getPrincipal());
            	// save
            	sessionFactory.getCurrentSession().save(debit);
            	Message = "SuccessDeductHolderNew";
            }
        	
        }
    	return Message;
    	
    	
    }
    
    @SuppressWarnings("unchecked")
    @Transactional   
    @Override
    public List<Debit> existOrNotDebit(String user, String debitName){
    	
        String hql123 = "from Debit where  debit_Name= :debitname and user= :debituser";
        Query query12 = sessionFactory.getCurrentSession().createQuery(hql123);
        query12.setParameter("debitname", debitName);
        query12.setParameter("debituser", user);
        List<Debit> result = query12.list();
        return result;
     }
    
    @SuppressWarnings("unchecked")
   	@Override
    @Transactional
    public List<DebitRecord> recordOfDebit(String user){
    	Criteria cr = sessionFactory.getCurrentSession().createCriteria(DebitRecord.class);
        cr.add(Restrictions.eq("dr_User", user)).addOrder( Order.asc("id"));
        List<DebitRecord> DebitRecord = cr.list();
        return DebitRecord;
    }
    
    @SuppressWarnings("unchecked")
   	@Override
    @Transactional
    public List<DebitClearRecord> debitClearRecord(String user){
    	Criteria crDR = sessionFactory.getCurrentSession().createCriteria(DebitClearRecord.class);
        crDR.add(Restrictions.eq("debitClear_User", user)).addOrder( Order.asc("debitClear_Id"));
        List<DebitClearRecord> DebitCRecord = crDR.list();
        return DebitCRecord;
    }
    
    @SuppressWarnings("unchecked")
   	@Override
    @Transactional
    public List<DebitRecord> recordOfDebTwoDates(String user, String fromDate, String toDate){
    	Criteria cr = sessionFactory.getCurrentSession().createCriteria(DebitRecord.class);
        cr.add(Restrictions.eq("dr_User", user)).addOrder( Order.asc("id"));
        cr.add(Restrictions.between("dr_Date", fromDate, toDate));
        List<DebitRecord> DebitRecord = cr.list();
        return DebitRecord;
    }
    
    @SuppressWarnings("unchecked")
   	@Override
    @Transactional
    public List<DebitClearRecord> reOfDebClearTwoDates(String user, String fromDate, String toDate){
    	Criteria crDCR = sessionFactory.getCurrentSession().createCriteria(DebitClearRecord.class);
    	crDCR.add(Restrictions.eq("debitClear_User", user)).addOrder( Order.asc("debitClear_Id"));
    	crDCR.add(Restrictions.between("debitClear_Date", fromDate, toDate));
        List<DebitClearRecord> DebitClRecord = crDCR.list();
        return DebitClRecord;
    }
    
    @Override
    @Transactional
    public String saveDebitRecord(Debit debit){
        DebitRecord debitRecord = new DebitRecord();
        debitRecord.setDr_Date(debit.getDebit_Date());
        debitRecord.setDr_Name(debit.getDebit_Name());
        debitRecord.setDr_Balance(debitService.debitBalance(debit.getUser(), debit.getDebit_Name()));
        debitRecord.setDr_Withdraw(debit.getDebit_Amount());
        debitRecord.setDr_SourceName(debit.getDebit_Source());
        debitRecord.setDr_SourceBalance(debitService.debitRecordPurpose(debit.getDebit_Source(), getPrincipal()));
        debitRecord.setDr_Description(debit.getDebit_Description());
        debitRecord.setDr_User(debit.getUser());
        sessionFactory.getCurrentSession().save(debitRecord);
        return "Success";
    }
    
    @Override
    @Transactional
    public BigDecimal debitRecordPurpose(String De_Source, String DE_User){	//this will return holder balance or credit available balance or source balance
    	//float recordUV = 0.0f;
    	BigDecimal recordUV = new BigDecimal("0");
    	String holderBankName  = "notavail";
    	String debit_name = "notavail";
    	List<String> resultOnlyBank = holderService.existOrNotBank(getPrincipal());
    	System.out.println(De_Source+"-----():"+resultOnlyBank);
        for (String sss : resultOnlyBank) {
        	System.out.println("---123---"+sss);
            if(De_Source.equalsIgnoreCase(sss)){
            holderBankName = sss;            
            }
          }
        List<String> listOfdebit = debitService.debiterList(DE_User);
        	for(String list: listOfdebit){
        		if(De_Source.equalsIgnoreCase(list)){
        			debit_name = list;
        		}
        	}
        System.out.println(debit_name+"-----123 "+De_Source);    	
    	if(De_Source.indexOf("Credit") > -1){
    		recordUV = creditService.creditAvailBalance(DE_User, De_Source);
    	}else if(holderBankName.equalsIgnoreCase(De_Source)){
    		recordUV = holderService.holderBalance(DE_User, De_Source);
    	}else if(debit_name.equalsIgnoreCase(De_Source)){
    		recordUV = debitService.debitBalance(DE_User, De_Source);
    		System.out.println(debit_name+"-----balance  "+recordUV);  
    	}else{    	
    		recordUV = sourceService.sourceBalance(getPrincipal(), De_Source);
    	}
    	return recordUV;
    }
    
    @SuppressWarnings("unchecked")
   	@Override
    @Transactional
    public BigDecimal debitBalance(String user, String Source){
    	//Float debitCurBal = 0.0f;
    	BigDecimal debitCurBal = new BigDecimal("0");
    	 Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Debit.class);
         Criterion userD = Restrictions.eq("user", user);
         Criterion debit_Name = Restrictions.eq("debit_Name", Source);
         LogicalExpression twoExp = Restrictions.and(userD, debit_Name);
         criteria.add( twoExp );
         List<Debit> debitBal = criteria.list();
         	for(Debit debt: debitBal){
         		debitCurBal = debt.getDebit_Amount();
         	}
         System.out.println("---debitCurBal---"+debitCurBal);
        return debitCurBal;
    }
    
	@Override
    @Transactional
    public String debitClearDynamic(String CDname, BigDecimal CDamount, String CDsource, String CDuser){
		String messageOut = "none";
		//float curDeBalance = debitService.debitBalance(CDuser, CDname);
		BigDecimal curDeBalance = debitService.debitBalance(CDuser, CDname);
		System.out.println(curDeBalance+"---CDname daoimpl---"+CDname);
		//float curupdate = curDeBalance - CDamount;
		BigDecimal curupdate = curDeBalance.subtract(CDamount);
		//if(curDeBalance >= CDamount){
		if(curDeBalance.compareTo(CDamount) >= 0){	//check here
	   		Query CreditUpdate = sessionFactory.getCurrentSession().createQuery("update Debit set debit_Amount 	=:sourAmount where debit_Name=:source_Name and user= :user_name");
	   	     	CreditUpdate.setBigDecimal("sourAmount", curupdate);
	   	     	CreditUpdate.setString("source_Name", CDname);
	   	     	CreditUpdate.setString("user_name", CDuser);
	   	     	CreditUpdate.executeUpdate();
	   	    
	   	    holderService.AmountAddToHolder(CDsource, CDamount, CDuser);
	   	    messageOut = "Success";	   	     	
		}
		return messageOut;
	}
	
	@Override
    @Transactional
   	public String saveDebitClearRecord(DebitClearRecord DCRecord){
		System.out.println(DCRecord.getDebitClear_Source()+"--"+DCRecord.getDebitClear_name());
		DebitClearRecord DclearRecord = new DebitClearRecord();
		DclearRecord.setDebitClear_Date(DCRecord.getDebitClear_Date());
		DclearRecord.setDebitClear_name(DCRecord.getDebitClear_name());
		DclearRecord.setDebitClear_Amount(DCRecord.getDebitClear_Amount());
		DclearRecord.setDebitClear_balance(debitService.debitBalance(DCRecord.getDebitClear_User(), DCRecord.getDebitClear_name()));
		DclearRecord.setDebitClear_Source(DCRecord.getDebitClear_Source());
		DclearRecord.setDebitClear_SourceBal(debitService.debitRecordPurpose(DCRecord.getDebitClear_Source(), DCRecord.getDebitClear_User()));
		DclearRecord.setDebitClear_Des(DCRecord.getDebitClear_Des());
		DclearRecord.setDebitClear_User(DCRecord.getDebitClear_User());
		sessionFactory.getCurrentSession().save(DclearRecord);		
		return "Success";
	}
 
    @Override
    @Transactional
    public String debitListExcel(XSSFWorkbook workbook) throws FileNotFoundException{
    		
    		System.out.println("-------------++download DaoImpl debit lidt++------------");
            String SuccessMes = "none";
            List<Debit> listDR = debitService.listOfDebit(getPrincipal());

            XSSFSheet sheet = workbook.createSheet("sheet 1");
            sheet.setDefaultColumnWidth(20);
           
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
            
            header.createCell(1).setCellValue("Debit_Date");
            header.getCell(1).setCellStyle(style);
             
            header.createCell(2).setCellValue("Debit_Name");
            header.getCell(2).setCellStyle(style);
             
            header.createCell(3).setCellValue("Debit_Amount");
            header.getCell(3).setCellStyle(style);
             
            header.createCell(4).setCellValue("Debit_Source");
            header.getCell(4).setCellStyle(style);
             
            header.createCell(5).setCellValue("Debit_Description");
            header.getCell(5).setCellStyle(style);
            // create data rows
            int rowCount = 1;
            DecimalFormat df = new DecimalFormat("#.00"); 
            for (Debit CRBook : listDR) {
                XSSFRow aRow = sheet.createRow(rowCount++);
                System.out.println(CRBook.getDebit_Name());
                aRow.createCell(0).setCellValue(CRBook.getId());
                aRow.createCell(1).setCellValue(CRBook.getDebit_Date());
                aRow.createCell(2).setCellValue(CRBook.getDebit_Name());
                aRow.createCell(3).setCellValue(df.format(CRBook.getDebit_Amount()));
                aRow.createCell(4).setCellValue(CRBook.getDebit_Source());
                aRow.createCell(5).setCellValue(CRBook.getDebit_Description());
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
            
            Calendar cle = Calendar.getInstance();
            int dd = cle.get(Calendar.DATE);
            int mm = cle.get(Calendar.MONTH);
            ++mm;
            int year = cle.get(Calendar.YEAR);
            String curDate = "["+mm+"-"+dd+"-"+year+"].xlsx";
            
            String FilePath = filename.concat("\\Debit").concat(curDate);
            System.out.println(curDate+"---path--"+FilePath);
            //Write the workbook in file system
            FileOutputStream out = null;
    		
    			out = new FileOutputStream(new File(FilePath));
    		
    			try {
    				workbook.write(out);
    				out.close();
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    				SuccessMes = e.toString();
    			}
    		SuccessMes = "Success";
            } else {
                System.out.println("No Selection ");
                SuccessMes = "FileNotSelect";		// write here apple else to whole
              }
    		
            System.out.println("Writesheet.xlsx written successfully" );
            return SuccessMes;
    	}
    
    @Override
    @Transactional
    public String debitRecordExcel(XSSFWorkbook workbook, String fromDate, String toDate) throws FileNotFoundException{
    		
    		System.out.println("-------------++download DaoImpl debit record lidt++------------");
            String SuccessMes = "none";
            List<DebitRecord> listDRR = debitService.recordOfDebTwoDates(getPrincipal(), fromDate, toDate);

            XSSFSheet sheet = workbook.createSheet("sheet 1");
            sheet.setDefaultColumnWidth(20);
           
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
            
            header.createCell(1).setCellValue("Dr_Date");
            header.getCell(1).setCellStyle(style);
             
            header.createCell(2).setCellValue("Dr_Name");
            header.getCell(2).setCellStyle(style);
             
            header.createCell(3).setCellValue("Dr_Balance");
            header.getCell(3).setCellStyle(style);
             
            header.createCell(4).setCellValue("Dr_Withdraw");
            header.getCell(4).setCellStyle(style);
             
            header.createCell(5).setCellValue("Dr_SourceName");
            header.getCell(5).setCellStyle(style);
            
            header.createCell(6).setCellValue("Dr_SourceBalance");
            header.getCell(6).setCellStyle(style);
            
            header.createCell(7).setCellValue("Dr_Description");
            header.getCell(7).setCellStyle(style);
            // create data rows
            int rowCount = 1;
            DecimalFormat dff = new DecimalFormat("#.00"); 
            for (DebitRecord CRBook : listDRR) {
                XSSFRow aRow = sheet.createRow(rowCount++);
                System.out.println(CRBook.getDr_Name());
                aRow.createCell(0).setCellValue(CRBook.getId());
                aRow.createCell(1).setCellValue(CRBook.getDr_Date());
                aRow.createCell(2).setCellValue(CRBook.getDr_Name());
                aRow.createCell(3).setCellValue(dff.format(CRBook.getDr_Balance()));
                aRow.createCell(4).setCellValue(dff.format(CRBook.getDr_Withdraw()));
                aRow.createCell(5).setCellValue(CRBook.getDr_SourceName());
                aRow.createCell(6).setCellValue(dff.format(CRBook.getDr_SourceBalance()));
                aRow.createCell(7).setCellValue(CRBook.getDr_Description());
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

            String FilePath = filename.concat("\\DebitRecord").concat(fnnn);
            System.out.println("---path--"+FilePath);
            //Write the workbook in file system
            FileOutputStream out = null;
    		
    			out = new FileOutputStream(new File(FilePath));
    		
    			try {
    				workbook.write(out);
    				out.close();
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    				SuccessMes = e.toString();
    			}
    		SuccessMes = "Success";
            } else {
                System.out.println("No Selection ");
                SuccessMes = "FileNotSelect";		// write here apple else to whole
              }
    		
            System.out.println("Writesheet.xlsx written successfully" );
            return SuccessMes;
    	} 
    
    @Override
    @Transactional
    public String debitClearRecordExcel(XSSFWorkbook workbook, String fromDate, String toDate) throws FileNotFoundException{
    		
    		System.out.println("-------------++download DaoImpl debit clear record++------------");
            String SuccessMes = "none";
            List<DebitClearRecord> listCDR = debitService.reOfDebClearTwoDates(getPrincipal(), fromDate, toDate);

            XSSFSheet sheet = workbook.createSheet("sheet 1");
            sheet.setDefaultColumnWidth(20);
           
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
             
            header.createCell(0).setCellValue("debitClear_Id");
            header.getCell(0).setCellStyle(style);
             
            header.createCell(1).setCellValue("DebitClear_Date");
            header.getCell(1).setCellStyle(style);
             
            header.createCell(2).setCellValue("DebitClear_name");
            header.getCell(2).setCellStyle(style);
             
            header.createCell(3).setCellValue("DebitClear_Amount");
            header.getCell(3).setCellStyle(style);
             
            header.createCell(4).setCellValue("DebitClear_balance");
            header.getCell(4).setCellStyle(style);
            
            header.createCell(5).setCellValue("DebitClear_Source");
            header.getCell(5).setCellStyle(style);
            
            header.createCell(6).setCellValue("DebitClear_SourceBal");
            header.getCell(6).setCellStyle(style);
            
            header.createCell(7).setCellValue("DebitClear_Des");
            header.getCell(7).setCellStyle(style);
            // create data rows
            int rowCount = 1;
            DecimalFormat dfff = new DecimalFormat("#.00");
            for (DebitClearRecord DCRBook : listCDR) {
                XSSFRow aRow = sheet.createRow(rowCount++);
                System.out.println(DCRBook.getDebitClear_name());
                aRow.createCell(0).setCellValue(DCRBook.getDebitClear_Id());
                aRow.createCell(1).setCellValue(DCRBook.getDebitClear_Date());
                aRow.createCell(2).setCellValue(DCRBook.getDebitClear_name());
                aRow.createCell(3).setCellValue(dfff.format(DCRBook.getDebitClear_Amount()));
                aRow.createCell(4).setCellValue(dfff.format(DCRBook.getDebitClear_balance()));
                aRow.createCell(5).setCellValue(DCRBook.getDebitClear_Source());
                aRow.createCell(6).setCellValue(dfff.format(DCRBook.getDebitClear_SourceBal()));
                aRow.createCell(7).setCellValue(DCRBook.getDebitClear_Des());
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
            
            String FilePath = filename.concat("\\DebitClearRecord").concat(fnnn);
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
    @Override
    @Transactional
    public String securityForClintSide(){
    	List<DebitRecord> listDRR = debitService.recordOfDebit(getPrincipal());
    	return listDRR.toString(); //place here true boolisn
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
