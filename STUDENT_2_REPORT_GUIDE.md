# Final Project Report Guide - Student 2 (Hotel & Room Service)

> **ගුරුපදේශය (Note for Student):**  
> මෙම ලේඛනය ඔබගේ **Service-Oriented Computing (SOC)** Final Group Report එක සකස් කර ගැනීමට සහ Screenshots ලබා ගැනීමට අවශ්‍ය සියලුම තොරතුරු ඇතුළත් කර සකස් කර ඇත. ඔබ කිසිවක් දන්නේ නැතත් (beginner), පහත දක්වා ඇති පියවර (Steps) එකින් එක අනුගමනය කර ඔබගේ Report එක සාර්ථකව නිමකර ගත හැක.

---

## 1. Student Responsibility Matrix (Report Section)

| Field | Details |
| :--- | :--- |
| **Student Name / Number** | Student 2 |
| **Assigned Role** | Microservice Developer / Team Member |
| **Microservice Name** | **Hotel & Room Service** (`hotel-room-service`) |
| **Tech Stack** | Java 17/21, Spring Boot 3.2.5, Spring Data MongoDB, OpenAPI 3.0 (Swagger), Docker |
| **Port** | `8082` |
| **Database** | MongoDB (`27017`) |
| **Authentication Scheme** | Custom API Key Header Verification (`X-API-KEY`) |
| **Key Responsibilities** | Manages hotel listings, room types, pricing models, and real-time room availability. |

---

## 2. API Endpoints & Specification Table

Below is the complete list of RESTful API endpoints implemented for Student 2:

```
+--------+------------------------------------+-------------------------------------------+-----------------------------------+
| Method | Endpoint Path                      | Description                               | Header Security                   |
+--------+------------------------------------+-------------------------------------------+-----------------------------------+
| GET    | /api/v1/hotels                     | Retrieve list of all hotels               | X-API-KEY: hotel-service-...-123 |
| GET    | /api/v1/hotels?location={city}     | Search hotels by location/city            | X-API-KEY: hotel-service-...-123 |
| GET    | /api/v1/hotels/{id}                | Get detailed hotel profile by ID          | X-API-KEY: hotel-service-...-123 |
| POST   | /api/v1/hotels                     | Create new hotel listing (Admin)          | X-API-KEY: hotel-service-...-123 |
| PUT    | /api/v1/hotels/{id}                | Update hotel info by ID                   | X-API-KEY: hotel-service-...-123 |
| DELETE | /api/v1/hotels/{id}                | Remove hotel listing by ID                | X-API-KEY: hotel-service-...-123 |
| GET    | /api/v1/hotels/{id}/rooms          | List all rooms for a specific hotel       | X-API-KEY: hotel-service-...-123 |
| POST   | /api/v1/hotels/{id}/rooms          | Create a room for a specific hotel        | X-API-KEY: hotel-service-...-123 |
| GET    | /api/v1/rooms/{id}                 | Get room details by room ID               | X-API-KEY: hotel-service-...-123 |
| PUT    | /api/v1/rooms/{id}/availability    | Update room availability status           | X-API-KEY: hotel-service-...-123 |
| DELETE | /api/v1/rooms/{id}                 | Delete room by ID                         | X-API-KEY: hotel-service-...-123 |
+--------+------------------------------------+-------------------------------------------+-----------------------------------+
```

---

## 3. Database Schema Design (MongoDB NoSQL Documents)

The **Hotel & Room Service** uses MongoDB as its document storage database, allowing flexible schema design for complex hotel amenities and room attributes.

### A. `hotels` Collection Schema
```json
{
  "_id": "66c1f2a3b4e5f67890123456",
  "name": "Grand Palace Hotel & Spa",
  "description": "Luxury 5-star oceanfront resort with full spa services.",
  "location": "Colombo",
  "address": "100 Galle Face Green, Colombo 03",
  "rating": 4.8,
  "amenities": ["WiFi", "Swimming Pool", "Spa", "Fitness Center", "Ocean View"],
  "contactEmail": "info@grandpalace.com",
  "contactPhone": "+94112345678",
  "createdAt": "2026-08-17T21:45:00.000"
}
```

### B. `rooms` Collection Schema
```json
{
  "_id": "66c1f2a3b4e5f67890654321",
  "hotelId": "66c1f2a3b4e5f67890123456",
  "roomNumber": "101",
  "roomType": "DELUXE",
  "pricePerNight": 150.00,
  "capacity": 2,
  "available": true,
  "description": "Deluxe Double Room with private balcony overlooking the ocean.",
  "features": ["King Bed", "Air Conditioning", "Sea View", "Minibar", "TV"]
}
```

---

## 4. API Key Security Implementation

