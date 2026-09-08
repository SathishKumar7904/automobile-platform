# 02 — MVP Business Flow

## 1. Real-World Business Scenario

A customer wants to buy a vehicle.

The platform helps the customer discover a suitable vehicle and connect with a dealer. The dealer then handles the sales conversation.

## 2. End-to-End Flow

```text
Visitor
  ↓
Browse/Search Vehicles
  ↓
Vehicle Details
  ↓
Select Variant
  ↓
Select Dealer
  ↓
Submit Enquiry
  ↓
System Validates Request
  ↓
Create Enquiry
  ↓
Create Lead
  ↓
Assign Dealer
  ↓
Notify Dealer
  ↓
Dealer Opens Lead
  ↓
Dealer Contacts Customer
  ↓
Dealer Updates Lead
  ↓
Customer Views Updated Status
```

## 3. Business Responsibilities

### Platform
- Maintain structured vehicle catalog
- Maintain dealer records
- Validate customer actions
- Create enquiries/leads
- Assign leads
- Maintain status history
- Provide role-based access
- Notify relevant users
- Maintain audit information

### Customer
- Search and compare available vehicles
- Select vehicle/variant
- Select dealer
- Provide enquiry details
- Follow enquiry progress

### Dealer
- Receive lead
- Review customer and vehicle context
- Contact customer
- Update status
- Add internal notes
- Perform sales activities outside/alongside the platform

### Admin
- Maintain platform data
- Approve/manage dealers
- Maintain catalog
- Monitor leads
- Handle operational administration

## 4. Example

```text
Customer: Rahul
Vehicle: Creta
Variant: SX(O)
Dealer: ABC Hyundai

Customer submits:
"I want the on-road price."

System:
Enquiry #50001
Lead #10001
Status = NEW
Dealer = ABC Hyundai

Dealer:
Opens lead
Contacts Rahul
Status = CONTACTED
Adds note:
"Customer interested. Follow up tomorrow."

Customer:
Sees CONTACTED in enquiry timeline.
```

## 5. Important Boundary

The platform coordinates the digital workflow. It does not imply that every physical sales operation happens inside the application.

For example, a dealer may call the customer using a phone and conduct a physical test drive at the dealership. The platform records the resulting business state.
