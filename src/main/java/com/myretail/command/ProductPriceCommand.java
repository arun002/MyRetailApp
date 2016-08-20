package com.myretail.command;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myretail.beans.Price;
import com.myretail.dao.ProductPriceDAO;
import com.myretail.dao.domain.ProductDocument;

/**
 * @author Arun
 *
 */
@Component
public class ProductPriceCommand {

	private Logger log = LogManager.getLogger(ProductPriceCommand.class);
	
	@Autowired 
	ProductPriceDAO  priceDAO;
	
	/**
	 * Gets the product price from database for the product id
	 * 
	 * @param productId
	 * @return price
	 */
	public Price getProductPrice(Integer productId) {
		ProductDocument doc = priceDAO.retrieveProdcut(productId);
		Price price = null;
		if(null != doc){
			price = new Price();
			price.setCurrencyCode(doc.getCurrencyCode());
			price.setValue(doc.getPrice());
			log.info("Price is "+price.getValue());
		}else{
			log.error("Product id "+productId+" is not found");
		}
		return price;
	}

	/**
	 * Update the product price to database
	 * 
	 * @param productId
	 * @param newPrice
	 * @return price
	 */
	public Price updatePrice(Integer productId, BigDecimal newPrice) {
		ProductDocument doc = priceDAO.updatePrice(productId,newPrice);
		Price price = null;
		if(null != doc){
			price = new Price();
			price.setCurrencyCode(doc.getCurrencyCode());
			price.setValue(doc.getPrice());
			log.info("New Price is "+newPrice);
		}else{
			log.error("Product id "+productId+" is not found");
		}
		return price;
	}
	
}
