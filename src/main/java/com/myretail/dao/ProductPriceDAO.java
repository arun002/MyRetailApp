package com.myretail.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.myretail.dao.domain.ProductDocument;

/**
 * @author Arun
 *
 */
@Repository
public class ProductPriceDAO {

	@Autowired 
	MongoTemplate mongo;

	/**
	 * 
	 * @param productId
	 * @return ProductDocument
	 */
	public ProductDocument retrieveProdcut(Integer productId) {
		Query query = new Query(Criteria.where("productId").is(productId));
		return mongo.findOne(query, ProductDocument.class);
	}
	
	
	/**
	 * 
	 * @param productId
	 * @return ProductDocument
	 */
	public List<ProductDocument> retrieveAllProdcut() {
		return mongo.findAll(ProductDocument.class);
	}

	

	/**
	 * @param productId
	 * @param price
	 * @return ProductDocument
	 */
	public ProductDocument updatePrice(Integer productId, BigDecimal price) {
		Query query = new Query(Criteria.where("productId").is(productId));
		mongo.updateFirst(query, Update.update("price", price), ProductDocument.class);
		return mongo.findOne(query, ProductDocument.class);
	}

}
