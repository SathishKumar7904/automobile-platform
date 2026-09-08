# Phase 2 — System Design

## Automobile Digital Platform

**Phase:** Phase 2 — System Design  
**Document:** Complete Master README  
**Status:** Completed  
**Version:** 1.0

---

## 1. Purpose

Phase 0 answered **what we are building**.  
Phase 1 answered **what the system must do**.  
Phase 2 answers **how we are going to build it**.

```text
PHASE 0
Product Understanding
      |
      v
"What are we building?"
      |
      v
PHASE 1
Requirements Engineering
      |
      v
"What must it do?"
      |
      v
PHASE 2
System Design
      |
      v
"How will we build it?"
      |
      v
PHASE 3
Development / Implementation
```

The design must be practical for a single developer while still providing a clear path to future scale.

---

## 2. Project Context

The Automobile Digital Platform is a unified digital ecosystem supporting the vehicle lifecycle:

```text
Vehicle Discovery
      |
      v
Vehicle Configuration
      |
      v
Vehicle Ordering
      |
      v
Inventory & Fulfillment
      |
      v
Vehicle Ownership
      |
      +-------------------+
      |                   |
      v                   v
Service Management   Connected Vehicle
                          |
                          v
                    Vehicle Health
                          |
                          v
                        Alerts
```

Main actors:

- Customer
- Dealer
- Service Advisor
- Fleet Manager
- System Administrator
- Vehicle / Telematics Unit

---

# 3. Phase 2 Learning Order

```text
Step 1  -> System Design Goals
Step 2  -> System Context
Step 3  -> System Architecture
Step 4  -> Architecture Components
Step 5  -> Service Boundaries
Step 6  -> Communication Architecture
Step 7  -> Data Architecture
Step 8  -> API Architecture
Step 9  -> Security Architecture
Step 10 -> Deployment Architecture
Step 11 -> Observability
Step 12 -> Disaster Recovery & Resilience
Step 13 -> Cost & Capacity Planning
Step 14 -> Technology Stack & Technology Decisions
Step 15 -> Architecture Decision Records
Step 16 -> Architecture Review
Step 17 -> Complete System Architecture Review / Final Readiness
```

---

# Step 1 — System Design Goals

## Goal

Translate Phase 1 requirements into technical design objectives.

The architecture must support:

- Customers
- Dealers
- Service Teams
- Fleet Managers
- Administrators
- Vehicles

Core workflows:

```text
Browse -> Configure -> Order -> Fulfill -> Own -> Service -> Monitor
```

## Design Principles

### Business-first
Architecture exists to support business requirements.

### Clear responsibilities
Each component has a defined responsibility.

### Security by design
Security is part of the architecture from the beginning.

### Failure awareness
Important dependencies have failure strategies.

### Observability
The system must be diagnosable.

### Scalability
Important components can grow when required.

### Simplicity
Do not introduce complexity that a solo developer cannot reasonably operate.

---

# Step 2 — System Context

```text
                         +----------------+
                         |    Customer    |
                         +-------+--------+
                                 |
                                 v
+------------+             +------------+             +----------------+
|   Dealer   | ----------> | Automobile | <---------- | Service Team  |
+------------+             |  Platform  |             +----------------+
                           +------+-----+
                                  |
                    +-------------+-------------+
                    |             |             |
                    v             v             v
              Fleet Manager   Administrator   Vehicles
                                                   |
                                                   v
                                             Telematics Data
```

Potential external systems:

- Identity Provider
- Payment Provider
- Notification Provider
- Vehicle / Telematics systems
- Cloud infrastructure
- Monitoring infrastructure

External integrations should be isolated behind clear interfaces.

---

# Step 3 — System Architecture

## High-Level Architecture

```text
                         USERS
                           |
                           v
                  +----------------+
                  |    Angular     |
                  |   Frontend     |
                  +-------+--------+
                          |
                          v
                  +----------------+
                  |  API Gateway   |
                  +-------+--------+
                          |
          +---------------+----------------+
          |               |                |
          v               v                v
   Customer Service  Vehicle Service   Order Service
                                          |
                                          v
                                  Inventory Service
                                          |
                    +---------------------+----------------+
                    |                     |                |
                    v                     v                v
               PostgreSQL               Kafka       External APIs
                                          |
                           +--------------+-------------+
                           |              |             |
                           v              v             v
                    Notification      Telemetry      Other
                       Service         Service      Consumers
```

## Architecture Style

Use a **modular service-oriented architecture**.

Do not create a microservice for every small function.

A separate service should have a meaningful business boundary, independent responsibility, scaling need, deployment value, or ownership boundary.

