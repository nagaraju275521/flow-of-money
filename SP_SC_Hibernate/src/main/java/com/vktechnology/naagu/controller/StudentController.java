package com.vktechnology.naagu.controller;

import java.io.IOException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vktechnology.naagu.models.AppException;
import com.vktechnology.naagu.models.Student;
import com.vktechnology.naagu.service.StudentService;
import org.springframework.web.client.RestTemplate;

@Controller
public class StudentController{
	
	private static final Logger logger = Logger.getLogger(StudentController.class);
	
	@Autowired
	private StudentService studentService;
	
	
	@RequestMapping(value = "/openStudentResult", method = RequestMethod.GET)
	public ModelAndView openStudent() {
		ModelAndView model = new ModelAndView();
		try {

			logger.debug("Log4jExample: A Sample Debug Message");
			logger.info("Log4jExample: A Sample Info  Message");
			logger.warn("Log4jExample: A Sample Warn  Message");
			logger.error("Log4jExample: A Sample Error Message");
			logger.fatal("Log4jExample: A Sample Fatal Message");
			logger.info("-------------++ student ++------------");
			Student s = new Student();
			s.setName("naagu");
			s.setRollNo(100/1);
			String showpath = studentService.addStudent(s);
			model.addObject("names", showpath);
			model.setViewName("student");
		} catch (ArithmeticException e) {
			throw new ArithmeticException();
		}
		return model;
	}
	
	@RequestMapping(value = "/exceone", method = RequestMethod.GET)
	public ModelAndView exExce() {
		ModelAndView model = new ModelAndView();
		
			int s = 100/0;
			int x = s+10;
			logger.info("-------x value--------"+x);
			model.setViewName("student");
		
		return model;
	}
	
	@RequestMapping(value = "/excetwo", method = RequestMethod.GET)
	public ModelAndView exExceTwo() {
		ModelAndView model = new ModelAndView();
		try {
			String s = "abcd";
			char x = s.charAt(5);
			logger.info("-------x value--------"+x);
		}catch(Exception e){
			throw new StringIndexOutOfBoundsException();
		}finally{
			logger.info("-------finally--------");
		}
		return model;
	}
	
	@RequestMapping(value = "/clientrest", method = RequestMethod.GET)
	public void clientCode() {
		RestTemplate re = new RestTemplate();
		String u = "http://localhost:8080/FlowOfMoney/stu/";
		@SuppressWarnings("unchecked")
		List<Student> s = re.getForObject(u, List.class);
		logger.info(s.size());
		logger.info("-------------client--------------"+s);
	}
	

	@RequestMapping(value = "/clientSchool", method = RequestMethod.GET)
	public ModelAndView clientSchool() {
		ModelAndView model = new ModelAndView();
		RestTemplate re = new RestTemplate();
		String u = "http://192.168.1.15:8084/SpringMVCHibernateManyToManyCRUDExample/usi";
		Student s = re.getForObject(u, Student.class);
		model.setViewName("student");
		logger.info("-------------client---school-----------"+s.getName());
		return model;
	}
	
	

}
