# Phase 1 — Requirements Engineering

## Automobile Digital Platform

**Phase:** Phase 1 — Requirements Engineering  
**Document:** Complete Master Requirements Document  
**Status:** Completed through Step 13 — MVP Prioritization  
**Version:** 1.0  
**Previous Phase:** Phase 0 — Product Understanding  
**Next Phase:** Phase 2 — System Design

---

# 1. Purpose of Phase 1

Phase 0 established:

> **What are we building?**

Phase 1 establishes:

> **What exactly must the platform do?**

The purpose of this phase is to convert the product understanding from Phase 0 into a clear, structured, testable set of business and system requirements.

```text
PHASE 0
PRODUCT UNDERSTANDING
        |
        | What are we building?
        v
PHASE 1
REQUIREMENTS ENGINEERING
        |
        | What exactly must it do?
        v
PHASE 2
SYSTEM DESIGN
        |
        | How are we going to build it?
        v
PHASE 3
DEVELOPMENT / IMPLEMENTATION
```

This document is the **Phase 1 master reference** for the Automobile Digital Platform.

---

# 2. Project Context

The Automobile Digital Platform is a unified digital ecosystem designed to support the complete vehicle ownership lifecycle.

The platform connects:

- Customers
- Dealers
- Service Advisors
- Fleet Managers
- System Administrators
- Vehicles / Telematics Units

The platform replaces fragmented automotive processes with a centralized digital experience covering:

```text
Vehicle Discovery
        |
        v
Vehicle Configuration
        |
        v
Vehicle Purchase / Order
        |
        v
Inventory & Fulfillment
        |
        v
Vehicle Ownership
        |
        +----------------------+
        |                      |
        v                      v
Service Management       Connected Vehicle
                               |
                               v
                         Vehicle Health
                               |
                               v
                             Alerts
```

---

# 3. Phase 1 Learning Order

The Phase 1 requirements process is:

```text
Step 1  → Business Goals
Step 2  → Actors & Responsibilities
Step 3  → Business Capabilities
Step 4  → Epics
Step 5  → Features
Step 6  → User Stories
Step 7  → Acceptance Criteria
Step 8  → Business Rules
Step 9  → Edge Cases & Failures
Step 10 → Functional Requirements
Step 11 → Non-Functional Requirements
Step 12 → Dependencies & Constraints
Step 13 → MVP Prioritization

                 ↓

Step 14 → Traceability
Step 15 → Requirements Review with Agentic AI
Step 16 → Requirements Baseline
```

**Current master document scope:** Steps 1–13.

Steps 14–16 are the later requirements-governance activities and should be completed after the requirements are reviewed.

---

# Step 1 — Business Goals

## 1.1 What is a Business Goal?

A business goal answers:

> **What business outcome are we trying to achieve by building this platform?**

A business goal is **not**:

```text
X Java requirement
X Spring Boot feature
X Database table
X API
X Microservice
```

Example:

```text
Technical Requirement:
Create Order API

Business Goal:
Enable customers to digitally purchase vehicles with transparent
order tracking.
```

---

## 1.2 Core Business Goal

> **Create a unified digital automotive platform that allows customers and automotive stakeholders to manage key parts of the vehicle lifecycle—from vehicle discovery and purchase through ownership, servicing, and connected-vehicle monitoring—while improving operational visibility and reducing manual processes.**

---

## 1.3 Supporting Business Goals

### BG-01 — Improve Digital Vehicle Purchase

Enable customers to discover, configure, and purchase vehicles through a digital experience.

### BG-02 — Improve Order Transparency

Give customers and dealers clear visibility into the order lifecycle.

### BG-03 — Improve Inventory Visibility

Provide dealers with accurate and timely visibility into vehicle inventory and availability.

### BG-04 — Reduce Manual Processes

Replace disconnected/manual workflows with centralized digital workflows.

### BG-05 — Improve Service Management

Enable customers and service teams to manage vehicle service appointments and maintenance activities digitally.

### BG-06 — Enable Connected Vehicle Monitoring

Use vehicle telemetry to provide vehicle health information and relevant alerts.

### BG-07 — Improve Stakeholder Communication

Provide timely notifications for important business events.

### BG-08 — Provide Secure Role-Based Access

Ensure each stakeholder can access only the functionality and information appropriate to their role.

### BG-09 — Improve Operational Visibility

Give dealers, service teams, and fleet managers better visibility into the activities they are responsible for.

### BG-10 — Establish a Platform for Future Automotive Services

Create a foundation that can be extended with additional digital automotive capabilities.

---

## 1.4 Business Goal → Outcome

```text
Fragmented Processes
        |
        v
Unified Digital Platform
        |
        +--> Better Customer Experience
        |
        +--> Better Order Visibility
        |
        +--> Better Inventory Visibility
        |
        +--> Less Manual Work
        |
        +--> Better Service Management
        |
        +--> Connected Vehicle Insights
        |
        v
Improved Automotive Lifecycle Management
```

---

# Step 2 — Actors & Responsibilities

## 2.1 Actor Definition

An actor is a person, organization, external system, or machine that interacts with the platform.

---

## 2.2 Customer

### Responsibilities

