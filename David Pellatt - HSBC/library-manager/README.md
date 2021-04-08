# HSBC Library Demo

Author : David Pellatt

No warranties with this one... I have, however, tried to demonstrate my typical approach to the architecture of any non-trivial system. 

Overview : Thin REST Controllers wrap Services (which could be exposed via other Controllers if required, or composed into other Services), which depend on Spring JPA Repositories using the H2 in-memory database.

Caveat : With a proper timescale, I'd expect to see an initial domain model accompany any user story, define interfaces and implement Unit Tests first. But time... 

...and there's virtually no error handling... for some classes we use a natural key, and an auto-generated key for Rentals. YMMV.

## Usage

### Start the Server

Open a terminal window, cd to this directory and execute the following command:

$ mvn spring-boot:run -Dstart-class=com.example.libman.server.LibraryManagerRestServer

### Run the Client

Open a terminal window, cd to this directory and execute the following command:

$ mvn spring-boot:run -Dstart-class=com.example.libman.client.LibraryManagerRestClient

You can run the Client multiple times and see the rentals building up (we let Borrowers rent multiple copies of Books...).

## Key Assumptions

* Given the timescale, only a MVP demonstrating the required REST API, and the architecture required, will be built.
* We will model only Books, Borrowers and Rentals.
* Unlimited copies of each Book are available in the library, and a Borrower may rent as many as they like.

## Derived Assumptions

* The Spring framework can be used for implementation.
* Cross-cutting concerns such as Security and Transaction management are not required at this point. Typically, these would be implemented by defining advice to be applied to the exposed methods of Services, etc.

## Running the demo

This demo project contains two applications; a Server implenting a simple REST API and a basic Client to demonstrate it.

## Project & Package Structure

For the purposes of this demo, the following sub-packages group related elements within a single project. Typically, we might split these among several projects (e.g. a project per service/microservice, etc.). 

The project root package is com.example.libman, which has the following sub-packages :-

* .client - contains only the REST client to demo the system.
* .controllers - REST Controllers exposing the required endpoints.
* .domain - the domain object model, defining entities and their relationships which would typically be agreed with the input of end users and/or BAs.
* .dto - Data Transport Objects, in this case just a wrapper for Lists to simplify the interfaces.
* .repositories - Data Access Layer, containing Data Access Objects (DAOs - repositories) for persisting domain entities.
* .server - contains only the REST Server app and required configuration.
* .services - skeletal services that, in a real system, would encapsulate 'business' logic and rules around book rentals.

## Component Structure and Configuration

Each app is in its own package, with no sub-packages, to eliminate excessive component scanning on startup. Similarly, class-based configuration is used for explicit inclusion of required components.

Prefer constructor injection, as it's more explicit, helps avoid circular dependencies and guarantees components will be created in a usable state.