# 01 — MVP Scope

## 1. Scope Statement

The first MVP is a **Vehicle Discovery + Dealer Discovery + Lead Generation + Dealer Lead Management** platform.

It is not a complete online vehicle purchasing system.

## 2. In Scope

### Authentication
- Customer registration/login/logout
- Dealer login
- Admin login
- Role-based authorization
- Basic profile information

### Vehicle Catalog
- Brands
- Models
- Variants
- Vehicle specifications
- Features
- Pricing data
- Images/media references
- Basic availability/inventory information

### Vehicle Discovery
- Vehicle listing
- Search
- Core filters
- Sorting
- Vehicle details
- Variant selection

### Dealer Discovery
- Dealer listing
- Dealer details
- Vehicle-to-dealer discovery
- Basic dealer availability information

### Enquiry and Lead
- Enquiry form
- Enquiry persistence
- Lead creation
- Dealer assignment
- Lead status
- Lead notes
- Lead timeline/history

### Customer Tracking
- My enquiries
- Enquiry details
- Current status
- Basic timeline

### Dealer Operations
- Dealer dashboard
- Lead list
- Lead details
- Status update
- Internal notes
- Basic inventory management

### Admin
- Catalog management
- Dealer management
- User visibility
- Lead monitoring
- Basic platform dashboard

## 3. Conditional MVP Feature

### Test Drive

A basic test-drive request may be included if it is already classified as a core Phase 1 requirement.

The first implementation should use simple request/status handling rather than a complex calendar engine.

## 4. Out of Scope

See `15_MVP_OUT_OF_SCOPE_README.md`.

## 5. Scope Lock Rule

Any new feature must answer:

1. Does it support vehicle discovery?
2. Does it support dealer discovery?
3. Does it support lead generation?
4. Does it support dealer lead management?
5. Is it required for the MVP acceptance flow?

If the answer is no, defer it unless a previously approved Phase 1 requirement makes it mandatory.

## 6. MVP Boundary

```text
                MVP
┌─────────────────────────────────────────────┐
│ Vehicle Discovery                           │
│ Dealer Discovery                            │
│ Enquiry                                     │
│ Lead Creation                               │
│ Dealer Lead Management                      │
│ Customer Lead Tracking                      │
│ Basic Admin                                  │
└─────────────────────────────────────────────┘

             FUTURE RELEASES
┌─────────────────────────────────────────────┐
│ Advanced Quotation                          │
│ Booking & Payments                          │
│ Finance                                     │
│ Insurance                                   │
│ Registration                                │
│ Delivery                                    │
│ After-sales                                 │
│ Advanced integrations                       │
└─────────────────────────────────────────────┘
```