- Browse vehicles
- View vehicle details
- Configure a vehicle
- Calculate configuration price
- Save a configuration
- Place an order
- View order status
- Receive notifications
- View owned vehicles
- Schedule service
- View vehicle health information

### Main Goal

Purchase and manage vehicles through one digital platform.

---

## 2.3 Dealer

### Responsibilities

- Manage dealership inventory
- View vehicle availability
- Review customer orders
- Process orders
- Update fulfillment status
- Coordinate vehicle fulfillment

### Main Goal

Manage inventory and fulfill customer vehicle orders efficiently.

---

## 2.4 Service Advisor

### Responsibilities

- View service appointments
- Manage appointment schedules
- Review vehicle/service information
- Respond to maintenance-related requests
- Update service status
- Coordinate service activities

### Main Goal

Manage vehicle servicing efficiently.

---

## 2.5 Fleet Manager

### Responsibilities

- View fleet vehicles
- Monitor vehicle health
- Review vehicle performance information
- Monitor alerts
- Manage maintenance activities
- Identify vehicles requiring attention

### Main Goal

Maintain fleet visibility and reduce vehicle downtime.

---

## 2.6 System Administrator

### Responsibilities

- Manage users
- Manage roles
- Manage permissions
- Manage platform configuration
- Monitor platform activity
- Support operational administration

### Main Goal

Maintain a secure and correctly configured platform.

---

## 2.7 Vehicle / Telematics Unit

The vehicle is a machine actor.

### Responsibilities

- Publish telemetry
- Provide vehicle health data
- Provide relevant vehicle status information
- Generate data that can be used for monitoring and alerts

### Main Goal

Provide connected-vehicle information to the platform.

---

## 2.8 Actor Summary

| Actor | Primary Responsibility |
|---|---|
| Customer | Purchase and manage vehicles |
| Dealer | Manage inventory and fulfill orders |
| Service Advisor | Manage vehicle service |
| Fleet Manager | Monitor fleet health and maintenance |
| Administrator | Manage users, roles, and platform configuration |
| Vehicle / Telematics Unit | Provide telemetry and vehicle health data |

---

# Step 3 — Business Capabilities

A business capability describes **what the business must be capable of doing**, independent of implementation technology.

---

## 3.1 Vehicle Discovery & Configuration

The platform must support:

- Vehicle catalog
- Vehicle details
- Model selection
- Trim selection
- Option selection
- Configuration validation
- Configuration pricing
- Saving configurations

---

## 3.2 Vehicle Ordering

The platform must support:

- Order creation
- Order validation
- Order tracking
- Order status management
- Payment processing integration
- Order notifications

---

## 3.3 Dealer & Inventory Management

The platform must support:

- Dealer management
- Vehicle inventory
- Vehicle availability
- Inventory updates
- Inventory reservation
- Order fulfillment

---

## 3.4 Customer Management

The platform must support:

- Customer profiles
- Customer preferences
- Vehicle ownership records
- Customer-related notifications

---

## 3.5 Service Management

The platform must support:

- Service appointment booking
- Service slot availability
- Appointment status
- Maintenance scheduling
- Service history

---

## 3.6 Connected Vehicle Management

The platform must support:

- Telemetry collection
- Vehicle health monitoring
- Vehicle diagnostics information
- Vehicle alert generation

---

## 3.7 Notification Management

The platform must support:

- Order notifications
- Service notifications
- Maintenance alerts
- Fleet alerts

---

## 3.8 Identity & Access Management

The platform must support:

- Authentication
- Authorization
- Role-based access control
- User management

---

## 3.9 Business Capability Map

```text
AUTOMOBILE DIGITAL PLATFORM
│
├── Vehicle Discovery & Configuration
│
├── Vehicle Ordering
│
├── Dealer & Inventory Management
│
├── Customer Management
│
├── Service Management
│
├── Connected Vehicle Management
│
├── Notification Management
│
└── Identity & Access Management
```

---

# Step 4 — Epics

An epic is a large business requirement that contains multiple related features and user stories.

---

## Epic 1 — Vehicle Discovery & Configuration

### Objective

Allow customers to discover vehicles and create valid vehicle configurations.

### Scope

- Vehicle catalog
- Vehicle details
- Model selection
- Trim selection
- Options
- Configuration validation
- Price calculation
- Save configuration

---

## Epic 2 — Customer Vehicle Ordering

### Objective

Allow customers to place and track vehicle orders digitally.

### Scope

- Order creation
- Order validation
- Payment integration
- Order status
- Order history
- Notifications

---

## Epic 3 — Dealer & Inventory Management

### Objective

Allow dealers to manage inventory and fulfill customer orders.

### Scope

- Dealer inventory
- Availability
- Inventory updates
- Reservation
- Order fulfillment

---

## Epic 4 — Customer & Ownership Management

### Objective

Allow customers to manage their profile and owned vehicles.

### Scope

- Customer profile
- Preferences
- Ownership records
- Vehicle association

---

## Epic 5 — Service Management

### Objective

Allow customers and service teams to manage vehicle service.

### Scope

- Service centers
- Available slots
- Appointment booking
- Appointment status
- Maintenance
- Service history

---

## Epic 6 — Connected Vehicle Monitoring

### Objective

Provide vehicle health and connected-vehicle insights.

