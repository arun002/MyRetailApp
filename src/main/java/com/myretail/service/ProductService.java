package com.myretail.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.myretail.beans.Product;

@Service
public interface ProductService {
	
	public Product getProductById(Integer id);
	
	public Product updatePrice(Integer id, BigDecimal newPrice);
	
}
