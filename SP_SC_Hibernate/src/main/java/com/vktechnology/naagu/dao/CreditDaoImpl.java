package com.vktechnology.naagu.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import org.springframework.transaction.annotation.Transactional;

import com.vktechnology.naagu.models.Credit;
import com.vktechnology.naagu.models.CreditClearRecord;
import com.vktechnology.naagu.models.CreditRecord;
import com.vktechnology.naagu.models.Debit;
import com.vktechnology.naagu.service.CreditService;
import com.vktechnology.naagu.service.CreditServiceImpl;
import com.vktechnology.naagu.service.DebitService;
import com.vktechnology.naagu.service.HolderService;

import org.springframework.web.servlet.view.document.AbstractExcelView;

public class CreditDaoImpl implements CreditDao{
	
    @Autowired
    private SessionFactory sessionFactory;
    //private float creditUpdate = 0.0f;
    BigDecimal creditUpdate = new BigDecimal("0");
    @Autowired
    private CreditService creditService;
    
    @Autowired
    private HolderService holderService;
    
    @Autowired
    private DebitService debitService;
    

    public CreditDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
    @Override
    @Transactional
    public String showCredit(){
    	String show_credit = "redirect:/htmlpages/credit.html";
    	return show_credit;
    }
    
    @SuppressWarnings("unchecked")
   	@Override
    @Transactional
    public List<Credit> listOfCredit(String user){
          Criteria cr = sessionFactory.getCurrentSession().createCriteria(Credit.class);
                   cr.add(Restrictions.eq("user", user)).addOrder(Order.desc("credit_Date"));
                   List<Credit> listcredit = cr.list();
           return listcredit;
    }
    