### Scope

- Telemetry
- Vehicle health
- Diagnostics
- Alerts
- Monitoring

---

## Epic 7 — Notifications & Communication

### Objective

Keep stakeholders informed about important events.

### Scope

- Order notifications
- Service notifications
- Maintenance notifications
- Fleet alerts

---

## Epic 8 — Identity & Platform Administration

### Objective

Provide secure access and platform administration.

### Scope

- Login
- Authentication
- Authorization
- Roles
- Permissions
- User management

---

# Step 5 — Features

## Epic 1 — Vehicle Discovery & Configuration

### F-001 — Vehicle Catalog

Customers can browse available vehicle models.

### F-002 — Vehicle Details

Customers can view specifications, pricing, trims, and available options.

### F-003 — Vehicle Configuration

Customers can select model, trim, and options.

### F-004 — Configuration Validation

The system validates whether selected options are compatible.

### F-005 — Configuration Price Calculation

The system calculates the total configuration price.

### F-006 — Save Configuration

Customers can save a configuration for later use.

---

## Epic 2 — Customer Vehicle Ordering

### F-007 — Create Order

Customers can create an order from a valid configuration.

### F-008 — Order Validation

The system validates customer, configuration, inventory, and order information.

### F-009 — Payment Integration

The platform integrates with a payment provider.

### F-010 — Order Tracking

Customers can view the current order status.

### F-011 — Order History

Customers can view previous orders.

### F-012 — Order Notifications

Customers receive important order updates.

---

## Epic 3 — Dealer & Inventory Management

### F-013 — Inventory Listing

Dealers can view inventory.

### F-014 — Add Inventory

Authorized dealer users can add vehicles to inventory.

### F-015 — Update Inventory

Authorized users can update inventory information.

### F-016 — Availability Management

The system tracks vehicle availability.

### F-017 — Inventory Reservation

Inventory can be reserved during order processing.

### F-018 — Order Fulfillment

Dealers can process and fulfill orders.

---

## Epic 4 — Customer & Ownership Management

### F-019 — Customer Profile

Customers can view and manage their profile.

### F-020 — Ownership Records

The platform associates owned vehicles with customers.

### F-021 — Customer Preferences

Customers can manage relevant preferences.

---

## Epic 5 — Service Management

### F-022 — Service Center Management

Service locations can be represented in the platform.

### F-023 — Service Slot Availability

Available service slots can be viewed.

### F-024 — Book Appointment

Customers can create service appointments.

### F-025 — Appointment Status

Customers and service advisors can view/update appointment status according to permissions.

### F-026 — Maintenance Scheduling

Maintenance activities can be scheduled.

### F-027 — Service History

Relevant service records can be viewed.

---

## Epic 6 — Connected Vehicle Monitoring

### F-028 — Telemetry Ingestion

The platform receives telemetry data.

### F-029 — Vehicle Health Monitoring

The platform processes relevant vehicle health information.

### F-030 — Diagnostic Information

Authorized users can view relevant diagnostics.

### F-031 — Vehicle Alerts

The platform generates alerts for defined vehicle conditions.

---

## Epic 7 — Notifications & Communication

### F-032 — Order Notifications

Send notifications for important order events.

### F-033 — Service Notifications

Send appointment and service notifications.

### F-034 — Maintenance Alerts

Notify relevant users about maintenance conditions.

### F-035 — Fleet Alerts

Notify fleet managers about relevant fleet conditions.

---

## Epic 8 — Identity & Platform Administration

### F-036 — User Authentication

Users can securely log in.

### F-037 — Role-Based Authorization

Access is controlled by user role.

### F-038 — User Management

Administrators can manage users.

### F-039 — Role Management

Administrators can manage role assignments and permissions.

---

# Step 6 — User Stories

User stories express requirements from the perspective of an actor.

Standard format:

```text
As a <role>
I want <capability>
So that <business value>
```

---

## Vehicle Discovery & Configuration

### US-001 — Browse Vehicles

**As a customer,**  
I want to browse available vehicles,  
**so that** I can find a vehicle that meets my needs.

### US-002 — View Vehicle Details

**As a customer,**  
I want to view vehicle specifications and pricing,  
**so that** I can make an informed decision.

### US-003 — Configure Vehicle

**As a customer,**  
I want to select a model, trim, and options,  
**so that** I can create my desired vehicle configuration.

### US-004 — Validate Configuration

**As a customer,**  
I want the system to validate my configuration,  
**so that** I cannot create an invalid combination.

### US-005 — Calculate Price

**As a customer,**  
I want to see the total configuration price,  
**so that** I understand the expected purchase cost.

### US-006 — Save Configuration

**As a customer,**  
I want to save my configuration,  
**so that** I can return to it later.

---

## Ordering

### US-007 — Place Order

**As a customer,**  
I want to place an order for a valid vehicle configuration,  
**so that** I can purchase the vehicle digitally.

### US-008 — Track Order

**As a customer,**  
I want to view my order status,  
**so that** I know the current progress of my purchase.

### US-009 — Receive Order Updates

**As a customer,**  
I want to receive notifications when my order changes,  
**so that** I do not need to repeatedly check the platform.

---

## Dealer & Inventory

### US-010 — View Inventory