---

# Step 4 — Architecture Components

## Frontend

**Technology:** Angular + TypeScript

Responsibilities:

- User interface
- Navigation
- Forms
- Client-side validation
- Authentication state
- API communication
- Role-based UI
- Dashboards

## API Gateway

Responsibilities:

- Central API entry point
- Request routing
- Authentication integration
- Authorization enforcement
- Rate limiting
- Request filtering

## Customer Service

Responsibilities:

- Customer profile
- Customer preferences
- Customer-related business operations

## Vehicle Service

Responsibilities:

- Vehicle catalog
- Vehicle information
- Vehicle configuration
- Vehicle-related business rules

## Order Service

Responsibilities:

- Create orders
- Validate orders
- Manage order lifecycle
- Track order status
- Publish order events

## Inventory Service

Responsibilities:

- Dealer inventory
- Vehicle availability
- Inventory reservation
- Inventory updates
- Fulfillment support

## Service Management

Responsibilities:

- Service appointments
- Service scheduling
- Service status
- Maintenance records

## Telemetry / Connected Vehicle Service

Responsibilities:

- Receive telemetry
- Process vehicle health data
- Detect relevant conditions
- Generate vehicle alerts

## Notification Service

Responsibilities:

- Consume notification events
- Send notifications
- Track notification delivery status

---

# Step 5 — Service Boundaries

Each service should own its business data and rules.

```text
Customer Service
    -> Customer data

Vehicle Service
    -> Vehicle / configuration data

Order Service
    -> Order data

Inventory Service
    -> Inventory data

Service Management
    -> Appointment / service data

Telemetry Service
    -> Telemetry / health data
```

Example:

```text
Order Service
      |
      | reservation request
      v
Inventory Service
      |
      v
Inventory Database
```

The Order Service should not directly modify Inventory tables.

---

# Step 6 — Communication Architecture

## Synchronous Communication

Use REST when an immediate response is required.

```text
Angular
   |
   v
API Gateway
   |
   v
Service
   |
   v
Response
```

Examples:

```text
GET  /api/v1/vehicles
GET  /api/v1/orders/{id}
POST /api/v1/orders
POST /api/v1/appointments
```

## Asynchronous Communication

Use Kafka for event-driven operations.

```text
Order Service
      |
      v
    Kafka
      |
      +----> Notification Service
      |
      +----> Analytics Consumer
      |
      +----> Other Consumers
```

Example events:

```text
order.created
order.updated
inventory.reserved
payment.completed
service.appointment.created
vehicle.telemetry.received
notification.requested
```

Rule:

```text
REST  = Request / Response
Kafka = Event / Asynchronous Processing
```

---

# Step 7 — Data Architecture

## Primary Database

Use **PostgreSQL** for transactional business data.

Core data:

```text
Customer
Vehicle
VehicleConfiguration
Inventory
Order
OrderItem
Appointment
ServiceRecord
```

## Data Ownership

```text
Customer Service     -> Customer
Vehicle Service      -> Vehicle / Configuration
Order Service        -> Order
Inventory Service    -> Inventory
Service Management   -> Appointment / Service
Telemetry Service    -> Telemetry / Health
```

## Transaction Principle

Use database transactions for business-critical operations where appropriate.

Avoid unnecessary distributed transactions.

---

# Step 8 — API Architecture

## API Style

```text
REST
+
JSON
+
OpenAPI
```

## Versioning

```text
/api/v1/vehicles
/api/v1/orders
/api/v1/appointments
```

## Example APIs

### Vehicle

```text
GET  /api/v1/vehicles
GET  /api/v1/vehicles/{id}
POST /api/v1/configurations
GET  /api/v1/configurations/{id}
```

### Order

```text
POST /api/v1/orders
GET  /api/v1/orders/{id}
GET  /api/v1/orders
```

### Inventory

```text
GET   /api/v1/inventory
POST  /api/v1/inventory
PATCH /api/v1/inventory/{id}
```

### Service

```text
GET   /api/v1/appointments
POST  /api/v1/appointments
PATCH /api/v1/appointments/{id}
```

## API Request Flow

```text
Client
  |
  v
Gateway
  |
  v
Authentication
  |
  v
Authorization
  |
  v
Validation
  |
  v
Business Logic
  |
  v
Persistence / Event
  |
  v
Response
```

---

# Step 9 — Security Architecture

## Authentication

Use:

```text
OAuth2 / OIDC
```

with an identity provider.

## Authorization

Use:

```text
RBAC
```

Roles:

```text
CUSTOMER
DEALER
SERVICE_ADVISOR
FLEET_MANAGER
ADMIN
```

