/**
 * 
 */
package com.hb.util;

/**
 * All rights reserved by RealSoftInc 2017
 * @author Mazaharul Haque
 * 
 */
public class ResponseBean {
	
	private  int statusCode;
	private  String message;
	private  String errMessage;
	private  Object result;
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrMessage() {
		return errMessage;
	}
	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
}