**As a dealer,**  
I want to view available inventory,  
**so that** I know which vehicles are available.

### US-011 — Manage Inventory

**As a dealer,**  
I want to add and update inventory,  
**so that** vehicle availability remains accurate.

### US-012 — Fulfill Order

**As a dealer,**  
I want to process customer orders,  
**so that** vehicles can move through fulfillment.

---

## Customer & Ownership

### US-013 — Manage Profile

**As a customer,**  
I want to manage my profile,  
**so that** my account information remains accurate.

### US-014 — View Owned Vehicles

**As a customer,**  
I want to view vehicles associated with my ownership,  
**so that** I can manage my vehicles through the platform.

---

## Service

### US-015 — Find Service Slot

**As a customer,**  
I want to view available service slots,  
**so that** I can select a convenient appointment.

### US-016 — Book Service

**As a customer,**  
I want to book a service appointment,  
**so that** I can get my vehicle serviced.

### US-017 — Manage Appointment

**As a service advisor,**  
I want to manage service appointments,  
**so that** service operations remain organized.

### US-018 — View Service History

**As a customer,**  
I want to view service history,  
**so that** I can understand previous maintenance activities.

---

## Connected Vehicle

### US-019 — View Vehicle Health

**As a customer,**  
I want to view vehicle health information,  
**so that** I can understand the current condition of my vehicle.

### US-020 — Monitor Fleet

**As a fleet manager,**  
I want to monitor vehicle health across my fleet,  
**so that** I can identify issues and reduce downtime.

### US-021 — Receive Vehicle Alert

**As a relevant vehicle stakeholder,**  
I want to receive alerts when defined vehicle conditions occur,  
**so that** I can take appropriate action.

---

## Administration

### US-022 — Authenticate

**As a platform user,**  
I want to securely authenticate,  
**so that** only authorized users can access the platform.

### US-023 — Manage Users

**As an administrator,**  
I want to manage platform users,  
**so that** access remains controlled.

### US-024 — Manage Roles

**As an administrator,**  
I want to assign appropriate roles and permissions,  
**so that** users can access only permitted functionality.

---

# Step 7 — Acceptance Criteria

Acceptance criteria define when a user story is considered complete from a business perspective.

---

## US-001 — Browse Vehicles

### Acceptance Criteria

```text
Given the customer is authenticated or browsing the public catalog
When the customer opens the vehicle catalog
Then available vehicles are displayed
```

```text
Given vehicles exist
When the customer views the catalog
Then relevant vehicle information is displayed
```

```text
Given no vehicles are available
When the customer opens the catalog
Then the platform displays an appropriate empty state
```

---

## US-003 — Configure Vehicle

### Acceptance Criteria

```text
Given a valid vehicle model exists
When the customer selects a model, trim, and compatible options
Then the configuration is created successfully
```

```text
Given an option is incompatible
When the customer selects it
Then the system prevents or rejects the invalid configuration
```

---

## US-005 — Calculate Price

### Acceptance Criteria

```text
Given a valid configuration
When the customer views the price
Then the system displays the calculated total
```

```text
Given the customer changes an option
When the configuration changes
Then the total price is recalculated
```

---

## US-007 — Place Order

### Acceptance Criteria

```text
Given the customer has a valid configuration
And required customer information is available
And required inventory is available
When the customer places the order
Then the order is created
```

```text
Given inventory is unavailable
When the customer attempts to place the order
Then the order is not created as a confirmed order
And the customer receives an appropriate message
```

---

## US-008 — Track Order

### Acceptance Criteria

```text
Given the customer owns the order
When the customer requests the order
Then the current order status is displayed
```

```text
Given the customer requests another customer's order
When the request is processed
Then access is denied
```

---

## US-010 — View Inventory

### Acceptance Criteria

```text
Given the dealer is authorized
When the dealer opens inventory
Then permitted inventory is displayed
```

---

## US-012 — Fulfill Order

### Acceptance Criteria

```text
Given an order is available for fulfillment
When an authorized dealer updates the fulfillment status
Then the order status is updated
And the relevant event/notification is generated where applicable
```

---

## US-016 — Book Service

### Acceptance Criteria

```text
Given the customer owns the vehicle
And a valid service slot is available
When the customer books the appointment
Then an appointment is created
```

```text
Given the selected slot is no longer available
When the customer attempts to book it
Then the booking is rejected
And the customer is asked to select another slot
```

---

## US-020 — Monitor Fleet

### Acceptance Criteria

```text
Given the fleet manager is authorized
When the fleet dashboard is opened
Then permitted fleet vehicles are displayed
```

```text
Given a vehicle has a relevant health alert
When the fleet manager views the fleet
Then the alert is visible according to the defined rules
```

---

## US-022 — Authenticate

### Acceptance Criteria

```text
Given a registered user provides valid credentials
When authentication succeeds
Then the user receives an authenticated session/token
```

```text
Given invalid credentials
When authentication is attempted
Then access is denied
```

---

# Step 8 — Business Rules

Business rules define policies and constraints that control business behavior.

---

## BR-001 — Valid Vehicle Configuration

A vehicle configuration must contain a valid model and valid compatible options.

```text
Invalid combination
        |
        v
Configuration rejected
```

---

