package com.myretail.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.myretail.config.ProductServiceProperties;

/**
 * @author Arun
 *
 */
@Component
public class ProductDetailsCommand {
	
	private static final Logger log = LogManager.getLogger(ProductDetailsCommand.class);
	
	@Autowired 
	ProductServiceProperties properties;

	/**
	 * Gets the product name by consuming the product API.
	 * 
	 * @param id
	 * @return productName
	 */
	public String getProductName(Integer id) {
		final UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(properties.getProductDetailsUrl());
		builder.path(id.toString());
		builder.queryParam("fields", properties.getFields());
		builder.queryParam("id_type", properties.getId_type());
		builder.queryParam("key", properties.getKey());
		RestTemplate restTemplate = new RestTemplate();
		log.info("Calling Product APT - START "+builder.build().toUriString());
		String responseJson = restTemplate.getForObject(builder.build().toUriString(), String.class);
		log.info("Calling Product APT - END");
		log.debug("Response from Product API - "+responseJson);
		return getProductName(responseJson);
	}
	
	/**
	 * Gets the product name from the JSON response
	 * 
	 * @param responseJson
	 * @return prodName
	 */
	private String getProductName(String responseJson) {
		String prodName = null;
		if(null != responseJson && !responseJson.isEmpty()){
			JSONObject object = new JSONObject(responseJson);
			if(null != object && object.has("product_composite_response")){
				JSONObject productResponse = object.getJSONObject("product_composite_response");
				if(null != productResponse && productResponse.has("items")){
					JSONArray itemsArray = productResponse.getJSONArray("items");
					if(null != itemsArray && itemsArray.length() > 0){
						JSONObject item = (JSONObject) itemsArray.get(0);
						if(null != item && item.has("general_description")){
							prodName = item.get("general_description").toString();
							log.info("Product name is "+ prodName);
						}
					}
				}
			
			}
		}
		return prodName;
	}

}
