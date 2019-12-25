package com.vktechnology.naagu.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.vktechnology.naagu.models.Student;
import com.vktechnology.naagu.service.StudentService;

@Controller
public class StudentController{
	
	private static final Logger logger = Logger.getLogger(StudentController.class);
	
	@Autowired
	private StudentService studentService;
	
	
	@RequestMapping(value = "/openStudentResult", method = RequestMethod.GET)
    public ModelAndView openStudent() {
		ModelAndView model  = new ModelAndView();
        logger.info("-------------++ student ++------------");
        Student s = new Student();
        s.setName("naagu");
        s.setRollNo(100);
        String showpath = studentService.addStudent(s);
        model.addObject("names", showpath);
        model.setViewName("student");
        return model;
    }

}
