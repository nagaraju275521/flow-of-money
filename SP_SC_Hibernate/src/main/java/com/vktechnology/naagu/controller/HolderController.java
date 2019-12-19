package com.vktechnology.naagu.controller;

import com.google.gson.Gson;
import com.vktechnology.naagu.dao.HolderDao;
import com.vktechnology.naagu.dao.SourceDao;
import com.vktechnology.naagu.models.AllTransRecord;
import com.vktechnology.naagu.models.Expenses;
import com.vktechnology.naagu.models.Holder;
import com.vktechnology.naagu.models.HolderRecord;
import com.vktechnology.naagu.models.Source;
import com.vktechnology.naagu.service.HolderService;
import com.vktechnology.naagu.service.SourceService;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.security.Principal;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by HOME on 12/20/2015.
 */
@Controller
public class HolderController extends MultiActionController{


    @Autowired
    private HolderService holderService;
    @Autowired
    private SourceService sourceService;

    @RequestMapping(value = "/bank", method = RequestMethod.GET)
    public String bank() {
        System.out.println("-------------++bank++------------");
        return ("redirect:/htmlpages/bank.html");
    }
    
    @RequestMapping(value = "/allRecords", method = RequestMethod.GET)
    public String allRecords() {
        System.out.println("-------------++allRecords++------------");
        return "allRecords";
    }
    
    @RequestMapping(value = "/allTransRecord", method = RequestMethod.GET)
    public @ResponseBody String allTransRecord(HttpServletRequest request){
        List<AllTransRecord> allList = holderService.allTransList(getPrincipal());
        
        Gson gson = new Gson();
        String allListJS = gson.toJson(allList);
        System.out.println("---json allTransRecord List"+allListJS);
        return allListJS;  
    }


    @RequestMapping(value = "/HolderList", method = RequestMethod.GET)
    public @ResponseBody String bankList(HttpServletRequest request){
        List<Holder> holderList = holderService.BankList(getPrincipal());
        //ModelAndView model = new ModelAndView("bankResult");
        //model.addObject("BankList", listUs);
        //System.out.println("create list: "+holderService.BankList("sbi"));
        //return model;
        
        Gson gson = new Gson();
        String holderListJS = gson.toJson(holderList);
        System.out.println("---json holder List JS----"+holderListJS);
        return holderListJS;  
    }

    @RequestMapping(value = "/holderlistOnly", method = RequestMethod.GET)
    public @ResponseBody
    String sourceRe(HttpServletResponse response, HttpServletRequest request) throws Exception{
        System.out.println("-------------++source for holder++------------");
        List<String> onlysource = sourceService.sourceListOnly(getPrincipal());

        Gson gson = new Gson();
        String js = gson.toJson(onlysource);
        System.out.println("---only source for holder"+js);
        // return new ModelAndView("redirect:/htmlpages/source.html", "command", new Source());
        return js;
    }


    @RequestMapping(value = "/holderList", method = RequestMethod.GET)
    public ModelAndView holderList(HttpServletRequest request){
        List<HolderRecord> listUs = holderService.HolderList(getPrincipal());
        ModelAndView model = new ModelAndView("HolderResult");
        model.addObject("HolderList", listUs);
        System.out.println("create holder list: "+holderService.HolderList(getPrincipal()));
        return model;
    }
    
    @RequestMapping(value = "/holderRecord", method = RequestMethod.GET)
    public @ResponseBody String holderRecord(HttpServletRequest request, Principal principal){
    	System.out.println(":  HolderRecordr---:"+getPrincipal());
    	
    	List<HolderRecord> HolderRecord = holderService.HolderList(getPrincipal());

       // List<Source> listUs = sourceService.ListOfSource(getPrincipal());
       
        Gson gson = new Gson();
        String listRecordJs = gson.toJson(HolderRecord);
        System.out.println("---json Holder Record----"+listRecordJs);
        return listRecordJs;  
    }


    
    @RequestMapping(value="/addBank", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody String  addBank(HttpServletRequest request, HttpServletResponse response) throws Exception{	
    	String ddd = request.getParameter("child_selection");
    	System.out.println("------xxxxx-----"+ddd);
    	Holder holder = new Holder();
    	holder.setHolder_Date(request.getParameter("Holder_Date"));
    	holder.setHolder_Type(request.getParameter("parent_selection"));
    	holder.setHolder_Name(request.getParameter("child_selection"));
    	//holder.setHolder_Amount(Float.parseFloat(request.getParameter("Holder_Amount")));
    	BigDecimal bigDecimal = new BigDecimal(request.getParameter("Holder_Amount"));
    	holder.setHolder_Amount(bigDecimal);
    	holder.setHolder_Description(request.getParameter("Description"));
    	holder.setHolder_Source(request.getParameter("Holder_Source"));
    	holder.setHolder_User(getPrincipal());
    	
        
        String Message = holderService.saveBank(holder);
        
        holderService.saveBankList(holder);
        
        System.out.println("save bank success");
        Gson gson = new Gson();
        String forT123 = gson.toJson(Message);
        return forT123;
    }
    @RequestMapping(value = "/loadHolderNames", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody String loadHolderNames(HttpServletRequest request, @RequestParam("banktype") String banktype) {
        System.out.println(banktype+"-------------++ loadHolderNames++------------");
        
        System.out.println("-- banktype------:-"+getPrincipal());
        List<String> holderList = holderService.loadHolderComplteList(getPrincipal());
        Gson gson = new Gson();
        String holder_List = gson.toJson(holderList);
        System.out.println("holder_List : "+holder_List);
        return holder_List;   
        }
    
@RequestMapping(value = "/availabilityHolderName", method = RequestMethod.GET, produces="application/json")
  public @ResponseBody String availabilityHolderName(HttpServletRequest request, @RequestParam("holdertype") String holder_type, @RequestParam("holdername") String holder_name) {
        System.out.println(holder_type+"-------------++ availabilityHolderName++------------"+holder_name);
            System.out.println("-- banktype------:-"+getPrincipal());
            List<String> holderNameCheck = holderService.availability_Holder_Name(getPrincipal(), holder_type, holder_name);
            Gson gson = new Gson();
            String holder_List_check = gson.toJson(holderNameCheck);
            System.out.println("holder_List : "+holder_List_check);
            return holder_List_check;   
            }
@RequestMapping(value = "/amountAvailabilityForHolder", method = RequestMethod.GET, produces="application/json")
public @ResponseBody String amountAvailabilityForHolder(HttpServletRequest request, @RequestParam("source") String Asource, @RequestParam("amount") String Aamount) {
    System.out.println(Asource+"-------------++ amountAvailabilityForHolder++------------"+Aamount);
    //float amountA = Float.parseFloat(Aamount);
    BigDecimal amountA = new BigDecimal(Aamount);
    System.out.println("CREATE -- create list------:-"+getPrincipal());
    String available = holderService.availablityCheckForHolder(getPrincipal(), Asource, amountA);
    Gson gson = new Gson();
    String forTwoDate = gson.toJson(available);
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
