package com.cap.biz.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

class ApiValidationError {
	@JsonProperty("object")
   private String object;
	
	@JsonProperty("field")
   private String field;
	@JsonProperty("rejectValue")
   private Object rejectedValue;
	
	@JsonProperty("message")
   private String message;

   ApiValidationError(String object, String message) {
       this.object = object;
       this.message = message;
   }

public ApiValidationError(String object, String field, Object rejectedValue, String message) {
	super();
	this.object = object;
	this.field = field;
	this.rejectedValue = rejectedValue;
	this.message = message;
}

@Override
public String toString() {
	return "ApiValidationError [object=" + object + ", field=" + field + ", rejectedValue=" + rejectedValue
			+ ", message=" + message + "]";
}
   
   
}