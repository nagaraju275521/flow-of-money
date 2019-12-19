package com.vktechnology.naagu.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.vktechnology.naagu.models.Signup;

public class SignupDaoImpl implements SignupDao{

	@Autowired
    private SessionFactory sessionFactory;

    public SignupDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void saveUser(Signup signup){
        System.out.println("--we r in save user");
        sessionFactory.getCurrentSession().save(signup);

    }

}
