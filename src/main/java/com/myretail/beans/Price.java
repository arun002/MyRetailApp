package com.myretail.beans;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;

@Component
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Price {
	
	private BigDecimal value;
	
	private String currencyCode;

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getCurrenceCode() {
		return currencyCode;
	}

	public void setCurrenceCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

}
