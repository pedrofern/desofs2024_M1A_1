# Software Architectural Analysis and Design of Delivery Aggregate

- This document de describes the architecture of ElectricGo's Delivery aggregate, providing an overview of the design decisions and architectural analysis made during development.
- Delivery Aggregate is a sub-system developed to manage the delivery of products to customers. It is responsible for managing the delivery process, from the moment the order is placed until the product is delivered to the customer.

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

#### Selected Data
- Warehouse

#### Input Data
- Order Date
- Weight

### 1.6 System Sequence Diagram (SSD)

- Create Delivery

![Level1_ProcessView_CreateDelivery.svg](diagrams/Level1_ProcessView_CreateDelivery.svg)

- Edit Delivery

![Level1_ProcessView_EditDelivery.svg](diagrams/Level1_ProcessView_EditDelivery.svg)

- Get All Deliveries

- ![Level1_ProcessView_GetAllDeliveries.svg](diagrams/Level1_ProcessView_GetAllDeliveries.svg)

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

- **Risk:** Unauthorized access to the download feature.
    - **Mitigation:** Implement strong, multi-factor authentication and comprehensive authorization checks before allowing access to the download feature.


- **Risk:** Insecure file handling leading to path traversal attacks.
    - **Mitigation:** Enforce strict input validation and file path sanitization to prevent path traversal attacks.


- **Risk:** Data corruption during the download process.
    - **Mitigation:** Implement checksum validation or similar data integrity checks post-download to ensure file integrity.


- **Risk:** Lack of encryption during data transmission.
    - **Mitigation:** Use TLS encryption for all data transmissions to ensure confidentiality and integrity of data in transit.


- **Risk:** Denial of Service (DoS) attacks targeting the files download feature.
    - **Mitigation:** Employ rate limiting, anomaly detection, and network security solutions to identify and mitigate potential DoS attacks effectively.


- **Risk:** PDF generation could expose the system to service disruption attacks.
    - **Mitigation:** Apply rate limiting, resource allocation limits, and regular monitoring to detect and prevent abuse during PDF generation.


- **Risk:** Insecure PDF generation library leading to code execution vulnerabilities.
    - **Mitigation:** Ensure that the PDF generation library is up-to-date with the latest security patches and perform regular security assessments.


- **Risk:** Lack of auditing and monitoring capabilities.
    - **Mitigation:** Implement comprehensive logging and real-time monitoring systems to track all activities related to file downloads and generate alerts for suspicious actions.

### 2.2 Security Requirements Engineering
In the context of security requirements engineering, it is crucial to establish both functional and non-functional requirements that protect the system from identified threats and vulnerabilities. This process involves detailed planning and implementation strategies that align with best security practices and compliance standards.

### 2.3 Abuse Cases

![Delivery_Abuse_Case.png](diagrams/Delivery_Abuse_Case.png)

#### Case 1: Unauthorized access
- **Description:** An unauthorized user tries to access the file transfer functionality without the proper permissions.
- **Response:** The system should apply rigorous authentication and authorization checks, denying access and logging the attempt for security monitoring and analysis.

#### Case 2: Path traversal attack
- **Description:** An unauthorized user exploits path traversal vulnerabilities to access restricted files.
- **Response:** Implement robust input validation to sanitize file paths and prevent directory traversal, ensuring that user inputs cannot alter file paths.

#### Case 3: Downloading confidential information
- **Description:** An unauthorized user downloads sensitive information by exploiting security flaws.
- **Response:** Apply encryption to sensitive data and use secure access controls to ensure that only authorized personnel can access sensitive information.

#### Case 4: Denial of file download
- **Description:** An unauthorized user denies having downloaded files, exploiting the lack of auditing.
- **Response:** Implement comprehensive logging and auditing mechanisms that capture all download activities with user identifiers and timestamps.

#### Case 5: Manipulation of download requests
- **Description:** An attacker manipulates the download request to cause damage or gain unauthorized access.
- **Response:** Use parameterized queries and regularly update and patch systems to prevent request manipulation.

