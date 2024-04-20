# Software Architectural Analysis and Design of Delivery Aggregate

- This document de describes the architecture of ElectricGo's Delivery aggregate, providing an overview of the design
  decisions and architectural analysis made during development.
- Delivery Aggregate is a sub-system developed to manage the delivery of products to customers. It is responsible for
  managing the delivery process, from the moment the order is placed until the product is delivered to the customer.

## Table of Contents

1. [Requirements Engineering](#1-requirements-engineering)
    1. [User Stories Description](#11-user-stories-description)
    2. [Customer Specifications and Clarifications](#12-customer-specifications-and-clarifications)
    3. [Acceptance Criteria](#13-acceptance-criteria)
    4. [Found out Dependencies](#14-found-out-dependencies)
    5. [Input and Output Data](#15-input-and-output-data)
    6. [System Sequence Diagram (SSD)](#16-system-sequence-diagram-ssd)
    7. [API Endpoints](#17-api-endpoints)
    8. [Database Schema](#18-database-schema-relational-model)
    9. [Authorization Roles](#19-authorization-roles)
2. [Analysis](#2-analysis)
    1. [Preliminary Software Risk Analysis](#21-preliminary-software-risk-analysis)
    2. [Security Requirements Engineering](#22-security-requirements-engineering)
    3. [Abuse Cases](#23-abuse-cases)
    4. [Functional Security Requirements](#24-functional-security-requirements)
    5. [Non-Functional Security Requirements](#25-non-functional-security-requirements)
    6. [Secure Development Requirements](#26-secure-development-requirements)
3. [Design](#3-design)
    1. [Security Risk-Driven Design](#31-security-risk-driven-design)
    2. [Secure Architecture](#32-secure-architecture)
    3. [Secure Design Patterns](#33-secure-design-patterns)
    4. [Threat Modelling](#34-threat-modelling)
    5. [Security Test Planning](#35-security-test-planning)
    6. [Security Architecture Review](#36-security-architecture-review)

## 1. Requirements Engineering

### 1.1 User Stories Description

- As a Warehouse Manager, I want to be able to register a new delivery order.
- As a Warehouse Manager, I want to be able to list and update a delivery order.
- As a Warehouse Manager, I want to be able to download the delivery plan as a PDF.

### 1.2 Customer Specifications and Clarifications

- The delivery plan should contain the following information:
    - Delivery Orders
    - Routes (already registered in the system)

- The delivery order should contain the following information:
    - Order ID
    - Order Date
    - Weight
    - Warehouse

### 1.3 Acceptance Criteria

- The delivery order should be successfully registered in the system.
- The delivery order should be successfully listed and updated in the system.
- The delivery plan should be successfully downloaded as a PDF if the warehouse manager is logged in.

### 1.4 Found out Dependencies

- The warehouse manager must be logged in to download the delivery order.
- The warehouses must be registered in the system before a delivery order can be created.
- The routes must be registered in the system before a delivery plan can be created.

### 1.5 Input and Output Data

#### Input Data

- Selected data:
    - Delivery Order:
        - Warehouse

- Input data:
    - Delivery Order:
        - Order Date
        - Weight

### 1.6 System Sequence Diagram (SSD)

- Create Delivery
  ![Level1_ProcessView_CreateDelivery.svg](diagrams/Level1_ProcessView_CreateDelivery.svg)

- Edit Delivery
  ![Level1_ProcessView_EditDelivery.svg](diagrams/Level1_ProcessView_EditDelivery.svg)

- Get All Deliveries
  ![Level1_ProcessView_GetAllDeliveries.svg](diagrams/Level1_ProcessView_GetAllDeliveries.svg)

### 1.7 API Endpoints

- GET /deliveries - List all delivery orders
- POST /deliveries - Create a new delivery order
- PUT /deliveries/{id} - Update a delivery order
- DELETE /deliveries/{id} - Delete a delivery order
- GET /deliveries/plan/{id}/download - Download the delivery plan as a PDF

### 1.8 Database Schema: Relational Model

#### Diagram of the Relational Model

The Domain Model for the Delivery Aggregate is as follows:

![Delivery_Domain_Model.png](diagrams/Delivery_Domain_Model.png)

Considering the previous model, the user aggregate has the following database schema:

![Delivery_Relational_Model.png](diagrams/Delivery_Relational_Model.png)

### 1.9 Authorization Roles

- Warehouse Manager: Access to functionalities related to the management of delivery data.
- Operator: Access only to the visualization functionalities related to the management of delivery data.

## 2. Analysis

### 2.1 Preliminary Software Risk Analysis
- **Risk:** Unauthorized access to sensitive delivery data.
    - **Mitigation:** Implement strong authentication and authorization checks before allowing access to the download feature.
- **Risk:** Data corruption during the download process.
    - **Mitigation:** Implement checksum or similar data integrity checks post-download.
- **Risk:** PDF generation could expose the system to service disruption attacks.
    - **Mitigation:** Implement rate limiting and monitoring to detect and prevent abuse.

### 2.2 Security Requirements Engineering
- **Functional Requirements:**
    - Authentication must be strictly enforced before accessing the PDF download feature.
    - Input validation must be performed to avoid injection attacks during PDF generation.
    - Use of TLS to ensure encryption in transit.
- **Non-Functional Requirements:**
    - The system must handle a high number of PDF download requests without degradation in performance.
    - Ensure data integrity and accuracy during PDF creation.

### 2.3 Abuse Cases

![Delivery_Abuse_Case.png](diagrams/Delivery_Abuse_Case.png)

#### Case 1: Unauthorized Access
- **Description:** An unauthorized user attempts to access the PDF download feature without proper permissions.
- **Response:** The system should enforce strict authentication and authorization checks, denying access and logging the attempt for security monitoring and analysis.

#### Case 2: Path Traversal Attack
- **Description:** An unauthorized user exploits path traversal vulnerabilities to access restricted files.
- **Response:** Implement robust input validation to sanitize file paths and prevent directory traversal. Ensure that user inputs cannot alter file paths.

#### Case 3: Downloading Sensitive Information
- **Description:** An unauthorized user downloads sensitive information exploiting security flaws.
- **Response:** Apply encryption to sensitive data and use secure access controls to ensure that only authorized personnel can access sensitive information.

#### Case 4: Denial of Downloading Files
- **Description:** An unauthorized user denies having downloaded files, exploiting a lack of auditing.
- **Response:** Implement comprehensive logging and auditing mechanisms that capture all download activities with user identifiers and timestamps.

#### Case 5: Manipulating Download Requests
- **Description:** An attacker manipulates the request for downloading to cause harm or gain unauthorized access.
- **Response:** Use parameterized queries and routinely update and patch systems to prevent manipulation of requests.

#### Case 6: Overloading the System
- **Description:** An attacker sends a massive amount of download requests to overload the system (DoS attack).
- **Response:** Implement rate limiting and anomaly detection to identify and mitigate potential DoS attacks.

#### Case 7: Malicious File Upload and Download
- **Description:** An attacker uploads malicious files and encourages others to download them, spreading malware.
- **Response:** Scan all uploaded files for malware and block downloads of identified malicious files. Educate users about the risks of downloading unknown files.

#### Case 8: Bypassing Security Controls
- **Description:** An attacker bypasses security controls to download unauthorized files.
- **Response:** Regularly review and update security controls. Employ a layered security approach to reduce the risk of unauthorized access.

#### Case 9: Exploiting PDF Generation Vulnerabilities
- **Description:** An attacker exploits vulnerabilities in the PDF generation process to execute malicious code.
- **Response:** Ensure that the PDF generation library is up to date with all security patches applied. Use sandboxing if available to isolate the PDF generation process.

### 2.4 Functional Security Requirements
- Implement role-based access control for the download functionality to ensure only authorized users can download the delivery plans.
- Log all access attempts to the PDF download feature for audit and monitoring purposes.

### 2.5 Non-Functional Security Requirements
- The PDF download service must be capable of handling a high volume of requests efficiently to prevent DoS attacks.
- The system should ensure the integrity and confidentiality of the data during the PDF creation and download process.

### 2.6 Threat Modelling


## 3. Design

### 3.1 Security Risk-Driven Design

### 3.2 Secure Architecture

### 3.3 Secure Design Patterns

### 3.4 Threat Modelling

### 3.5 Security Test Planning

### 3.6 Security Architecture Review