## Security Flow

```text
User
 |
 v
Login
 |
 v
Identity Provider
 |
 v
Token
 |
 v
Angular
 |
 v
API Gateway
 |
 v
Backend Service
```

## Security Requirements

- Secure authentication
- Authorization on protected APIs
- Role-based access
- HTTPS/TLS
- Secret management
- Input validation
- Secure error responses
- Audit logging where required
- Protection of sensitive information

---

# Step 10 — Deployment Architecture

## Development

```text
Developer Machine
       |
       +-- Angular
       +-- Spring Boot
       +-- PostgreSQL
       +-- Kafka
```

Docker can simplify local infrastructure.

## Production

```text
                 Internet
                    |
                    v
             Load Balancer
                    |
                    v
               API Gateway
                    |
          +---------+---------+
          |         |         |
          v         v         v
       Services  Services  Services
          |
          +-------> PostgreSQL
          |
          +-------> Kafka
          |
          +-------> External APIs
```

## Containerization

Use Docker for consistent packaging.

Kubernetes is not required for the initial MVP unless real operational requirements justify it.

---

# Step 11 — Observability

A distributed system must be observable.

## Three Pillars

```text
          Observability
          /     |               /      |             Logs    Metrics   Traces
```

## Tools

```text
Spring Boot Actuator
Micrometer
OpenTelemetry
Prometheus
Grafana
```

## Logs

Logs should answer:

- What happened?
- Which request failed?
- Which service failed?
- What was the error?
- When did it happen?

## Metrics

Monitor:

```text
CPU
Memory
API latency
Request count
Error rate
Database connections
Kafka throughput
Kafka consumer lag
```

## Tracing

Trace important flows:

```text
Angular
  |
Gateway
  |
Order Service
  |
Inventory Service
  |
PostgreSQL
```

---

# Step 12 — Disaster Recovery & Resilience

## Failure Model

```text
Failure
   |
   v
Detect
   |
   v
Isolate
   |
   +----> Retry
   |
   +----> Recover
   |
   v
Restore
   |
   v
Verify
   |
   v
Resume
```

Potential failures:

- Application service failure
- PostgreSQL failure
- Kafka failure
- Network failure
- External payment failure
- Notification provider failure
- Container/server failure

Use where appropriate:

```text
Timeout
Retry
Circuit Breaker
Health Checks
Backups
Recovery Procedures
```

## Database Protection

At minimum:

- Automated backups
- Backup verification
- Restore procedure
- Controlled data deletion

## Kafka Protection

Important events such as:

```text
order.created
order.updated
inventory.reserved
```

must not be casually lost.

## Initial Recovery Targets

```text
RTO -> 1 hour
RPO -> 15 minutes
```

These are planning targets and should be validated before production.

---

# Step 13 — Cost & Capacity Planning

## Principle

Do not design the MVP for millions of users without a real requirement.

```text
MVP
 |
 v
Measure
 |
 v
Find Bottleneck
 |
 v
Scale
```

Estimate and monitor:

- Users
- API traffic
- Database growth
- Vehicle count
- Telemetry volume
- Kafka events
- Storage
- CPU
- Memory
- Network

## Scaling

### Vertical

```text
2 CPU -> 4 CPU
4 GB RAM -> 8 GB RAM
```

### Horizontal

```text
Order Service
    |
 +--+--+--+
 |  |  |
 O1 O2 O3
```

Keep initial infrastructure small and scale according to actual measurements.

---

# Step 14 — Technology Stack & Technology Decisions

| Layer | Technology |
|---|---|
| Frontend | Angular + TypeScript |
| Backend | Java + Spring Boot |
| API | REST + OpenAPI |
| Gateway | API Gateway |
| Security | OAuth2/OIDC + JWT + RBAC |
| ORM | Spring Data JPA / Hibernate |
| Database | PostgreSQL |
| Messaging | Apache Kafka |
| Logging | SLF4J + Logback |
| Metrics | Micrometer |
| Tracing | OpenTelemetry |
| Monitoring | Prometheus + Grafana |
| Testing | JUnit + Spring Boot Test + Angular testing |
| Build | Maven + npm |
| Version Control | Git |
| Containers | Docker / Docker Compose |

## Technology Principle

For every technology:

```text
Do we need it?
      |
What problem does it solve?
      |
Can I understand and maintain it?
      |
What does it cost?
      |
Can we replace it later?
```

Do not add technology simply because it is popular.

---

# Step 15 — Architecture Decision Records

ADR = **Architecture Decision Record**.

It records:

