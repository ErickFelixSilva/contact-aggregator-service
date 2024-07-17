# Contact Aggregator Service
This project is a demonstration service application built with Spring Boot and Java to
show basic communication between services using REST APIs. This application queries paginated data from another service,
aggregating all the data into a single list to return it.

## Table of Contents

- [Technologies](#technologies)
- [Getting Started](#getting-started)
    - [Installation](#installation)
    - [Running the Application](#running-the-application)
- [Usage](#usage)
- [Testing](#testing)


## Technologies

- Java
- Spring Boot
- Spring Cloud OpenFeign
- Unit Testing with JUnit and Mockito
- Integration Testing with Spring Boot Test

## Getting Started

### Installation

The application setup is very simple it is just a basic Spring Boot application. The most important is
to use a compatible java version which is Java 22. The service uses Maven as a build tool, but it is possible to use
the maven wrapper to build and run the application.

The following steps will guide you through the installation process:

1. **Clone the repository**

   ```sh
   git clone https://github.com/ErickFelixSilva/contact-aggregator-service
   ```
   
2. **Running the Application**

- Navigate to the application directory:

  ```sh
  cd contact-aggregator-service
  ```

- Build the project with local maven:

  ```sh
  mvn clean install
  ```
- If there is no local maven installed, it is possible to use the maven wrapper this way:

     ```sh
     mvnw.cmd clean install
     ```

- Start the backend server with local maven:

  ```sh
  mvn spring-boot:run
  ```
- Start the backend server with maven wrapper:

  ```sh
  mvnw.cmd spring-boot:run
  ```

### Running the Application

- The service will be running at `http://localhost:8080`

## Usage

- Do a GET request to `http://localhost:8080/contacts` to get all contacts.
- This will execute the main part of the application, which queries Kenect Labs API and aggregates the data.


## Testing
I included a simple unit test for the adapter layer which does the most important part of the logic and also an 
integration test for the controller layer which actually executes this part of the application.

- To run the service tests, use the following command:

  ```sh
  mvn test
  ```
- Or again, to use the maven wrapper:

  ```sh
  mvnw.cmd test
  ```