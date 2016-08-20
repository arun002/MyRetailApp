package com.myretail.service;

import java.math.BigDecimal;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myretail.beans.Price;
import com.myretail.beans.Product;
import com.myretail.command.ProductDetailsCommand;
import com.myretail.command.ProductPriceCommand;

/**
 * @author Arun
 *
 */
@Service
public class ProductServiceImpl implements ProductService {
	
	private static final Logger log = LogManager.getLogger(ProductServiceImpl.class);
	
	@Autowired 
	ProductDetailsCommand productCommand;
	
	@Autowired 
	ProductPriceCommand priceCommand;
	
	/**
	 * Gets the Product details by passing the product id
	 * 
	 * @param id
	 * @return Product
	 */
	public Product getProductById(Integer id){
		log.debug("Entering getProductById () "+id);
		Product product = null;
		try{
			ExecutorService executor = Executors.newCachedThreadPool();
			Future<String> productDetailsCall = executor.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					// To get the product name from API
					return productCommand.getProductName(id);
					
				}
				
			});
			Future<Price> productPriceCall = executor.submit(new Callable<Price>() {
				@Override
				public Price call() throws Exception {
					// To get the product price from mongo db
					return priceCommand.getProductPrice(id);
					
				}
				
			});
			
			String productName = null; 
			Price price = null;
			try {
				productName = productDetailsCall.get();
				price = productPriceCall.get();
			} catch (InterruptedException e) {
				log.error("Error while trying to get the product details ", e);
			} catch (ExecutionException e) {
				log.error("Error while trying to get the product details ", e);
			}
			executor.shutdown();
			
			product = new Product();
			product.setId(id);
			if(null != productName){
				product.setName(productName);
			}
			if(null != price){
				product.setCurrentPrice(price);
			}
		}catch(Exception ex){
			log.error("Exception thrown while trying to get the Product Details" , ex);
		}
		return product;
	}

	/**
	 * Updates the price for a product
	 * 
	 * @param id
	 * @param newPrice
	 * 
	 * @Return Product
	 */
	public Product updatePrice(Integer id, BigDecimal newPrice) {
		log.debug("Entering updatePrice () "+id+" , "+newPrice);
		Product product = null;
		try{
			product = new Product();
			Price price = priceCommand.updatePrice(id,newPrice);
			product.setId(id);
			product.setCurrentPrice(price);
		} catch(Exception ex){
			log.error("Error while trying to update the price", ex);
			throw ex;
		}
		return product;
	}

}