## BR-002 — Price Calculation

The configuration price must be based on the selected vehicle, trim, and applicable options.

```text
Base Vehicle
    +
Trim
    +
Options
    =
Configuration Price
```

---

## BR-003 — Inventory Availability

A vehicle cannot be confirmed for an order when the required inventory is unavailable.

---

## BR-004 — Inventory Reservation

When required, inventory must be reserved according to the order process so that the same inventory is not incorrectly allocated to multiple confirmed orders.

---

## BR-005 — Order Ownership

A customer can view only orders they are authorized to access.

---

## BR-006 — Order Status

Orders must follow valid lifecycle transitions.

Example:

```text
CREATED
   |
   v
CONFIRMED
   |
   v
PROCESSING
   |
   v
FULFILLED
   |
   v
COMPLETED
```

Cancellation/failure states must follow explicitly defined transition rules.

---

## BR-007 — Appointment Availability

A service appointment can be created only for an available slot.

---

## BR-008 — Appointment Ownership

Customers can manage only appointments they are authorized to access.

---

## BR-009 — Vehicle Ownership

Ownership information must associate a vehicle with the correct customer according to the defined ownership process.

---

## BR-010 — Role-Based Access

Users can perform only actions permitted by their role.

```text
CUSTOMER
DEALER
SERVICE_ADVISOR
FLEET_MANAGER
ADMIN
```

---

## BR-011 — Vehicle Alerts

A vehicle alert should be generated only when a configured condition or business rule is satisfied.

---

## BR-012 — Notification Trigger

Important business events should trigger relevant notifications.

Examples:

```text
Order Created
Order Updated
Appointment Created
Maintenance Alert
Vehicle Health Alert
```

---

# Step 9 — Edge Cases & Failures

Requirements must describe not only the happy path but also what happens when something goes wrong.

---

## 9.1 Vehicle Configuration

### Edge Cases

- Customer selects incompatible options.
- Selected option becomes unavailable.
- Vehicle model is discontinued.
- Configuration contains missing required information.
- Price information is temporarily unavailable.

### Expected Behavior

The system should:

```text
Detect
  |
  v
Reject / Prevent
  |
  v
Explain the problem
  |
  v
Allow correction
```

---

## 9.2 Order Creation

### Edge Cases

- Inventory becomes unavailable immediately before order creation.
- Customer submits the order twice.
- Payment fails.
- Payment succeeds but order persistence fails.
- Order request times out.
- Customer loses connection during submission.

### Expected Behavior

The platform should prevent duplicate orders and maintain a consistent order state.

---

## 9.3 Inventory

### Edge Cases

- Two customers attempt to reserve the same vehicle.
- Dealer updates inventory while an order is being created.
- Inventory service becomes unavailable.
- Inventory data is inconsistent.

---

## 9.4 Payment

### Edge Cases

- Payment rejected.
- Payment provider unavailable.
- Payment timeout.
- Duplicate payment callback.
- Payment succeeds but notification fails.

The order workflow must define how these states are reconciled.

---

## 9.5 Service Booking

### Edge Cases

- Slot becomes unavailable before booking.
- Customer attempts duplicate booking.
- Service center is unavailable.
- Appointment creation times out.
- Appointment update conflicts with another update.

---

## 9.6 Connected Vehicle

### Edge Cases

- Vehicle stops sending telemetry.
- Telemetry arrives late.
- Duplicate telemetry arrives.
- Invalid telemetry is received.
- Very large telemetry bursts occur.
- Vehicle sends an unexpected value.
- Telemetry processing fails.

---

## 9.7 Notification

### Edge Cases

- Notification provider is unavailable.
- Delivery fails.
- Duplicate notification event occurs.
- Customer has invalid contact information.

---

## 9.8 Authentication

### Edge Cases

- Invalid credentials.
- Expired token.
- Revoked access.
- Unauthorized role.
- Attempt to access another user's resource.

---

# Step 10 — Functional Requirements

Functional requirements define what the system shall do.

---

## 10.1 Vehicle Requirements

### FR-001

The system shall allow users to view available vehicle models.

### FR-002

The system shall display relevant vehicle information.

### FR-003

The system shall allow customers to configure vehicles.

### FR-004

The system shall validate configuration compatibility.

### FR-005

The system shall calculate configuration pricing.

### FR-006

The system shall allow customers to save valid configurations.

---

## 10.2 Order Requirements

### FR-007

The system shall allow customers to create orders from valid configurations.

### FR-008

The system shall validate required order information.

### FR-009

The system shall verify required inventory availability.

### FR-010

The system shall maintain order status.

### FR-011

The system shall allow authorized users to view order information.

### FR-012

The system shall provide order history where applicable.

### FR-013

The system shall integrate with a payment provider.

---

## 10.3 Inventory Requirements

### FR-014

The system shall allow authorized dealer users to view inventory.

### FR-015

The system shall allow authorized dealer users to add inventory.

### FR-016

The system shall allow authorized dealer users to update inventory.

### FR-017

The system shall track inventory availability.

### FR-018

The system shall support inventory reservation during applicable order workflows.

---

## 10.4 Customer Requirements

### FR-019

The system shall allow customers to manage their profile.

### FR-020

The system shall associate owned vehicles with customers.

