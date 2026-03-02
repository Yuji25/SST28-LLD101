public class Demo10 {
    public static void main(String[] args) {
        System.out.println("=== Transport Booking ===");
        TripRequest req = new TripRequest("23BCS1010", new GeoPoint(12.97, 77.59), new GeoPoint(12.93, 77.62));

        DistanceCalculable dist = new DistanceCalculator();
        DriverAllocatable alloc = new DriverAllocator();
        PaymentChargeable pay = new PaymentGateway();
        FareCalculable fareCalc = new FareCalculator();

        TransportBookingService svc = new TransportBookingService(dist, alloc, pay, fareCalc);
        svc.book(req);
    }
}

// now we will do dependency injection by passing correct implementations, 
// of all the lean interfaces via the constructor of our service.
