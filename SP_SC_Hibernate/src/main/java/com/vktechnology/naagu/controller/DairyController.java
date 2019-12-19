package com.vktechnology.naagu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.google.gson.Gson;
import com.vktechnology.naagu.models.CreditRecord;
import com.vktechnology.naagu.models.Dairy;
import com.vktechnology.naagu.service.DairyService;

@Controller
public class DairyController extends MultiActionController{
	
	@Autowired
	private DairyService dairyService;
	
	private static final Logger logger = Logger.getLogger(DairyController.class);
	
	@RequestMapping(value = "/dairy", method = RequestMethod.GET)
    public String credit() {
        logger.info("--------dairy----------");
        return dairyService.showDairy();
    }
	
	@RequestMapping(value = "/saveDairy", method = RequestMethod.GET)
	public @ResponseBody String clearLentAmount(HttpServletResponse response, HttpServletRequest request, 
	    		@RequestParam("Dairy_Date") String dairy_Date,
	    		@RequestParam("Dairy_Des") String dairy_Des) throws Exception{
		logger.info("-------------save Dairy---------------");
		Dairy dairy = new Dairy();
		dairy.setDairyDate(dairy_Date);
		dairy.setDairyDes(dairy_Des);
		dairy.setDairyUser(getPrincipal());
		String Message = dairyService.saveDairy(dairy);
        Gson gson = new Gson();
        String jsMes = gson.toJson(Message);
        return jsMes;
	}
	
	@RequestMapping(value = "/dairyResult", method = RequestMethod.GET)
    public @ResponseBody String creditRecord(HttpServletRequest request){
        logger.info("--------dairyResult---------");
        List<Dairy> record = dairyService.resultOfDairy(getPrincipal());        
        Gson gson = new Gson();
        String js = gson.toJson(record);
        logger.info("json object :"+js);
        return js;
    }
	
	@RequestMapping(value = "/openDairyResult", method = RequestMethod.GET)
    public String openDairyResult() {
        logger.info("--------/openDairyResult----------");
        return "DairyResultBest";
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
