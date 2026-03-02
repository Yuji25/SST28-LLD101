public class FareCalculator implements FareCalculable {
    @Override
    public double calculate(double km) {
        double fare = 50.0 + km * 6.6666666667;
        return Math.round(fare * 100.0) / 100.0;
    }
}

// now it implements its respective interface to avoid messy pricing