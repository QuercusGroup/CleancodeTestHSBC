# REST Code Test - Library API

## Requirements 

Given the user story below:

As a librarian
I need a REST API to manage my book library and book rentals
So that I can replace my manual system.

1. Implement the solution using Java and any libraries you need.
2. Document your design decisions and assumptions.
3. Use Maven as a build tool for this project.
4. Send us your solution as a zipped archive.



## Solution Description

Skills demonstrated: 

1. Basic Spring/Spring Boot concepts
2. Building REST endpoint routing using SpringMVC
3. Using pessimistic locking to ensure consistency in concurrently accessed database
4. Simple integration testing 
5. Using Spring RestTemplate to call other services

Assumptions:
The universe of books available to the library is backed by the Google Books Volume API. The Library can add
volumes to its inventory for borrowing by invoking the a `POST` to the `/book` endpoint. It can initiate a borrowing 
by invoking a `POST` to `/book/{ISBN}/borrowing`. A book can be returned through a `PUT` to `/book/{ISNB}/borrowing/{borrowingId}`.
The details of a borrowing can be obtained with a reciprocating `GET` to the same endpoint. 

To obtain all books present in the library along with their borrowing details can be furnished with a `GET` to `/books`

The Library API assumes that books are uniquely identified with ISBN by convention. 

## Caveats
1. Could use a lot more testing and documentation. I just wrote an MVC Integration Test to sanity check the code with a happy path. 
   Much more could be done. 
2. Could use more validation
3. I've done a few microservices tests this week, and I'm running low on time. Hopefully this 
is satisfactory, and my current capacity is taken into consideration.
4. Would need a finer-grained design around locking concerns, pagination, object model to scale. 
5. Determine if preference is to use unchecked vs checked exceptions + catch and/or re-throw for logging
   
To run:

```./mvnw spring-boot:run```
   
All the best,
Chris 
