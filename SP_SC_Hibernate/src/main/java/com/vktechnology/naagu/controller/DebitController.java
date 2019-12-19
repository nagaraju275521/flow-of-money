package com.vktechnology.naagu.controller;

import java.math.*;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.google.gson.Gson;
import com.vktechnology.naagu.dao.CreditDao;
import com.vktechnology.naagu.models.Credit;
import com.vktechnology.naagu.models.CreditClearRecord;
import com.vktechnology.naagu.models.Debit;
import com.vktechnology.naagu.models.DebitClearRecord;
import com.vktechnology.naagu.models.DebitRecord;
import com.vktechnology.naagu.models.Expenses;
import com.vktechnology.naagu.service.CreditService;
import com.vktechnology.naagu.service.DebitService;


@Controller
public class DebitController extends MultiActionController{
	

	    @Autowired
	    private DebitService debitService;


	    @RequestMapping(value = "/debit", method = RequestMethod.GET)
	    public String credit() {
	        System.out.println("-------------++credit++------------");
	        return debitService.showDebit();
	    }
	    
	    @RequestMapping(value = "/openDebitClear", method = RequestMethod.GET)
	    public String openDebitClear() {
	        System.out.println("-------------++open Debit Clear++------------");
	        return debitService.debitClearPage();
	    }
	    
	    @RequestMapping(value = "/openDebitClearRecord", method = RequestMethod.GET)
	    public String openDebitClearRecord() {
	        System.out.println("-------------++open openDebitClearRecord++------------");
	        return debitService.showdebitClearRpage();
	    }
	    
	    @RequestMapping(value = "/debitList", method = RequestMethod.GET)
	    public @ResponseBody String debitList(HttpServletRequest request){
	        
	        System.out.println("debitList -- debitList ------:-"+getPrincipal());
	        List<Debit> listUsDa = debitService.listOfDebit(getPrincipal());
	        System.out.println("debit list:;;;;"+listUsDa);
	        
	        Gson gson = new Gson();
	        String listRecordJ = gson.toJson(listUsDa);
	        System.out.println("---json debit record----"+listRecordJ);
	        return listRecordJ;  
	    }
	    
	    @RequestMapping(value = "/debiterNamesList", method = RequestMethod.GET)
	    public @ResponseBody String crediterNamesList(HttpServletResponse response, HttpServletRequest request) throws Exception{
	        System.out.println("-------------++ajax debiterNamesList call++------------");
	        List<String> onlysource = debitService.debiterList(getPrincipal());
	        Gson gson = new Gson();
	        String jss = gson.toJson(onlysource);
	        System.out.println("---only holderNameListOnly"+jss);
	        return jss;
	    }
	    
	    @RequestMapping(value="/saveDebit", method = RequestMethod.GET, produces="application/json")
	    public @ResponseBody String  saveDebit(HttpServletRequest request, HttpServletResponse response) throws Exception{
	    	String MessageRecord = "none";
	    	String BindTwo = "none";
	    	Debit debit = new Debit();
	    	debit.setDebit_Date(request.getParameter("Debit_Date"));
	    	debit.setDebit_Name(request.getParameter("Debit_name"));
	    	//debit.setDebit_Amount(Float.parseFloat(request.getParameter("Debit_Amount")));
	    	debit.setDebit_Amount(new BigDecimal(request.getParameter("Debit_Amount")));
	    	debit.setDebit_Source(request.getParameter("Debit_Source"));
	    	debit.setDebit_Description(request.getParameter("Debit_Description"));
	    	debit.setUser(getPrincipal());
	    	String Message = debitService.saveDebitData(debit);
	        System.out.println("save debit success--"+Message);
	        
	        if(Message.indexOf("Success") > -1){
	        	MessageRecord = debitService.saveDebitRecord(debit);
	        	System.out.println("--------save debit record----");
	        }
	        if(MessageRecord.indexOf("Success") > -1){
	        	BindTwo = Message.concat("RecordSave");
	        	System.out.println("--------save debit record----"+BindTwo);
	        }
	        Gson gson = new Gson();
	        String forT12 = gson.toJson(BindTwo);
	        return forT12;
	    }
	    
	    @RequestMapping(value = "/debitRecord", method = RequestMethod.GET)
	    public @ResponseBody String debitRecord(HttpServletRequest request){
	        
	        System.out.println("debitRecord -- debitRecord ------:-"+getPrincipal());
	        List<DebitRecord> listRecord = debitService.recordOfDebit(getPrincipal());
	        System.out.println("debit record:;;;;"+listRecord);
	        Gson gson = new Gson();
	        String listRecordJs = gson.toJson(listRecord);
	        System.out.println("---json debit record----"+listRecordJs);
	        return listRecordJs;  
	       // return model;
	    }
	    
	    @RequestMapping(value = "/ClearRecordForDebit", method = RequestMethod.GET)
	    public @ResponseBody String CreditClearRecord(HttpServletRequest request){	        
	        System.out.println(" -- ClearRecordForDebit ------:-"+getPrincipal());
	        List<DebitClearRecord> recordDCR = debitService.debitClearRecord(getPrincipal());
	        System.out.println("debit clear Record s :"+recordDCR);	        
	        Gson gson = new Gson();
	        String jsDCR = gson.toJson(recordDCR);
	        System.out.println("---only credit Record"+jsDCR);
	        return jsDCR;
	    }
	    
	    @RequestMapping(value = "/saveDebitClearDynamic", method = RequestMethod.GET, produces="application/json")
	    public @ResponseBody String amountAvailabilityForHolder(HttpServletRequest request, 
	    		@RequestParam("ClearDebitDate") String CDdate, 
	    		@RequestParam("Debit_ClearName") String CDname,
	    		@RequestParam("Debit_ClearAmount") String CDamount,
	    		@RequestParam("Debit_ClearSource") String CDsource,
	    		@RequestParam("Debit_ClearDes") String CDdesc) {
	    	String message = "null";
	        System.out.println(CDname+"-------------++ saveDebitClearDynamic++------------"+CDamount);
	        //float CDFamount = Float.parseFloat(CDamount);
	        BigDecimal CDFamount = new BigDecimal(CDamount);
	        System.out.println("CREATE -- create list------:-"+getPrincipal());
	        if(CDname.equalsIgnoreCase(CDsource)){
	        	message = "Debiter name and source name should not be same.";
	        }else{
	        message = debitService.debitClearDynamic(CDname, CDFamount, CDsource, getPrincipal());
	        	if("Success".equalsIgnoreCase(message)){
	        DebitClearRecord DCRecord = new DebitClearRecord();
	        DCRecord.setDebitClear_Date(CDdate);
	        DCRecord.setDebitClear_Amount(CDFamount);
	        DCRecord.setDebitClear_name(CDname);
	        DCRecord.setDebitClear_Source(CDsource);
	        DCRecord.setDebitClear_Des(CDdesc);
	        DCRecord.setDebitClear_User(getPrincipal());
	        message = debitService.saveDebitClearRecord(DCRecord);
	        	}else{
	        		message = "There was a problem with saving debit clear data";
	        	}
	        }
	        Gson gson = new Gson();
	        String forTwoDate = gson.toJson(message);
	        System.out.println("message: "+forTwoDate);
	        return forTwoDate;   
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
