# 🔐 Spring Boot User Management API

A secure, scalable RESTful API for user management built with Spring Boot and Spring Security.
This backend system supports full user account operations with robust authentication, role-based access control, input validation, and clean architecture—ideal for real-world applications.

---

## 🚀 Features

- User CRUD (Create, Read, Update, Delete)
- Role-based access control (ADMIN, USER)
- Secure password encryption with BCrypt
- REST API using Spring Web
- Request/Response DTOs
- Input validation (Jakarta Bean Validation)
- Global exception handling
- MySQL database integration (via Spring Data JPA)
- Swagger UI for interactive documentation
- Preloaded roles and admin user (via DataInitializer)

---

## 🛠 Tech Stack

- Java 17+
- Spring Boot 3.x
- Spring Security 6.x
- Spring Data JPA
- MySQL
- Jakarta Bean Validation
- Springdoc OpenAPI (Swagger UI)
- Maven

---

## 🔗 API Endpoints

| Method | Endpoint           | Description              | Access                     |
|--------|--------------------|--------------------------|----------------------------|
| GET    | `/api/users`       | Get all users            | ADMIN                      |
| GET    | `/api/users/{id}`  | Get user by ID           | ADMIN or USER (own data)   |
| POST   | `/api/users`       | Register new user        | PUBLIC (open registration) |
| PUT    | `/api/users/{id}`  | Update user by ID        | ADMIN or USER (own data)   |
| DELETE | `/api/users/{id}`  | Delete user by ID        | ADMIN only                 |

---

## 🧑‍🔧 Default Users (Pre-loaded for Testing)

| Role  | Email                                         | Password |
| ----- | --------------------------------------------- | -------- |
| Admin | [admin@example.com](mailto:admin@example.com) | admin123 |
| User  | [user@example.com](mailto:user@example.com)   | user123  |

Passwords are stored using BCrypt encryption.

---

## 🔏 Role-Based Access Control

ADMIN can perform all actions.
USER can only view, update, or delete their own data.

---

## ✅ Validation Rules

| Field    | Rule                   |
| -------- | ---------------------- |
| fullName | Required, not blank    |
| email    | Required, valid format |
| password | Required, min length 6 |

---

## ❗ Global Exception Handling

| HTTP Code | Error Type  | When It Happens                     |
| --------- | ----------- | ----------------------------------- |
| 400       | Bad Request | Validation fails or invalid payload |
| 403       | Forbidden   | Unauthorized access                 |
| 404       | Not Found   | User/resource doesn't exist         |
| 409       | Conflict    | Duplicate email                     |

---

## ▶️ Run Locally

git clone https://github.com/afzal-ka/springboot-user-management-api.git
cd springboot-user-management-api

---

## 📘 Swagger UI

After running the application, open:
```
http://localhost:8080/swagger-ui.html
```
Test and explore the API using the interactive Swagger interface.

---

## 🧪 Sample JSON (POST/PUT)

{
  "name": "user",
  "email": "user@example.com",
  "password": "user123"
}

---

👤 Author

AFZAL K A
📎 GitHub: @afzal-ka

---

## 📃 License

This project is licensed under the MIT License.


