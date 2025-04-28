# InspectionApplication

A Spring Boot–powered REST API for managing **inspection centers**, **inspection reports**, and **vehicles**, complete with interactive Swagger/OpenAPI docs.

---

## 📝 Table of Contents

- [Features](#-features)  
- [Tech Stack](#-tech-stack)  
- [Getting Started](#-getting-started)  
  - [Prerequisites](#prerequisites)  
  - [Installation](#installation)  
  - [Configuration](#configuration)  
  - [Running the App](#running-the-app)  
- [API Documentation](#api-documentation)  
- [Testing](#testing)  
- [Project Structure](#project-structure)  
- [Contributing](#contributing)  
- [License](#license)  
- [Contact](#contact)

---

## ✨ Features

- **CRUD** operations for:
  - Inspection Centers
  - Inspection Reports
  - Vehicles  
- Field-level validation with custom error messages  
- API docs & UI via Swagger/OpenAPI (`springdoc-openapi-starter-webmvc-ui`)  
- Data persistence with Spring Data JPA & MySQL  
- DTO ↔ Entity mapping via MapStruct  
- Global exception handling via `@ControllerAdvice`  
- Logging to console & file  

---

## 🚀 Tech Stack

- **Java** 17  
- **Spring Boot** 3.x  
- **Spring Data JPA** + Hibernate  
- **MySQL Connector/J** (runtime)  
- **MapStruct** for object mapping  
- **Spring Validation** (`spring-boot-starter-validation`)  
- **Swagger/OpenAPI** via `springdoc-openapi-starter-webmvc-ui`  
- **Lombok** (optional, for boilerplate)  
- **Maven** for build & dependency management  
- **JUnit 5** + Mockito for testing  

---

## 🏁 Getting Started

### Prerequisites

- Java 17 SDK  
- Maven 3.6+  
- MySQL (or any JDBC-compatible database)  
- Git  

### Installation

```bash
# Clone the repo
git clone https://github.com/268138/InspectionApplication.git
cd InspectionApplication

# Build without tests
mvn clean install -DskipTests
