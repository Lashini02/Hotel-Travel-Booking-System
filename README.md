# Service-Oriented Computing (SOC) - Group Project
## Student 1: API Gateway & Auth Service Implementation

Welcome to the **Hotel & Travel Booking System** microservices platform!
This repository contains the complete implementation for **Student 1 (Gateway Lead & Auth Service)**.

---

## 👤 Student 1 Overview & Responsibilities

| Field | Detail |
| --- | --- |
| **Student Role** | Student 1 / Gateway Lead & Auth Service |
| **Microservice Name** | `auth-gateway-service` |
| **Tech Stack** | Java 17, Spring Boot 3.2.3, MongoDB, Spring Security, JWT (OAuth 2.0), Bucket4j, OpenAPI 3 (Swagger UI), Docker |
| **Responsibilities** | API Gateway setup, OAuth 2.0 / JWT Token Management, User Management & Profiles, Rate Limiting (10 req/min), CORS rules, API Key Security (`X-API-KEY`) |

---

## 🚀 Microservice Endpoints (Student 1)

| HTTP Method | Endpoint Path | Description | Access Level |
| --- | --- | --- | --- |
| `POST` | `/auth/register` | Register new user, encrypt password with BCrypt, assign API key & return JWT | Public |
| `POST` | `/auth/login` | Authenticate user credentials & return JWT token + API Key | Public |
| `GET` | `/auth/validate` | Validate JWT Token for Gateway & downstream microservices | Public |
| `GET` | `/users/profile` | Retrieve profile of authenticated user | Authenticated (JWT) |
| `PUT` | `/users/profile` | Update profile (fullName, phone, address) of authenticated user | Authenticated (JWT) |

---

## 🛡️ Security & Infrastructure Features

1. **OAuth 2.0 / JWT Token Management**:
   - High-security HMAC-SHA256 token generation and signature verification.
   - Tokens contain `username`, `email`, `role`, and `userId` claims.
2. **API Key Verification**:
   - Header enforced: `X-API-KEY`.
   - Default Global Key: `SOC-SECRET-API-KEY-2026` or individual user API keys generated upon registration.
3. **Rate Limiting**:
   - Powered by **Bucket4j**.
   - Enforces a limit of **10 requests per minute per IP**. Exceeding returns `HTTP 429 Too Many Requests`.
4. **CORS (Cross-Origin Resource Sharing)**:
   - Configured globally to support requests from React, Angular, Vue, Flutter, or desktop clients.
5. **MongoDB Integration**:
   - Stores users in `soc_auth_db` collection with encrypted passwords (`BCryptPasswordEncoder`).

---

## ⚙️ How to Run the Project (Step-by-Step)

### Option A: Using Docker (Recommended for Submission)

1. Open **Docker Desktop** on your computer.
2. Open PowerShell / Terminal in the project root folder (`c:\Users\USER\Desktop\SOC Project`).
3. Run the following command:
   ```bash
   docker compose up --build
   ```
4. Docker will download MongoDB and build `auth-gateway-service`.
5. Access Swagger UI in your browser:
   `http://localhost:8080/swagger-ui.html`

---

## 🧪 Testing with Postman (1-Click Collection)

1. Open **Postman**.
2. Click **Import** -> Select the file `SOC_Student1_Postman_Collection.json` located in the root directory.
3. Run the requests in sequence:
   - **1. Auth - Register User**: Creates a new user. The JWT token is automatically saved!
   - **2. Auth - Login User**: Log in with credentials.
   - **3. User - Get Profile**: Fetches profile data using the saved JWT token.
   - **4. User - Update Profile**: Updates user's name, phone, or address.
   - **5. Auth - Validate JWT Token**: Verifies token validity.

---

