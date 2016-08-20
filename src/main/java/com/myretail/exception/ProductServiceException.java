package com.myretail.exception;

/**
 * @author Arun
 *
 */
public class ProductServiceException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorCode = "Unknown_Exception";
	
	public ProductServiceException(){
		
	}

	public ProductServiceException (String erroMessage) {
		super(erroMessage);
	}
	
	public ProductServiceException (String errorCode, String erroMessage) {
		super(erroMessage);
		this.errorCode = errorCode;
	}
	
	public ProductServiceException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public ProductServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
}
