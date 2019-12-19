package com.vktechnology.naagu.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

@Controller
public class StudentController{
	
	private static final Logger logger = Logger.getLogger(StudentController.class);
	
	@RequestMapping(value = "/openStudentResult", method = RequestMethod.GET)
    public String credit() {
        logger.info("-------------++ student ++------------");
        String showpath = "redirect:/htmlpages/student.html";
        return showpath;
    }

}
