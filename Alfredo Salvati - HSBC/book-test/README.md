# HSBC Start service


### Notes
Assumptions:

- A book has a title and author. Title is unique across the platform
- A book can be available or not, when a book gets rented becomes not available 
- It's possible to create, delete, find all books and find by title



### Prerequisites

In order to run the project locally this is needed: 

```
- Java 11 +
- Maven 3+
- Docker (Optional)
```

## Swagger EndPoint

http://localhost:8080/swagger-ui.html#/
http://localhost:8080/swagger-ui/index.html
## EndPoints

### Installing

It can be run as a Docker container or as a Java Spring Boot application:

Using Docker: 

```
- unzip the folder
- cd [PATH]
- docker build -t service-star:1.0 .
- docker run -p 127.0.0.1:8080:8080 --name service-star -d service-star:1.0 
```

Using Java and Maven:
By Command line:

```
- unzip the folder
- cd [PATH]
- mvn clean install
- Run HSBCStartBoot file 
```

Importing the project IDE

```
- unzip the folder
- Open the project (I used Intellij for this test)
- Run SpringTestBoot.
```