```text
What did we decide?
        |
        v
Why?
        |
        v
What alternatives existed?
        |
        v
What are the consequences?
```

## ADR Template

```text
# ADR-XXX: Decision Title

## Status
Accepted

## Context
What problem are we solving?

## Decision
What did we choose?

## Alternatives
What else did we consider?

## Reason
Why did we choose it?

## Consequences
Benefits and drawbacks.
```

## Initial ADRs

```text
ADR-001 -> Backend Technology
ADR-002 -> Frontend Technology
ADR-003 -> Database
ADR-004 -> Messaging
ADR-005 -> API Style
ADR-006 -> Authentication & Authorization
ADR-007 -> Observability
ADR-008 -> Containerization
ADR-009 -> Architecture Style
```

Recommended location:

```text
docs/
└── phase-2-system-design/
    ├── README.md
    └── adrs/
        ├── ADR-001-backend.md
        ├── ADR-002-frontend.md
        ├── ADR-003-database.md
        └── ...
```

---

# Step 16 — Architecture Review

This is the main architecture checkpoint.

## Business Review

```text
Does the architecture support the business goals?
```

## Functional Review

```text
Can all required features be implemented?
```

## Data Review

```text
Is each data set owned by the correct component?
```

## Communication Review

```text
Are synchronous and asynchronous interactions clearly defined?
```

## Security Review

Check:

```text
Authentication
Authorization
RBAC
API protection
Secrets
TLS
Audit requirements
```

## Failure Review

Check:

```text
PostgreSQL failure
Kafka failure
Service failure
External API failure
Network failure
```

## API Review

Every major API should have:

```text
Request
Response
Validation
Authentication
Authorization
Business Logic
Error Handling
```

## Observability Review

The system should allow us to determine:

```text
What happened?
Where did it fail?
How long did it take?
How many requests failed?
Is PostgreSQL healthy?
Is Kafka healthy?
```

---

# Step 17 — Complete System Architecture Review / Final Readiness

This is the final Phase 2 gate before implementation.

## Architecture Readiness Checklist

```text
[ ] Business goals mapped to architecture
[ ] System scope defined
[ ] System context defined
[ ] Actors identified
[ ] Components defined
[ ] Component responsibilities defined
[ ] Service boundaries defined
[ ] Data ownership defined
[ ] Communication patterns defined
[ ] API approach defined
[ ] API versioning defined
[ ] Authentication defined
[ ] Authorization defined
[ ] Deployment approach defined
[ ] Containerization defined
[ ] Observability defined
[ ] Failure handling defined
[ ] Backup/recovery strategy defined
[ ] Capacity considered
[ ] Cost considered
[ ] Technology stack selected
[ ] ADRs documented
[ ] MVP architecture reviewed
[ ] Architecture is realistically buildable
```

---

# 17.1 Final Architecture

```text
                         USERS
                           |
                           v
                    +-------------+
                    |   Angular   |
                    +------+------+
                           |
                           v
                    +-------------+
                    | API Gateway |
                    +------+------+
                           |
          +----------------+----------------+
          |                |                |
          v                v                v
     Customer          Vehicle           Order
      Service           Service          Service
                                           |
                                           v
                                      Inventory
                                       Service
                                           |
                 +-------------------------+------------------+
                 |                         |                  |
                 v                         v                  v
             PostgreSQL                  Kafka          External APIs
                                           |
                         +-----------------+----------------+
                         |                 |                |
                         v                 v                v
                    Notification      Telemetry          Other
                       Service          Service         Consumers

                         ALL SERVICES
                              |
                              v
                       OBSERVABILITY
                    +---------+---------+
                    |         |         |
                    v         v         v
                  Logs     Metrics    Traces
                    |         |         |
                    +---------+---------+
                              |
                              v
                          Monitoring
                              |
                              v
                            Alerts
```

---

# 18. Main End-to-End Purchase Flow

```text
Customer
   |
   v
Angular
   |
   v
API Gateway
   |
   v
Order Service
   |
   +----> Validate Customer
   |
   +----> Validate Configuration
   |
   +----> Request Inventory Reservation
   |             |
   |             v
   |       Inventory Service
   |             |
   |             v
   |       Inventory Database
   |
   +----> Persist Order
   |
   +----> Publish order.created
                 |
                 v
                Kafka
                 |
                 v
          Notification Service
                 |
                 v
              Customer
```

---

# 19. Connected Vehicle Flow

```text
Vehicle / Telematics Unit
          |
          v
   Telemetry Ingestion
          |
          v
   Telemetry Processing
          |
          v
     Vehicle Health
          |
          +-------> Store relevant data
          |
          +-------> Generate alert
                         |
                         v
                  Notification Service
                         |
                         v
                      Customer /
                   Fleet Manager /
                   Service Advisor
```

