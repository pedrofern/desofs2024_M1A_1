# Software Architectural Analysis and Design of Warehouse Aggregate
- This document describes the architecture of EletricGo's Warehouse aggregate, providing an overview of the design decisions and architectural analysis made during development.
- Warehouse Aggregate is a sub-system developed to ...

# Table of Contents
1. [Requirements Engineering](#1-requirements-engineering)
  1. [User Stories Description](#11-user-stories-description)
  2. [Customer Specifications and Clarifications](#12-customer-specifications-and-clarifications)
  3. [Acceptance Criteria](#13-acceptance-criteria)
  4. [Found out Dependencies](#14-found-out-dependencies)
  5. [Input and Output Data](#15-input-and-output-data)
  6. [System Sequence Diagram (SSD)](#16-system-sequence-diagram-ssd)
  7. [API Endpoints](#17-api-endpoints)
  8. [Database Schema](#18-database-schema)
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

# 1. Requirements Engineering

## 1.1 User Stories Description

- As a warehouse manager, I want to be able to create a new warehouse.
- As a warehouse manager, I want to be able to update a warehouse.
- As a warehouse manager, I want to be able to delete a warehouse.
- As a warehouse manager, I want to be able to list all warehouses.

## 1.2 Customer Specifications and Clarifications

- The warehouse should contain the following information:
  - Id
  - Designation
  - Address
  - Geographic Coordinates

## 1.3 Acceptance Criteria

- The warehouse should be successfully created in the system.
- The log should be updated with the warehouse creation.
- The error message should be displayed if the warehouse is not created.

## 1.4 Found out Dependencies

- The warehouse aggregate depends on the following aggregates:
  - User

- The warehouse manager should be authenticated to create, update, and delete a warehouse.
- The Geographic Coordinates depend on the external api.

## 1.5 Input and Output Data

- Input:
  - Designation
  - Address (Street, City, State, Zip Code)
  - Geographic Coordinates (Latitude, Longitude)

## 1.6 System Sequence Diagram (SSD)

- Create Warehouse

![SSD1](./diagrams/Level1_ProcessView_CreateWarehouse.svg)

- Update Warehouse

![SSD1](./diagrams/Level1_ProcessView_EditWarehouse.svg)

- Get All Warehouses

![SSD1](./diagrams/Level1_ProcessView_GetAllWarehouse.svg)

## 1.7 API Endpoints

- POST /warehouse/ - Create a new warehouse
- PUT /warehouse/{id} - Update a warehouse
- DELETE /warehouse/{id} - Delete a warehouse
- GET /warehouses/ - List all warehouses

## 1.8 Database Schema: modelo relacional

Considering the domain model, the Warehouse aggregate has the following database schema:

![User Database Model](./diagrams/warehouse_database_schema.png)

## 1.9 Authorization Roles

- Warehouse Manager: Access to functionalities related to warehouse data management and delivery tracking.
- Operator: Restricted access for querying and limited operations within each aggregate, with permissions tailored to their role responsibilities.

# 2. Analysis

## 2.1 Preliminary Software Risk Analysis

## 2.2 Security Requirements Engineering

## 2.3 Abuse Cases

## 2.4 Functional Security Requirements

## 2.5 Non-Functional Security Requirements

## 2.6 Secure Development Requirements

# 3. Design

## 3.1 Security Risk-Driven Design

## 3.2 Secure Architecture

## 3.3 Secure Design Patterns

## 3.4 Threat Modelling

## 3.5 Security Test Planning

## 3.6 Security Architecture Review