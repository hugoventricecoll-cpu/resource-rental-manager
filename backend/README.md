# Resource Rental Manager

A REST API built with Spring Boot for managing the internal rental of company resources: meeting rooms, vehicles, and assigned staff (drivers, security, cleaning).

It allows creating reservations ("rentals") of a specific resource for a given time period, automatically validating that no overlapping reservations exist for that same resource.

## Features

- Full CRUD (Create, Read, Update, Delete) for Users, Products (Vehicles/Rooms), Staff, and Rentals.
- Authentication and authorization using Spring Security with JWT (stateless authentication).
- Password encryption using BCrypt.
- Role-based endpoint protection.
- JPA inheritance model (`Producto` as an abstract base class with `JOINED` strategy, extended by `Vehiculo` and `Sala`).
- `@ManyToOne` and `@ManyToMany` relationships with join tables, allowing multiple staff members to be assigned to a single rental.
- Date overlap validation preventing conflicting reservations for the same resource.
- Clean layered architecture with separate input/output DTOs, never exposing JPA entities directly.
- Persistence with PostgreSQL.
- Interactive API documentation with Swagger / OpenAPI.

## API Documentation

The project includes an interactive Swagger UI to explore and test all available endpoints directly from your browser.

Once the application is running, you can access the documentation at:

http://localhost:8091/swagger-ui.html

## Security

The API is secured using Spring Security and JSON Web Tokens (JWT).

### Authentication flow

1. Register a new user.
2. Login with valid credentials.
3. Receive a JWT access token.
4. Include the token in subsequent requests:

## Testing

Unit tests written with JUnit 5 and Mockito, covering the services with the most relevant business logic:

- `UsuarioService` full CRUD
- `AlquilacionService` including date overlap validation
- `PersonalService` read and create (getAllPersonal & addPersonal)


Coverage is intentionally focused on business logic and validations rather than exhaustive testing of every controller/service.

```
Authorization: Bearer <your_token>
```

Protected endpoints require a valid JWT.

## Docker

The application is fully containerized using Docker and Docker Compose:

- A `Dockerfile` builds the Spring Boot app into its own image.
- `docker-compose.yml` orchestrates the app together with a PostgreSQL
  container, connected over Docker's internal network.

To run the project locally:

```
docker compose up
```

## Tech Stack

- Java
- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- Spring Data JPA / Hibernate
- PostgreSQL
- Maven
- Swagger / OpenAPI
- Docker

## Project Status

This project started as a Spring Boot learning project and has evolved into
a complete backend application featuring authentication, authorization,
layered architecture, DTOs, business validations, database persistence,
unit testing, and containerization with Docker.

Future improvements will focus on a React frontend, cloud deployment, and distributed systems.

## Roadmap

### Phase 1 — Backend hardening 

- [X] Authentication and authorization with Spring Security + JWT
- [X] Unit tests (JUnit/Mockito)
- [X] Dockerization

### Phase 2 — Frontend

- [ ] React client consuming this API

### Phase 3 — Deployment & beyond

- [ ] AWS deployment
- [ ] Kafka exploration
- [ ] Microservices

## Author

Hugo Ventrice