package com.yesid.roulette.infrastructure.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

	private final Logger log = LoggerFactory.getLogger(ExceptionController.class);
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Object> error(RuntimeException excepcion, WebRequest request) {
		String bodyOfResponse = excepcion.getMessage();
		log.error("Error: ", excepcion);
        return handleExceptionInternal(excepcion, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);		
	}
}
