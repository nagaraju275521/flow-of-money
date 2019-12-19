package com.vktechnology.naagu.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.vktechnology.naagu.models.login.Users;

@Controller
public class LoginController {

	@RequestMapping(value = { "/", "/home" })
	public String getUserDefault() {
		return "home";
	}

	@RequestMapping("/login")
	public ModelAndView getLoginForm(@ModelAttribute Users users) {

		 
		return new ModelAndView("login");
	}

	@RequestMapping(value="/loginshow", method = RequestMethod.GET)
	public ModelAndView loginshow(@ModelAttribute Users users, @RequestParam("eee") String eee) {
		ModelAndView model = new ModelAndView();
		model.addObject("message", eee);
		 model.setViewName("login");
		return model;
	}
	
	@RequestMapping("/admin**")
	public String getAdminProfile() {
		return "user";
	}
	
	@RequestMapping("/user**")
	public String getUserProfile() {
		System.out.println("user name princi :"+getPrincipal());
		return "user";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
    public @ResponseBody ModelAndView logoutPage (HttpServletRequest request, HttpServletResponse response, 
    		@RequestParam("AccessDenied") String accessDeniedValue, @ModelAttribute Users users) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        System.out.println("----------logoooout"+accessDeniedValue);
        ModelAndView model = new ModelAndView();
        if("logedout".equalsIgnoreCase(accessDeniedValue)){
        	 model.addObject("message", "logoutsuccess");
        	 //model.setViewName("redirect");
        }else if("AccessDeniedError".equalsIgnoreCase(accessDeniedValue)){
        	model.addObject("message", "accessDeniedError");
        	
        }else if("SessionDeniedError".equalsIgnoreCase(accessDeniedValue)){
        	model.addObject("message", "sessionDeniedError");
        	
        }
        
        model.setViewName("redirect");
        
        return model;
    }

	 
	
	 @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	    public String accessDeniedPage(ModelMap model) {
	        model.addAttribute("user", getPrincipal());
	        return "redirect:/logout?AccessDenied=AccessDeniedError";
	    }
	 
	 @RequestMapping(value = "/Session_Invalid", method = RequestMethod.GET)
	    public String sessionDeniedPage(ModelMap model) {
	        model.addAttribute("user", getPrincipal());
	       // model.addAttribute("session_invalid", "Session_Invalid_Error");
	        return "redirect:/logout?AccessDenied=SessionDeniedError";
	    }
	 
	 @RequestMapping(value = "/Concurency_Invalid", method = RequestMethod.GET)
	    public String concurencyDeniedPage(ModelMap model) {
	        model.addAttribute("user", getPrincipal());
	        model.addAttribute("concurency_invalid", "Concurency_Invalid_Error");
	        return "accessDenied";
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
