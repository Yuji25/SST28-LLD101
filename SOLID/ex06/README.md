# ex06 — LSP: Notification Sender Inheritance

## 1. Context
A campus system sends notifications via email, SMS, and WhatsApp.

## 2. Current behavior
- `NotificationSender.send(Notification)` is the base method
- `EmailSender` silently truncates messages (changes meaning)
- `WhatsAppSender` rejects non-E.164 numbers (tightens precondition)
- `SmsSender` ignores subject but base type implies subject may be used

## 3. What’s wrong
1. Subtypes impose extra constraints not present in base contract.
2. Subtypes change semantics (truncation, ignoring fields).
3. Callers cannot rely on base behavior without knowing subtype.
4. Runtime surprises (exceptions) force subtype-specific handling.
5. Contract is vague and untested; inheritance is misused.

## 4. Your task
- Make substitutability true: if code works with `NotificationSender`, it should work with any sender.
- Preserve current outputs for the sample inputs in `Main`.

## 5. Humne Kya Kiya ?
| Principle | Evidence |
|-----------|----------|
| L (LSP) | All senders honor the same contract. No preconditions are tightened. Message bodies are sent as-is (no truncation). Field usage is clearly documented (no surprises). Caller can safely substitute any sender implementation. |
| S (SRP) | Base NotificationSender handles contract validation only. Subclasses handle format-specific sending only. Normalization logic is isolated. Each has ONE responsibility. |
| O (OCP) | New senders like TelegramSender or PushNotificationSender can be added without modifying the base contract or existing sender implementations. |

## 6. How to run
```bash
cd SOLID/ex06/src
javac *.java
java Demo06
```

## 7. Current output
```text
=== Notification Demo ===
EMAIL -> to=riya@sst.edu subject=Welcome body=Hello and welcome to SST!
SMS -> to=9876543210 body=Hello and welcome to SST!
WA -> to=+919876543210 body=Hello and welcome to SST!
AUDIT entries=3
```
