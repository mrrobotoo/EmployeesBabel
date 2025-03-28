# EmployeesBabel

# Employee Management API

## 📌 Project Overview
This is a RESTful API for managing employees, built using **Spring Boot** with **Clean Architecture** principles. The API allows operations such as:
- Retrieving all employees
- Adding new employees
- Updating existing employees
- Deleting employees

## 🛠 Technologies Used
- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Jakarta Validation** (for input validation)
- **Lombok** (for reducing boilerplate code)
- **Oracle** (or any relational database)
- **Maven** (for dependency management and building)

## 📂 Project Structure
```bash
src/main/java/com/babelgroup/employees/
├── controller   # API Controllers
├── dto          # Data Transfer Objects (DTOs)
├── entities     # JPA Entities
├── exceptions   # Custom Exception Handling
├── handler      # Global Exception Handlers
├── repository   # Database Access (JPA Repositories)
├── service      # Business Logic Layer
└── EmployeesBabelApplication.java  # Main application entry point
```

## 🚀 Running the Project
### 1️⃣ Clone the Repository
```bash
git clone https://github.com/mrrobotoo/EmployeesBabel.git
cd employees-api
```
### 2️⃣ Configure Database (PostgreSQL / MySQL)
Update `application.properties` with your database settings:
```properties
spring.datasource.url=jdbc:oracle:thin:@//localhost:1521/xe
spring.datasource.username=your_user
spring.datasource.password=your_password
```

### 3️⃣ Build and Run with Maven
```bash
mvn clean install
mvn spring-boot:run
```

### 4️⃣ API Endpoints

#### 📌 Get All Employees
**Request:**
```http
GET /api/employees
```
**Response:**
```json
{
  "msg": "ok",
  "data": [
    {
      "id": 1,
      "firstName": "John",
      "lastName": "Doe",
      "age": 30,
      "gender": "M",
      "jobPosition": "Software Engineer"
    }
  ]
}
```

#### 📌 Create Employees
**Request:**
```http
POST /api/employees
Content-Type: application/json
```
```json
[
  {
    "firstName": "Alice",
    "lastName": "Smith",
    "age": 28,
    "gender": "F",
    "jobPosition": "Project Manager"
  }
]
```
**Response:**
```json
{
  "msg": "Employees were inserted successfully."
}
```

#### 📌 Update Employee
**Request:**
```http
PUT /api/employees
Content-Type: application/json
```
```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "age": 31
}
```
**Response:**
```json
{
  "msg": "The user was updated successfully."
}
```

#### 📌 Delete Employee
**Request:**
```http
DELETE /api/employees?idEmployee=1
```
**Response:**
```json
{
  "msg": "Successfully removed"
}
```

## ⚠️ Error Handling
If a resource is not found:
```json
{
  "msg": "Employee not found with ID: 99"
}
```
If validation fails:
```json
{
  "msg": "Validation failed: First name cannot be null"
}
```

## 📝 License
This project is licensed under the MIT License.

---

