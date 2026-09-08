# 09 — MVP Screens

## 1. Customer Screens

### Public
1. Home
2. Vehicle Listing
3. Vehicle Details
4. Dealer Listing/Selection
5. Login
6. Register

### Authenticated
7. Customer Dashboard
8. My Enquiries
9. Enquiry Details
10. Profile

### Conditional
11. Test Drive Request

## 2. Vehicle Listing

Must support:
- Search
- Core filters
- Sorting
- Pagination where required
- Vehicle cards
- Clear price/variant information
- Empty state
- Error state
- Loading state

## 3. Vehicle Details

Must display:
- Vehicle/model
- Images where available
- Key specifications
- Features
- Starting/variant price information
- Variants
- Dealer action
- Enquiry action

## 4. Dealer Selection

Must show:
- Dealer name
- Location
- Basic contact information where allowed
- Relevant availability
- Select/enquire action

## 5. Enquiry

Must show selected context:

```text
Vehicle
Variant
Dealer
```

and collect required customer information/message.

## 6. Customer Enquiry Dashboard

Example:

```text
My Enquiries

Creta SX(O) — ABC Hyundai
Status: CONTACTED

Seltos HTX — XYZ Kia
Status: NEW
```

## 7. Dealer Screens

1. Dealer Login
2. Dashboard
3. Lead List
4. Lead Details
5. Inventory
6. Dealer Profile
7. Test Drive Requests if enabled

## 8. Admin Screens

1. Admin Login
2. Dashboard
3. Customers
4. Dealers
5. Brands
6. Models
7. Variants
8. Vehicles
9. Leads

## 9. UI States

Every data-driven screen should account for:
- Loading
- Success
- Empty
- Validation error
- Unauthorized
- Not found
- Server error
- Retry
