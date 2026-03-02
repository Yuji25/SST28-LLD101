public interface PaymentChargeable {
    String charge(String studentId, double amount);
}

// lean interface for layer of abstraction