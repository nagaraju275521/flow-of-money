package com.vktechnology.naagu.controller;

import com.google.gson.Gson;
import com.vktechnology.naagu.dao.SourceDao;
import com.vktechnology.naagu.models.Source;
import com.vktechnology.naagu.models.SourceRecord;
import com.vktechnology.naagu.service.SourceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

/**
 * Created by HOME on 12/15/2015.
 */
@Controller
public class SourceController extends MultiActionController {

    @Autowired
    private SourceDao sourceDao;
    
    @Autowired
    private SourceService sourceService;


    @RequestMapping(value = "/source", method = RequestMethod.GET)
    public ModelAndView source(HttpServletResponse response, HttpServletRequest request) {
        System.out.println("-------------++source++------------");
        System.out.println("---only source");
        return sourceService.showSource();
    }

    @RequestMapping(value = "/sourceRe", method = RequestMethod.GET)
    public @ResponseBody String sourceRe(HttpServletResponse response, HttpServletRequest request) throws Exception{
        System.out.println("-------------++reeeeeee++------------");
        List<String> onlysource = sourceService.sourceNamesList(getPrincipal());

        Gson gson = new Gson();
        String js = gson.toJson(onlysource);
        System.out.println("---only source"+js);
        return js;
    }

    
    @RequestMapping(value = "/sourceList", method = RequestMethod.GET)
    public @ResponseBody String expensesList(HttpServletRequest request, Principal principal){
    	System.out.println(":  exist user---:"+getPrincipal());

        List<Source> listUs = sourceService.ListOfSource(getPrincipal());
        Gson gson = new Gson();
        String listRecordJs = gson.toJson(listUs);
        System.out.println("---json debit record----"+listRecordJs);
        return listRecordJs;  
    }

    
    @RequestMapping(value="/addSource", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody String  addSource(HttpServletRequest request, HttpServletResponse response) throws Exception{	
    	Source source = new Source();
    	source.setSourceDate(request.getParameter("SourceDate"));
    	source.setSourceType(request.getParameter("SourceType"));
    	source.setSourceName(request.getParameter("SourceName"));
    	//source.setSourceAmount(Float.parseFloat(request.getParameter("Amount")));
    	source.setSourceAmount(new BigDecimal(request.getParameter("Amount")));
    	source.setSourceDescription(request.getParameter("Description"));
   	 		source.setUser(getPrincipal());               // add user name to expenses object
   	 		String Message = sourceService.saveSource(source);
   	 		
   	 	if(Message.indexOf("Success") > -1){
   	 		sourceService.saveSourceRecord(source);
   	 		System.out.println("------save Source Record-------");
   	 	}
   	 		Gson gson = new Gson();
   	 		String forT456 = gson.toJson(Message);
   	 		return forT456;
}
    
    @RequestMapping(value = "/sourceRecord", method = RequestMethod.GET)
    public @ResponseBody String sourceRecord(HttpServletRequest request, Principal principal){
    	List<SourceRecord> sourceRe = sourceService.recordOfSource(getPrincipal());
    	Gson gson = new Gson();
        String listRecordJs = gson.toJson(sourceRe);
        System.out.println("---json source Record ----"+listRecordJs);
        return listRecordJs;
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
