# Hotel-Travel-Booking-System

A distributed microservices-based travel management platform built with Spring Boot, Docker, and a single-page frontend application.

# Architecture Overview

- All client traffic routes through a central API Gateway on port 8080. The gateway verifies OAuth 2.0 JWT tokens, enforces rate limits, and attaches internal API keys before forwarding requests over an isolated Docker network to backend microservices.  
- API Gateway & Auth Service (Port 8080) — Central entry point, OAuth 2.0 / JWT issuance, rate limiting, and CORS enforcement.  
- Hotel & Room Service (Port 8082 | MySQL) — Hotel catalog, room inventory, dynamic pricing, and availability management.  
- Reservation Service (Port 8083 | PostgreSQL) — Booking lifecycle, stay modifications, cancellations, and transaction safety.  
- Review & Rating Service (Port 8084 | MongoDB) — Guest reviews, 5-star rating aggregation, and admin moderation.  
- Notification & Offer Service (Port 8085 | Redis/PostgreSQL) — Email/SMS notifications, promo codes, and discount validation.  
- Client App (Port 3000) — Single-page user interface (React / Vue / HTML5).
  
---

# Security Features
- Internal API Key Interceptor: Backend services require the header X-API-KEY: hotel_sec_key_prod_89f10a2c4e. Direct requests without this header are blocked with HTTP 401 Unauthorized
- OAuth 2.0 & JWT Authentication: User authentication issues RSA256-signed Bearer JWT tokens with standard claims (sub, roles, exp) for downstream authorization.
- Rate Limiting: Token Bucket algorithm (Bucket4j/Redis) enforces a limit of 100 requests per minute per IP.
- CORS Policy: Restricts origins to http://localhost:3000 for GET, POST, PUT, DELETE, and OPTIONS requests.

# API Endpoints

1. Gateway & Auth Service
   <img width="887" height="237" alt="image" src="https://github.com/user-attachments/assets/7dff8a23-f3c3-48b4-a778-61679398b224" />
2. Hotel & Room Service
  <img width="887" height="346" alt="image" src="https://github.com/user-attachments/assets/9f2cc05e-3eb3-40ea-8e82-b345aa3c2392" />
3. Reservation Service
   <img width="882" height="260" alt="image" src="https://github.com/user-attachments/assets/36bbc023-74c5-4956-a2e0-4833022230f5" />
4. Review & Rating Service
   <img width="890" height="255" alt="image" src="https://github.com/user-attachments/assets/d4260dd3-1527-4572-9250-149550c988b1" />
5. Notification & Offer Service
   <img width="888" height="266" alt="image" src="https://github.com/user-attachments/assets/c938ed40-396c-4f69-9ad1-846032202a09" />

Running the Application

Prerequisites
- Docker & Docker Compose
- Java 17+ (for local builds)

---




   
      