    @SuppressWarnings("unchecked")
   	@Override
    @Transactional
    public List<Credit> listOfCreditTwoDates(String user, String fromDate, String toDate){
          Criteria cr = sessionFactory.getCurrentSession().createCriteria(Credit.class);
          		cr.add(Restrictions.eq("user", user)).addOrder( Order.desc("credit_Date"));
                cr.add(Restrictions.between("credit_Date", fromDate, toDate));
                   List<Credit> listcredit = cr.list();
           return listcredit;
     }
    
    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public String saveCredit(Credit credit){
    	String Message = "";
    	String cname = " ";
    	//float Camount = 0.0f;
    	BigDecimal Camount = new BigDecimal("0");
    	//float C_Camount = 0.0f;
    	BigDecimal C_Camount = new BigDecimal("0");
        String sourcename = credit.getCredit_Name();
        BigDecimal creditAmount = credit.getCredit_Amount();
        String source_name_con = "";
        if(sourcename.indexOf("Credit") > -1){
        	source_name_con = sourcename;
        }else{
        	source_name_con = sourcename.concat("_Credit");
        }
        
        
        System.out.println(source_name_con+" ccredit name: " +sourcename);
        Criteria cr = sessionFactory.getCurrentSession().createCriteria(Credit.class);
        	Criterion useer = Restrictions.eq("user", credit.getUser());
        	Criterion creditName = Restrictions.eq("credit_Name", source_name_con);
        	LogicalExpression andExp = Restrictions.and(useer, creditName);
        	cr.add( andExp );
        List<Credit> results = cr.list();
        for(Credit s: results){
            System.out.println("-- source ---:"+s.getCredit_Name());
            cname = s.getCredit_Name();
            Camount = s.getCredit_Amount();
            C_Camount = s.getCredit_C_Amount();
        }
        
        System.out.println(cname+"-- source salary:"+source_name_con);
        BigDecimal One = new BigDecimal("1");
        //if(credit.getCredit_Amount() == 1){
        if(credit.getCredit_Amount().compareTo(One) == 0){
        	Message = "Error";
        }else if(cname.equals(source_name_con)){
        	
        	//float updateAmount = Camount + creditAmount;
        	BigDecimal updateAmount = Camount.add(creditAmount);
        	//float update_C_Amount = C_Camount + creditAmount;
        	BigDecimal update_C_Amount = C_Camount.add(creditAmount);
        	System.out.println("--90 source salary:"+update_C_Amount);
            Query query = sessionFactory.getCurrentSession().createQuery("update Credit set credit_Amount=:updateAmount, credit_C_Amount=:updateCCA  where credit_Name=:CName and user= :username");
            query.setBigDecimal("updateAmount", updateAmount);
            query.setBigDecimal("updateCCA", update_C_Amount);
            query.setString("CName", cname);
            query.setString("username", credit.getUser());
            query.executeUpdate();
            System.out.println("--update as old");
            Message = "SaveSuccessAsExist";
        }else{
        	
        	String add_credit = sourcename.concat("_Credit");
        	credit.setCredit_Name(add_credit);
        	credit.setCredit_C_Amount(credit.getCredit_Amount());
        	sessionFactory.getCurrentSession().save(credit);
        	System.out.println("--save as new");
        	Message = "SaveSuccessAsNew";
        }
                	
    	
        return Message;
    }
    
    
    @Override
    @Transactional
    public List<String> crediterList(String user){

        
        @SuppressWarnings("unchecked")
        List<String> listTwo = sessionFactory.getCurrentSession().createQuery("select credit_Name FROM Credit where user ='" + user + "' ").list();

        List<String> list123 = new ArrayList<String>();
        list123.addAll(listTwo);
        return list123;

    }
    
    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public BigDecimal crediterAmount(String user, String crediter){
        //float b_amount =0.0f;
    	BigDecimal b_amount = new BigDecimal("0");
        List<BigDecimal> amount = sessionFactory.getCurrentSession().createQuery("select credit_Amount FROM Credit where user ='" + user + "' and credit_Name ='" +crediter+  "' ").list();
        for(BigDecimal s: amount){
        	b_amount = s;
        	System.out.println("crediter available amount :"+b_amount);
        }
        //float crediterAmount = b_amount;
        BigDecimal crediterAmount = b_amount;
        return crediterAmount;
    } 
    
    
    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void addCreditMoney(String creditName, BigDecimal Amount, String user){
    	System.out.println(creditName + "----(--" + Amount);
        // addCreditMoney
        String hqlsource1 = "from Credit where credit_Name= :creditname and user= :username";
        Query query4 = sessionFactory.getCurrentSession().createQuery(hqlsource1);
        query4.setParameter("creditname", creditName);
        query4.setParameter("username", user);
        List<Credit> credit_result = query4.list();
        System.out.println(creditName + "----credit amount--" + Amount);
        for (Credit sss : credit_result) {
            System.out.println(sss.getCredit_Name() + "-- credit amount in database:" + sss.getCredit_C_Amount());
            creditUpdate = sss.getCredit_C_Amount();
        }
        
        //float addingCD = creditUpdate + Amount;
        BigDecimal addingCD = creditUpdate.add(Amount);
        Query sourceUpdate = sessionFactory.getCurrentSession().createQuery("update Credit set credit_C_Amount=:sourAmount where credit_Name=:source_Name and user= :user_name");
        sourceUpdate.setBigDecimal("sourAmount", addingCD);
        sourceUpdate.setString("source_Name", creditName);
        sourceUpdate.setString("user_name", user);
        sourceUpdate.executeUpdate();
       
        creditUpdate = new BigDecimal("0");
    }

    @Override
    @Transactional		// this is for available credit
    public void deductCredit(String C_name, String C_User, BigDecimal C_Amount){
    	System.out.println("-- update only crediter amount---"+creditService.crediterAmount(C_User, C_name));
    	BigDecimal old_amount = creditService.crediterAmount(C_User, C_name);
    	//float current = old_amount - C_Amount;
    	BigDecimal current = old_amount.subtract(C_Amount);
    	System.out.println("after deduct :"+current);
        Query query1 = sessionFactory.getCurrentSession().createQuery("update Credit set credit_Amount=:crediterAmount  where credit_Name=:crediterName and user= :userName");
        //query1.setFloat("crediterAmount", current);
        query1.setBigDecimal("crediterAmount", current);
        query1.setString("crediterName", C_name);
        query1.setString("userName", C_User);
        query1.executeUpdate();
        
    }
    
