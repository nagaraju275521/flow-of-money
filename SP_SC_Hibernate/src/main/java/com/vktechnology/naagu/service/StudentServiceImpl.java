package com.vktechnology.naagu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.vktechnology.naagu.dao.StudentDao;
import com.vktechnology.naagu.models.Student;

public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentDao studentDao;
	
	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public String addStudent(Student student){
		return studentDao.addStudent(student);
	}

}
