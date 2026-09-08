# 12 — MVP Development Strategy

## 1. Principle

Develop vertically, not by completing an entire frontend first and backend later.

Each vertical slice should travel through:

```text
Requirement
 ↓
UI
 ↓
API
 ↓
Business Logic
 ↓
Database
 ↓
Validation
 ↓
Error Handling
 ↓
Test
```

## 2. Recommended Build Order

### Slice 1 — Foundation
- Repository setup
- Environment configuration
- Database connection
- Backend structure
- Frontend structure
- Basic health check
- Logging
- Error handling

### Slice 2 — Authentication
- Registration
- Login
- Logout
- Roles
- Protected routes
- Server authorization

### Slice 3 — Catalog
- Brand
- Model
- Variant
- Vehicle
- Seed data
- Vehicle listing
- Vehicle details

### Slice 4 — Dealer Discovery
- Dealer records
- Dealer listing
- Vehicle-to-dealer relationship
- Dealer selection

### Slice 5 — Enquiry
- Enquiry UI
- API
- Validation
- Persistence
- Lead creation
- Dealer assignment

### Slice 6 — Dealer Lead Management
- Dealer dashboard
- Lead list
- Lead details
- Status update
- Notes
- History

### Slice 7 — Customer Tracking
- My enquiries
- Enquiry details
- Timeline
- Updated status

### Slice 8 — Admin
- Admin dashboard
- Catalog CRUD
- Dealer management
- Lead monitoring

### Slice 9 — Hardening
- Validation
- Authorization testing
- Error handling
- Security checks
- Integration tests
- E2E happy path

## 3. Development Rule

Do not move to the next major slice until the previous slice works end-to-end.

## 4. Seed Data

Create realistic development data:
- Multiple brands
- Multiple models
- Multiple variants
- Multiple dealers
- Dealer inventory
- Test users
- Test leads

The seed data must make the application usable immediately after setup.

## 5. Five-Day Constraint

When time is limited, prioritize:

```text
Day 1: Foundation + Auth + Catalog
Day 2: Catalog UI + Dealer Discovery
Day 3: Enquiry + Lead Creation
Day 4: Dealer Lead Management + Customer Tracking
Day 5: Admin basics + Testing + Bug fixing + Deployment
```

Optional/P1 features must not block the P0 path.
