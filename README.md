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

## 📸 Screenshots Needed for Final Report (Student 1)

Take screenshots of the following for your report PDF:
1. **Docker Running**: Screenshot of Docker Desktop or PowerShell showing `docker compose up` running without errors.
2. **Swagger UI**: Open `http://localhost:8080/swagger-ui.html` in browser showing all endpoints (`/auth/register`, `/auth/login`, `/users/profile`, etc.).
3. **Postman Register (`POST /auth/register`)**: Response showing status `201 Created` with JWT token and API Key.
4. **Postman Login (`POST /auth/login`)**: Response showing status `200 OK` with token.
5. **Postman Get Profile (`GET /users/profile`)**: Response showing user details with `Authorization: Bearer <token>` header.
6. **Postman Rate Limiting Test**: Send request 11 times quickly to see `429 Too Many Requests`.
7. **Postman API Key Test**: Remove `X-API-KEY` header to show `401 Unauthorized` response.
