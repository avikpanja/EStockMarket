package com.estock.company.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

	public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(message!=null) map.put("message", message);
		if(responseObj!=null) map.put("result", responseObj);
		if(status!=null) map.put("statusCode", status.value());
		
		return new ResponseEntity<Object>(map,status);
	}
}
