/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.model.http.responseModel;
import lombok.*;
import org.springframework.http.HttpStatus;

/**
 *
 * @author makhlouf
 */


public class ApiResponse {
	
	private HttpStatus code;
	private String message;
	private Object response;
	public ApiResponse(HttpStatus code, String message, Object response) {
		super();
		this.code = code;
		this.message = message;
		this.response = response;
	}
	public ApiResponse() {
		
	}
	public HttpStatus getCode() {
		return code;
	}
	public void setCode(HttpStatus code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getResponse() {
		return response;
	}
	public void setResponse(Object response) {
		this.response = response;
	}
	
	
}
