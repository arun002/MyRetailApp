package com.myretail.controller;


import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myretail.beans.ProductServiceRequest;
import com.myretail.beans.ProductServiceResponse;
import com.myretail.service.ProductService;

/**
 * @author Arun
 *
 */
@RestController
@RequestMapping("/myretail")
public class ProductControllerV1 {
	
	private static final Logger log = LogManager.getLogger(ProductControllerV1.class);
	
	@Autowired ProductService prodService;

	/**
	 * Service method to fetch the product details
	 * 
	 * @param id
	 * @return ProductServiceResponse
	 */
	@RequestMapping(value = "/v1/product/{id}", method = RequestMethod.GET)
	public @ResponseBody ProductServiceResponse getProductById(@PathVariable("id") Integer id){
		log.info("getProductById () "+ id);
		long startTime = new Date().getTime();
		ProductServiceResponse serviceResponse = new ProductServiceResponse();
		serviceResponse.setProduct(prodService.getProductById(id));
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
	@RequestMapping(value = "/v1/product/{id}", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ProductServiceResponse updateProductPrice(@PathVariable("id") Integer id, @RequestBody ProductServiceRequest request){
		log.info("updateProductPrice () " +id);
		long startTime = new Date().getTime();
		ProductServiceResponse serviceResponse = new ProductServiceResponse();
		serviceResponse.setProduct(prodService.updatePrice(id,request.getPrice()));
		log.info("Response time for the service - Update Product Price is "+(System.currentTimeMillis() - startTime )+"ms");
		return serviceResponse;
	}
}
