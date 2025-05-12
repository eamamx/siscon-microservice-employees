# siscon-microservice-employees

Microservice with CRUD operations for Siscon employees.

## Characteristics

- Full employee CRUD  
- Hexagonal / Clean architecture  
- Spring Boot 3.x, Java 17  
- Spring Data JPA + H2 in-memory database  
- Swagger/OpenAPI 3 for interactive documentation  
- Exception handling with `@ControllerAdvice`  
- Logging configured with Logback  
- Data validation using DTOs  
- Unit testing with JUnit 5 and Mockito  
- Integration testing with embedded H2  
- Docker + Docker Compose  
- Integration with SonarQube  

---

## Instructions

### 1. Clone the project

```bash
git clone https://github.com/eamamx/siscon-microservice-employees.git
cd siscon-microservice-employees
```

### 2. Run the app

```bash
mvn clean package
mvn spring-boot:run
```

or also


## Docker

### Run the application with Docker Compose

Make sure Docker is installed and running on your machine. Then run:

```bash
docker-compose up --build

---

## Swagger

Access the interactive API documentation:

**http://localhost:8080/swagger-ui.html**

---

## API Endpoints - Employee Management

**Base URL**: `/api/employees`

### 🔹 GET `/api/employees`

**Description**: Get a list of all employees  
**Response**: `200 OK`

---

### 🔹 GET `/api/employees/{id}`

**Description**: Get an employee by ID  
**Response**:  
- `200 OK` (if found)  
- `404 Not Found` (if not found)

---

### 🔹 POST `/api/employees`

**Description**: Create a new employee  
**Request Body**:

```json
{
  "firstName": "Ana",
  "lastName": "Vivas",
  "age": 38,
  "gender": "F",
  "birthDate": "20-04-1987",
  "position": "Tester"
}
```

**Response**: `201 Created`

---

### 🔹 POST `/api/employees/all`

**Description**: Create multiple employees at once  
**Request Body**:

```json
[
  {
    "firstName": "Ana",
    "lastName": "Vivas",
    "age": 38,
    "gender": "F",
    "birthDate": "20-04-1987",
    "position": "Tester"
  },
  {
    "firstName": "Ana2",
    "lastName": "Vivas",
    "age": 38,
    "gender": "F",
    "birthDate": "20-04-1987",
    "position": "Tester"
  }
]
```

**Response**: `201 Created`

---

### 🔹 PUT `/api/employees/{id}`

**Description**: Update an existing employee  
**Request Body**: Same as POST  
**Response**:  
- `200 OK` (if updated)  
- `404 Not Found` (if employee does not exist)

---

### 🔹 DELETE `/api/employees/{id}`

**Description**: Delete an employee by ID  
**Response**:  
- `204 No Content` (if deleted)  
- `404 Not Found`
