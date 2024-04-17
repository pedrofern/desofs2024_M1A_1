# Create a Delivery

## 1. Requirements Engineering

### 1.1. User Story Description

**US1** - As a warehouse manager I want to create a delivery.

* Area/Application:
  * Logistics

### 1.2. Customer Specifications and Clarifications

n/a

### 1.3. Acceptance Criteria

n/a

### 1.4. Found out Dependencies

There is a dependency on creating a delivery and getting all warehouses from the API, in order to view/search the deliveries and warehouses.

### 1.5 Input and Output Data

**Input Data:**

* Selected data:
  * Warehouse

* Input data:
  * Date
  * Weight
  * Delivery Request File

**Output Data:**

* The success of the operation

### 1.6. System Sequence Diagram (SSD)

![SSD_CreateDelivery](SSD_CreateDelivery.png)

## 2. Analysis

### 2.1. Relevant Domain Model Excerpt

![DM_CreateDelivery](DM_CreateDelivery.png)

## 3. Design

### 3.1. Sequence Diagram (SD)

#### Systematization

According to the taken rationale, the conceptual classes promoted to software classes are:

* Delivery

Other software classes (i.e. Pure Fabrication) identified:

* DeliveryService
* CreateDeliveryDTO

### 4 Tests

n/a

## 5. Implementation

n/a

## 6. Integration and Demo

* It was added a Delivery button to the web app menu, which opens the Deliveries page with a form to create one on the left and the list of the existing deliveries on the right.
* Upload the delivery request file

## 7. Observations

n/a

## 8. Secure File Handling in EletricGo

### 8.1 Overview

Brief introduction to the secure file handling component, its relevance to the EletricGo system, and how it aligns with SSDLC principles.

### 8.2 SDLC Integration

#### 8.2.1 Analysis/Requirements

Discuss how the file upload and deletion features were analyzed and the security requirements that arose from this analysis.

#### 8.2.2 Design

Outline the design decisions made for file handling, including secure file storage, user interface considerations, and secure access controls.

### 8.3 SSDLC Best Practices

#### 8.3.1 Preliminary Software Risk Analysis

Identify potential risks associated with file handling, such as unauthorized access or data leakage, and the measures taken to mitigate these risks.

#### 8.3.2 Security Requirements Engineering

Detail the security requirements specific to file handling, like encryption of files in transit and at rest, as well as integrity checks.

#### 8.3.3 Secure Design

Describe how security has been embedded into the design of file handling features, including the choice of technologies and protocols.

### 8.4 SSDLC Activities

#### 8.4.1 Cases of Abuse

Enumerate potential abuse cases, such as uploading malicious files or unauthorized deletion, and how the system guards against them.

#### 8.4.2 Functional Security Requirements

List the security functionalities the system provides, such as access controls, audit trails, and secure deletion processes.

#### 8.4.3 Non-Functional Security Requirements

Cover non-functional aspects like performance implications of security measures and system availability.

#### 8.4.4 Secure Development Requirements

Specify coding standards, libraries, and frameworks used to ensure secure development of the file handling features.

#### 8.4.5 Secure Architecture

Discuss the overall architecture of the file handling system, emphasizing components that contribute to security.

#### 8.4.6 Secure Design Patterns

Explain any secure design patterns implemented, such as using a proxy to handle file uploads or sandboxing file storage.

#### 8.4.7 Threat Modeling

Provide an overview of threat modeling conducted, the threats identified, and how the design mitigates these threats.

#### 8.4.8 Security Test Planning

Outline the plan for security testing of file handling features, including both automated and manual testing strategies.

#### 8.4.9 Security Architecture Review

Document any reviews or audits of the file handling architecture, and the outcomes or changes that resulted from them.

### 8.5 Compliance and Standards

Ensure that file handling practices comply with relevant standards and data protection regulations.

### 8.5.1 Testing and Validation

Describe how the file handling security features have been tested and validated against the identified requirements and threats.
