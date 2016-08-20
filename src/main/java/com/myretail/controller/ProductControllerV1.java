package com.myretail.controller;


import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myretail.beans.ProductServiceRequest;
import com.myretail.beans.ProductServiceResponse;
import com.myretail.config.ProductServiceProperties;
import com.myretail.exception.ProductServiceException;
import com.myretail.helper.ProductServiceHelper;
import com.myretail.service.ProductService;

/**
 * @author Arun
 *
 */
@RestController
@RequestMapping("/myretail/v1/products/")
public class ProductControllerV1 {
	
	private static final Logger log = LogManager.getLogger(ProductControllerV1.class);
	
	@Autowired 
	ProductService prodService;
	
	@Autowired
	ProductServiceHelper helper;
	
	@Autowired
	ProductServiceProperties properties;

	/**
	 * Service method to fetch the product details
	 * 
	 * @param id
	 * @return ProductServiceResponse
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public @ResponseBody ProductServiceResponse getProductById(@PathVariable("id") Integer id){
		log.info("getProductById () "+ id);
		long startTime = new Date().getTime();
		ProductServiceResponse serviceResponse = new ProductServiceResponse();
		try {
			serviceResponse.setProduct(prodService.getProductById(id));
		} catch (ProductServiceException pse) {
			serviceResponse.setError(helper.setErrorResponse(pse));
		} catch(Exception ex) {
			serviceResponse.setError(helper.setErrorResponse(ex));
		}
		log.info("Response time for the service - getProduct is "+(System.currentTimeMillis() - startTime )+"ms");
		return serviceResponse;
	}
	
	
	/**
	 * Service method to update the product price
	 * 
	 * @param id
	 * @param request
	 * @return ProductServiceResponse
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public @ResponseBody ProductServiceResponse updateProductPrice(@PathVariable("id") Integer id, @RequestBody ProductServiceRequest request){
		log.info("updateProductPrice () " +id);
		long startTime = new Date().getTime();
		ProductServiceResponse serviceResponse = new ProductServiceResponse();
		try {
			//Validate the request
			if(null == request || null == request.getPrice()){
				log.error("Request is not valid");
				throw new ProductServiceException(properties.getRequestNotValidCode(), properties.getRequestNotValidMsg());
			}
			serviceResponse.setProduct(prodService.updatePrice(id,request.getPrice()));
		} catch (ProductServiceException pse) {
			serviceResponse.setError(helper.setErrorResponse(pse));
		} catch (Exception ex){
			serviceResponse.setError(helper.setErrorResponse(ex));
		}
		log.info("Response time for the service - Update Product Price is "+(System.currentTimeMillis() - startTime )+"ms");
		return serviceResponse;
	}
}
