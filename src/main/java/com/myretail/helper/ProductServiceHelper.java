package com.myretail.helper;

import org.springframework.stereotype.Component;

import com.myretail.beans.ServiceError;
import com.myretail.exception.ProductServiceException;

/**
 * @author Arun
 *
 */
@Component
public class ProductServiceHelper {

	/**
	 * Sets the error response from ProductServiceException
	 * 
	 * @param pse
	 * @return error
	 */
	public ServiceError setErrorResponse(ProductServiceException pse) {
		ServiceError error = new ServiceError();
		error.setErrorCode(pse.getErrorCode());
		error.setErrorMessage(pse.getMessage());
		return error;
	}
	
	/**
	 * Sets the error response from Exception
	 * 
	 * @param pse
	 * @return error
	 */
	public ServiceError setErrorResponse(Exception ex) {
		ServiceError error = new ServiceError();
		error.setErrorMessage(ex.getMessage());
		return error;
	}

}
