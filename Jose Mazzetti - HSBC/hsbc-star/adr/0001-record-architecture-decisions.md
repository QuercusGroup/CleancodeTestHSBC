# 1. Record architecture decisions

This file contains some decisions made for the test. 

## Status

N/A

## Context

    Database
    Controllers
    Test
    Lombok

## Decision

Database: For this testing I decided to implement h2 DB for simplicity. 
Controllers: There are 2 controllers for each scenario:
    1. For BookLibrary Managment (BookController).
    2. For BookRentals (RentalController)

Tests: I decided to implement for RestController test. Services, DTOs can be tested in similar ways.
Scenarios for rental flow are ignored but can be done in the same way. 
Lombok: Implemented to avoid aditional setters/getters/constructor for test cases. 

## Consequences

N/A 