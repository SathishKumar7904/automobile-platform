# 10 — MVP Lead Lifecycle

## 1. Purpose

Lead status is a controlled business state machine. It must not be treated as arbitrary text.

## 2. Recommended MVP States

```text
NEW
 ↓
CONTACTED
 ↓
INTERESTED
 ↓
FOLLOW_UP
 ├── INTERESTED
 ├── CONVERTED
 └── CLOSED

CONTACTED
 ├── INTERESTED
 ├── FOLLOW_UP
 └── CLOSED

INTERESTED
 ├── FOLLOW_UP
 ├── CONVERTED
 └── CLOSED
```

## 3. State Definitions

### NEW
Lead has been created and is awaiting dealer action.

### CONTACTED
Dealer has contacted the customer.

### INTERESTED
Customer has indicated meaningful purchase interest.

### FOLLOW_UP
Further contact/action is required.

### CONVERTED
Lead has resulted in the intended sales conversion outcome.

### CLOSED
Lead is no longer active without conversion.

## 4. Transition Rules

Only valid transitions should be accepted.

Example:

```text
NEW → CONTACTED       allowed
NEW → CONVERTED       not allowed unless explicitly supported by business rules
CONTACTED → INTERESTED allowed
FOLLOW_UP → CONVERTED allowed
CLOSED → CONTACTED    not allowed by default
```

## 5. History

Every status change should record:
- previous status
- new status
- actor
- timestamp
- optional reason/note

## 6. Customer Visibility

Customer-facing status labels may be simplified.

For example:

```text
Dealer is reviewing
Dealer contacted you
Follow-up in progress
Completed
Closed
```

Internal dealer statuses can remain more detailed.
