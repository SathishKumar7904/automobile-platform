# 06 — MVP Features

## 1. Feature Groups

| Group | Priority | MVP |
|---|---|---|
| Authentication | P0 | Yes |
| Vehicle Catalog | P0 | Yes |
| Vehicle Search | P0 | Yes |
| Vehicle Details | P0 | Yes |
| Dealer Discovery | P0 | Yes |
| Enquiry | P0 | Yes |
| Lead Creation | P0 | Yes |
| Dealer Lead Management | P0 | Yes |
| Customer Lead Tracking | P0 | Yes |
| Basic Admin | P0 | Yes |
| Basic Inventory | P1 | Yes/limited |
| Notifications | P1 | Basic |
| Test Drive | P1 | Conditional |
| Quotation | P2 | No |
| Booking Payment | P2 | No |
| Finance | P2 | No |
| Insurance | P2 | No |
| Delivery | P2 | No |
| After-sales | P2 | No |

## 2. Authentication

Requirements:
- Secure registration/login
- Password handling using approved security practices
- Role-based authorization
- Session/token management according to Phase 2 architecture
- Protected routes
- Logout

## 3. Catalog

Requirements:
- Hierarchical brand/model/variant data
- Searchable attributes
- Active/inactive records
- Consistent relationships

## 4. Enquiry

Requirements:
- Customer selects vehicle/variant/dealer
- Server validates all references
- Enquiry is persisted
- Lead is created/linked
- Dealer assignment is persisted
- Customer receives confirmation

## 5. Lead Management

Requirements:
- Dealer sees authorized leads
- Dealer can update valid statuses
- Dealer can add notes
- Status changes are persisted
- History is retained

## 6. Customer Tracking

Requirements:
- Customer sees only their own enquiries
- Current status is visible
- Timeline/history is visible where implemented

## 7. Notifications

MVP can use in-app notifications and/or email depending on available infrastructure.

Minimum event:
- New lead notification to dealer
- Enquiry confirmation to customer

## 8. Error Handling

Every major operation must handle:
- Validation errors
- Authentication failures
- Authorization failures
- Missing records
- Duplicate/conflicting requests
- Server failures