    @Override
    @Transactional
    public String deductCredit_AC(String C_name, String C_User, BigDecimal C_Amount){	// this is for current credit
    	System.out.println("-- update only crediter_c amount---"+creditService.creditCurBalance(C_User, C_name));
    	//float old_amount = creditService.creditCurBalance(C_User, C_name);
    	BigDecimal old_amount = creditService.creditCurBalance(C_User, C_name);
    	//float current = old_amount - C_Amount;
    	BigDecimal current = old_amount.subtract(C_Amount);
        Query query1 = sessionFactory.getCurrentSession().createQuery("update Credit set credit_C_Amount=:crediterAmount  where credit_Name=:crediterName and user= :userName");
        query1.setBigDecimal("crediterAmount", current);
        query1.setString("crediterName", C_name);
        query1.setString("userName", C_User);
        query1.executeUpdate();
        return "Success";
    }

    @Override
    @Transactional
    public String saveCreditRecord(Credit credit){
    	CreditRecord creditRecord = new CreditRecord();
    	creditRecord.setCr_Date(credit.getCredit_Date());
    	creditRecord.setCr_Name(credit.getCredit_Name());
    	creditRecord.setCr_Withdraw(credit.getCredit_Amount());
    	creditRecord.setCr_A_Balance(creditService.creditAvailBalance(getPrincipal(), credit.getCredit_Name()));
    	creditRecord.setCr_C_Balance(creditService.creditCurBalance(getPrincipal(), credit.getCredit_Name()));
    	creditRecord.setCr_Description(credit.getCredit_Description());
    	creditRecord.setCr_User(credit.getUser());
    	sessionFactory.getCurrentSession().save(creditRecord);
    	return "Success";
    }
    
