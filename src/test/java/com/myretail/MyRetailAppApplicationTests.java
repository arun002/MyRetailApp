package com.myretail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.myretail.beans.ProductServiceRequest;
import com.myretail.beans.ProductServiceResponse;
import com.myretail.controller.ProductControllerV1;
import com.myretail.dao.ProductPriceDAO;
import com.myretail.dao.domain.ProductDocument;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyRetailAppApplicationTests {

	@Autowired
	ProductPriceDAO priceDAO;
	
	@Autowired
	ProductControllerV1 controller;
	
	@Test
	public void testFindAllProducts(){
		List<ProductDocument> prodList = priceDAO.retrieveAllProdcut();
		assertNotNull(prodList);
		assertTrue(!prodList.isEmpty());
	}
	
	@Test
	public void testGetProductWithInvalidProductId(){
		Integer id = 111;
		ProductServiceResponse response = controller.getProductById(id);
		assertNotNull(response);
		assertNotNull(response.getError());
		assertEquals("410", response.getError().getErrorCode());
		assertEquals("Product Id is not found", response.getError().getErrorMessage());
	}
	
	@Test
	public void testGetProductValidProductId(){
		Integer id = 16483589;
		ProductServiceResponse response = controller.getProductById(id);
		assertNotNull(response);
		assertNull(response.getError());
		assertNotNull(response.getProduct());
		assertEquals("16483589",response.getProduct().getId().toString());
		assertEquals("AT&T iPhone 6 plus Gold 128GB",response.getProduct().getName());
		assertEquals("78.99",response.getProduct().getCurrentPrice().getValue().toString());
		assertEquals("USD",response.getProduct().getCurrentPrice().getCurrencyCode());
	}
	
	@Test
	public void testUpdateProductPriceWithInvalidProductId(){
		Integer id = 112;
		ProductServiceRequest request = new ProductServiceRequest();
		request.setPrice(new BigDecimal("100.00"));
		ProductServiceResponse response = controller.updateProductPrice(id, request);
		assertNotNull(response);
		assertNull(response.getProduct());
		assertNotNull(response.getError());
		assertEquals("410", response.getError().getErrorCode());
		assertEquals("Product Id is not found", response.getError().getErrorMessage());
	}
	
	@Test
	public void testUpdateProductPriceWithInvalidRequest(){
		Integer id = 16696652;
		ProductServiceRequest request = new ProductServiceRequest();
		ProductServiceResponse response = controller.updateProductPrice(id, request);
		assertNotNull(response);
		assertNull(response.getProduct());
		assertNotNull(response.getError());
		assertEquals("412", response.getError().getErrorCode());
		assertEquals("Request is not valid", response.getError().getErrorMessage());
	}
	
	@Test
	public void testUpdateProductPriceWithValidProductId(){
		Integer id = 16752456;
		ProductServiceRequest request = new ProductServiceRequest();
		request.setPrice(new BigDecimal("45.10"));
		ProductServiceResponse response = controller.updateProductPrice(id, request);
		assertNotNull(response);
		assertNull(response.getError());
		assertNotNull(response.getProduct());
		assertEquals("16752456",response.getProduct().getId().toString());
		assertEquals("45.10",response.getProduct().getCurrentPrice().getValue().toString());
		assertEquals("USD",response.getProduct().getCurrentPrice().getCurrencyCode());
	}
	

}
