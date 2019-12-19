package com.vktechnology.naagu.dao;

import com.google.gson.Gson;
import com.vktechnology.naagu.models.Credit;
import com.vktechnology.naagu.models.CreditRecord;
import com.vktechnology.naagu.models.DebitRecord;
import com.vktechnology.naagu.models.Source;
import com.vktechnology.naagu.models.SourceRecord;
import com.vktechnology.naagu.service.SourceService;

import org.apache.log4j.Logger;
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
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.swing.JFileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * Created by HOME on 12/15/2015.
 */
public class SourceDaoImpl implements SourceDao {

    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private SourceService sourceService;
    
    private static final Logger logger = Logger.getLogger(SourceDaoImpl.class);
    //private float salary_lastvalue = -2.0f;
    BigDecimal salary_lastvalue = new  BigDecimal("-2.0");		//check here
    BigDecimal minusTwo = new BigDecimal("-2.0");
    public SourceDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    

    @Override
    @Transactional
    public ModelAndView showSource(){
    	ModelAndView model = new ModelAndView("redirect:/htmlpages/source.html");
    	List<String> onlysource = sourceService.sourceListOnly(getPrincipal());
        Gson gson = new Gson();
        String js = gson.toJson(onlysource);
        model.addObject("OnlySource", js);
        return model;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<Source> ListOfSource(String user){
        
        Criteria cr = sessionFactory.getCurrentSession().createCriteria(Source.class);
        cr.add(Restrictions.eq("user", user));

        List<Source> list = cr.list();
        return list;

    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public String saveSource(Source source){
    	String Message = null;
        String sourcename = source.getSourceName();
        String source_type = source.getSourceType();
        logger.info("source name :"+sourcename);
        Criteria cr = sessionFactory.getCurrentSession().createCriteria(Source.class);

        Criterion useer = Restrictions.eq("user", source.getUser());
        Criterion dess = Restrictions.eq("sourceName", source.getSourceName());
        LogicalExpression andExp = Restrictions.and(useer, dess);
        cr.add( andExp );
        //cr.add(Restrictions.eq("sourceName", sourceType));
        List<Source> results = cr.list();
        for(Source s: results){
            logger.info("source amount :"+s.getSourceAmount());
            salary_lastvalue = s.getSourceAmount();
        }

        if("Source".equals(source_type)) {

            //if (salary_lastvalue != -2.0) {
            if (salary_lastvalue.compareTo(minusTwo) != 0) {		// check here
                logger.info("source amount before add :"+salary_lastvalue);
                //float current = salary_lastvalue + source.getSourceAmount();
                BigDecimal current = salary_lastvalue.add(source.getSourceAmount());

                Query query = sessionFactory.getCurrentSession().createQuery("update Source set sourceAmount=:sourceAmount where sourceName=:sourceName and user=:username");
                query.setBigDecimal("sourceAmount", current);
                query.setString("sourceName", sourcename);
                query.setString("username", source.getUser());
                int modifications = query.executeUpdate();
                Message = "SuccessSaveAsOld";

            } else{
                sessionFactory.getCurrentSession().save(source);
                logger.info("source save new entry :");
                Message = "SuccessAsSaveNew";
            }
        }else{
        	Message = "Error";
        }
        //salary_lastvalue = -2.0f;
        salary_lastvalue = new BigDecimal("-2.0");
        logger.info("Message :"+Message);
        return Message;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<String> sourceListOnly(String user){

    	//List<Source> list = (List<Source>) sessionFactory.getCurrentSession().createQuery("from Source cat where cat.SourceName").list();
        Criteria cr = sessionFactory.getCurrentSession().createCriteria(Source.class);
        Criterion useer = Restrictions.eq("user", user);
        Criterion dess = Restrictions.isNotNull("sourceName");
        LogicalExpression andExp = Restrictions.and(useer, dess);
        cr.add( andExp );

        List<String> listOne  = sessionFactory.getCurrentSession().createQuery("select sourceName FROM Source where user ='"+user+"' ").list();
        
        List<String> listTwo  = sessionFactory.getCurrentSession().createQuery("select credit_Name FROM Credit where user ='"+user+"' ").list();
        
        List<String> listThree  = sessionFactory.getCurrentSession().createQuery("select holder_Name FROM Holder where holder_User ='"+user+"' ").list();
        

        List<String> list_source = new ArrayList<String>();
        list_source.addAll(listOne);
        list_source.addAll(listTwo);
        list_source.addAll(listThree);
        System.out.println("------Two:" +list_source);
        //cr.add(Restrictions.isNotNull("sourceName"));
        //List<Source> list = cr.list();
        return list_source;

    }
    
    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<String> sourceNamesList(String user){

        //List<Source> list = (List<Source>) sessionFactory.getCurrentSession().createQuery("from Source cat where cat.SourceName").list();
        Criteria cr = sessionFactory.getCurrentSession().createCriteria(Source.class);
        Criterion useer = Restrictions.eq("user", user);
        Criterion dess = Restrictions.isNotNull("sourceName");
        LogicalExpression andExp = Restrictions.and(useer, dess);
        cr.add( andExp );

        List<String> listOne  = sessionFactory.getCurrentSession().createQuery("select sourceName FROM Source where user ='"+user+"' ").list();
        
        //List<String> listTwo  = sessionFactory.getCurrentSession().createQuery("select credit_Name FROM Credit where user ='"+user+"' ").list();
        

       List<String> list_source = new ArrayList<String>();
       list_source.addAll(listOne);
       return list_source;

    }
    
    
    @Override
    @Transactional
    public String saveSourceRecord(Source source){
    	SourceRecord sourceRecord = new SourceRecord();
    	sourceRecord.setSr_Date(source.getSourceDate());
    	sourceRecord.setSr_Name(source.getSourceName());
    	sourceRecord.setSr_Deposits(source.getSourceAmount());
    	sourceRecord.setSr_Balance(sourceService.sourceBalance(getPrincipal(), source.getSourceName()));
    	sourceRecord.setSr_Description(source.getSourceDescription());
    	sourceRecord.setSr_User(source.getUser());
    	sessionFactory.getCurrentSession().save(sourceRecord);
    	return "Success";
    }
    
    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public BigDecimal sourceBalance(String user, String SourceName){
    //Float sourceCurBal = 0.0f;
    BigDecimal sourceCurBal = new BigDecimal("0");
   	 Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Source.class);
        Criterion sourceuser = Restrictions.eq("user", user);
        Criterion source_Name = Restrictions.eq("sourceName", SourceName);
        LogicalExpression twoExp = Restrictions.and(source_Name, sourceuser);
        criteria.add( twoExp );
        List<Source> sourceBal = criteria.list();
        	for(Source sour: sourceBal){
        		sourceCurBal = sour.getSourceAmount();
        	}
        System.out.println("---sourceCurBal---"+sourceCurBal);
       return sourceCurBal;
    }
    
    @SuppressWarnings("unchecked")
   	@Override
    @Transactional
    public List<SourceRecord> recordOfSource(String user){
        Criteria crite = sessionFactory.getCurrentSession().createCriteria(SourceRecord.class);
        crite.add(Restrictions.eq("sr_User", user)).addOrder( Order.asc("sr_Id"));
        List<SourceRecord> source_record = crite.list();
        return source_record;
    }
    
    @SuppressWarnings("unchecked")
   	@Override
    @Transactional
    public List<SourceRecord> recordSourTwoDates(String user, String fromDate, String toDate){
        Criteria crite = sessionFactory.getCurrentSession().createCriteria(SourceRecord.class);
        crite.add(Restrictions.eq("sr_User", user)).addOrder( Order.asc("sr_Id"));
        crite.add(Restrictions.between("sr_Date", fromDate, toDate));
        List<SourceRecord> source_record = crite.list();
        return source_record;
    }
    
    @Override
    @Transactional
    public String sourceListExcel(XSSFWorkbook workbook) throws FileNotFoundException{
    		
    		System.out.println("-------------++download DaoImpl source idt++------------");
            String SuccessMes = "none";
            List<Source> listSL = sourceService.ListOfSource(getPrincipal());

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
             
            header.createCell(0).setCellValue("SourceId");
            header.getCell(0).setCellStyle(style);
            
            header.createCell(1).setCellValue("SourceDate");
            header.getCell(1).setCellStyle(style);
             
            header.createCell(2).setCellValue("SourceType");
            header.getCell(2).setCellStyle(style);
             
            header.createCell(3).setCellValue("SourceName");
            header.getCell(3).setCellStyle(style);
             
            header.createCell(4).setCellValue("SourceAmount");
            header.getCell(4).setCellStyle(style);
             
            header.createCell(5).setCellValue("SourceDescription");
            header.getCell(5).setCellStyle(style);
            // create data rows
            int rowCount = 1;
            DecimalFormat dff = new DecimalFormat("#.00"); 
            for (Source CRBook : listSL) {
                XSSFRow aRow = sheet.createRow(rowCount++);
                System.out.println(CRBook.getSourceName());
                aRow.createCell(0).setCellValue(CRBook.getSourceId());
                aRow.createCell(1).setCellValue(CRBook.getSourceDate());
                aRow.createCell(2).setCellValue(CRBook.getSourceType());
                aRow.createCell(3).setCellValue(CRBook.getSourceName());
                aRow.createCell(4).setCellValue(dff.format(CRBook.getSourceAmount()));
                aRow.createCell(5).setCellValue(CRBook.getSourceDescription());
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
            
            String FilePath = filename.concat("\\Source").concat(curDate);
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
    public String sourceRecordExcel(XSSFWorkbook workbook, String fromDate, String toDate) throws FileNotFoundException{
    		
    		System.out.println("-------------++download DaoImpl source record++------------");
            String SuccessMes = "none";
            List<SourceRecord> listSR = sourceService.recordSourTwoDates(getPrincipal(), fromDate, toDate);

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
             
            header.createCell(0).setCellValue("Sr_Id");
            header.getCell(0).setCellStyle(style);
            
            header.createCell(1).setCellValue("Sr_Date");
            header.getCell(1).setCellStyle(style);
             
            header.createCell(2).setCellValue("Sr_Name");
            header.getCell(2).setCellStyle(style);
             
            header.createCell(3).setCellValue("Sr_Deposits");
            header.getCell(3).setCellStyle(style);
             
            header.createCell(4).setCellValue("Sr_Balance");
            header.getCell(4).setCellStyle(style);
             
            header.createCell(5).setCellValue("Sr_Description");
            header.getCell(5).setCellStyle(style);
            // create data rows
            int rowCount = 1;
            DecimalFormat dfff = new DecimalFormat("#.00"); 
            for (SourceRecord SRBook : listSR) {
                XSSFRow aRow = sheet.createRow(rowCount++);
                System.out.println(SRBook.getSr_Name());
                aRow.createCell(0).setCellValue(SRBook.getSr_Id());
                aRow.createCell(1).setCellValue(SRBook.getSr_Date());
                aRow.createCell(2).setCellValue(SRBook.getSr_Name());
                aRow.createCell(3).setCellValue(dfff.format(SRBook.getSr_Deposits()));
                aRow.createCell(4).setCellValue(dfff.format(SRBook.getSr_Balance()));
                aRow.createCell(5).setCellValue(SRBook.getSr_Description());
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

            String FilePath = filename.concat("\\SourceRecord").concat(fnnn);
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
