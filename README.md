# 💰 Expense Tracker Backend

A secure RESTful backend for the Expense Tracker application built using Spring Boot, Spring Security, JWT Authentication and MySQL.

---

## 📌 Overview

This backend provides secure authentication and expense management APIs for the Expense Tracker application. It follows REST architecture and uses JWT for authentication and BCrypt for password encryption.

---

## ✨ Features

- User Registration
- User Login
- JWT Authentication
- Password Encryption (BCrypt)
- Expense CRUD Operations
- Dashboard Statistics
- Input Validation
- Global Exception Handling
- Secure REST APIs

---

## 🛠 Tech Stack

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT
- MySQL
- Maven

---

## 📁 Project Structure

```
controller
dto
entity
exception
repository
security
service
util
```

---

## 🔐 Authentication

Protected APIs require:

```
Authorization: Bearer <JWT_TOKEN>
```

---

## 📡 API Endpoints

### Authentication

- POST /auth/register
- POST /auth/login

### Expenses

- GET /expenses/my
- POST /expenses/add
- PUT /expenses/update/{id}
- DELETE /expenses/delete/{id}

---

## 🗄 Database

- User
- Expense

Relationship:

User (1) --------> (Many) Expenses

---

## 👩‍💻 Author

**Hemavarshini D**
