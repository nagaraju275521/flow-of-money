package com.vktechnology.naagu.dao;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

public class GalleryDaoImpl implements GalleryDao{
	
private static final Logger logger = Logger.getLogger(GalleryDaoImpl.class);
	
	private SessionFactory sessionFactory;
	
	public GalleryDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public String showGallery() {
		return null;
	}

	@Override
	public String saveGallery() {
		
		return null;
	}
	
	

	
}
