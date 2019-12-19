package com.vktechnology.naagu.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vktechnology.naagu.models.Student;

public class StudentDaoImpl implements StudentDao{
	
	private SessionFactory sessionFactory;
	
	public StudentDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public String addStudent(Student student){
		
		return "nagaraju";
	}
}
