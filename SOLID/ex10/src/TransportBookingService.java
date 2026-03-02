public class TransportBookingService {
    private final DistanceCalculable dist;
    private final DriverAllocatable alloc;
    private final PaymentChargeable pay;
    private final FareCalculable fareCalc;

    public TransportBookingService(DistanceCalculable dist, DriverAllocatable alloc,
                                   PaymentChargeable pay, FareCalculable fareCalc) {
        this.dist = dist;
        this.alloc = alloc;
        this.pay = pay;
        this.fareCalc = fareCalc;
    }

    public void book(TripRequest req) {
        double km = dist.km(req.from, req.to);
        System.out.println("DistanceKm=" + km);

        String driver = alloc.allocate(req.studentId);
        System.out.println("Driver=" + driver);

        double fare = fareCalc.calculate(km);

        String txn = pay.charge(req.studentId, fare);
        System.out.println("Payment=PAID txn=" + txn);

        BookingReceipt r = new BookingReceipt("R-501", fare);
        System.out.println("RECEIPT: " + r.id + " | fare=" + String.format("%.2f", r.fare));
    }
}

// now rather than creating conrete classes it uses the layer of abstraction - the lean interfaces according to their use case.
// we have also put the fare calculator behind an interface to avoid mixing buisiness logic and pricing together
