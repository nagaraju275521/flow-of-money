package com.vktechnology.naagu.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.vktechnology.naagu.models.Credit;
import com.vktechnology.naagu.models.CreditRecord;
import com.vktechnology.naagu.models.Dairy;

public class DairyDaoImpl implements DairyDao{
	
	@Autowired
    private SessionFactory sessionFactory;
	
    public DairyDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	private static final Logger logger = Logger.getLogger(DairyDaoImpl.class);
	
	
	
	public String showDairy(){
		String pagename = "dairy";
		logger.info("return page name :"+pagename);
		return pagename;
	}
	
	@SuppressWarnings("unchecked")
    @Override
    @Transactional
    public String saveDairy(Dairy dairy){
		logger.info("in dao");
		sessionFactory.getCurrentSession().save(dairy);
		return "Success";
	}
	
    @SuppressWarnings("unchecked")
   	@Override
    @Transactional
    public List<Dairy> resultOfDairy(String user){
        Criteria crite = sessionFactory.getCurrentSession().createCriteria(Dairy.class);
        crite.add(Restrictions.eq("dairyUser", user)).addOrder( Order.asc("dairyDate"));
        List<Dairy> dairyResult = crite.list();
        return dairyResult;

    }

}
