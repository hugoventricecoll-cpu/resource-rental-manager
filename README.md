# Resource Rental Manager

A REST API built with Spring Boot for managing the internal rental of company resources: meeting rooms, vehicles, and assigned staff (drivers, security, cleaning).

It allows creating reservations ("rentals") of a specific resource for a given time period, automatically validating that no overlapping reservations exist for that same resource.

## Features

- Full CRUD (Create, Read, Update, Delete) for Users, Products (Vehicles/Rooms), Staff, and Rentals.
- JPA inheritance model (`Producto` as an abstract base class with `JOINED` strategy, extended by `Vehiculo` and `Sala`), representing different types of resources under a single hierarchy.
- `@ManyToOne` and `@ManyToMany` relationships with join tables, allowing multiple staff members to be assigned to a single rental.
- Date overlap validation: prevents creating or updating a rental if it collides in time with another existing rental for the same resource.
- Clean layered architecture with separate input/output DTOs per operation, never exposing JPA entities directly through the API.
- Persistence with PostgreSQL.

## Tech Stack

- Java + Spring Boot
- Spring Data JPA / Hibernate
- PostgreSQL
- Maven

## Project Status

This is a learning project built to practice Spring Boot fundamentals beyond basic CRUD: entity inheritance, complex relationships, and non-trivial business logic (overlap validation).

## Roadmap

- [ ] Authentication and authorization with Spring Security + JWT
- [ ] Unit tests (JUnit/Mockito)
- [ ] Dockerization
- [ ] AWS deployment

## Author

Hugo Ventrice