# Enterprise OAuth2 & JWT Security System

## Overview

This project is a production-grade Authentication and Authorization System built using Spring Boot, Spring Security, OAuth2 Authorization Server, JWT, Redis, and MYSQL.

The system provides secure authentication, authorization, token management, multi-tenancy support, audit logging, rate limiting, and scalable security architecture for microservices-based applications.

---

## Features

### Authentication

* Username/Password Authentication
* JWT Access Token Generation
* Refresh Token Support
* Secure Logout
* BCrypt Password Encryption

### Authorization

* OAuth2 Authorization Server
* JWT Resource Server
* Role-Based Access Control (RBAC)
* Permission-Based Access Control (ABAC)
* Custom JWT Claims

### Security Enhancements

* Token Revocation
* Redis Token Blacklisting
* Audit Logging
* Rate Limiting
* Multi-Tenant Support
* HTTPS Ready
* SQL Injection Protection
* Secure HTTP Headers

---

## Architecture

```text
Client
   |
   v
API Gateway
   |
   +------------------+
   |                  |
   v                  v
Auth Server      Resource Services
   |
   v
User Service
   |
   +----------+
   |          |
   v          v
PostgreSQL   Redis
```

---

## Technology Stack

### Backend

* Java 17
* Spring Boot 3.x
* Spring Security 6
* Spring Authorization Server
* Spring Data JPA

### Database

* PostgreSQL

### Caching

* Redis

### Documentation

* Swagger/OpenAPI

### Build Tool

* Maven

---

## Database Schema

### users

| Column    | Type    |
| --------- | ------- |
| id        | BIGINT  |
| username  | VARCHAR |
| password  | VARCHAR |
| tenant_id | VARCHAR |

### roles

| Column    | Type    |
| --------- | ------- |
| id        | BIGINT  |
| role_name | VARCHAR |

### permissions

| Column          | Type    |
| --------------- | ------- |
| id              | BIGINT  |
| permission_name | VARCHAR |

### user_roles

| Column  | Type   |
| ------- | ------ |
| user_id | BIGINT |
| role_id | BIGINT |

### role_permissions

| Column        | Type   |
| ------------- | ------ |
| role_id       | BIGINT |
| permission_id | BIGINT |

### refresh_tokens

| Column      | Type      |
| ----------- | --------- |
| id          | BIGINT    |
| token       | VARCHAR   |
| expiry_date | TIMESTAMP |

### audit_logs

| Column     | Type      |
| ---------- | --------- |
| id         | BIGINT    |
| username   | VARCHAR   |
| action     | VARCHAR   |
| ip_address | VARCHAR   |
| created_at | TIMESTAMP |

---

## API Endpoints

### Authentication

#### Login

```http
POST /auth/login
```

Request

```json
{
  "username": "admin",
  "password": "Admin@123"
}
```

Response

```json
{
  "accessToken": "jwt-token",
  "refreshToken": "refresh-token"
}
```

---

#### Refresh Token

```http
POST /auth/refresh
```

Request

```json
{
  "refreshToken": "refresh-token"
}
```

---

#### Logout

```http
POST /auth/logout
```

Headers

```http
Authorization: Bearer ACCESS_TOKEN
```

---

### User APIs

```http
GET /users
POST /users
PUT /users/{id}
DELETE /users/{id}
```

---

### Admin APIs

```http
GET /admin/dashboard
GET /admin/users
```

---

## JWT Claims

Example Payload

```json
{
  "sub": "admin",
  "tenant_id": "tenant_001",
  "roles": [
    "ADMIN"
  ],
  "permissions": [
    "USER_READ",
    "USER_WRITE"
  ]
}
```

---

## Configuration

### PostgreSQL

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/authdb
spring.datasource.username=postgres
spring.datasource.password=postgres
```

### Redis

```properties
spring.data.redis.host=localhost
spring.data.redis.port=6379
```

### JWT

```properties
jwt.secret=your-secret-key
jwt.access-token-expiration=900000
jwt.refresh-token-expiration=604800000
```

---

## Running the Project

### Clone Repository

```bash
git clone https://github.com/your-username/oauth2-jwt-security.git
```

### Build Project

```bash
mvn clean install
```

### Run Application

```bash
mvn spring-boot:run
```

---

## Swagger Documentation

Open:

```text
http://localhost:9090/swagger-ui/index.html
```

or

```text
http://localhost:9090/swagger-ui.html
```

---

## Postman Testing

### Login

```http
POST /auth/login
```

Copy the access token from the response.

### Authorize Requests

Add Header:

```http
Authorization: Bearer <access_token>
```

---

## Performance Targets

* 1000+ Concurrent Users
* Token Validation < 50ms
* Redis-Based Token Caching
* Stateless Authentication

---

## Security Considerations

* BCrypt Password Storage
* JWT Signature Validation
* Refresh Token Rotation
* Token Blacklisting
* Audit Logging
* Multi-Tenant Isolation
* Rate Limiting
* Secure Headers

---

## Deliverables

* Source Code
* Swagger Documentation
* Postman Collection
* Architecture Diagram
* README Documentation

---

## Author

Enterprise OAuth2 & JWT Security System
Spring Boot | OAuth2 | JWT | Redis | PostgreSQL
