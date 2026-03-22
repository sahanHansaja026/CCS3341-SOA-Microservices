
```markdown
# SOA Microservices Project with RabbitMQ

This project implements a simple Service-Oriented Architecture (SOA) using Spring Boot microservices, communicating via **RabbitMQ**.

---

## **Folder Structure**



SOA/
│
├─ orders-service/
│   ├─ src/
│   ├─ pom.xml
│   └─ application.properties
│
├─ payments-service/
│   ├─ src/
│   ├─ pom.xml
│   └─ application.properties
│
├─ shipping-service/
│   ├─ src/
│   ├─ pom.xml
│   └─ application.properties
│
├─ catalog-service/ (if used)
│   ├─ src/
│   ├─ pom.xml
│   └─ application.properties
│
└─ README.md



---

## **Requirements**

- Java 17
- Maven
- RabbitMQ (local or Docker)
- Postman (optional, for API testing)

---

## **RabbitMQ Setup**

1. **Install and run RabbitMQ** or use Docker:

```bash
docker run -d --hostname my-rabbit --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
````

2. **Access RabbitMQ Management Dashboard**:

```
http://localhost:15672
```

* Default credentials:

  * Username: `guest`
  * Password: `guest`

3. **Exchanges**

* `ordersExchange` (direct)
* `paymentsExchange` (direct)

4. **Queues**

* `paymentQueue` → consumes from `paymentsExchange`
* `shippingQueue` → consumes from `ordersExchange`

5. **Bindings**

* `paymentQueue` bound to `paymentsExchange` with routing key `payment`
* `shippingQueue` bound to `ordersExchange` with routing key `shipping`

---

Here’s your **README.md** version with proper Markdown formatting, including CatalogService instructions:

````markdown
# GlobalBooks SOA & Microservices

## How to Run Each Service

### 1. Start RabbitMQ

If using Docker:

```bash
docker run -d --hostname my-rabbit --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
````

Or run locally:

```bash
rabbitmq-server
```

---

### 2. Run CatalogService (SOAP - Tomcat Deployment)

1. Navigate to the CatalogService project:

```bash
cd CatalogService
```

2. Build the WAR file:

```bash
mvn clean package
```

3. Copy the generated WAR file to your Tomcat `webapps` folder:

```bash
cp target/CatalogService.war <TOMCAT_HOME>/webapps/
```

4. Start Apache Tomcat:

```bash
cd <TOMCAT_HOME>/bin
startup.bat   # Windows
./startup.sh  # Linux/Mac
```

5. Verify the service is running by opening the WSDL in a browser:

```
http://localhost:8080/CatalogService/catalog?wsdl
```

---

### 3. Run Orders Service (REST)

```bash
cd orders-service
mvn spring-boot:run
```

---

### 4. Run Payments Service

```bash
cd payments-service
mvn spring-boot:run
```

---

### 5. Run Shipping Service

```bash
cd shipping-service
mvn spring-boot:run
```

---

## API Testing

### Catalog Service (SOAP – using SOAP UI)

* WSDL:

```
http://localhost:8080/CatalogService/catalog?wsdl
```

* Operation: `getBookDetails`

#### Example SOAP Request

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:cat="http://catalog.globalbooks.com/">
   <soapenv:Header/>
   <soapenv:Body>
      <cat:getBook>
         <arg0>1111</arg0>
      </cat:getBook>
   </soapenv:Body>
</soapenv:Envelope>
```

---

### Orders Service (REST – using Postman)

#### Create Order

```
POST http://localhost:8080/orders
```

```json
{
  "productId": "123",
  "quantity": 2,
  "price": 25.5
}
```

#### Get Orders

```
GET http://localhost:8080/orders
```

---

### Payments Service

* Receives messages from `orders-service` via `paymentsExchange`
* Automatically processes payments and logs confirmation

---

### Shipping Service

* Receives messages from `orders-service` via `ordersExchange`
* Logs shipping confirmation

---

## Expected RabbitMQ Dashboard

Access the management console:

```
http://localhost:15672
```

### You should see:

* **Exchanges:** `ordersExchange`, `paymentsExchange`
* **Queues:** `paymentQueue`, `shippingQueue`
* **Bindings:** Queues connected to correct exchanges
* **Consumers:** Active for each queue
* **Messages:** 0 (after processing)

---

## Testing Notes

* Submit an order using Postman

* Observe RabbitMQ dashboard:

  * Messages appear in `paymentQueue` and `shippingQueue`
  * Messages are consumed and disappear after processing

* Verify logs in:

  * Orders Service
  * Payments Service
  * Shipping Service

---

## Dependencies

* Spring Boot 3.x
* Spring AMQP
* Jackson (JSON)
* Lombok (optional)
* Apache Tomcat (for SOAP service)

---

## Quick Run Summary

1. Start RabbitMQ
2. Start Tomcat (CatalogService)
3. Run Orders Service
4. Run Payments Service
5. Run Shipping Service
6. Test using SOAP UI + Postman

