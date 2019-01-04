package com.rest.hellowebservice.Udemy.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rest.hellowebservice.Udemy.user.UserNotFoundException;

@RestControllerAdvice //this exceptions will share all across the contoller 
@RestController  //it gives response
public class CustomizedResponseEntity extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		
    ExceptionResponseFormat exceptionResponse=new ExceptionResponseFormat(new Date(),ex.getMessage(),request.getDescription(false));
    //sample  added
   return new ResponseEntity(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleAllExceptions(UserNotFoundException ex, WebRequest request) {
		
    ExceptionResponseFormat exceptionResponse=new ExceptionResponseFormat(new Date(),ex.getMessage(),request.getDescription(false));
    
   return new ResponseEntity(exceptionResponse,HttpStatus.NOT_FOUND);
	}
	
    @Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    	ExceptionResponseFormat exceptionResponse=new ExceptionResponseFormat(new Date(),"not valid",ex.getBindingResult().toString());
        
    	   return new ResponseEntity(exceptionResponse,HttpStatus.BAD_REQUEST);
	}
	
}