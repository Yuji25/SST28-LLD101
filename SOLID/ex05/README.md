# ex05 — LSP: File Exporter Hierarchy

## 1. Context
A reporting tool exports student performance data to multiple formats.

## 2. Current behavior
- `Exporter` has `export(ExportRequest)` that returns `ExportResult`
- `PdfExporter` throws for large content (tightens preconditions)
- `CsvExporter` silently changes meaning by dropping newlines and commas poorly
- `JsonExporter` returns empty on null (inconsistent contract)
- `Main` demonstrates current behavior

## 3. What’s wrong (at least 5 issues)
1. Subclasses violate expectations of the base `Exporter` contract.
2. `PdfExporter` throws for valid requests (from base perspective).
3. `CsvExporter` changes semantics of fields (data corruption risk).
4. `JsonExporter` handles null differently than others.
5. Callers cannot rely on substitutability; they need format-specific workarounds.
6. Contract is not documented; behavior surprises are runtime.

## 4. Your task
Checkpoint A: 
- Run and capture output.

Checkpoint B: 
- Define a clear base contract (preconditions/postconditions).

Checkpoint C: 
- Refactor hierarchy so all exporters honor the same contract.

Checkpoint D: 
- Keep observable outputs identical for current inputs.

## 5. Humne Kya Kiya ? 
| Principle | Evidence |
|-----------|----------|
| L (LSP) | All exporters honor the same contract. No precondition tightening (PDF accepts all sizes). Postconditions remain consistent (data preserved). Caller can substitute any implementation without surprises. |
| S (SRP) | Base Exporter handles contract enforcement only. Subclasses handle format-specific encoding only. Each has ONE responsibility. |
| O (OCP) | New exporters like XmlExporter or YamlExporter can be added without modifying the base contract or existing exporters. |


## 6. How to run
```bash
cd SOLID/ex05/src
javac *.java
java Demo05
```

## 7. Current output
```text
=== Export Demo ===
PDF: OK bytes=47
CSV: OK bytes=56
JSON: OK bytes=66
```
