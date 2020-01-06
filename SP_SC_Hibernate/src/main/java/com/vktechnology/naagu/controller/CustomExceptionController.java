package com.vktechnology.naagu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.vktechnology.naagu.models.AppException;

@ControllerAdvice
public class CustomExceptionController {
	
	public static final Logger logger = Logger.getLogger(CustomExceptionController.class);
	
	@ExceptionHandler({
		NullPointerException.class, 
		ArrayIndexOutOfBoundsException.class, 
		IOException.class, 
		ArithmeticException.class, 
		StringIndexOutOfBoundsException.class, 
		NumberFormatException.class
		})
	public ModelAndView handleException(Exception e)
	{
		ModelAndView model = new ModelAndView();
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		AppException v = new AppException("OW00077", sw.toString(), null);
		model.addObject("ob", v);
		model.setViewName("Exceptions");
		logger.info("-------------++@@ControllerAdvice++------------");			
		return model;
	}

}
