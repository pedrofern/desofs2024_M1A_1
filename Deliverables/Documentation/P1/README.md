# DESFOS 2024_M1A_1

- Developed by:
  - 1060503 - Pedro Fernandes
  - 1170541 - Alexandra Leite
  - 1171540 - Fábio Cruz
  - 1180511 - Vitor Costa
  - 1191816 - Cristiano Soares

## Table of Contents

- [Introduction](#introduction)
- [Aggregate's Documentation](#aggregate-documentatio)
- [Issue Tracker](#issue-tracker)
- [Domain Model](#domain-model)
- [Logical View](#logical-view)
- [Infrastructure View](#infrastructure-view)
- [Physical View](#physical-view)
- [Data Flow Diagram](#data-flow-diagram)
- [Application Security Verification Standard (ASVS) Checklist](#application-security-verification-standard-asvs-checklist)
- [Glossary](#glossary)


## Introduction

The main objective of this document is to present da Analysis and Design of the DESFOS project. This project is a system for planning distribution of deliveries between warehouses using a fleet of electric vehicles.

This means that we will demonstrate the first two (of six) phases of Software Development Life Cycle (SDLC):

![SDLC](diagrams/sdlc.png)

The first phase is the **Analysis** phase, where we will analyze the requirements and constraints of the system. The second phase is the **Design** phase, where we will design the system's architecture and components. 

One of the goals is to focus on security, so we adopt the Secure Software Development Life Cycle (SSDLC). The following image represent all stages of this process and we will demonstrate specifically the first two stages:

![SDLC](diagrams/ssdlc.png)

## Aggregate's Documentation

For each aggregate, a specific analysis and design were performed. On the following pages, it is possible to consult:

- [User Documentation](user/README_User.md)
- [Warehouse Documentation](warehouse/README_Warehouse.md)
- [Delivery Documentation](delivery/README_Delivery.md)
- [Truck Documentation](truck/README_Truck.md)
- [Logistics Documentation](logistics/README_Logistics.md)

## Issue Tracker

We have created the following issues to track the tasks related to the DESFOS project:

- [#1 Documentation](https://github.com/pedrofern/desofs2024_M1A_1/issues/1): Global documentation
- [#2 Users documentation](https://github.com/pedrofern/desofs2024_M1A_1/issues/2): Users documentation
- [#3 Warehouse documentation](https://github.com/pedrofern/desofs2024_M1A_1/issues/3): Warehouse documentation
- [#4 Delivery Plan documentation](https://github.com/pedrofern/desofs2024_M1A_1/issues/4): Delivery Plan documentation
- [#5 Truck documentation](https://github.com/pedrofern/desofs2024_M1A_1/issues/5): Truck documentation
- [#6 Logistics documentation](https://github.com/pedrofern/desofs2024_M1A_1/issues/6): Logistics documentation

## Domain Model

![Domain Model](diagrams/domainModel.png)

The image shows a domain model for the ElectricGo system, detailing various aggregates and their relationships:

- DeliveryPlan Aggregate: Central to the model, it has a one-to-many relationship with Route and Delivery, suggesting a single delivery plan consists of multiple routes and deliveries.
- Route Entity: Contains details such as warehouse IDs, distance, time, energy, and extra time, indicating the specifics of each delivery route.
- Delivery Entity: Holds information like weight and warehouse ID, likely representing an individual delivery action within a plan.
- Truck Aggregate: Represents the physical trucks, with attributes like truck ID, tare, load capacity, and status (active/inactive). It is associated with a Battery value object, which details battery capacity, autonomy, and charging time.
- Warehouse Management Aggregate: Consists of the Warehouse entity, which includes designation and an ID, and is linked to Address and GeographicCoordinates value objects.
- Logistics Aggregate: Contains Packaging as its root entity, which is linked to Delivery, suggesting it details the packaging involved in deliveries.
- User Aggregate: Describes user details such as username, first/last name, phone number, email, and password, and has a relationship with the Role entity.
- Localization Value Object: Found within Logistics, it provides coordinates (x, y, z) for positioning.

### Users

- The system will have the following types of users:
  - **System Administrator** - manages user accounts and permissions
  - **Warehouse Manager** - manages warehouse data and deliveries
  - **Fleet Manager** - manages truck data
  - **Logistics Manager** - manages route data and distribution planning
  - **Operator** - has basic access for querying and limited operations within each aggregate

## Logical View

![Logic View](diagrams/logic_view.png)

The image depicts a Level 2 C4 model component diagram for the ElectricGO system. It outlines three key components:

SPA (Single Page Application) – The user interface for client-side interactions.
Backend API – Serves as the middleman processing business logic, client data requests, and responses.
SGBD (Database Management System) on a Database Server – Handles data storage and management tasks.

The flow is SPA communicates with the Backend API, which in turn interacts with the Database Server to perform data operations.

## Infrastucture View

![Infrastucture View](diagrams/servers.png)

The image is a diagram depicting the architecture of a web application system, divided into three segments:

- Single Page Application: The user interacts with the ElectricGO application, which is hosted on Google Firebase, suggesting the use of Firebase for hosting and possibly other backend services.
- APIs and Webservices: The application communicates via HTTPS with backend services hosted on Azure App Services, where the Backend API processes requests and performs logic operations.
- Databases: The Backend API then interacts, also via HTTPS, with a database hosted on SQL Server, indicating that SQL Server is used for data storage and management.

The user icon on the left indicates the entry point of interaction, while the directional arrows suggest the flow of data and requests through the system.

## Physical View

![Physical View](diagrams/physical_view.png)

The image displays a diagram illustrating the physical view of a server setup, consisting of two main components:

- Physical Server: Hosts the "ElectricGO" application and a "Backend API", indicating that the server handles both the user-facing application and the backend processes. The connection between ElectricGO and the Backend API is secured with HTTPS, suggesting encryption for secure data transfer.
- Database Server: Contains a "Database", which the Backend API communicates with, also using HTTPS for secure transactions.

The diagram communicates that there are dedicated servers for the application and database, each responsible for different parts of the system architecture, with secure communication between them.

## Data Flow Diagram

![Data Flow Diagram](diagrams/dfd.png)


## Application Security Verification Standard (ASVS) Checklist

In terms of security we followed the Application Security Verification Standard (ASVS) checklist. The following file shows the ASVS requirements: [ASVS Check List](./diagrams/v4-ASVS-checklist-en.xlsx)

## Glossary

**Terms, Expressions and Acronyms (TEA) organized alphabetically.**

| **_TEA_** (EN)                     | **_TEA_** (PT)              | **_Description_** (EN)                                                                                                      |                                       
|:-----------------------------------|:----------------------------|:----------------------------------------------------------------------------------------------------------------------------|
| Address                            | Morada                      | The particulars of the place where a store or a warehouse is situated.                                                      |
| Autonomy                           | Autonomia                   | Truck range with full load capacity and fully charged electric batteries.                                                   |
| Battery Capacity                   | Capacidade da bateria       | Maximum load of the truck's electric battery pack, energy stored in the batteries.                                          |
| Charging Time                      | Tempo de Carregamento       | Fast charging time of the truck's batteries.                                                                                |
| Date                               | Data                        | Date of delivery.                                                                                                           |
| Delivery                           | Entrega                     | Transportation of goods from the warehouse to a other warehouse.                                                            |
| Delivery Plan                      | Plano de entrega            | Document with a set of routes where it contains the various deliveries for the trucks to follow.                            |
| Distribution Requirements Planning | Planeamento de distribuição | Process with the objective of serving the various orders placed by the stores without running out or overstocking products. |
| Electric Truck                     | Camião Elétrico             | Type of truck used in this company.                                                                                         |
| EletricGo                          | EletricGo                   | A system for planning distribution of deliveries between warehouses using a fleet of electric vehicles.                     |
| FM                                 | FM                          | Fleet manager                                                                                                               |
| Geographic Coordinates             | Coordenadas Geográficas     | Location system used to locate stores and warehouses, used for deliveries.                                                  |
| ID                                 | Identificador               | Delivery identifier                                                                                                         |
| LM                                 | LM                          | Logistics manager                                                                                                           |
| Load Capacity                      | Capacidade de carga         | The mass that can be carried on the truck.                                                                                  |
| Load Time                          | Tempo de Carga              | Time to place delivery on truck in minutes.                                                                                 |
| Logistics                          | Logística                   | A set of methods and means, designed to deliver the right products, to the right place, at the right time.                  |
| Route                              | Rota                        | Paths taken by trucks in the deliveries. It contains a starting point and an ending point.                                  |
| Store                              | Loja                        | Place where the products are sold. Each store contains a warehouse.                                                         |
| Tare                               | Tara                        | Weight of the truck without load.                                                                                           |
| Truck                              | Camião                      | Vehicle used for deliveries.                                                                                                |
| Unload Time                        | Tempo de Descarga           | Time to remove delivery from truck in minutes.                                                                              |
| Warehouse                          | Armazém                     | Place where the products will be delivered.                                                                                 |
| Warehouse ID                       | Identificador do Armazém    | Warehouse to make the delivery, store identifier.                                                                           |
| Warehouse Management               | Gestão de armazéns          | Warehouse management is directly related to the process of transferring products to the end customers.                      |
| WM                                 | WM                          | Warehouse manager                                                                                                           |
| Weight                             | Peso                        | Mass of the delivery associated to the weight of the products to deliver.                                                   |