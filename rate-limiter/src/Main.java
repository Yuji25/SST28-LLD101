import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<RateLimitRule> rules = List.of(
                RateLimitRule.perMinutes("per-tenant-minute", 5, 1),
                RateLimitRule.perHours("per-tenant-hour", 1000, 1)
        );

        ExternalProviderClient client = new ExternalProviderClient();

        RateLimiter fixedWindowLimiter = new PluggableRateLimiter(
                new FixedWindowCounterStrategy(),
                rules,
                new SystemClock()
        );

        RateLimiter slidingWindowLimiter = new PluggableRateLimiter(
                new SlidingWindowCounterStrategy(),
                rules,
                new SystemClock()
        );

        System.out.println("--- Using Fixed Window ---");
        InternalBusinessService fixedService = new InternalBusinessService(fixedWindowLimiter, client);
        runSampleFlow(fixedService, "T1");

        System.out.println("\n--- Using Sliding Window (same business logic, only limiter swapped) ---");
        InternalBusinessService slidingService = new InternalBusinessService(slidingWindowLimiter, client);
        runSampleFlow(slidingService, "T1");
    }

    private static void runSampleFlow(InternalBusinessService service, String tenantId) {
        service.processRequest("R1", tenantId, false);

        for (int i = 2; i <= 8; i++) {
            service.processRequest("R" + i, tenantId, true);
        }
    }
}
