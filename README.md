# My Retail Application

Create an end-to-end Proof-of-Concept for a products API, which will aggregate product data from multiple sources and return it as JSON to the caller. 

Technology and Frameworks Used : 

1. Java 8 (JDK 1.8)
2. Spring Boot 4.0.0 
3. JMX with Spring Boot for dynamic configuration changes via JConsole
4. MongoDB (version - 3.2)

Prerequisites :

1. Install latest version of MongoDB.
2. Install MongoDB client - Robomongo and create the data base 'myretaildb' and create collection 'product' under 'myretaildb'.
3. Insert documents inside the  collection 'product'
	
	{
    "productId" : 15117729,
    "price" : "299.99",
    "currencyCode" : "USD"
	}
	
	{
    "productId" : 16483589,
    "price" : "78.99",
    "currencyCode" : "USD"
	}
	
	{
    "productId" : 16696652,
    "price" : 299.99,
    "currencyCode" : "USD"
	}
	
	{
    "productId" : 16752456,
    "price" : "45.10",
    "currencyCode" : "USD"
	}
	
	{
    "productId" : 15643793,
    "price" : 32.49,
    "currencyCode" : "USD"
	}
	
	{
    "productId" : 13860428,
    "price" : 14.99,
    "currencyCode" : "USD"
	}

Steps to Build and Run the application via commandline:

1. git clone https://github.com/arun002/MyRetailApp.git

2. cd MyRetailApp

3. mvn package

4. java -jar target/myretailapp-0.0.1-SNAPSHOT.jar


#APIs

#Get Product Details By Product ID

Request :

METHOD:GET

URL: http://localhost:8080/myretail/v1/products/{productId}

Sample URL : http://localhost:8080/myretail/v1/products/15117729

Response:

{
  "product": {
    "id": 15117729,
    "name": "Apple iPad Air 2 Wi-Fi 16GB, Gold",
    "current_price": {
      "value": 299.99,
      "currency_code": "USD"
    }
  }
}

#Update Product Price by Product ID

Request :

METHOD:PUT

URL: http://localhost:8080/myretail/v1/products/{productId}

Sample URL : http://localhost:8080/myretail/v1/products/15117729

Request Header :

Content-Type : application/json

Request Body :

{
    "price":340.99
}

Response:

{
  "product": {
    "id": 15117729,
    "current_price": {
      "value": 2233.99,
      "currency_code": "USD"
    }
  }
}