To satisfy section **2.A (API Key Security)** of the coursework brief:
- The service implements `ApiKeyInterceptor.java` which inspects every incoming HTTP request to `/api/v1/**`.
- If the HTTP header `X-API-KEY` is missing or does not match `hotel-service-secret-key-123`, the request is immediately rejected with HTTP Status Code `401 Unauthorized`.
- Public documentation endpoints (`/swagger-ui/**`, `/v3/api-docs/**`) are white-listed so evaluators can inspect the interactive API documentation without impediment.

---

## 📸 5. STEP-BY-STEP GUIDE FOR RUNNING & TAKING SCREENSHOTS FOR YOUR REPORT

ඔබගේ Final PDF Report එකට අවශ්‍ය Screenshots ගැනීමට පහත සරල පියවර (Steps) අනුගමනය කරන්න:

### Step 1: Run the Project with Docker
1. Windows වල Command Prompt හෝ PowerShell එකක් open කර පහත command එක ලබා දෙන්න:
   ```cmd
   cd "c:\Users\NEW PCWORLD\Desktop\soc project"
   docker compose up --build
   ```
2. Terminal එකේ `Started HotelRoomServiceApplication` සහ `Sample Data Initialized Successfully!` ලෙස පෙන්වන තෙක් තත්පර කිහිපයක් රැඳී සිටින්න.

---

### Screenshot 1: Docker Containers Running
- **Action**: Open Docker Desktop app OR open terminal and run `docker ps`.
- **What to capture**: Docker Desktop showing containers `hotel_room_service`, `hotel_mongodb`, and `hotel_mongo_express` running in green status.
- **Caption for Report**: *"Figure 1: Docker Containers running MongoDB, Mongo Express, and Hotel & Room Service Microservice."*

---

### Screenshot 2: Swagger UI Interactive API Documentation
- **Action**: Chrome/Edge browser එකේ [http://localhost:8082/swagger-ui/index.html](http://localhost:8082/swagger-ui/index.html) ලබා දෙන්න.
- **What to capture**:
  1. Header text showing **Hotel & Room Service API - Student 2**.
  2. The list of expandables for `Hotel Controller` and `Room Controller`.
  3. Green **Authorize** button showing `X-API-KEY` configuration.
- **Caption for Report**: *"Figure 2: OpenAPI 3.0 / Swagger UI documentation interface for Student 2."*

---

### Screenshot 3: Postman Request - GET All Hotels (Success 200 OK)
- **Action**:
  1. Postman app එක open කර `Import` -> `Hotel_Room_Service.postman_collection.json` තෝරන්න.
  2. `1. Hotels Management` -> `GET All Hotels` request එක open කරන්න.
  3. **Send** බොත්තම ක්ලික් කරන්න.
- **What to capture**: Postman screen showing `Status: 200 OK`, `X-API-KEY` header in request, and JSON array of sample hotels in response.
- **Caption for Report**: *"Figure 3: Successful GET /api/v1/hotels request returning pre-populated hotel listings."*

---

### Screenshot 4: Postman Request - PUT Update Room Availability (Matrix Endpoint Requirement)
- **Action**:
  1. Postman හි `2. Room & Availability Management` -> `PUT Update Room Availability Status` open කරන්න.
  2. URL එකේ `REPLACE_WITH_ROOM_ID` වෙනුවට `GET Rooms for a Hotel` මගින් ලැබුණු room ID එකක් හෝ MongoDB Mongo-Express වෙතින් ගත් ID එකක් දමන්න.
  3. **Send** ක්ලික් කරන්න.
- **What to capture**: Status `200 OK` and Response Body showing `"available": false`.
- **Caption for Report**: *"Figure 4: Updating real-time room availability status endpoint execution."*

---

### Screenshot 5: Security Test - Unauthorized Request (401 Response)
- **Action**:
  1. Postman හි `3. Security Verification Tests` -> `UNAUTHORIZED - GET Hotels Without API Key` open කරන්න.
  2. **Send** ක්ලික් කරන්න.
- **What to capture**: Request without `X-API-KEY` returning `Status: 401 Unauthorized` and message `"Unauthorized: Invalid or missing X-API-KEY header."`.
- **Caption for Report**: *"Figure 5: API Key verification rejecting unauthenticated direct call with 401 Unauthorized."*

---

### Screenshot 6: Mongo Express Database Web Dashboard
- **Action**: Browser එකේ [http://localhost:8081](http://localhost:8081) open කර `hotel_db` -> `hotels` / `rooms` collections වෙත යන්න.
- **What to capture**: Web dashboard displaying stored MongoDB documents for hotels and rooms.
- **Caption for Report**: *"Figure 6: Mongo Express dashboard displaying stored MongoDB hotel and room documents."*
