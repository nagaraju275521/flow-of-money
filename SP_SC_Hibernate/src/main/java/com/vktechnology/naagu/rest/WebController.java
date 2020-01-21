package com.vktechnology.naagu.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vktechnology.naagu.models.Student;
import com.vktechnology.naagu.service.StudentService;

@RestController
public class WebController {
	
	public static final Logger logger = Logger.getLogger(WebController.class);
	
	@Autowired
	private StudentService studentService;
	
	private static ArrayList<Student> al = new ArrayList<Student>();
 static {
         
	 	Student stu = new Student();
	 	stu.setRollNo(123);
	 	stu.setName("Dineh Rajput");	
         
	 	Student stu2 = new Student();
	 	stu2.setRollNo(123);
	 	stu2.setName("Dineh Rajput");
	 	al.add(stu);
	 	al.add(stu2);
         
     }
	
 	@RequestMapping(value = "/stu/", method = RequestMethod.GET)
 	public List<Student> listEmployees(){
 		return al;
 	}
	

}
