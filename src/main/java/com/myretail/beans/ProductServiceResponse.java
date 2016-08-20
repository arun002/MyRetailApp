package com.myretail.beans;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Component
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductServiceResponse {
	
	private Product product;
	
	@JsonProperty("error")
	private ServiceError error;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ServiceError getError() {
		return error;
	}

	public void setError(ServiceError error) {
		this.error = error;
	}

}
