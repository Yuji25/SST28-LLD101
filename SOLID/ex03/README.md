# ex03 — OCP: Placement Eligibility Rules Engine

## 1. Context
Placement eligibility depends on multiple rules: CGR threshold, attendance percentage, earned credits, and disciplinary flags. More rules will be added later.

## 2. Current behavior
- Evaluates a `StudentProfile`
- Returns ELIGIBLE or NOT_ELIGIBLE with reasons
- Prints a report

## 3. What’s wrong with the design
1. `EligibilityEngine.evaluate` is a long if/else chain with mixed responsibilities.
2. Adding a new rule requires editing the same method (risk of regressions).
3. Rule configuration is hard-coded.
4. Reasons formatting is mixed with evaluation.
5. Engine does persistence-ish logging via `FakeEligibilityStore`.
6. Type/flag logic is scattered.

## 4. Our task
Checkpoint A:
- Run and capture output.

Checkpoint B: 
- Move each rule to its own unit (class) behind a shared abstraction.

Checkpoint C: 
- Make it possible to add a new rule without editing the main evaluation logic.

Checkpoint D: 
- Keep report text identical.

## 5. Humne kya kiya
| Principle | Evidence |
|-----------|----------|
| S (SRP) | Each rule has ONE responsibility. EligibilityEngine handles orchestration only. ReportPrinter handles formatting only. EligibilityRepository handles persistence only. |
| O (OCP) | EligibilityEngine is closed for modification and open for extension via the EligibilityRule interface (new rules like ProjectRule or InternshipRule can be added without editing the engine). |
| L (LSP) | Any EligibilityRule or EligibilityRepository implementation can substitute the existing one without breaking the system. |

## 6. How to run
```bash
cd SOLID/ex03/src
javac *.java
java Demo03
```

## 7. Current output
```text
=== Placement Eligibility ===
Student: Ayaan (CGR=8.10, attendance=72, credits=18, flag=NONE)
RESULT: NOT_ELIGIBLE
- attendance below 75
Saved evaluation for roll=23BCS1001
```
