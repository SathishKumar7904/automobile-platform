# Automobile Digital Platform

## Overview

The Automobile Digital Platform is a full-stack digital platform for the automotive domain.

The platform supports the digital vehicle journey from vehicle discovery and configuration through ordering, inventory, fulfillment, ownership, service management, and connected-vehicle capabilities.

This repository contains the application implementation and supporting project infrastructure.

## Repository Structure

automobile-platform/
├── docs/              # Project and implementation documentation
├── frontend/          # Angular frontend application
├── services/          # Backend services
├── infrastructure/    # Local and deployment infrastructure
└── tests/             # Cross-component and end-to-end tests

```
## Technology Stack

The implementation follows the technology decisions established during system design.

Frontend: Angular + TypeScript
Backend: Java + Spring Boot
Build Tools: Maven + npm
Database: PostgreSQL
Messaging: Apache Kafka
API Style: REST + JSON
API Documentation: OpenAPI
Containerization: Docker + Docker Compose
Version Control: Git

## Documentation

Project documentation is maintained separately from the application source code.

The documentation covers:

Product discovery
Requirements
System architecture
Database design
API design
Application implementation
Development

Development is performed incrementally, starting with the core MVP journey and expanding the platform capability by capability.

Each implementation stage should be tested and verified before moving to the next stage.

## Project Status

Phase 0 — Product Discovery: Completed

Phase 1 — Requirement Engineering: Completed

Phase 2 — System Design: Completed

Phase 3 — Application Implementation: In Progress