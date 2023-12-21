# Flight Search API

## Project Description

Flight Search API is a web application that allows users to search for flights based on various criteria such as departure airport, arrival airport, and departure time. It provides a convenient way to manage flights and find available options for travel.

## Technologies Used

- ![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white)
- ![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
- ![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
- ![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=spring&logoColor=white)

## Project Structure

The project adopts a structured modern architecture designed to provide users with flight search capabilities upon registration and authorization. Here's an overview of the key features and components:

### User Authentication and Authorization

- **User Registration:** Users must register via the "/api/auth/register" endpoint to gain access to flight search functionalities. The user's password is securely hashed using the bcrypt algorithm during registration.

- **Token-based Authentication:** Upon successful login via the "/api/auth/authentication" endpoint, users receive a JSON Web Token (JWT) that grants access to the flight search API. The token has a validity period of 10 minutes, enhancing security.

### Flight Search

- **Search Capabilities:** Authenticated users can search for flights, including one-way or round-trip options. The system supports various search parameters, providing flexibility for users to find the desired flights.

- **Airport Queries:** Users can perform queries on airports, enhancing the search experience.

- **Error Handling:** Modern custom exceptions are implemented to gracefully handle scenarios where users input invalid flights or airports.

- **DTOs for Requests:** Data Transfer Objects (DTOs) are employed for handling incoming requests, ensuring a clean and organized structure.

### Scheduled Jobs

- **Third-Party API Integration:** A Scheduled Job mechanism runs every night to fetch flights from a third-party API. For demonstration purposes, a MockController generates custom data, showcasing the integration of external data into the project.

This structured approach ensures a secure, user-friendly, and feature-rich flight search system, catering to the needs of registered and authenticated users.

## Swagger API Documentation

- **API Documentation:** The project includes Swagger API documentation, providing detailed information about the available endpoints, request parameters, and responses. 
