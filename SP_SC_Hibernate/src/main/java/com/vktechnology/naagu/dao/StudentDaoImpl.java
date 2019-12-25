package com.vktechnology.naagu.dao;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vktechnology.naagu.models.Student;

public class StudentDaoImpl implements StudentDao{
	
	public static final Logger log = Logger.getLogger(StudentDaoImpl.class);
	
	private SessionFactory sessionFactory;
	
	public StudentDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public String addStudent(Student student){
		
		log.debug("In student save dao impl");
		return "nagaraju";
	}
}
