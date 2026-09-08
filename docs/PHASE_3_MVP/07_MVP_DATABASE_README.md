# 07 — MVP Database

## 1. Purpose

This document describes the conceptual data model for the MVP. The final physical schema must follow the approved Phase 2 system design.

## 2. Core Entities

### User
Represents an authenticated platform identity.

Typical attributes:
- id
- role
- name
- email/phone as applicable
- password/auth reference
- status
- created_at
- updated_at

### Customer Profile
Customer-specific information.

### Dealer
Represents a dealership/business entity.

### Dealer User
Represents users operating on behalf of a dealer.

### Brand
Vehicle manufacturer/brand.

### Model
A vehicle model belonging to a brand.

### Variant
A purchasable/catalog variant of a model.

### Vehicle/Inventory Item
Represents dealer-level availability or a configured inventory record as defined by Phase 2.

### Enquiry
The customer's submitted request.

### Lead
The dealer-facing sales opportunity created from an enquiry.

### Lead Status History
Records status transitions.

### Lead Note
Dealer/internal notes associated with a lead.

### Test Drive
Optional MVP entity if test-drive functionality is included.

### Notification
Records user-facing system notifications if notification persistence is implemented.

### Audit Event
Records security/business events where required by the architecture.

## 3. Conceptual Relationships

```text
Brand
  1 ──── N Model
              1 ──── N Variant

Dealer
  1 ──── N Inventory

Customer
  1 ──── N Enquiry
              1 ──── 1/N Lead

Dealer
  1 ──── N Lead

Lead
  1 ──── N StatusHistory
  1 ──── N Notes
```

## 4. Critical Integrity Rules

- A variant must belong to a valid model.
- A model must belong to a valid brand.
- A lead must reference a valid customer.
- A lead must reference a valid dealer where assignment is required.
- An enquiry must reference the selected vehicle/variant context.
- A customer must only access their own customer data.
- A dealer must only access leads belonging to that dealer.
- Admin access must be server-authorized.
- Status transitions must obey the lead state machine.

## 5. Avoid

Do not:
- store the complete vehicle object as unstructured JSON when structured querying is required;
- trust customer-supplied dealer IDs without validation;
- expose database IDs unnecessarily when secure public identifiers are required by the design;
- allow frontend-only authorization;
- silently overwrite business history.
