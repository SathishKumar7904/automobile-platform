# 14 — MVP Acceptance Criteria

## 1. Global Definition of Done

A feature is complete only when:
- UI exists where required
- API exists where required
- Database persistence works
- Validation exists
- Authorization exists
- Error states are handled
- Happy path is tested
- Relevant status/history is persisted
- No manual database changes are needed during normal operation

## 2. Customer Acceptance Test

### Registration
- Customer can register.
- Invalid input is rejected.
- Duplicate identity is handled safely.

### Login
- Customer can log in.
- Invalid credentials are rejected.
- Protected resources require authentication.

### Discovery
- Customer can search vehicles.
- Customer can filter vehicles.
- Customer can open vehicle details.
- Customer can view variants.

### Dealer Selection
- Customer can view relevant dealers.
- Customer can select a dealer.

### Enquiry
- Customer can submit an enquiry.
- Required fields are validated.
- Selected vehicle/variant/dealer are validated server-side.
- Enquiry is persisted.
- Lead is created/assigned.
- Customer receives confirmation.

### Tracking
- Customer can see their enquiry.
- Customer can see current status.
- Customer cannot see another customer's enquiry.

## 3. Dealer Acceptance Test

- Dealer can log in.
- Dealer sees only authorized leads.
- New customer enquiry appears as a lead.
- Dealer can open lead details.
- Dealer can update valid status.
- Invalid status transitions are rejected.
- Dealer can add notes.
- Status history is retained.

## 4. Admin Acceptance Test

- Admin can log in.
- Admin can manage foundational catalog data.
- Admin can manage dealer records.
- Admin can monitor leads.
- Admin-only operations are protected server-side.

## 5. End-to-End Acceptance Test

```text
1. Create customer
2. Log in
3. Search vehicle
4. Open vehicle
5. Select variant
6. Select dealer
7. Submit enquiry
8. Verify enquiry exists
9. Login as dealer
10. Verify lead exists
11. Open lead
12. Change status
13. Add note
14. Login as customer
15. Verify updated status
```

## 6. MVP Quality Bar

The system must be:
- Functionally usable
- Persistent
- Secure at a basic production-minded level
- Role-aware
- Testable
- Deployable
- Understandable by another developer
