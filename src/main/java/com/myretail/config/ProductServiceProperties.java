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
	
	private String prdNotFoundCode;
	
	private String prdNotFoundMsg;
	
	private String requestNotValidCode;
	
	private String requestNotValidMsg;
	
	private String genErrorCode;
	
	private String genErrorMsg;

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

	@ManagedAttribute
	public String getPrdNotFoundCode() {
		return prdNotFoundCode;
	}

	@ManagedAttribute
	public void setPrdNotFoundCode(String prdNotFoundCode) {
		this.prdNotFoundCode = prdNotFoundCode;
	}

	@ManagedAttribute
	public String getPrdNotFoundMsg() {
		return prdNotFoundMsg;
	}

	@ManagedAttribute
	public void setPrdNotFoundMsg(String prdNotFoundMsg) {
		this.prdNotFoundMsg = prdNotFoundMsg;
	}

	@ManagedAttribute
	public String getRequestNotValidCode() {
		return requestNotValidCode;
	}

	@ManagedAttribute
	public void setRequestNotValidCode(String requestNotValidCode) {
		this.requestNotValidCode = requestNotValidCode;
	}

	@ManagedAttribute
	public String getRequestNotValidMsg() {
		return requestNotValidMsg;
	}

	@ManagedAttribute
	public void setRequestNotValidMsg(String requestNotValidMsg) {
		this.requestNotValidMsg = requestNotValidMsg;
	}

	@ManagedAttribute
	public String getGenErrorCode() {
		return genErrorCode;
	}

	@ManagedAttribute
	public void setGenErrorCode(String genErrorCode) {
		this.genErrorCode = genErrorCode;
	}

	@ManagedAttribute
	public String getGenErrorMsg() {
		return genErrorMsg;
	}

	@ManagedAttribute
	public void setGenErrorMsg(String genErrorMsg) {
		this.genErrorMsg = genErrorMsg;
	}

}
