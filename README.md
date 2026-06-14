# Real Estate Reservation System - Functional Design

## Objective

Allow buyers to express interest in a property by placing a reservation offer.

A reservation:

* Includes the buyer's proposed price.
* Requires a reservation fee payment.
* Gives the buyer a priority level.
* Notifies the agent.
* Can optionally lock the property from further reservations.

The final transaction happens outside the platform between buyer and agent.

---

## Actors

### Agent

* Creates and manages property listings.
* Defines reservation settings.
* Receives reservation requests.
* Accepts or rejects reservations.
* Views reservation queue.
* Uses valuation APIs to estimate market price.

### Buyer

* Browses properties.
* Places reservation offers.
* Pays reservation fee.
* Tracks reservation status.
* Receives agent contact information if eligible.

---

# Reservation Types

## 1. Immediate Reservation

Highest priority.

### Behavior

* Buyer pays highest reservation fee.
* Property becomes locked immediately.
* No other buyer can create reservations.
* Agent contact details are revealed instantly:

  * Phone
  * Email
  * WhatsApp
* Agent receives urgent notification.

### Use Case

Buyer wants exclusive first access.

---

## 2. Normal Reservation

Medium priority.

### Behavior

* Buyer submits offer and reservation fee.
* Property remains open.
* Agent defines maximum reservation count.

Example:

Maximum Normal Reservations = 5

Only first 5 active reservations allowed.

### Notifications

* Email notification
* In-app notification
* SMS (optional)

### Use Case

Buyer wants priority but not exclusivity.

---

## 3. Slow Reservation

Lowest priority.

### Behavior

* Lowest reservation fee.
* Added to waitlist queue.
* Does not consume reservation slots.
* Agent receives passive notification.

### Use Case

Buyer is interested but not urgent.

---

# Reservation Status Lifecycle

PENDING_PAYMENT

↓

ACTIVE

↓

UNDER_REVIEW

↓

ACCEPTED
or
REJECTED
or
EXPIRED
or
CANCELLED

---

# Reservation Entity

Reservation

* id

* propertyId

* buyerId

* agentId

* reservationType

  * IMMEDIATE
  * NORMAL
  * SLOW

* offeredPrice

* reservationFee

* status

* priorityScore

* createdAt

* expiresAt

---

# Agent Configuration

Per Property

* immediateEnabled

* normalEnabled

* slowEnabled

* maximumNormalReservations

* immediateReservationFee

* normalReservationFee

* slowReservationFee

* reservationExpiryHours

---

# Contact Reveal Rules

IMMEDIATE

Reveal:

* phone
* email
* whatsapp

NORMAL

Reveal:

* masked phone
* in-app messaging

SLOW

No contact information

---

# Price Intelligence

Agent may request valuation.

Input

* location
* propertyType
* areaSqFt
* bedrooms
* age

Output

* estimatedMarketValue
* confidenceScore
* comparableProperties

This does not modify listing price automatically.

Agent decides final price.

---

# Reservation Queue

Agent Dashboard

Property A

Immediate:
1 buyer

Normal:
5 buyers

Slow:
12 buyers

Ranked by:

1. Reservation Type
2. Offered Price
3. Reservation Date

Priority Order

Immediate > Normal > Slow

Within same type:

Higher Offer > Earlier Reservation

---

# Revenue Opportunities

Reservation Fee

Example:

Immediate = ₹999

Normal = ₹299

Slow = ₹99

Premium Contact Unlock

Featured Reservation

Priority Boost

Valuation API Credits

Agent Subscription Plans
