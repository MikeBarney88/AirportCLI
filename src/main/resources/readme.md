# HTTP REST CLI Application

A Java command-line interface application for interacting with a Spring Boot REST API to 
generate reports about aircraft, airports, passengers, and cities.

### Description

This CLI application connects to a REST API backend and generates formatted reports based on the provided endpoint URL. It can answer questions such as:

- What aircraft has each passenger flown on?
- What airports do aircraft take off from and land at?
- What airports are available?
- What cities are in the system?

### Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- A running Spring Boot REST API backend (on localhost:8080 or your configured URL)

### Project Structure

INSERT PICTURE HERE WHEN DONE!!!!!


### Available Endpoints and Reports
#### Questions
1. What airports do aircraft take off from and land at?
- http://localhost:8080/airports/city/2

2. What aircraft has each passenger flown on?
- http://localhost:8080/airports/passengers

3. What airports do aircraft take off from and land at?
- http://localhost:8080/aircrafts

4. What airports have passengers used?
- http://localhost:8080/
needs to be added!! 

### Authors

- Michael Barney
- Joey Thomas
- Brandon Pike