#### Case 6: System overload
- **Description:** An attacker sends a large number of download requests to overload the system (DoS attack).
- **Response:** Implement rate limiting and anomaly detection to identify and mitigate potential DoS attacks.

#### Case 7: Bypassing security controls
- **Description:** An attacker bypasses security controls to download unauthorized files.
- **Response:** Regularly review and update security controls. Employ a layered security approach to reduce the risk of unauthorized access.

#### Case 8: Exploitation of PDF generation vulnerabilities
- **Description:** An attacker exploits vulnerabilities in the PDF generation process to execute malicious code.
- **Response:** Ensure that the PDF generation library is up-to-date with all applied security patches. Use sandboxing, if available, to isolate the PDF generation process.

### 2.4 Functional Security Requirements

- **Authentication and Authorization:** Ensure robust authentication before allowing file download requests. Utilize multi-factor authentication (MFA) for sensitive file accesses. Enforce strict authorization checks to confirm that the user has the correct permissions to download the requested file.


- **Data Validation and Sanitization:** Implement rigorous input validation to prevent injection attacks through download requests, such as SQL injection or command injection. Sanitize file paths to prevent directory traversal or path manipulation attacks.


- **Secure File Transmission:** Use TLS (Transport Layer Security) for all file transmissions to ensure that files are encrypted during transit. Apply additional file-level encryption for highly sensitive or confidential files before they are made available for download.


- **Audit Trails:** Log all file download activities, including user identification, timestamp, file details, and access location. Ensure that these logs are immutable and securely stored for future audit and incident response purposes.


- **Rate Limiting:** Implement rate limiting to prevent abuse of the file download feature, which could lead to Denial of Service (DoS) attacks or system overloads. Adjust rate limits based on user behavior patterns and threat intelligence to dynamically manage load and mitigate potential attacks.


- **File Integrity Checks:** Provide checksums or cryptographic hashes for files before download, allowing users to verify the integrity of files post-download. Perform server-side integrity checks before serving the file to detect any unauthorized modifications.

### 2.5 Non-Functional Security Requirements

- **Performance:** Security mechanisms must not degrade system performance beyond acceptable limits.


- **Scalability:** Security architectures must keep pace with system growth without compromising security effectiveness.


- **Reliability:** The system must remain available and functional.


- **Usability:** Security measures must be implemented in such a way that they do not degrade the usability of the system.


- **Compliance:** The system must comply with applicable legal and regulatory requirements relating to security and data protection.

### 2.6 Secure Development Requirements

- **Security Training for Developers:** Conduct regular security training sessions for all developers to keep them updated on the latest security practices and vulnerabilities.


- **Secure Coding Standards:** Adhere to industry-standard secure coding guidelines such as OWASP Top 10, CWE/SANS TOP 25, and language-specific best practices.


- **Static and Dynamic Analysis Tools:** Use static application security testing (SAST) tools to automatically detect vulnerabilities in code before it is deployed and employ dynamic application security testing (DAST) tools to test running applications for vulnerabilities, with a focus on areas involving file uploads and downloads.


- **Third-Party Component Management:** Regularly review and update these components to ensure they are not introducing known vulnerabilities into the application, especially those that affect file handling capabilities.


- **Secure Deployment Practices:** Use automated deployment tools that integrate security checks to prevent misconfigurations and vulnerabilities from reaching production environments.


- **Incident Response and Patch Management:** Implement a systematic patch management process to quickly deploy fixes for newly discovered vulnerabilities affecting file download features.


- **Regular Security Audits:** Conduct regular security audits of the application to assess the effectiveness of implemented security measures.

## 3. Design

### 3.1 Security Risk-Driven Design

### 3.2 Secure Architecture

### 3.3 Secure Design Patterns

### 3.4 Threat Modelling

### 3.5 Security Test Planning

### 3.6 Security Architecture Review
