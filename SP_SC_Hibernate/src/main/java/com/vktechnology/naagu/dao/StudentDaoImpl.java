package com.vktechnology.naagu.dao;

import java.io.File;
import java.io.FileReader;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import com.vktechnology.naagu.models.Student;

public class StudentDaoImpl implements StudentDao{

	private static final Logger logger = Logger.getLogger(StudentDaoImpl.class);
	
	private SessionFactory sessionFactory;
	
	public StudentDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	{System.out.println("call constr");}
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	@Transactional
	public String addStudent(Student student){
		try{
		int num = Integer.parseInt("22") ;  
		
		logger.debug("------------inside--------------");
		logger.info("------------insideinfo--------------"+num);
		long a = (Long) sessionFactory.getCurrentSession().save(student);
		return a+"";
		
		}catch(NumberFormatException e){
			throw new NumberFormatException();
		}
		
	}
}