---

# 20. Service Booking Flow

```text
Customer
   |
   v
Angular
   |
   v
API Gateway
   |
   v
Service Management
   |
   +--> Check availability
   |
   +--> Validate appointment
   |
   +--> Create appointment
   |
   +--> Publish event
             |
             v
            Kafka
             |
             v
       Notification Service
```

---

# 21. Repository-Level Organization

A practical structure:

```text
automobile-platform/
|
+-- README.md
|
+-- docs/
|   |
|   +-- phase-0-product-understanding/
|   |
|   +-- phase-1-requirements/
|   |
|   +-- phase-2-system-design/
|       |
|       +-- README.md
|       +-- architecture/
|       +-- diagrams/
|       +-- adrs/
|
+-- frontend/
|   |
|   +-- angular-application/
|
+-- services/
|   |
|   +-- api-gateway/
|   +-- customer-service/
|   +-- vehicle-service/
|   +-- order-service/
|   +-- inventory-service/
|   +-- service-management/
|   +-- notification-service/
|   +-- telemetry-service/
|
+-- infrastructure/
|   |
|   +-- docker/
|   +-- database/
|   +-- kafka/
|   +-- deployment/
|
+-- tests/
|
+-- .gitignore
```

> Do not create every service immediately. This is the target architectural organization. During implementation, build incrementally and keep boundaries clear.

---

# 22. Solo Developer Architecture Rule

This project is being built end-to-end by one Software Engineer.

Therefore:

```text
GOOD ARCHITECTURE
        =
Enough structure to control complexity
        +
Not so much infrastructure that infrastructure becomes the project
```

Avoid unnecessary complexity:

```text
X Kubernetes from day one
X Service mesh
X Multiple databases without a requirement
X Multiple message brokers
X Dozens of microservices
X Complex distributed transactions
X Infrastructure that cannot be operated alone
```

Architecture should evolve based on real requirements.

---

# 23. What Phase 2 Produces

At the end of Phase 2 we have:

```text
1. System Architecture
2. Component Architecture
3. Service Boundaries
4. Communication Design
5. Data Architecture
6. API Architecture
7. Security Architecture
8. Deployment Architecture
9. Observability Design
10. Resilience Strategy
11. Capacity Strategy
12. Technology Stack
13. ADRs
14. Architecture Review
15. Implementation Readiness
```

---

# 24. Phase 2 Completion Criteria

Phase 2 is complete when:

```text
Business Requirements
        |
        v
Technical Architecture
        |
        v
Components
        |
        v
APIs + Data + Communication
        |
        v
Security + Deployment
        |
        v
Observability + Resilience
        |
        v
Technology Decisions
        |
        v
Architecture Review
        |
        v
READY FOR IMPLEMENTATION
```

---

# 25. Phase 2 Status

| Area | Status |
|---|---|
| System Design Goals | Complete |
| System Context | Complete |
| System Architecture | Complete |
| Architecture Components | Complete |
| Service Boundaries | Complete |
| Communication Architecture | Complete |
| Data Architecture | Complete |
| API Architecture | Complete |
| Security Architecture | Complete |
| Deployment Architecture | Complete |
| Observability | Complete |
| Disaster Recovery & Resilience | Complete |
| Cost & Capacity Planning | Complete |
| Technology Stack | Complete |
| Architecture Decision Records | Complete |
| Architecture Review | Complete |
| Final Architecture Readiness | Complete |

---

# Phase 2 → COMPLETE

```text
PHASE 0
Product Understanding
        |
        v
PHASE 1
Requirements Engineering
        |
        v
PHASE 2
System Design
        |
        +--> Architecture
        +--> Services
        +--> APIs
        +--> Database
        +--> Kafka
        +--> Security
        +--> Deployment
        +--> Observability
        +--> Resilience
        +--> Technology Decisions
        |
        v
ARCHITECTURE REVIEW
        |
        v
READY FOR PHASE 3
```

## Final Outcome

We now have a technical blueprint for building the Automobile Digital Platform.

The next phase turns the architecture into working software:

```text
PHASE 3
DEVELOPMENT / IMPLEMENTATION
        |
        v
Project Setup
        |
        v
Repository Setup
        |
        v
Development Environment
        |
        v
Frontend
        |
        v
Backend Services
        |
        v
Database
        |
        v
APIs
        |
        v
Kafka
        |
        v
Security
        |
        v
Testing
        |
        v
Deployment
```

**Phase 2 Status: COMPLETE**
