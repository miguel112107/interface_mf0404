# interface_mf0404

## Description

This project was created to simulate a tool rental application with a simple interface and a backend with rest endpoints. The backend of the application was built with Spring Boot, used to fetch data and create Rest endpoints. The front end uses React as an interface for user interaction.

## Installation

This project was developed with VSCode: 
Node v13.7.0
npm v6.14.4
SpringBoot v2.6.5

To build and run this project, use the following commands on console to create react application:
    ./mvnw package ## Build Spring Application with dependencies
    npm install ## React project with dependencies
    ./mvnw spring-boot:Run ## run built spring boot application. Allows front end interaction and api access 
    npm run watch ## Runs React front end for local access for client. 

Package.json file is preconfigured with all required dependencies.