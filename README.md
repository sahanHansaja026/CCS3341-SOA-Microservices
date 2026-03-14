
# OrdersService (REST API)

## Overview

OrdersService is a REST-based microservice built using Spring Boot.
It allows clients to create and retrieve book orders.

This service is part of the **GlobalBooks SOA system**.

---

## Technologies Used

* Java 17
* Spring Boot
* Maven
* Spring Security (Basic Authentication)
* Postman (API testing)

---

## Project Structure

src/main/java/com/globalbooks/orders_service

controller/
 OrderController.java

model/
 Order.java

config/
 SecurityConfig.java

OrdersServiceApplication.java

---

## API Endpoints

### 1. Create Order

POST /orders

Creates a new order.

Example Request Body:

{
"bookTitle": "SOA Guide",
"quantity": 2
}

Example Response:

{
"id": "generated-uuid",
"bookTitle": "SOA Guide",
"quantity": 2,
"total": 100.0
}

---

### 2. Get Order

GET /orders/{id}

Returns an order by ID.

Example:

GET [http://localhost:8080/orders/{order-id}](http://localhost:8080/orders/{order-id})

---

## Authentication

Basic Authentication is enabled.

Username: sahan
Password: Password123

Configure in Postman:

Authorization → Basic Auth

---

## How to Run the Service

1. Open terminal in project folder.

2. Run:

mvn spring-boot:run

3. Service will start on:

[http://localhost:8080](http://localhost:8080)

---

## How to Test Using Postman

### Create Order

POST
[http://localhost:8080/orders](http://localhost:8080/orders)

Authorization:
Basic Auth

Body → raw JSON

{
"bookTitle": "SOA Guide",
"quantity": 2
}

---

### Get Order

GET
[http://localhost:8080/orders/{id}](http://localhost:8080/orders/{id})

Use the ID returned from the POST request.

---

## Notes

* Orders are stored in an **in-memory HashMap**.
* Restarting the application will remove all stored orders.
* Future improvements will include RabbitMQ messaging for PaymentsService and ShippingService.

---

# CatalogService (SOAP Web Service)

## Overview

CatalogService is a SOAP-based web service that provides book information such as price and availability.

It is deployed on Apache Tomcat and used by other services in the GlobalBooks SOA system.

---

## Technologies Used

* Java
* JAX-WS
* Apache Tomcat
* Maven

---

## Service Function

CatalogService provides operations such as:

getBookPrice()

Returns the price of a book.

---

## Deployment Steps

1. Build the project:

mvn clean package

2. A WAR file will be generated:

CatalogService-1.0-SNAPSHOT.war

3. Copy the WAR file to Tomcat:

apache-tomcat/webapps/

4. Start Apache Tomcat.

5. The service will deploy automatically.

---

## Accessing the Service

Open browser:

[http://localhost:8080/CatalogService/CatalogService?wsdl](http://localhost:8080/CatalogService/CatalogService?wsdl)

This shows the WSDL of the SOAP service.

---

## Testing the Service

You can test using:

* SOAP UI
* Postman (SOAP request)

Example SOAP Request:

<soapenv:Envelope xmlns:soapenv="[http://schemas.xmlsoap.org/soap/envelope/](http://schemas.xmlsoap.org/soap/envelope/)"
xmlns:cat="[http://catalog/">](http://catalog/%22>)
[soapenv:Header/](soapenv:Header/)
[soapenv:Body](soapenv:Body)
[cat:getBookPrice](cat:getBookPrice)

<title>SOA Guide</title>
</cat:getBookPrice>
</soapenv:Body>
</soapenv:Envelope>

---

## Notes

* CatalogService is deployed as a SOAP service.
* It will later be called by OrdersService or BPEL process to retrieve book pricing.
* Apache Tomcat must be running before accessing the service.

---

✅ Now you have **proper documentation for both services**.

Your project now includes:

* **OrdersService (REST)**
* **CatalogService (SOAP)**
* **Deployment instructions**
* **Testing instructions**

---


```

SOA/
├─ CatalogService/
│  ├─ src/
│  │  └─ main/
│  │     ├─ java/
│  │     │  └─ com/
│  │     │     └─ globalbooks/
│  │     │        └─ catalog/
│  │     │           ├─ Book.java
│  │     │           └─ CatalogService.java
│  │     └─ webapp/
│  │        └─ WEB-INF/
│  │           ├─ sun-jaxws.xml
│  │           └─ web.xml
│  ├─ target/
│  │  ├─ CatalogService/
│  │  │  ├─ META-INF/
│  │  │  └─ WEB-INF/
│  │  │     ├─ classes/
│  │  │     │  └─ com/
│  │  │     │     └─ globalbooks/
│  │  │     │        └─ catalog/
│  │  │     │           ├─ Book.class
│  │  │     │           └─ CatalogService.class
│  │  │     ├─ lib/
│  │  │     │  ├─ FastInfoset-1.2.18.jar
│  │  │     │  ├─ gmbal-api-only-4.0.3.jar
│  │  │     │  ├─ ha-api-3.1.13.jar
│  │  │     │  ├─ jakarta.activation-1.2.2.jar
│  │  │     │  ├─ jakarta.annotation-api-1.3.5.jar
│  │  │     │  ├─ jakarta.jws-api-2.1.0.jar
│  │  │     │  ├─ jakarta.mail-1.6.7.jar
│  │  │     │  ├─ jakarta.xml.bind-api-2.3.3.jar
│  │  │     │  ├─ jakarta.xml.soap-api-1.4.2.jar
│  │  │     │  ├─ jakarta.xml.ws-api-2.3.3.jar
│  │  │     │  ├─ javax.activation-api-1.2.0.jar
│  │  │     │  ├─ javax.annotation-api-1.3.2.jar
│  │  │     │  ├─ javax.xml.soap-api-1.4.0.jar
│  │  │     │  ├─ jaxb-api-2.3.1.jar
│  │  │     │  ├─ jaxb-impl-2.3.5.jar
│  │  │     │  ├─ jaxws-api-2.3.1.jar
│  │  │     │  ├─ jaxws-rt-2.3.5.jar
│  │  │     │  ├─ management-api-3.2.3.jar
│  │  │     │  ├─ mimepull-1.9.15.jar
│  │  │     │  ├─ policy-2.7.10.jar
│  │  │     │  ├─ saaj-impl-1.5.3.jar
│  │  │     │  ├─ stax-ex-1.8.3.jar
│  │  │     │  ├─ stax2-api-4.2.1.jar
│  │  │     │  ├─ streambuffer-1.5.10.jar
│  │  │     │  └─ woodstox-core-6.2.6.jar
│  │  │     ├─ sun-jaxws.xml
│  │  │     └─ web.xml
│  │  ├─ classes/
│  │  │  └─ com/
│  │  │     └─ globalbooks/
│  │  │        └─ catalog/
│  │  │           ├─ Book.class
│  │  │           └─ CatalogService.class
│  │  ├─ generated-sources/
│  │  │  └─ annotations/
│  │  ├─ maven-archiver/
│  │  │  └─ pom.properties
│  │  ├─ maven-status/
│  │  │  └─ maven-compiler-plugin/
│  │  │     └─ compile/
│  │  │        └─ default-compile/
│  │  │           ├─ createdFiles.lst
│  │  │           └─ inputFiles.lst
│  │  ├─ test-classes/
│  │  └─ CatalogService.war
│  └─ pom.xml
└─ orders-service/
   ├─ .mvn/
   │  └─ wrapper/
   │     └─ maven-wrapper.properties
   ├─ src/
   │  ├─ main/
   │  │  ├─ java/
   │  │  │  └─ com/
   │  │  │     └─ globalbooks/
   │  │  │        └─ orders_service/
   │  │  │           ├─ config/
   │  │  │           │  └─ SecurityConfig.java
   │  │  │           ├─ controller/
   │  │  │           │  └─ OrderController.java
   │  │  │           ├─ model/
   │  │  │           │  └─ Order.java
   │  │  │           └─ OrdersServiceApplication.java
   │  │  └─ resources/
   │  │     ├─ static/
   │  │     ├─ templates/
   │  │     └─ application.properties
   │  └─ test/
   │     └─ java/
   │        └─ com/
   │           └─ globalbooks/
   │              └─ orders_service/
   │                 └─ OrdersServiceApplicationTests.java
   ├─ target/
   │  ├─ classes/
   │  │  ├─ com/
   │  │  │  └─ globalbooks/
   │  │  │     └─ orders_service/
   │  │  │        ├─ config/
   │  │  │        │  └─ SecurityConfig.class
   │  │  │        ├─ controller/
   │  │  │        │  └─ OrderController.class
   │  │  │        ├─ model/
   │  │  │        │  └─ Order.class
   │  │  │        └─ OrdersServiceApplication.class
   │  │  └─ application.properties
   │  ├─ generated-sources/
   │  │  └─ annotations/
   │  ├─ generated-test-sources/
   │  │  └─ test-annotations/
   │  ├─ maven-status/
   │  │  └─ maven-compiler-plugin/
   │  │     ├─ compile/
   │  │     │  └─ default-compile/
   │  │     │     ├─ createdFiles.lst
   │  │     │     └─ inputFiles.lst
   │  │     └─ testCompile/
   │  │        └─ default-testCompile/
   │  │           ├─ createdFiles.lst
   │  │           └─ inputFiles.lst
   │  └─ test-classes/
   │     └─ com/
   │        └─ globalbooks/
   │           └─ orders_service/
   │              └─ OrdersServiceApplicationTests.class
   ├─ .gitattributes
   ├─ .gitignore
   ├─ HELP.md
   ├─ mvnw
   ├─ mvnw.cmd
   └─ pom.xml


```
