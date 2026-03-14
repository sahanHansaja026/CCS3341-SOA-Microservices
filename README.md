
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

## **How to Run Each Service**

1. Start RabbitMQ (if not using Docker, run `rabbitmq-server` locally)
2. Open terminal in service folder, e.g.:

```bash
cd orders-service
mvn spring-boot:run
```

3. Repeat for other services:

```bash
cd payments-service
mvn spring-boot:run

cd shipping-service
mvn spring-boot:run
```

---

## **API Testing (Using Postman)**

### **Orders Service**

* **Create Order**

  * `POST http://localhost:8080/orders`
  * Body (JSON):

    ```json
    {
      "productId": "123",
      "quantity": 2,
      "price": 25.5
    }
    ```
* **Get Orders**

  * `GET http://localhost:8080/orders`

### **Payments Service**

* Receives messages from `orders-service` via `paymentsExchange`
* Auto-processes payments and logs confirmation

### **Shipping Service**

* Receives messages from `orders-service` via `ordersExchange`
* Logs shipping confirmation

---

## **Expected RabbitMQ Dashboard**

* **Exchanges:** `ordersExchange`, `paymentsExchange`
* **Queues:** `paymentQueue`, `shippingQueue`
* **Bindings:** Queues attached to correct exchanges
* **Consumers:** Active for each queue (check “Consumers” column)
* **Messages:** Zero ready/unacknowledged if all messages processed

---

## **Testing Notes**

* Submit an order → check RabbitMQ dashboard:

  * Messages appear in `paymentQueue` and `shippingQueue`
  * Processed messages disappear after consumers ack
* Verify logs in each service for confirmations

---

## **Dependencies**

* Spring Boot 3.x
* Spring AMQP
* Jackson (JSON)
* Lombok (optional)

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