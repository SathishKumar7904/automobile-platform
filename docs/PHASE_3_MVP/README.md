# Phase 3 — MVP Implementation Documentation

## 1. Purpose

This directory defines the first functional MVP for the Automobile Digital Platform.

The MVP is intentionally smaller than the complete platform. It proves the core business loop:

> Customer discovers a vehicle → selects a variant → selects a dealer → submits an enquiry → dealer receives and manages the lead → customer tracks the lead status.

This document set is an implementation baseline for Phase 3 and must remain aligned with the previously established Phase 0 product understanding, Phase 1 requirements engineering, and Phase 2 system design.

## 2. MVP Objective

The first MVP must demonstrate a real, persistent, end-to-end business workflow rather than a static UI demonstration.

### Core outcome

- Customers can discover vehicles.
- Customers can view vehicle and variant information.
- Customers can discover/select dealers.
- Customers can submit enquiries.
- The system creates and assigns leads.
- Dealers can receive and manage leads.
- Dealers can update lead status and add internal notes.
- Customers can see the current enquiry/lead status.
- Admin can manage the foundational catalog, dealers, users, and leads.

## 3. Primary Actors

| Actor | Responsibility |
|---|---|
| Customer | Discover vehicles, choose a vehicle/variant/dealer, submit enquiries, track enquiries |
| Dealer | Receive leads, contact customers, update lead status, record notes, manage basic inventory |
| Admin | Manage platform users, dealers, catalog, and monitor leads |

## 4. MVP Core Loop

```text
Customer
  ↓
Vehicle Discovery
  ↓
Vehicle Details
  ↓
Variant Selection
  ↓
Dealer Selection
  ↓
Enquiry
  ↓
Lead Creation
  ↓
Dealer Dashboard
  ↓
Lead Management
  ↓
Status Update
  ↓
Customer Tracking
```

## 5. Documentation Map

1. [MVP Scope](01_MVP_SCOPE_README.md)
2. [Business Flow](02_MVP_BUSINESS_FLOW_README.md)
3. [Customer Journey](03_CUSTOMER_JOURNEY_README.md)
4. [Dealer Workflow](04_DEALER_WORKFLOW_README.md)
5. [Admin Workflow](05_ADMIN_WORKFLOW_README.md)
6. [MVP Features](06_MVP_FEATURES_README.md)
7. [MVP Database](07_MVP_DATABASE_README.md)
8. [MVP API](08_MVP_API_README.md)
9. [MVP Screens](09_MVP_SCREENS_README.md)
10. [Lead Lifecycle](10_MVP_LEAD_LIFECYCLE_README.md)
11. [Architecture](11_MVP_ARCHITECTURE_README.md)
12. [Development Strategy](12_MVP_DEVELOPMENT_STRATEGY_README.md)
13. [Priority](13_MVP_PRIORITY_README.md)
14. [Acceptance Criteria](14_MVP_ACCEPTANCE_CRITERIA_README.md)
15. [Out of Scope](15_MVP_OUT_OF_SCOPE_README.md)

## 6. Definition of MVP Success

The MVP is successful when a complete test can be performed without manually editing the database:

```text
Customer registers/logs in
→ searches for a vehicle
→ opens vehicle details
→ selects variant
→ selects dealer
→ submits enquiry

Dealer logs in
→ sees the new lead
→ opens lead details
→ updates status
→ adds note

Customer returns
→ opens enquiry
→ sees updated status
```

## 7. Scope Principle

The MVP digitizes and coordinates automobile discovery and dealer lead management. It does not attempt to digitize every activity performed by a manufacturer, dealer, finance provider, insurer, registration authority, or delivery operation.

Later capabilities such as quotation, booking payment, finance, insurance, registration, delivery, and after-sales can be added as subsequent releases.
