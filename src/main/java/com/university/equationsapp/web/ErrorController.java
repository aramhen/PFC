package com.university.equationsapp.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {
	
	private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);
	
	@ExceptionHandler(Exception.class)
	public String handleIOException(Exception ex) {
		logger.error("There has been an uncontrolled error", ex);
		return "fatalerror";
	}
}
