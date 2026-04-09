# Skill 15 - Implementing JWT-Based Authentication & Role Authorization

This project demonstrates how to implement secure authentication using JWT tokens and Role-Based Access Control (RBAC) in a Spring Boot application.

## Features
- **User Authentication**: Login endpoint retrieves a JWT token.
- **Role-Based Authorization**:
    - `ADMIN`: Access to `/admin/**` and `/employee/**` endpoints.
    - `EMPLOYEE`: Access to `/employee/**` endpoints.
- **JWT Filter**: Intercepts requests to validate tokens and set security context.
- **H2 Database**: In-memory database for user persistence.

## Prerequisites
- Java 17 or higher
- Maven

## How to Run
1. Clone the repository.
2. Run `mvn spring-boot:run`.
3. The server will start on `http://localhost:8080`.

## Testing with Postman/Curl

### 1. Login to get Token (Admin)
**URL:** `POST http://localhost:8080/login`
**Body:**
```json
{
    "username": "admin",
    "password": "admin123"
}
```
**Response:** `{"token": "JWT_TOKEN_HERE"}`

### 2. Access Admin Endpoint (Add)
**URL:** `POST http://localhost:8080/admin/add`
**Header:** `Authorization: Bearer JWT_TOKEN_HERE`

### 3. Access Employee Profile
**URL:** `GET http://localhost:8080/employee/profile`
**Header:** `Authorization: Bearer JWT_TOKEN_HERE`

### 4. Login as Employee
**URL:** `POST http://localhost:8080/login`
**Body:**
```json
{
    "username": "employee",
    "password": "emp123"
}
```

## Security Rules
- `/login`: Public
- `/h2-console/**`: Public
- `/admin/**`: `ADMIN` role required
- `/employee/**`: `EMPLOYEE` or `ADMIN` role required
