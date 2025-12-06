# Expense Tracker API

A simple RESTful API for managing personal expenses built with **Java**, **Spring Boot**, and **PostgreSQL**.

---

## Features

- Create, Read, Update, Delete (CRUD) expenses
- Validation using `@Valid` annotations
- Global Exception Handling
- Pagination & Sorting for GET expenses
- Swagger/OpenAPI documentation available

---

## Tech Stack

- **Backend:** Java 21, Spring Boot 3
- **Database:** PostgreSQL
- **ORM:** Spring Data JPA, Hibernate
- **API Documentation:** Swagger / Springdoc OpenAPI
- **Build Tool:** Maven

---

## Access API documentation (Swagger UI):

http://localhost:8082/swagger-ui.html

## API Endpoints
Method	    Endpoint	               Description
POST	   /api/expenses	              Add new expense
GET	     /api/expenses	Get           all expenses
GET	    /api/expenses/{id}	          Get expense by ID
PUT	    /api/expenses/{id}	          Update expense
DELETE /api/expenses/{id}	            Delete expense
GET	  /api/expenses/paged	            Get expenses (paged/sorted)