### FR-021

The system shall allow customers to view their owned vehicles.

---

## 10.5 Service Requirements

### FR-022

The system shall provide available service slots.

### FR-023

The system shall allow authorized customers to book service appointments.

### FR-024

The system shall prevent invalid or unavailable appointment bookings.

### FR-025

The system shall allow service advisors to manage appointments.

### FR-026

The system shall maintain relevant service history.

---

## 10.6 Connected Vehicle Requirements

### FR-027

The system shall receive vehicle telemetry.

### FR-028

The system shall process relevant telemetry.

### FR-029

The system shall provide vehicle health information.

### FR-030

The system shall generate alerts based on configured conditions.

### FR-031

The system shall allow authorized users to view relevant vehicle information.

---

## 10.7 Notification Requirements

### FR-032

The system shall generate relevant order notifications.

### FR-033

The system shall generate relevant service notifications.

### FR-034

The system shall generate relevant vehicle maintenance/health alerts.

### FR-035

The system shall track notification processing/delivery status where supported.

---

## 10.8 Identity & Administration Requirements

### FR-036

The system shall authenticate users.

### FR-037

The system shall authorize requests based on permissions.

### FR-038

The system shall support role-based access control.

### FR-039

The system shall allow authorized administrators to manage users.

### FR-040

The system shall allow authorized administrators to manage roles/permissions.

---

# Step 11 — Non-Functional Requirements

Non-functional requirements define **how well** the system must operate.

---

## 11.1 Security

### NFR-001

Protected functionality shall require authentication.

### NFR-002

The platform shall enforce role-based authorization.

### NFR-003

Sensitive communication shall use secure transport.

### NFR-004

Secrets shall not be hard-coded in source code.

### NFR-005

The platform shall validate input received from clients and external systems.

### NFR-006

Unauthorized access attempts shall be rejected.

---

## 11.2 Performance

### NFR-007

Normal API requests should respond within an agreed performance target.

### NFR-008

The system should handle expected concurrent user activity without unacceptable degradation.

### NFR-009

Telemetry processing should support expected data bursts.

Performance targets must be validated with actual workload assumptions before production.

---

## 11.3 Scalability

### NFR-010

Stateless application components should support horizontal scaling where required.

### NFR-011

The messaging architecture should support increased event volume.

### NFR-012

Database growth should be monitored and managed.

---

## 11.4 Availability

### NFR-013

The platform should target high availability for critical business functions.

Initial planning target:

```text
99.9% availability
```

The target must be validated against business requirements before production.

---

## 11.5 Reliability

### NFR-014

The platform shall handle expected dependency failures gracefully.

### NFR-015

Important asynchronous events shall not be silently lost.

### NFR-016

The system shall avoid unintended duplicate business operations where idempotency is required.

---

## 11.6 Observability

### NFR-017

The platform shall produce useful application logs.

### NFR-018

The platform shall expose relevant system metrics.

### NFR-019

Critical distributed workflows should support request tracing.

### NFR-020

Critical failures should generate operational alerts.

---

## 11.7 Maintainability

### NFR-021

Code should follow consistent engineering standards.

### NFR-022

Business logic should be separated from infrastructure concerns.

### NFR-023

APIs and important business behavior should be documented.

---

## 11.8 Testability

### NFR-024

Core business logic shall be unit-testable.

### NFR-025

Important integrations shall support integration testing.

### NFR-026

Critical end-to-end journeys shall be testable.

---

## 11.9 Data Integrity

### NFR-027

The platform shall maintain consistency of transactional business data.

### NFR-028

Important business state transitions shall be auditable where required.

---

# Step 12 — Dependencies & Constraints

## 12.1 External Dependencies

Potential external dependencies include:

```text
Identity Provider
Payment Provider
Notification Provider
Vehicle / Telematics Systems
Cloud Infrastructure
Monitoring Infrastructure
```

---

## 12.2 Internal Dependencies

```text
Customer
   |
   v
Vehicle Configuration
   |
   v
Order
   |
   v
Inventory
   |
   v
Fulfillment
```

Additional:

```text
Order
   |
   v
Notification
```

```text
Vehicle
   |
   v
Telemetry
   |
   v
Vehicle Health
   |
   v
Alert
   |
   v
Notification
```

---

## 12.3 Technical Constraints

The project is designed around:

- Angular
- TypeScript
- Java
- Spring Boot
- PostgreSQL
- Kafka
- REST APIs
- OAuth2/OIDC
- Docker
- Git

These are project technology decisions and should be validated during Phase 2 architecture design.

---

## 12.4 Project Constraints

### Solo Developer

The entire application is being developed end-to-end by one Software Engineer.

Therefore:

```text
Architecture
      |
      v
Must be understandable
      +
Must be implementable
      +
Must be testable
      +
Must be operable
      |
      v
Without unnecessary complexity
```

### Time / Complexity Constraint

The system should be developed incrementally.

### Infrastructure Constraint

Infrastructure should be introduced only when it provides clear project value.

### Learning Constraint

Because this is a first full-stack project, each major technical decision should be understandable and documented.

---

## 12.5 Business Constraints

