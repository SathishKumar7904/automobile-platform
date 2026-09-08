# 15 — MVP Out of Scope

## 1. Purpose

This document protects the MVP from uncontrolled scope expansion.

## 2. Explicitly Deferred

### Purchasing
- Full online car purchase
- Full checkout
- Full payment orchestration
- Refund engine

### Finance
- Loan underwriting
- Credit scoring
- Bank integrations
- Loan disbursement

### Insurance
- Insurance marketplace
- Policy underwriting
- Claims processing

### Registration
- Government registration integration
- RTO workflow automation

### Fulfillment
- Physical vehicle logistics
- Vehicle transport tracking
- PDI management
- Delivery management

### After-Sales
- Service booking
- Service history
- Warranty management
- Spare-parts marketplace

### Enterprise Integrations
- Manufacturer ERP integration
- Dealer DMS/ERP integration
- Real-time manufacturer inventory synchronization

### Advanced Communication
- Real-time customer/dealer chat
- Voice calling infrastructure
- Omnichannel messaging platform

### Advanced Intelligence
- AI vehicle recommendations
- AI sales assistant
- Predictive lead scoring
- Dynamic pricing engine

### Advanced Analytics
- Enterprise BI
- Complex sales forecasting
- Advanced cohort analytics

### Platform Expansion
- Native mobile applications
- Multi-country localization
- Multi-currency architecture unless already required by the approved baseline
- Microservices migration solely for scale assumptions

## 3. Handling New Requests

Every new feature must be recorded as:
- MVP change request, or
- Future release item.

It must not silently enter the implementation backlog.

## 4. Reconsideration Rule

A deferred capability may be promoted into MVP only if:
1. it is required by an approved Phase 1 requirement;
2. it is required for the MVP acceptance flow;
3. it is required to satisfy a critical security/legal/business constraint; or
4. the project owner explicitly changes the MVP scope.

## 5. Principle

> A smaller complete business workflow is more valuable than a large collection of unfinished modules.
