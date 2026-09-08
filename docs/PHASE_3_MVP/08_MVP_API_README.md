# 08 — MVP API

## 1. API Principle

The frontend communicates with the backend through the approved Phase 2 API architecture. These examples describe responsibilities; exact paths, naming, authentication mechanism, and response envelopes must follow the Phase 2 baseline.

## 2. Authentication

```text
POST   /auth/register
POST   /auth/login
POST   /auth/logout
GET    /me
```

## 3. Catalog

```text
GET    /brands
GET    /models
GET    /variants
GET    /vehicles
GET    /vehicles/{id}
```

Possible query capabilities:
```text
brand
model
fuel
transmission
bodyType
minPrice
maxPrice
page
limit
sort
```

## 4. Dealers

```text
GET    /dealers
GET    /dealers/{id}
GET    /vehicles/{id}/dealers
```

## 5. Customer Enquiries

```text
POST   /enquiries
GET    /my/enquiries
GET    /my/enquiries/{id}
```

## 6. Dealer Leads

```text
GET    /dealer/leads
GET    /dealer/leads/{id}
PATCH  /dealer/leads/{id}/status
POST   /dealer/leads/{id}/notes
```

## 7. Admin

```text
GET    /admin/users
GET    /admin/dealers
POST   /admin/dealers
PATCH  /admin/dealers/{id}
GET    /admin/brands
POST   /admin/brands
GET    /admin/models
POST   /admin/models
GET    /admin/variants
POST   /admin/variants
GET    /admin/vehicles
POST   /admin/vehicles
PATCH  /admin/vehicles/{id}
GET    /admin/leads
```

## 8. API Request Lifecycle

```text
Request
 ↓
Authentication
 ↓
Authorization
 ↓
Input Validation
 ↓
Business Rule Validation
 ↓
Service/Use Case
 ↓
Database Transaction
 ↓
Event/Notification where required
 ↓
Response
```

## 9. Security

Never rely on the frontend to decide whether a user can access a resource.

Example:
A dealer requesting `/dealer/leads/123` must be checked server-side to ensure lead 123 belongs to that dealer.
