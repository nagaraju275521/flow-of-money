package com.vktechnology.naagu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import com.vktechnology.naagu.dao.GalleryDao;

public class GalleryServiceImpl implements GalleryService{
	
	@Autowired
	public GalleryDao galleryDao;

	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public String showGallery() {		
		return galleryDao.showGallery();
	}

	@Override
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public String saveGallery() {
		return galleryDao.saveGallery();
	}

	
}
