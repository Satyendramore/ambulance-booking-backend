# Ambulance Booking Backend

A Spring Boot backend for an Ambulance Booking system with role-based access, booking management, and auto-expiry.

## GitHub Repository
[https://github.com/Satyendramore/ambulance-booking-backend](https://github.com/Satyendramore/ambulance-booking-backend)

---

## Architecture Decisions

- **Framework:** Spring Boot  
- **Database:** MySQL for persistent storage  
- **Layers:**
  - **Controller Layer:** REST APIs for users and drivers
  - **Service Layer:** Business logic and transaction management
  - **Repository Layer:** Database interactions using Spring Data JPA
- **Security:** Spring Security with JWT for stateless authentication  
- **Auto-expiry:** Bookings automatically expire 2 minutes after creation via expiry time field

---

## Authentication Approach

- **JWT-based authentication**  
- Roles (`ROLE_DRIVER`, `ROLE_USER`) stored in JWT claims  
- Filter (`JWT.java`) validates token and sets `SecurityContext`  
- Role-based endpoint access:
  - `/public/**` → open to all (login, registration)
  - `/user/**` → only `USER` role
  - `/driver/**` → only `DRIVER` role  

**Flow:**  
1. Register → `/public/register`  
2. Login → `/public/login` → JWT token returned  
3. Include JWT in `Authorization: Bearer <token>` header for protected endpoints  
4. Token validated on each request  

---

## Booking Conflicts & Concurrency

- **Transactional service methods** prevent multiple drivers from accepting the same booking simultaneously  
- Booking assignment uses a **conditional update query** (`updatebooking`) to ensure:
  - Only `REQUESTED` and non-expired bookings can be accepted  
  - Expired bookings are rejected with `ExpiredBooking`  
  - Already accepted bookings are rejected with `AllreadyBooked`  
- Expiry is managed via `expiryTime` set 2 minutes after booking creation  

---

## Improvements for Production

- Dockerize the application for deployment  
- Add **API documentation** via Swagger/OpenAPI  

---

## Project Structure


src/
├── main/
│ ├── java/com/Adily/Project/demo
│ │ ├── Configuration/ # Security & JWT filter
│ │ ├── Controller/ # User & Driver endpoints
│ │ ├── Service/ # Business logic
│ │ ├── Repository/ # MySQL database interaction
│ │ ├── Security/ # JWT, UserDetails, UserPrincipal
│ │ ├── Entity/ # UserInfo, BookingInfo
│ │ └── Exception/ # Custom exceptions
│ └── resources/
│ └── application.properties
└── test/
└── java/com/Adily/Project/demo

