
<h1>Student 5: Notification & Offer Service Implementation</h1>
Welcome to the Hotel & Travel Booking System microservices platform! This repository contains the complete implementation for Student 5 (Notification & Offer Service).

 ### 👤 Student 5 Overview & Responsibilities</h2>
| Field | Detail  |
| :---- | :---- |
| Student Role |  Student 5 / Notification & Offer Service Lead |
|Microservice Name |notification-offer-service |
|Tech Stack |"Java 21, Spring Boot 3.x, MongoDB, OpenAPI 3 (Swagger UI), Docker"|
|Responsibilities |"Booking Confirmation Processing, Offer & Discount Management, Promo Code Validation, Notification History Tracking, API Key Security (X-API-KEY)"|

### 🚀 Microservice Endpoints (Student 5)</h2>
|HTTP Method| Endpoint Path |Description |Access Level|
|:--- | :--- | :---| :---|
|POST|/api/notifications/confirm-booking| Send booking confirmation email/SMS and store record in DB| Public / Service-to-Service|
|POST|/api/notifications/offers/validate-promo|Validate promo code and calculate discounted price|Public
|POST|/api/notifications/offers/subscribe|Subscribe user email to discount alerts|Public|
|GET|/api/notifications/history/{userId}|Get notification history for a user from database|Public|

### 🛡️ Security & Infrastructure Features</h2>
1. API Key Verification: 
* Header enforced: X-API-KEY.
* Default Secret Key: notification-service-secret-key.

2. OpenAPI / Swagger Integration:
* Integrated SpringDoc OpenAPI with built-in API key authorization UI.
* Direct documentation access bypassing API key filter for testing.

3. MongoDB Integration:
* Stores user notifications in notification_db database under notifications collection.
  
4. Containerization & Deployment:
* Fully containerized with Docker and orchestrated alongside MongoDB via Docker Compose.

### ⚙️ How to Run the Project (Step-by-Step)</h2>
Using Docker (Recommended for Submission)
1. Open Docker Desktop on your computer.
2. Open PowerShell / Terminal in the project root folder.
3. Run the following command:
Bash - 
docker compose up --build
4. Docker will download MongoDB and build notification-offer-service.
5. Access Swagger UI in your browser: http://localhost:8085/swagger-ui/index.html

### 🧪 Testing with Postman (1-Click Collection)</h2>
1. Open Postman.
2. Set up headers for all requests:
* X-API-KEY: notification-service-secret-key
* Content-Type: application/json
3. Run the requests in sequence:
* 1. Confirm Booking: POST http://localhost:8085/api/notifications/confirm-booking — Saves a new booking notification.
* 2. Validate Promo: POST http://localhost:8085/api/notifications/offers/validate-promo — Calculates discounts.
* 3. Subscribe Offer: POST http://localhost:8085/api/notifications/offers/subscribe?email=student@example.com — Subscribes user to alerts.
* 4. Get History: GET http://localhost:8085/api/notifications/history/user123 — Fetches saved records from MongoDB.
