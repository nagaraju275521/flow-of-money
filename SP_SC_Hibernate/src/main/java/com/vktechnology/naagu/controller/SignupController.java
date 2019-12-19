package com.vktechnology.naagu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.vktechnology.naagu.dao.SignupDao;
import com.vktechnology.naagu.models.Signup;
import com.vktechnology.naagu.service.SignupService;

@Controller
public class SignupController extends MultiActionController{
	
	@Autowired
    private SignupService signupService;

	
    @RequestMapping(value = "/Register", method = RequestMethod.GET)
    public String Register() {
        System.out.println("-------------++Register++------------");
        return "redirect:/htmlpages/register.html";
    }
    

    @RequestMapping(value = "/addRegister", method = RequestMethod.GET)
   // @ResponseStatus(value= HttpStatus.OK)
    public ModelAndView AddPocket(@ModelAttribute("SpringWeb")Signup signup){
        System.out.println("-------------++addRegister++------------");
        signupService.saveUser(signup);
        System.out.println("save signup success");
        return new ModelAndView("redirect:/login");
    }

}
