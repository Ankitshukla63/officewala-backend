# Office Wala Backend

A Spring Boot REST API for the "Office Wala" humorous Indian corporate life media platform.

## Prerequisites
- Java 21
- Maven
- MySQL Database

## Setup Instructions

1. **Database Setup**
   Create a MySQL database or let the application create it.
   The default application properties expect a local MySQL instance:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/officewala?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
   spring.datasource.username=root
   spring.datasource.password=root
   ```
   If your MySQL runs on a different port or has different credentials, update `src/main/resources/application.properties`.

2. **Build the Application**
   ```bash
   mvn clean install
   ```

3. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```

4. **Verify Application**
   The application will run on `http://localhost:8080`.

## Features
- JWT Authentication
- Video Management
- Deadline Notes Management
- Friday Posts Management
- Local File Uploads mapped to `/uploads/**`

## API Details
All API endpoints (except `/api/auth/**` and `/uploads/**`) require a valid JWT token sent in the `Authorization` header as `Bearer <token>`.