- Inventory information must be sufficiently accurate for ordering.
- Access must be controlled by stakeholder role.
- Vehicle telemetry must be securely received.
- Payment processing is delegated to an external provider.
- Important customer and operational events require notification mechanisms.

---

# Step 13 — MVP Prioritization

The MVP should demonstrate the most important business journey first.

The primary MVP journey is:

```text
Customer
   |
   v
Browse Vehicle
   |
   v
Configure Vehicle
   |
   v
Calculate Price
   |
   v
Place Order
   |
   v
Inventory Validation
   |
   v
Order Created
   |
   v
Track Order
   |
   v
Receive Notification
```

---

## 13.1 MVP Priority Levels

```text
P0 = Must have for MVP
P1 = Important after core MVP
P2 = Later enhancement
```

---

## 13.2 P0 — Must Have

### Customer

```text
P0
- Authentication
- Vehicle Catalog
- Vehicle Details
- Vehicle Configuration
- Configuration Validation
- Price Calculation
- Save Configuration
- Place Order
- Order Tracking
- Order Notifications
```

### Dealer

```text
P0
- Authentication
- Inventory View
- Add Inventory
- Update Inventory
- Availability Management
- Order View
- Basic Order Fulfillment
```

### Platform

```text
P0
- Authentication / Authorization
- Role-Based Access
- REST APIs
- PostgreSQL
- Basic event processing
- Notification mechanism
- Logging
- Basic monitoring
```

---

## 13.3 P1 — Important Next

```text
P1
- Payment integration refinement
- Advanced order lifecycle
- Customer ownership management
- Service center management
- Service slot availability
- Service appointment booking
- Appointment management
- Service history
- Basic connected vehicle monitoring
- Basic telemetry processing
- Basic vehicle health alerts
```

---

## 13.4 P2 — Later

```text
P2
- Advanced fleet dashboards
- Advanced vehicle diagnostics
- Advanced fleet analytics
- Advanced predictive maintenance
- Advanced communication preferences
- Advanced reporting
- Additional automotive integrations
```

---

## 13.5 MVP Boundary

The MVP should prove:

```text
Can a customer
    |
    v
Discover a vehicle
    |
    v
Configure it
    |
    v
Understand the price
    |
    v
Place an order
    |
    v
Have inventory considered
    |
    v
Track the order
    |
    v
Receive important updates?
```

If this journey works reliably, the core digital purchasing proposition has been demonstrated.

---

# 14. Requirements Relationship

The complete requirements chain is:

```text
BUSINESS GOAL
      |
      v
ACTOR
      |
      v
BUSINESS CAPABILITY
      |
      v
EPIC
      |
      v
FEATURE
      |
      v
USER STORY
      |
      v
ACCEPTANCE CRITERIA
      |
      v
BUSINESS RULE
      |
      v
EDGE CASE / FAILURE
      |
      v
FUNCTIONAL REQUIREMENT
      |
      v
NON-FUNCTIONAL REQUIREMENT
      |
      v
MVP PRIORITY
```

This relationship is important because it prevents requirements from becoming disconnected technical tasks.

---

# 15. Primary End-to-End Business Journey

## Customer Vehicle Purchase

```text
1. Customer
      |
      v
2. Browse Vehicles
      |
      v
3. Select Vehicle
      |
      v
4. Select Trim
      |
      v
5. Select Options
      |
      v
6. Validate Configuration
      |
      v
7. Calculate Price
      |
      v
8. Save Configuration
      |
      v
9. Place Order
      |
      v
10. Validate Inventory
      |
      v
11. Process Payment
      |
      v
12. Create Order
      |
      v
13. Publish Order Event
      |
      v
14. Send Notification
      |
      v
15. Customer Tracks Order
      |
      v
16. Dealer Fulfills Order
```

This is the primary business journey that Phase 2 System Design must support.

---

# 16. Secondary Business Journeys

## Dealer Journey

```text
Login
  |
  v
View Inventory
  |
  v
Manage Inventory
  |
  v
View Orders
  |
  v
Process Order
  |
  v
Fulfill Vehicle
```

## Service Journey

```text
Customer
  |
  v
Select Owned Vehicle
  |
  v
View Service Availability
  |
  v
Book Appointment
  |
  v
Service Advisor
  |
  v
Manage Appointment
  |
  v
Complete Service
  |
  v
Update Service History
```

## Connected Vehicle Journey

```text
Vehicle
  |
  v
Telemetry
  |
  v
Platform
  |
  v
Process Data
  |
  v
Vehicle Health
  |
  v
Condition Detected
  |
  v
Alert
  |
  v
Customer / Fleet Manager / Service Advisor
```

---

# 17. Requirements Scope Summary

## In Scope

```text
Vehicle Catalog
Vehicle Configuration
Vehicle Pricing
Customer Management
Vehicle Ordering
Dealer Inventory
Order Tracking
Order Fulfillment
Notifications
Service Management
Connected Vehicle Monitoring
Vehicle Health
Authentication
Authorization
Role Management
```

## Out of Initial MVP Scope

Unless later prioritized:

```text
Advanced predictive maintenance
Advanced analytics
Complex fleet optimization
Large-scale marketplace capabilities
Advanced AI-based vehicle diagnosis
Complex financial products
Advanced dealer CRM
```

These can be introduced after the core platform is stable.

