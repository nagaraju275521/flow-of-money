package com.vktechnology.naagu.dao.login;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.stereotype.Repository;

import com.vktechnology.naagu.models.login.Users;


@Repository("loginDao")
public class LoginDaoImpl implements LoginDao{
	
	@Autowired
	SessionFactory sessionFactory;
	
	public LoginDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	Session session = null;
	Transaction tx = null;

	@Override
	@Transactional
	public Users findByUserName(String username){
		session = sessionFactory.openSession();
		tx = session.getTransaction();
		session.beginTransaction();
		Users user = (Users) session.load(Users.class, new String(username));
		tx.commit();
		System.out.println("-------"+user);
		return user;
	}

}