```

---

## **Submission Checklist**

* [ ] All service folders with code
* [ ] `pom.xml` / dependencies
* [ ] `application.properties`
* [ ] README.md
* [ ] Postman collection (optional)
* [ ] RabbitMQ Docker / local instructions
* [ ] Screenshots of dashboard (optional)

```
```
SOA/
├─ CatalogService/
│  ├─ src/
│  │  └─ main/
│  │     ├─ java/
│  │     │  └─ com/
│  │     │     └─ globalbooks/
│  │     │        └─ catalog/
│  │     └─ webapp/
│  │        └─ WEB-INF/
│  └─ target/
│     ├─ CatalogService/
│     │  ├─ META-INF/
│     │  └─ WEB-INF/
│     │     ├─ classes/
│     │     │  └─ com/
│     │     │     └─ globalbooks/
│     │     │        └─ catalog/
│     │     └─ lib/
│     ├─ classes/
│     │  └─ com/
│     │     └─ globalbooks/
│     │        └─ catalog/
│     ├─ generated-sources/
│     │  └─ annotations/
│     ├─ maven-archiver/
│     ├─ maven-status/
│     │  └─ maven-compiler-plugin/
│     │     └─ compile/
│     │        └─ default-compile/
│     └─ test-classes/
├─ orders-service/
│  ├─ .mvn/
│  │  └─ wrapper/
│  ├─ src/
│  │  ├─ main/
│  │  │  ├─ java/
│  │  │  │  └─ com/
│  │  │  │     └─ globalbooks/
│  │  │  │        └─ orders_service/
│  │  │  │           ├─ config/
│  │  │  │           ├─ controller/
│  │  │  │           └─ model/
│  │  │  └─ resources/
│  │  │     ├─ static/
│  │  │     └─ templates/
│  │  └─ test/
│  │     └─ java/
│  │        └─ com/
│  │           └─ globalbooks/
│  │              └─ orders_service/
│  └─ target/
│     ├─ classes/
│     │  └─ com/
│     │     └─ globalbooks/
│     │        └─ orders_service/
│     │           ├─ config/
│     │           ├─ controller/
│     │           └─ model/
│     ├─ generated-sources/
│     │  └─ annotations/
│     ├─ generated-test-sources/
│     │  └─ test-annotations/
│     ├─ maven-archiver/
│     ├─ maven-status/
│     │  └─ maven-compiler-plugin/
│     │     ├─ compile/
│     │     │  └─ default-compile/
│     │     └─ testCompile/
│     │        └─ default-testCompile/
│     ├─ surefire-reports/
│     └─ test-classes/
│        └─ com/
│           └─ globalbooks/
│              └─ orders_service/
├─ payments-service/
│  ├─ .mvn/
│  │  └─ wrapper/
│  ├─ src/
│  │  ├─ main/
│  │  │  ├─ java/
│  │  │  │  └─ com/
│  │  │  │     └─ globalbooks/
│  │  │  │        └─ payments_service/
│  │  │  │           ├─ config/
│  │  │  │           ├─ listener/
│  │  │  │           ├─ messaging/
│  │  │  │           └─ model/
│  │  │  └─ resources/
│  │  │     ├─ static/
│  │  │     └─ templates/
│  │  └─ test/
│  │     └─ java/
│  │        └─ com/
│  │           └─ globalbooks/
│  │              └─ payments_service/
│  └─ target/
│     ├─ classes/
│     │  └─ com/
│     │     └─ globalbooks/
│     │        └─ payments_service/
│     │           ├─ config/
│     │           ├─ listener/
│     │           ├─ messaging/
│     │           └─ model/
│     ├─ generated-sources/
│     │  └─ annotations/
│     ├─ generated-test-sources/
│     │  └─ test-annotations/
│     ├─ maven-archiver/
│     ├─ maven-status/
│     │  └─ maven-compiler-plugin/
│     │     ├─ compile/
│     │     │  └─ default-compile/
│     │     └─ testCompile/
│     │        └─ default-testCompile/
│     ├─ surefire-reports/
│     └─ test-classes/
│        └─ com/
│           └─ globalbooks/
│              └─ payments_service/
└─ shipping-service/
   ├─ .mvn/
   │  └─ wrapper/
   ├─ src/
   │  ├─ main/
   │  │  ├─ java/
   │  │  │  └─ com/
   │  │  │     └─ globalbooks/
   │  │  │        └─ shipping_service/
   │  │  │           ├─ config/
   │  │  │           ├─ listener/
   │  │  │           ├─ messaging/
   │  │  │           └─ model/
   │  │  └─ resources/
   │  │     ├─ static/
   │  │     └─ templates/
   │  └─ test/
   │     └─ java/
   │        └─ com/
   │           └─ globalbooks/
   │              └─ shipping_service/
   └─ target/
      ├─ classes/
      │  └─ com/
      │     └─ globalbooks/
      │        └─ shipping_service/
      │           ├─ config/
      │           ├─ listener/
      │           ├─ messaging/
      │           └─ model/
      ├─ generated-sources/
      │  └─ annotations/
      ├─ generated-test-sources/
      │  └─ test-annotations/
      ├─ maven-archiver/
      ├─ maven-status/
      │  └─ maven-compiler-plugin/
      │     ├─ compile/
      │     │  └─ default-compile/
      │     └─ testCompile/
      │        └─ default-testCompile/
      ├─ surefire-reports/
      └─ test-classes/
         └─ com/
            └─ globalbooks/
               └─ shipping_service/
```