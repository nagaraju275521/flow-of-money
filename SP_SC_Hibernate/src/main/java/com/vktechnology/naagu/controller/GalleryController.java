package com.vktechnology.naagu.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.vktechnology.naagu.models.Gallery;
import com.vktechnology.naagu.service.GalleryService;

@Controller
public class GalleryController extends MultiActionController{
	
	private static final Logger logger = Logger.getLogger(GalleryController.class);
	
	@Autowired
	public GalleryService galleryService;
	
	@RequestMapping(value = "/galleryPage")
	public ModelAndView showGallery(){
		ModelAndView model = new ModelAndView();
		model.setViewName("Gallery");
		logger.info("--------gallery---------");
		return model;
	} 
	
	@RequestMapping(value = "/saveGallery", method = RequestMethod.POST)
	public @ResponseBody ModelAndView savegal(HttpServletResponse response, HttpServletRequest request, 
    		@RequestParam("file") MultipartFile file) throws MultipartException, IOException{
		logger.info("--------xxxxxxxxxx---------"+file.getOriginalFilename());
		byte[] bytes = file.getBytes();
		long size = file.getSize();
		ModelAndView mod = new ModelAndView();
		mod.setViewName("Gallery");
		
		logger.info("------size----------"+size);
		logger.info("-------Name---------"+file.getName());
		logger.info("----------save gallery----------"+bytes);
		//MultipartFile bytes = gallery.getBytes();
		
		return mod;
	}
	

}
