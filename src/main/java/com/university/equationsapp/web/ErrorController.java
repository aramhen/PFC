package com.university.equationsapp.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * This class is a ControllerAdvice If any of the exceptions handled here are thrown in any place, they will be handled
 * here and we can redirect where we want
 *
 */
@ControllerAdvice
public class ErrorController {

	private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);

	/**
	 * Handle 404 error
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	public String handleError404(Exception e) {
		logger.error("There has been a request of an unknow page", e);
		return "error/error404";
	}

	/**
	 * Handle uncontrolled exceptions
	 */
	@ExceptionHandler(Exception.class)
	public String handleUncontrolledException(Exception ex) {
		logger.error("There has been an uncontrolled error", ex);
		return "error/errorfatal";
	}
}
