package com.cap.biz.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cap.biz.repositoryImpl.ProductNotFoundException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	
	 @Autowired
	    private ApiError apiError;
	  
	 
	/* @RequestMapping(produces = "application/json")
	 @ExceptionHandler(Exception.class)
	 public  ResponseEntity<?> defaultExceptionHandler()
	 {
		 apiError.setMessage("invalid request");
		 return new ResponseEntity<>(apiError,apiError.getStatus());
		 
	 }*/
	 
	 
	    @RequestMapping(produces = "application/json")
	    @ExceptionHandler(ProductNotFoundException.class)
	    public ResponseEntity<?> handleException(Throwable ex) {
	    	// String error = "Malformed JSON request";
	    	 apiError.setStatus(HttpStatus.NOT_FOUND);
	    	 apiError.setDebugMessage(ex.toString());
	    	 apiError.setMessage(ex.getMessage());
	    	 
	    	/*StackTraceElement[] stack=  ex.getStackTrace();
	    	for (StackTraceElement stackTraceElement : stack) {
				
				System.out.println(stackTraceElement.getFileName());
				System.out.println(stackTraceElement.getClassName());
				System.out.println(stackTraceElement.getMethodName());
				System.out.println(stackTraceElement.getLineNumber());
			}
	    	 System.out.println(ex.getStackTrace().toString());*/
	    	return new ResponseEntity<>(apiError, apiError.getStatus());
	        // return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, error, ex));
	    }
	    
	    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
	        return new ResponseEntity<>(apiError, apiError.getStatus());
	    }
	    


	    
	    @Override
	    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	        HttpHeaders headers, HttpStatus status, WebRequest request) {
	    	 apiError.setStatus(HttpStatus.BAD_REQUEST);
	    	 apiError.setDebugMessage("Validation Failed");
	    	 
	    	List<ApiValidationError> errors= new ArrayList<ApiValidationError>();
	    	for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
	    		
	            errors.add(new ApiValidationError(error.getObjectName(), error.getField(), error.getRejectedValue(), error.getDefaultMessage()));
	        }
	    	
	   // apiError.setMessage(ex.getBindingResult().toString());
	    	
	    //	 apiError.setMessage(ex.getLocalizedMessage());
	    //	apiError.setMessage(ex.getLocalizedMessage());
	    	 apiError.setMessage("validation error");
	    	 apiError.setSubErrors(errors);
	    	 return new ResponseEntity<>(apiError, apiError.getStatus());
	         
	    } 
	    
  /* @Override
   @ExceptionHandler(ProductNotFoundException.class)
   protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
       String error = "Malformed JSON request";
       return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
   }

   private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
       return new ResponseEntity<>(apiError, apiError.getStatus());
   }*/

   //other exception handlers below

	    
	    
}