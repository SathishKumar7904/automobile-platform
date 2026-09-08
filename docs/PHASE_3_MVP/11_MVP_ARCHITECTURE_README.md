# 11 — MVP Architecture

## 1. Architecture Goal

Build a maintainable full-stack MVP without unnecessary distributed-system complexity.

## 2. Recommended Logical Architecture

```text
┌───────────────────────────────┐
│ Customer Web Application      │
└───────────────┬───────────────┘
                │
┌───────────────▼───────────────┐
│ Backend API                   │
│                               │
│ Authentication               │
│ Catalog                      │
│ Dealer                       │
│ Enquiry                      │
│ Lead                         │
│ Notification                 │
│ Administration               │
└───────────────┬───────────────┘
                │
┌───────────────▼───────────────┐
│ Relational Database           │
└───────────────────────────────┘
        ▲                 ▲
        │                 │
┌───────┴───────┐ ┌───────┴───────┐
│ Dealer Portal │ │ Admin Portal  │
└───────────────┘ └───────────────┘
```

## 3. Architecture Principles

- Prefer a modular monolith for MVP unless Phase 2 explicitly requires another architecture.
- Keep domain boundaries clear inside the backend.
- Use a relational database for transactional data.
- Centralize authorization.
- Validate at API boundaries.
- Keep business rules out of UI components.
- Use transactions for operations that must remain atomic.
- Record important status changes and audit events.
- Keep external integrations behind clear interfaces.

## 4. Domain Modules

```text
Identity & Access
Catalog
Dealer
Inventory
Enquiry
Lead
Notification
Administration
```

## 5. External Integrations

For MVP, integrations should be minimized.

Possible future integrations:
- Manufacturer inventory systems
- Dealer DMS/ERP
- Finance providers
- Insurance providers
- Payment gateway
- Messaging providers

Use adapters/interfaces so later integrations do not contaminate core domain logic.
