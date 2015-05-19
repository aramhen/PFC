package com.university.equationsapp.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ErrorController {

	private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);

	@ExceptionHandler(NoHandlerFoundException.class)
	public String handleError404(Exception e) {
		logger.error("There has been a request of an unknow page", e);
		return "error/error404";
	}

	@ExceptionHandler(Exception.class)
	public String handleUncontrolledException(Exception ex) {
		logger.error("There has been an uncontrolled error", ex);
		return "error/errorfatal";
	}
}
