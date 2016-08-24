# My Retail Application

Create an end-to-end Proof-of-Concept for a products API, which will aggregate product data from multiple sources and return it as JSON to the caller. 

Technology and Frameworks Used : 

1. Java 8 (JDK 1.8)
2. Spring Boot 4.0.0 
3. JMX with Spring Boot for dynamic configuration changes via JConsole
4. MongoDB (version - 3.2)

Prerequisites :

Before building the application , need to create local MongoDB instance and insert data into the collection.

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

4. Make sure MongoDB server is started.

#Steps to Build and Run the application in local via commandline:

1. git clone https://github.com/arun002/MyRetailApp.git

2. cd MyRetailApp

3. mvn package

4. java -jar target/myretailapp-0.0.1-SNAPSHOT.jar
	
Note: Created Profile for different envs - dev, qa, perf and prod

java -jar -Dspring.profiles.active=dev target/myretailapp-0.0.1-SNAPSHOT.jar

java -jar -Dspring.profiles.active=qa target/myretailapp-0.0.1-SNAPSHOT.jar

java -jar -Dspring.profiles.active=perf target/myretailapp-0.0.1-SNAPSHOT.jar

java -jar -Dspring.profiles.active=production target/myretailapp-0.0.1-SNAPSHOT.jar


#APIs

#Health Check 

Request:

METHOD :GET 

URL: http://localhost:8080/health

Response:

{
  "status": "UP",
  "diskSpace": {
    "status": "UP",
    "total": 974807408640,
    "free": 833895628800,
    "threshold": 10485760
  },
  "mongo": {
    "status": "UP",
    "version": "3.2.9"
  }
}

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

Screenshot of the API call : https://github.com/arun002/MyRetailApp/blob/master/ProductAPI.png

Error Details:

---------------------
Error Code - 410

Error Message - Product Id is not found

Scenario - Product Id  passed  is not found in data store

----------------

Error Code - 500

Error Message - Service is unavailable now

Scenario - If there is any exception thrown in the application.

#Update Product Price by Product ID

Request :

METHOD:PUT

URL: http://localhost:8080/myretail/v1/products/{productId} 

Sample URL : http://localhost:8080/myretail/v1/products/15117729 

Request Header :

Content-Type : application/json

Request Body :

{
    "price":320.99
}

Response:

{
  "product": {
    "id": 15117729,
    "current_price": {
      "value": 320.99,
      "currency_code": "USD"
    }
  }
}

Screenshot of the API call : https://github.com/arun002/MyRetailApp/blob/master/UpdatePriceAPI.png


Error Details:

---------------------
Error Code - 410

Error Message - Product Id is not found

Scenario - Product Id  passed  is not found in data store

-----------------

Error Code - 412

Error Message - Request is not valid

Scenario - Request body does not contain 'price'  field

----------------

Error Code - 500

Error Message - Service is unavailable now

Scenario - If there is any exception thrown in the application. 


#Testing Results

Tests are run during the build process. Run the below command, to run it separately

mvn test