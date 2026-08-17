# Service-Oriented Computing (SOC) Project - Student 2

## Microservice: Hotel & Room Service (Service 2)

**Student Role**: Member / Student 2  
**Microservice Name**: Hotel & Room Service (`hotel-room-service`)  
**Technology Stack**: Java 17/21, Spring Boot 3.2.x, Spring Data MongoDB, OpenAPI 3.0 (Swagger), Docker & Docker Compose  
**Service Port**: `8082`  
**Database**: MongoDB (`27017`)  
**DB Admin Interface**: Mongo Express (`8081`)  

---

## 📌 Architecture & Responsibilities

The **Hotel & Room Service** is responsible for managing:
1. **Hotel Listings**: Complete CRUD operations for hotels, search by location, rating, contact details, and amenities.
2. **Room Catalog**: Managing room types (Single, Double, Suite, Deluxe), pricing per night, capacity, and feature list.
3. **Real-time Room Availability**: Updating and retrieving room availability status (`available: true/false`).
4. **API Security**: Enforcing `X-API-KEY` header authentication (`hotel-service-secret-key-123`).
5. **Interactive OpenAPI / Swagger Documentation**: Available live at runtime.

---

## 🚀 How to Run the System (Quick Step-by-Step)

### Option 1: Running with Docker Compose (Recommended)

1. Open PowerShell or Terminal in the project root folder:
   ```bash
   cd "c:\Users\NEW PCWORLD\Desktop\soc project"
   ```
2. Run Docker Compose to build and start MongoDB, Mongo Express, and Hotel Service:
   ```bash
   docker compose up --build
   ```
3. Once running, access the following URLs in your browser:
   - **Swagger UI (Interactive API Docs)**: [http://localhost:8082/swagger-ui/index.html](http://localhost:8082/swagger-ui/index.html)
   - **Mongo Express (Web DB Dashboard)**: [http://localhost:8081](http://localhost:8081)
   - **Hotel API Base URL**: `http://localhost:8082/api/v1/hotels`

---

### Option 2: Running Locally via Spring Boot Maven Plugin

1. Ensure MongoDB is running locally on port `27017` or start MongoDB container:
   ```bash
   docker compose up -d mongodb
   ```
2. Navigate into `hotel-room-service` folder:
   ```bash
   cd hotel-room-service
   ```
3. Run the Spring Boot application:
   ```bash
   ./mvnw spring-boot:run
   ```
   *(Or on Windows Command Prompt: `mvnw.cmd spring-boot:run`)*

---

## 🔑 Security & API Key Format

All API requests to `/api/v1/**` must include the following HTTP Header:

- **Header Name**: `X-API-KEY`
- **Header Value**: `hotel-service-secret-key-123`

### Example Curl Request:
```bash
curl -X GET "http://localhost:8082/api/v1/hotels" \
     -H "X-API-KEY: hotel-service-secret-key-123"
```

If the header is missing or incorrect, the service returns:
```json
{
  "success": false,
  "message": "Unauthorized: Invalid or missing X-API-KEY header."
}
```

---

## 📡 API Endpoints Summary

| Method | Endpoint Path | Description | Required Header |
| :--- | :--- | :--- | :--- |
| `GET` | `/api/v1/hotels` | Get all hotels (Optionally filter by `location`) | `X-API-KEY` |
| `GET` | `/api/v1/hotels/{id}` | Get hotel details by ID | `X-API-KEY` |
| `POST` | `/api/v1/hotels` | Create new hotel listing (Admin endpoint) | `X-API-KEY` |
| `PUT` | `/api/v1/hotels/{id}` | Update existing hotel information | `X-API-KEY` |
| `DELETE` | `/api/v1/hotels/{id}` | Delete hotel listing | `X-API-KEY` |
| `GET` | `/api/v1/hotels/{id}/rooms` | Get all rooms for a hotel | `X-API-KEY` |
| `POST` | `/api/v1/hotels/{id}/rooms` | Add a new room to a hotel | `X-API-KEY` |
| `GET` | `/api/v1/rooms/{id}` | Get room details by room ID | `X-API-KEY` |
| `PUT` | `/api/v1/rooms/{id}/availability` | Update room availability status (`true/false`) | `X-API-KEY` |
| `DELETE` | `/api/v1/rooms/{id}` | Delete room | `X-API-KEY` |

---

## 🧪 Testing with Postman

1. Open Postman.
2. Click **Import** -> Select file `Hotel_Room_Service.postman_collection.json`.
3. Execute any request in the collection! The header `X-API-KEY` is already pre-configured.

---

## 📄 Final Report Documentation Guide
For step-by-step instructions on what screenshots to take and what text to copy into your PDF assignment report, open:
`STUDENT_2_REPORT_GUIDE.md`
