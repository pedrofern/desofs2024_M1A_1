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

### 2.2 Security Requirements Engineering

### 2.3 Abuse Cases

### 2.4 Functional Security Requirements

### 2.5 Non-Functional Security Requirements

### 2.6 Secure Development Requirements

## 3. Design

### 3.1 Security Risk-Driven Design

### 3.2 Secure Architecture

### 3.3 Secure Design Patterns

### 3.4 Threat Modelling

### 3.5 Security Test Planning

### 3.6 Security Architecture Review
