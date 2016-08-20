package com.myretail.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.myretail.beans.Product;
import com.myretail.exception.ProductServiceException;

@Service
public interface ProductService {
	
	public Product getProductById(Integer id) throws ProductServiceException;
	
	public Product updatePrice(Integer id, BigDecimal newPrice) throws ProductServiceException;
	
}