---

# 18. Phase 1 Requirement Quality Checklist

Before moving to Phase 2, requirements should be:

```text
[ ] Clear
[ ] Specific
[ ] Understandable
[ ] Business-aligned
[ ] Testable
[ ] Consistent
[ ] Traceable
[ ] Prioritized
[ ] Feasible
[ ] Free from unnecessary technical assumptions
```

Each important requirement should answer:

```text
What?
Why?
Who?
When?
What happens if it fails?
How do we know it works?
```

---

# 19. Requirements Completion Status

| Step | Requirement Area | Status |
|---|---|---|
| 1 | Business Goals | Complete |
| 2 | Actors & Responsibilities | Complete |
| 3 | Business Capabilities | Complete |
| 4 | Epics | Complete |
| 5 | Features | Complete |
| 6 | User Stories | Complete |
| 7 | Acceptance Criteria | Complete |
| 8 | Business Rules | Complete |
| 9 | Edge Cases & Failures | Complete |
| 10 | Functional Requirements | Complete |
| 11 | Non-Functional Requirements | Complete |
| 12 | Dependencies & Constraints | Complete |
| 13 | MVP Prioritization | Complete |
| 14 | Traceability | Next |
| 15 | Requirements Review with Agentic AI | Next |
| 16 | Requirements Baseline | Next |

---

# 20. What Comes After Step 13

The remaining Phase 1 governance steps are:

## Step 14 — Traceability

Connect:

```text
Business Goal
   ↓
Epic
   ↓
Feature
   ↓
User Story
   ↓
Acceptance Criteria
   ↓
Requirement
   ↓
Implementation
   ↓
Test
```

This allows us to prove that every important business requirement is represented in the system.

---

## Step 15 — Requirements Review with Agentic AI

Use Agentic AI as a requirements reviewer to identify:

- Missing requirements
- Contradictions
- Ambiguous wording
- Missing edge cases
- Missing acceptance criteria
- Security gaps
- Role/access gaps
- Dependency gaps
- Unrealistic assumptions
- MVP scope problems

The AI is a reviewer, not the final decision maker.

---

## Step 16 — Requirements Baseline

After review:

```text
Requirements
      |
      v
Review
      |
      v
Corrections
      |
      v
Approval
      |
      v
BASELINE
      |
      v
PHASE 2 SYSTEM DESIGN
```

The baseline becomes the controlled version of the requirements used for system design.

---

# 21. Phase 1 → Final Output

At the end of Phase 1, we should know:

```text
WHAT BUSINESS PROBLEM?
        |
        v
WHAT BUSINESS GOALS?
        |
        v
WHO USES THE SYSTEM?
        |
        v
WHAT CAN THEY DO?
        |
        v
WHAT ARE THE EPICS?
        |
        v
WHAT FEATURES ARE REQUIRED?
        |
        v
WHAT ARE THE USER STORIES?
        |
        v
HOW DO WE KNOW EACH STORY WORKS?
        |
        v
WHAT BUSINESS RULES APPLY?
        |
        v
WHAT CAN GO WRONG?
        |
        v
WHAT MUST THE SYSTEM DO?
        |
        v
HOW WELL MUST IT DO IT?
        |
        v
WHAT DEPENDS ON WHAT?
        |
        v
WHAT BELONGS IN THE MVP?
```

---

# Phase 1 — Current Status

```text
✅ Step 1  — Business Goals
✅ Step 2  — Actors & Responsibilities
✅ Step 3  — Business Capabilities
✅ Step 4  — Epics
✅ Step 5  — Features
✅ Step 6  — User Stories
✅ Step 7  — Acceptance Criteria
✅ Step 8  — Business Rules
✅ Step 9  — Edge Cases & Failures
✅ Step 10 — Functional Requirements
✅ Step 11 — Non-Functional Requirements
✅ Step 12 — Dependencies & Constraints
⭐ Step 13 — MVP Prioritization
```

---

# PHASE 1 → REQUIREMENTS ENGINEERING

```text
                    PHASE 0
              PRODUCT UNDERSTANDING
                       |
                       v
              "What are we building?"
                       |
                       v
             +---------------------+
             |       PHASE 1       |
             | REQUIREMENTS        |
             |    ENGINEERING      |
             +----------+----------+
                        |
                        v
                  Business Goals
                        |
                        v
                     Actors
                        |
                        v
                 Capabilities
                        |
                        v
                     Epics
                        |
                        v
                    Features
                        |
                        v
                 User Stories
                        |
                        v
              Acceptance Criteria
                        |
                        v
                Business Rules
                        |
                        v
             Edge Cases / Failures
                        |
                        v
           Functional Requirements
                        |
                        v
        Non-Functional Requirements
                        |
                        v
          Dependencies & Constraints
                        |
                        v
              MVP Prioritization
                        |
                        v
                  Traceability
                        |
                        v
              Agentic AI Review
                        |
                        v
             Requirements Baseline
                        |
                        v
                    PHASE 2
                 SYSTEM DESIGN
```

## Phase 1 Objective

> **Turn the product vision into a complete, clear, testable, prioritized, and reviewable set of requirements that can be used as the foundation for System Design.**

**Phase 1 Steps 1–13: COMPLETE**
