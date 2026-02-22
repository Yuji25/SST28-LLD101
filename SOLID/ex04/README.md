# ex04 — OCP: Hostel Fee Calculator

## 1. Context
Hostel fees depend on room type and add-ons. New room types and add-ons will be introduced.

## 2. Current behavior
- Uses `BookingRequest` with roomType and add-ons
- Calculates monthly fee + one-time deposit
- Prints a receipt and saves booking

## 3. What’s wrong (at least 5 issues)
1. `HostelFeeCalculator.calculate` is a switch-case on room types.
2. Add-ons are handled with repeated branching logic.
3. Adding a room type requires editing the big switch (OCP violation).
4. Money arithmetic is scattered and formatted inconsistently.
5. Calculator also prints and persists booking data.

## 4. Your task
Checkpoint A: 
- Run and capture output.

Checkpoint B: 
- Encapsulate room pricing and add-on pricing behind abstractions.

Checkpoint C: 
- Remove switch-case from main calculation logic.

Checkpoint D: 
- Preserve output.

## 5. Humne kya kia
| Principle | Evidence |
|-----------|----------|
| S (SRP) | Room pricing → RoomPricing, Add-on pricing → AddOnPricing, Calculator handles orchestration only, Repository handles persistence only. Each has ONE responsibility. |
| O (OCP) | Calculator is closed for modification and open for extension via RoomPricing and AddOnPricing interfaces (add DeluxeACRoomPricing, ParkingPricing without editing the calculator). |
| L (LSP) | Any RoomPricing, AddOnPricing, or BookingRepository implementation can substitute the existing one without breaking the system. |

## 6. How to run
```bash
cd SOLID/ex04/src
javac *.java
java Demo04
```

## 7. Current output
```text
=== Hostel Fee Calculator ===
Room: DOUBLE | AddOns: [LAUNDRY, MESS]
Monthly: 16000.00
Deposit: 5000.00
TOTAL DUE NOW: 21000.00
Saved booking: H-7781
```
