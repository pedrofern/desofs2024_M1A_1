# DESFOS 2024_M1A_1

- Developed by:
  - 1060503 - Pedro Fernandes
  - 1170541 - Alexandra Leite
  - 1171540 - Fábio Cruz
  - 1180511 - Vitor Costa
  - 1191816 - Cristiano Soares

## Table of Contents

- [Introduction](#introduction)
- [Application Security Verification Standard (ASVS) Checklist](#application-security-verification-standard-asvs-checklist)
- [Issue Tracker](#issue-tracker)
- [Build and Deploy Process](#build-and-deploy-process)
  - [Continuous Integration (CI)](#continuous-integration-ci)
  - [Continuous Deployment (CD)](#continuous-deployment-cd)
  - [Automation Tools](#automation-tools)
- [Frontend](#frontend)
  - [Key Features](#key-features)
  - [Development Setup](#development-setup)
  - [Testing](#testing)
- [Backend](#backend)
  - [Key Features](#key-features-1)
  - [Development Setup](#development-setup-1)
  - [Testing](#testing-1)
- [Database](#database)


// TODO - rever todo o documento e adaptar para o sprint 2 !!!!!!!!

## Introduction

The main objective of this document is to present the second sprint P2 implementation decisions.

As we decided in the previous document, we have three main components:
  1. Frontend
  2. Backend
  3. Database

The frontend is developed using Angular, the backend is developed using Java and the database is developed using PostgreSQL.

Additionally, we are using the GitHub Actions to automate the build and deploy process.

All of the work is tracked in the GitHub Issues.

## Application Security Verification Standard (ASVS) Checklist

In terms of security we followed the Application Security Verification Standard (ASVS) checklist.

The following file shows the ASVS requirements: [ASVS Check List](v4-ASVS-checklist-en_sprint_1.xlsx)

## Issue Tracker

We have created the following issues to track the tasks related to the DESFOS project:

- [#1 Documentation](https://github.com/pedrofern/desofs2024_M1A_1/issues/1)
- [#2 Users documentation](https://github.com/pedrofern/desofs2024_M1A_1/issues/2)
- [#3 Warehouse documentation](https://github.com/pedrofern/desofs2024_M1A_1/issues/3)
- [#4 Delivery Plan documentation](https://github.com/pedrofern/desofs2024_M1A_1/issues/4)
- [#5 Truck documentation](https://github.com/pedrofern/desofs2024_M1A_1/issues/5)
- [#6 Logistics documentation](https://github.com/pedrofern/desofs2024_M1A_1/issues/6)
- [#7 Pipeline/Common implementation](https://github.com/pedrofern/desofs2024_M1A_1/issues/7)
- [#8 BE - Users implementation](https://github.com/pedrofern/desofs2024_M1A_1/issues/8)
- [#9 BE - Warehouse implementation](https://github.com/pedrofern/desofs2024_M1A_1/issues/9)
- [#10 BE - Delivery Plan implementation](https://github.com/pedrofern/desofs2024_M1A_1/issues/10)
- [#11 BE - Truck implementation](https://github.com/pedrofern/desofs2024_M1A_1/issues/11)
- [#12 BE - Logistics implementation](https://github.com/pedrofern/desofs2024_M1A_1/issues/12)
- [#13 FE - Users implementation](https://github.com/pedrofern/desofs2024_M1A_1/issues/13)
- [#14 FE - Warehouse implementation](https://github.com/pedrofern/desofs2024_M1A_1/issues/14)
- [#15 FE - Delivery Plan implementation](https://github.com/pedrofern/desofs2024_M1A_1/issues/15)
- [#16 FE - Truck implementation](https://github.com/pedrofern/desofs2024_M1A_1/issues/16)
- [#17 FE - Logistics implementation](https://github.com/pedrofern/desofs2024_M1A_1/issues/17)


## Build and Deploy Process

We have set up a continuous integration and continuous deployment (CI/CD) pipeline using GitHub Actions. The pipeline is configured to automate the build, test, and deployment processes for both the frontend and backend components of the project.

Here there is a copy of the GitHub Actions Workflow file: [GitHub Actions Pipeline](pipeline.yml)

### Continuous Integration (CI)

The CI pipeline includes the following steps for both backend and frontend:

1. **Checkout Repository**: Clone the repository to the build server.
2. **Clean Environment**: Ensure the build environment is clean.
3. **Setup Environment**: Configure the necessary environment (JDK for backend, Node.js for frontend).
4. **Install Dependencies**: Install required dependencies for the project.
5. **Run Tests**: Execute unit tests and generate coverage reports.
6. **Static Analysis**: Perform static code analysis to check code quality and security.
7. **Build Artifacts**: Compile the code and build the artifacts (JAR files for backend, build files for frontend).
8. **Archive Artifacts**: Store the build artifacts for later use.
9. **Deploy Artifacts**: Deploy the artifacts to the appropriate environments.

### Continuous Deployment (CD)

The CD pipeline is triggered after the CI pipeline completes successfully. It involves:

1. **Download Reports**: Download dependency and coverage reports generated during the CI process.
2. **Deploy to GitHub Pages**: Deploy reports to GitHub Pages for easy access and review.
3. **Build Docker Images**: Build Docker images for both backend and frontend.
4. **Push Docker Images**: Push the Docker images to Docker Hub.

GitHub Actions Workflow diagram:

![GitHub Actions Workflow](GitHubActionsWorkflow.png)

### Automation Tools

- **Dependabot**:
  - GitHub tool enabled to keep dependencies up to date.
- **Codacy Quality**: 
  - Enabled to monitor code quality.
  - [![Codacy Badge](https://app.codacy.com/project/badge/Grade/65b4c2ec5835498eb2463147b77be122)](https://app.codacy.com?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)

- **Codacy Coverage**: 
  - Enabled to monitor code coverage. 
  - [![Codacy Badge](https://app.codacy.com/project/badge/Coverage/65b4c2ec5835498eb2463147b77be122)](https://app.codacy.com?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_coverage)

- **Deploy to Docker Hub**: 
  - Enabled to deploy Docker images to Docker Hub.


### Tools Evidence

- **Dependabot**:
  - ![Dependabot](dependabot.png)
- **GitHub Secrets**:
  - ![GitHub Secrets](githubsecrets.png)  
- **Codacy Quality and Coverage**:
  - ![Codacy](codacy.png)  
- **Docker Hub**:
  - ![Docker Hub](dockerhub.png)




## Frontend

The frontend is developed using Angular. It is responsible for the user interface and user experience of the EletricGo application.

### Key Features

- **User Management**: Interfaces for managing user accounts and profiles.
- **Warehouse Management**: Interfaces for managing warehouse data and operations.
- **Delivery Plan**: Interfaces for planning and managing deliveries.
- **Truck Management**: Interfaces for managing truck data and operations.
- **Logistics Management**: Interfaces for managing logistics operations.

### Development Setup

1. **Node.js**: 20.9.0
2. **Angular CLI**: 15.0.4
3. **Install Dependencies**: Run `npm install` to install required dependencies.
4. **Run Development Server**: Start the application using `ng start`.

### Testing

- **Unit Tests**: Run unit tests using `npm run test-no-browser`.


## Backend

The backend is developed using Java and Spring Boot. It provides RESTful APIs for the frontend and handles business logic, data processing and integration with the database.

### Key Features

- **User Management**: APIs for user authentication, authorization, and profile management.
- **Warehouse Management**: APIs for managing warehouse data and operations.
- **Delivery Plan**: APIs for planning and managing deliveries.
- **Truck Management**: APIs for managing truck data and operations.
- **Logistics Management**: APIs for managing logistics operations.

### Development Setup

- **JDK**: JDK 17.
- **Install Dependencies**: Run `mvn clean install` to install required dependencies.
- **Run Application**: Start the application using `mvn spring-boot:run`.

### Testing

- **Unit Tests**: Run unit tests using `mvn test jacoco:report`.
- **Integration Tests**: Run integration tests using the Postman collection file `BackendAPI/EletricGo_BackEnd.postman_collection.json`.



## Database

The database is developed using PostgreSQL. It stores all the data related to the DESFOS application, including user data, warehouse information, delivery plans, truck data, and logistics operations.


