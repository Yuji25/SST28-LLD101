# ex02 — SRP: Campus Cafeteria Billing

## 1. Context
A cafeteria billing console generates invoices for student orders. It currently handles menu definition, tax logic, discount logic, invoice formatting, and persistence.

## 2. Current behavior
- Uses an in-memory menu
- Builds an order (hard-coded in `Demo02`)
- Computes subtotal, tax, discount, and total
- Prints an invoice and writes it to a file-like store (in-memory)

## 3. What’s wrong with the design
1. `CafeteriaSystem.checkout` mixes menu lookup, pricing, tax rules, discount rules, printing, and persistence.
2. Tax rules are hard-coded and not extensible.
3. Discounts are hard-coded with ad-hoc conditions.
4. Invoice formatting is mixed with money calculations.
5. Persistence is a concrete class; hard to test without writing.
6. `Demo02` depends on too many internal details.

## 4. Our task
Checkpoint A: 
- Run and capture output.

Checkpoint B: 
- Separate pricing/tax/discount computations into dedicated components.

Checkpoint C: 
- Move invoice formatting out of `CafeteriaSystem`.

Checkpoint D: 
- Introduce small abstractions to decouple persistence and rules.

Checkpoint E: 
- Keep output identical.

## 5. Humne kya kiya

| Principle | Evidence |
|-----------|----------|
| S (SRP) | Tax → TaxRules, Discount → DiscountRules, Formatting → InvoiceFormatter, Persistence → InvoiceRepository. Each has ONE job. |
| O (OCP) | CafeteriaSystem closed for modification. Open for extension via interfaces (add SeniorTaxRules, BulkDiscountRules without edits). |
| L (LSP) | Any TaxRules / DiscountRules / InvoiceRepository implementation can substitute without breaking the system. |

## 6. How to run
```bash
cd SOLID/ex02/src
javac *.java
java Demo02
```

## 7. Current output
```text
=== Cafeteria Billing ===
Invoice# INV-1001
- Veg Thali x2 = 160.00
- Coffee x1 = 30.00
Subtotal: 190.00
Tax(5%): 9.50
Discount: -10.00
TOTAL: 189.50
Saved invoice: INV-1001 (lines=7)

=== Staff Order ===
Invoice# INV-1002
- Veg Thali x1 = 80.00
- Coffee x1 = 30.00
- Sandwich x1 = 60.00
Subtotal: 170.00
Tax(2%): 3.40
Discount: -15.00
TOTAL: 158.40
Saved invoice: INV-1002 (lines=7)
```
