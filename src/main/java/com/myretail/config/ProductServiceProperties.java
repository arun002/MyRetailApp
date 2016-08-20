package com.myretail.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

/**
 * 
 * Config/Properties file for My Retail app
 * The data is JMX enabled , thus can be modified at run time using JCONSOLE
 * @author Arun

 *
 */
@Configuration
@ConfigurationProperties(prefix = "myretail.product")
@ManagedResource(objectName = "myretail.product.config:name=ProductServiceProperties", description = "My Retail Product Service Properties")
public class ProductServiceProperties {
	
	private String productDetailsUrl;
	
	private String fields;
	
	private String id_type;
	
	private String key;

	@ManagedAttribute
	public String getProductDetailsUrl() {
		return productDetailsUrl;
	}

	@ManagedAttribute
	public void setProductDetailsUrl(String productDetailsUrl) {
		this.productDetailsUrl = productDetailsUrl;
	}

	@ManagedAttribute
	public String getFields() {
		return fields;
	}

	@ManagedAttribute
	public void setFields(String fields) {
		this.fields = fields;
	}

	@ManagedAttribute
	public String getId_type() {
		return id_type;
	}

	@ManagedAttribute
	public void setId_type(String id_type) {
		this.id_type = id_type;
	}

	@ManagedAttribute
	public String getKey() {
		return key;
	}

	@ManagedAttribute
	public void setKey(String key) {
		this.key = key;
	}

}