    @SuppressWarnings("unchecked")
   	@Override
    @Transactional
    public BigDecimal creditAvailBalance(String user, String Source){
    	//Float creditCurAvailBal = 0.0f;
    	BigDecimal creditCurAvailBal = new BigDecimal("0");
    	 Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Credit.class);
         Criterion credituser = Restrictions.eq("user", user);
         Criterion credit_Name = Restrictions.eq("credit_Name", Source);
         LogicalExpression twoExp = Restrictions.and(credituser, credit_Name);
         criteria.add( twoExp );
         List<Credit> creditBal = criteria.list();
         	for(Credit cre: creditBal){
         		creditCurAvailBal = cre.getCredit_Amount();
         	}
         System.out.println("---creditCurAvailBal---"+creditCurAvailBal);
        return creditCurAvailBal;
    }
    
    @SuppressWarnings("unchecked")
   	@Override
    @Transactional
    public BigDecimal creditCurBalance(String user, String Source){
    	System.out.println(user+"-----"+Source);
    	//Float creditCurBal = 0.0f;
    	BigDecimal creditCurBal = new BigDecimal("0");
    	 Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Credit.class);
         Criterion credituser = Restrictions.eq("user", user);
         Criterion credit_Name = Restrictions.eq("credit_Name", Source);
         LogicalExpression twoExp = Restrictions.and(credituser, credit_Name);
         criteria.add( twoExp );
         List<Credit> creditBal = criteria.list();
         System.out.println("-=-=--:"+creditBal);
         	for(Credit cre: creditBal){
         		creditCurBal = cre.getCredit_C_Amount();
         	}
         System.out.println("---creditCurlBal---"+creditCurBal);
        return creditCurBal;
    }
    
    @SuppressWarnings("unchecked")
   	@Override
    @Transactional
    public List<CreditRecord> recordOfCredit(String user){
        Criteria crite = sessionFactory.getCurrentSession().createCriteria(CreditRecord.class);
        crite.add(Restrictions.eq("cr_User", user)).addOrder( Order.asc("cr_Id"));
        List<CreditRecord> creditrecord = crite.list();
        return creditrecord;

    }
    
    @SuppressWarnings("unchecked")
   	@Override
    @Transactional
    public List<CreditRecord> CreditRecordTwoDates(String user, String fromDate, String toDate){
        Criteria crite = sessionFactory.getCurrentSession().createCriteria(CreditRecord.class);
        crite.add(Restrictions.eq("cr_User", user)).addOrder( Order.asc("cr_Id"));
        crite.add(Restrictions.between("cr_Date", fromDate, toDate));
        List<CreditRecord> creditrecord = crite.list();
        return creditrecord;

    }
    
    @SuppressWarnings("unchecked")
   	@Override
    @Transactional
    public List<CreditClearRecord> recordOfClearRecord(String CC_user){
    	Criteria crite = sessionFactory.getCurrentSession().createCriteria(CreditClearRecord.class);
        crite.add(Restrictions.eq("creditClear_User", CC_user)).addOrder( Order.asc("creditClear_Id"));
        List<CreditClearRecord> creditrecord = crite.list();
        return creditrecord;
    }
    
    @SuppressWarnings("unchecked")
   	@Override
    @Transactional
    public List<CreditClearRecord> recordCreditClearTD(String user, String fromDate, String toDate){	// this return  two dates data of credit clear record
    	Criteria crite = sessionFactory.getCurrentSession().createCriteria(CreditClearRecord.class);
        crite.add(Restrictions.eq("creditClear_User", user)).addOrder( Order.asc("creditClear_Id"));
        crite.add(Restrictions.between("creditClear_Date", fromDate, toDate));
        List<CreditClearRecord> creditCrecord = crite.list();
        return creditCrecord;
    }

   	@Override
    @Transactional
    public String clearLentAmountS(String DebitName, String CreditName, BigDecimal Diff_amount, BigDecimal Remaining, String user){
   		String Message = "actionNotPerform";
   		BigDecimal Zero = new BigDecimal("0");
   		//if(Diff_amount < 0){
   		if(Diff_amount.compareTo(Zero) == -1){	
   		Query sourceUpdate = sessionFactory.getCurrentSession().createQuery("update Credit set credit_C_Amount=:sourAmount where credit_Name=:source_Name and user= :user_name");
   	        sourceUpdate.setBigDecimal("sourAmount", (Diff_amount)); // check here
   	        sourceUpdate.setString("source_Name", CreditName);
   	        sourceUpdate.setString("user_name", user);
   	        sourceUpdate.executeUpdate();
   	        
   	     Query DebitUpdate = sessionFactory.getCurrentSession().createQuery("update Debit set debit_Amount=:DebAmount where debit_Name=:Deb_Name and user= :user_name");
   	     	DebitUpdate.setFloat("DebAmount", 0.0f);
   	     	DebitUpdate.setString("Deb_Name", DebitName);
   	     	DebitUpdate.setString("user_name", user);
   	     	DebitUpdate.executeUpdate();
   	        
   	     System.out.println("<<<<<< :"+Diff_amount);
   	     	Message = "CreditMore";
   		//}else if(Diff_amount > 0){
   		}else if(Diff_amount.compareTo(Zero) == 1){	
   		Query DebitUpdate = sessionFactory.getCurrentSession().createQuery("update Debit set debit_Amount=:DebAmount where debit_Name=:Deb_Name and user= :user_name");
   	     	DebitUpdate.setBigDecimal("DebAmount", Diff_amount);
   	     	DebitUpdate.setString("Deb_Name", DebitName);
   	     	DebitUpdate.setString("user_name", user);
   	     	DebitUpdate.executeUpdate();
   	     	
   	     Query CreditUpdate = sessionFactory.getCurrentSession().createQuery("update Credit set credit_C_Amount=:sourAmount where credit_Name=:source_Name and user= :user_name");
   	     	CreditUpdate.setFloat("sourAmount", 0.0f);
   	     	CreditUpdate.setString("source_Name", CreditName);
   	     	CreditUpdate.setString("user_name", user);
   	     	CreditUpdate.executeUpdate();
   			
   			System.out.println(">>>>>>> :"+Diff_amount);
   			Message = "DebitMore";
   		//}else if(Diff_amount == 0){
   		}else if(Diff_amount.compareTo(Zero) == 0){	
   		Query DebitUpdate = sessionFactory.getCurrentSession().createQuery("update Debit set debit_Amount=:DebAmount where debit_Name=:Deb_Name and user= :user_name");
   	     	DebitUpdate.setBigDecimal("DebAmount", new BigDecimal("0.00"));
   	     	DebitUpdate.setString("Deb_Name", DebitName);
   	     	DebitUpdate.setString("user_name", user);
   	     	DebitUpdate.executeUpdate();
   	     	
   	    Query CreditUpdate = sessionFactory.getCurrentSession().createQuery("update Credit set credit_C_Amount=:sourAmount where credit_Name=:source_Name and user= :user_name");
   	     	CreditUpdate.setFloat("sourAmount", 0.0f);
   	     	CreditUpdate.setString("source_Name", CreditName);
   	     	CreditUpdate.setString("user_name", user);
   	     	CreditUpdate.executeUpdate();
   	     	
   	     	Message = "EquallMore";
   			System.out.println("======= :"+Diff_amount);
   		}
   		return Message;
   	}
   	
   	@Override
    @Transactional
   	public String clearLentAmountDynamic(String CreditName, String source, BigDecimal amount, String user){
   		String Message = "NoAction";
   		//float CreditCurBalance = creditService.creditCurBalance(user, CreditName);
   		BigDecimal CreditCurBalance = creditService.creditCurBalance(user, CreditName);
   		//float credUpdate = CreditCurBalance - amount;
   		BigDecimal credUpdate = CreditCurBalance.subtract(amount);
   		//if(CreditCurBalance >= amount){
   		if(CreditCurBalance.compareTo(amount) >= 1 ){	// check here
   		Query CreditUpdate = sessionFactory.getCurrentSession().createQuery("update Credit set credit_C_Amount=:sourAmount where credit_Name=:source_Name and user= :user_name");
   	     	CreditUpdate.setBigDecimal("sourAmount", credUpdate);
   	     	CreditUpdate.setString("source_Name", CreditName);
   	     	CreditUpdate.setString("user_name", user);
   	     	CreditUpdate.executeUpdate();
   	     // deduct from sources
   	     	if(source.indexOf("Credit") > -1){
   	     		creditService.deductCredit(source, user, amount);
   	     		Message = "SuccessCredit";
   	     	}else{
   	     		holderService.deductHolderMoney(source, amount, user);
   	     		Message = "SuccessHolder";
   	     	}
   	     
   	    }else{
   		 Message = "Error";	
   		}
   		return Message;
   	}
   	
   	@Override
    @Transactional
   	public String saveCreditClearRecord(CreditClearRecord cCRecord){
   		CreditClearRecord clearRecord = new CreditClearRecord();
   		clearRecord.setCreditClear_Date(cCRecord.getCreditClear_Date());
   		clearRecord.setCreditClear_name(cCRecord.getCreditClear_name());
   		clearRecord.setCreditClear_Amount(cCRecord.getCreditClear_Amount());
   		clearRecord.setCreditClear_balance(creditService.creditCurBalance(getPrincipal(), cCRecord.getCreditClear_name()));
   		clearRecord.setCreditClear_Source(cCRecord.getCreditClear_Source());
   		clearRecord.setCreditClear_SourceBal(debitService.debitRecordPurpose(cCRecord.getCreditClear_Source(), cCRecord.getCreditClear_User()));
   		clearRecord.setCreditClear_Des(cCRecord.getCreditClear_Des());
   		clearRecord.setCreditClear_User(cCRecord.getCreditClear_User());
   		sessionFactory.getCurrentSession().save(clearRecord);
   		return "Success";
   	}
   
   	@Override
    @Transactional
    public String buildExcelDocument(XSSFWorkbook workbook, String fromDate, String toDate) throws FileNotFoundException{
		
		System.out.println("-------------++download DaoImpl Credit++------------");
        String SuccessMes = "none";
        List<Credit> list = creditService.listOfCredit(getPrincipal());

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
         
        header.createCell(1).setCellValue("Credit_Date");
        header.getCell(1).setCellStyle(style);
         
        header.createCell(2).setCellValue("Credit_Name");
        header.getCell(2).setCellStyle(style);
         
        header.createCell(3).setCellValue("Credit_Amount");
        header.getCell(3).setCellStyle(style);
         
        header.createCell(4).setCellValue("Credit_C_Amount");
        header.getCell(4).setCellStyle(style);
        
        header.createCell(5).setCellValue("Credit_Description");
        header.getCell(5).setCellStyle(style);
        
        // create data rows
        int rowCount = 1;
        DecimalFormat dff = new DecimalFormat("#.00"); 
        for (Credit aBook : list) {
            XSSFRow aRow = sheet.createRow(rowCount++);
            System.out.println(aBook.getCredit_Name());
            aRow.createCell(0).setCellValue(aBook.getId());
            aRow.createCell(1).setCellValue(aBook.getCredit_Date());
            aRow.createCell(2).setCellValue(aBook.getCredit_Name());
            aRow.createCell(3).setCellValue(dff.format(aBook.getCredit_Amount()));
            aRow.createCell(4).setCellValue(dff.format(aBook.getCredit_C_Amount()));
            aRow.createCell(5).setCellValue(aBook.getCredit_Description());
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
        
        String FilePath = filename.concat("\\Credit").concat(curDate);
        System.out.println(FilePath+"---sss--"+curDate);
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
		
        System.out.println("Writesheet.xls written successfully" );
        return SuccessMes;
	}
 
@Override
@Transactional
public String creditRecordExcel(XSSFWorkbook workbook, String fromDate, String toDate) throws FileNotFoundException{
		
		System.out.println("-------------++download DaoImpl Credit record++------------");
        String SuccessMes = "none";
        List<CreditRecord> listCR = creditService.CreditRecordTwoDates(getPrincipal(), fromDate, toDate);

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
         
        header.createCell(0).setCellValue("Cr_Id");
        header.getCell(0).setCellStyle(style);
         
        header.createCell(1).setCellValue("Cr_Date");
        header.getCell(1).setCellStyle(style);
         
        header.createCell(2).setCellValue("Cr_Name");
        header.getCell(2).setCellStyle(style);
         
        header.createCell(3).setCellValue("Cr_Withdraw");
        header.getCell(3).setCellStyle(style);
         
        header.createCell(4).setCellValue("Cr_A_Balance");
        header.getCell(4).setCellStyle(style);
        
        header.createCell(5).setCellValue("Cr_C_Balance");
        header.getCell(5).setCellStyle(style);
        
        header.createCell(6).setCellValue("Cr_Description");
        header.getCell(6).setCellStyle(style);
        // create data rows
        int rowCount = 1;
        DecimalFormat dfff = new DecimalFormat("#.00");
        for (CreditRecord CRBook : listCR) {
            XSSFRow aRow = sheet.createRow(rowCount++);
            System.out.println(CRBook.getCr_Name());
            aRow.createCell(0).setCellValue(CRBook.getCr_Id());
            aRow.createCell(1).setCellValue(CRBook.getCr_Date());
            aRow.createCell(2).setCellValue(CRBook.getCr_Name());
            aRow.createCell(3).setCellValue(dfff.format(CRBook.getCr_Withdraw()));
            aRow.createCell(4).setCellValue(dfff.format(CRBook.getCr_A_Balance()));
            aRow.createCell(5).setCellValue(dfff.format(CRBook.getCr_C_Balance()));
            aRow.createCell(6).setCellValue(CRBook.getCr_Description());
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
        
        String FilePath = filename.concat("\\CreditRecord").concat(fnnn);
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
public String creditClearReExcel(XSSFWorkbook workbook, String fromDate, String toDate) throws FileNotFoundException{
		
		System.out.println("-------------++download credit ClearReExcel record++------------");
        String SuccessMes = "none";
        List<CreditClearRecord> listCClearR = creditService.recordCreditClearTD(getPrincipal(), fromDate, toDate);

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
         
        header.createCell(0).setCellValue("CreditClear_Id");
        header.getCell(0).setCellStyle(style);
         
        header.createCell(1).setCellValue("CreditClear_Date");
        header.getCell(1).setCellStyle(style);
         
        header.createCell(2).setCellValue("CreditClear_name");
        header.getCell(2).setCellStyle(style);
         
        header.createCell(3).setCellValue("CreditClear_Amount");
        header.getCell(3).setCellStyle(style);
         
        header.createCell(4).setCellValue("CreditClear_balance");
        header.getCell(4).setCellStyle(style);
        
        header.createCell(5).setCellValue("CreditClear_Source");
        header.getCell(5).setCellStyle(style);
        
        header.createCell(6).setCellValue("CreditClear_SourceBal");
        header.getCell(6).setCellStyle(style);
        
        header.createCell(7).setCellValue("CreditClear_Des");
        header.getCell(7).setCellStyle(style);        
        // create data rows
        int rowCount = 1;
        DecimalFormat dfff = new DecimalFormat("#.00");
        for (CreditClearRecord CRBook : listCClearR) {
            XSSFRow aRow = sheet.createRow(rowCount++);
            System.out.println(CRBook.getCreditClear_name());
            aRow.createCell(0).setCellValue(CRBook.getCreditClear_Id());
            aRow.createCell(1).setCellValue(CRBook.getCreditClear_Date());
            aRow.createCell(2).setCellValue(CRBook.getCreditClear_name());
            aRow.createCell(3).setCellValue(dfff.format(CRBook.getCreditClear_Amount()));
            aRow.createCell(4).setCellValue(dfff.format(CRBook.getCreditClear_balance()));            
            aRow.createCell(5).setCellValue(CRBook.getCreditClear_Source());
            aRow.createCell(6).setCellValue(dfff.format(CRBook.getCreditClear_SourceBal()));
            aRow.createCell(7).setCellValue(CRBook.getCreditClear_Des());
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
        
        String FilePath = filename.concat("\\CreditClearRecord").concat(fnnn);
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
  	
   	public String sendMail(String ToMail, String FromMail, String Password, String Attachfile, String Subject, String MailMessage) 
			throws MessagingException, IOException{
        System.out.println("-------------++send daiImpl Mail++------------");
        String MessageRe = "none";
        String host = "smtp.gmail.com";
        String port = "587";
        final String userName = FromMail;
        final String password = Password;
        String toAddress = ToMail;
        String subject = Subject;
        String message = MailMessage;
       
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", userName);
        properties.put("mail.password", password);
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
                
        Session session = Session.getInstance(properties, auth);
        try{
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        
        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");
 
        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
         // adds attachments
                 MimeBodyPart attachPart = new MimeBodyPart();
                     attachPart.attachFile(Attachfile);
                     multipart.addBodyPart(attachPart);
                     // sets the multi-part as e-mail's content
        msg.setContent(multipart);
 
        // sends the e-mail
        Transport.send(msg);
        MessageRe  = "Success"; 
 	}catch(MessagingException  exception){
 		MessageRe  = exception.toString();
 	}catch(IOException PRexception){
 		MessageRe = PRexception.toString();
 	}
           
	return MessageRe;
   	}
 
@Override
@Transactional   	
public String mailSuccessMessage(String Message123, String ToMail, String FromMail, String Password, String Attachfile, String Subject, String MailMessage) throws MessagingException, IOException{
	String MessageFromMail = "none";
	String AjaxMessage = "none";
	System.out.println("---Holder--"+Message123);
	if("Success".equalsIgnoreCase(Message123)){
			MessageFromMail = creditService.sendMail(ToMail, FromMail, Password, Attachfile, Subject, MailMessage);
			System.out.println("---Holder--"+MessageFromMail);	
			AjaxMessage = MessageFromMail;
		}else if("Success".equalsIgnoreCase(MessageFromMail)){
			AjaxMessage = "Success";
		}else if("FileNotSelect".equalsIgnoreCase(Message123)){
			AjaxMessage = "Excel file not create";
		}
	return AjaxMessage;
	}

@Override
@Transactional
public String gettingFilePath(){
	String filePath = null;
	JFileChooser fileopen = new JFileChooser();
    FileFilter filter = new FileNameExtensionFilter("c files", "c");
    fileopen.addChoosableFileFilter(filter);

    int ret = fileopen.showDialog(null, "Open file");

    if (ret == JFileChooser.APPROVE_OPTION) {
      File file = fileopen.getSelectedFile();
      System.out.println(file);
      filePath = file.toString();
    }
    return filePath;
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
