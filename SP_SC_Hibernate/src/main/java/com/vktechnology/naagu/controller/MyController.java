package com.vktechnology.naagu.controller;

import javax.servlet.http.HttpServletRequest;

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
import com.vktechnology.naagu.service.DebitService;

/**
 * Created by HOME on 11/21/2015.
 */
@Controller
public class MyController extends MultiActionController {
	
	@Autowired
	private DebitService debitService;
	
	private static final Logger logger = Logger.getLogger(MyController.class);

    @RequestMapping(value = "/HeaderAfter", method = RequestMethod.GET)
    public String header(){
        return "redirect:/htmlpages/headerAfter.html";
    }

    @RequestMapping(value = "/footer", method = RequestMethod.GET)
      public String footer(){
        return "footer";
    }
        
    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String menu(){
        return "redirect:/htmlpages/menu.html";
    }
    
    
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(){
        return "redirect:/htmlpages/welcome.html";
    }
    
    @RequestMapping(value = "/sessionRedir", method = RequestMethod.GET)
    public String sessionRedir(){
        return "sessionRedirect";
    }
    
    @RequestMapping(value = "/homePage", method = RequestMethod.GET)
    public String homePage(){
        return "redirect:/htmlpages/HomePage.html";
    }
    
    @RequestMapping(value = "/openDebitRecords", method = RequestMethod.GET)
    public String openDebitRecords(){
      return "debitRecords";
    }
    
    @RequestMapping(value = "/openSourceList", method = RequestMethod.GET)
    public String openSourceList(){
        return "sourceResult";
    }
    
    @RequestMapping(value = "/openHolderList", method = RequestMethod.GET)
    public String openHolderList(){
      return "bankResult";
    }
    
    @RequestMapping(value = "/openCreditList", method = RequestMethod.GET)
    public String openCreditList(){
      return "creditResult";
    }
    
    @RequestMapping(value = "/openDebitList", method = RequestMethod.GET)
    public String openDebitList(){
      return "debitResult";
    }
    
    @RequestMapping(value = "/jqueryPlugins", method = RequestMethod.GET)
    public String jqueryPlugins(){
      return "redirect:/htmlpages/jqueryPlugins.html";
    }
    
    @RequestMapping(value = "/openCreditRecord", method = RequestMethod.GET)
    public String openCreditRecord(){
      return "creditRecords";
    }
    
    @RequestMapping(value = "/openCreditClearRecord", method = RequestMethod.GET)
    public String openCreditClearRecord(){
      return "creditClearRecord";
    }
    
    @RequestMapping(value = "/openSourceRecord", method = RequestMethod.GET)
    public String openSourceRecord(){
      return "sourceRecord";
    }
    
    @RequestMapping(value = "/openExportPage", method = RequestMethod.GET)
    public String openExportPage(){
      return "excelExportSend";
    }
    
    @RequestMapping(value = "/serverSideSecurity", method = RequestMethod.GET)
    public @ResponseBody String serverSideSecurity(HttpServletRequest request){
    	System.out.println("----------serverSideSecurity----------");
    	String SSSecurity = debitService.securityForClintSide();
    	
    	Gson gson = new Gson();
        String SSecurity = gson.toJson(SSSecurity);
      return SSecurity;
    }
    
    
    @RequestMapping(value = "/displyUserOnHeader", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody String displyUserOnHeader(HttpServletRequest request, @RequestParam("dle_Id") String dle_Id) {
        System.out.println(dle_Id+"-------------++ displyUserOnHeader++------------");
        
        logger.debug("debug message");
        logger.error("error message");
        logger.trace("Trace Message!");
        logger.info("Info Message!");
        logger.warn("Warn Message!");
        logger.fatal("Fatal Message!");
        
        String available = getPrincipal();
        Gson gson = new Gson();
        String username = gson.toJson(available);
        System.out.println("current user: "+username);
        return username; 
        
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
