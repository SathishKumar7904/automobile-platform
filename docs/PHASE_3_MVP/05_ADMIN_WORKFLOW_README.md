# 05 — Admin Workflow

## 1. Admin Goal

Maintain trustworthy platform data and monitor platform activity.

## 2. Admin Areas

```text
Admin Login
  ↓
Dashboard
  ├── Users
  ├── Dealers
  ├── Brands
  ├── Models
  ├── Variants
  ├── Vehicles
  └── Leads
```

## 3. Dealer Management

Admin can:
- View dealers
- Create/manage dealer records
- Activate/deactivate dealers
- Review basic dealer information
- Control platform access where required

## 4. Catalog Management

Admin manages:
- Brands
- Models
- Variants
- Specifications
- Features
- Pricing information
- Catalog availability/status

## 5. Lead Monitoring

Admin can:
- Search leads
- Filter leads
- View lead details
- Monitor status
- Identify operational problems

Admin should not arbitrarily modify business history without an auditable reason.

## 6. Dashboard

Useful MVP metrics:
- Total customers
- Active dealers
- Catalog item counts
- Total leads
- New leads
- Converted leads
- Closed leads

## 7. Authorization

Admin operations must be protected by server-side authorization. Hiding an admin button in the frontend is not sufficient security.
