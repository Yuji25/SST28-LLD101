import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class FixedWindowCounterStrategy implements RateLimitingStrategy {
    private static class Counter {
        private long windowStart;
        private int count;

        Counter(long windowStart) {
            this.windowStart = windowStart;
            this.count = 0;
        }
    }

    private final ConcurrentMap<String, Counter> counters = new ConcurrentHashMap<>();

    @Override
    public RateLimitDecision allow(String key, RateLimitRule rule, long nowMillis) {
        String storageKey = key + "::" + rule.getId();
        long windowMillis = rule.getWindowMillis();
        long alignedWindowStart = nowMillis - (nowMillis % windowMillis);

        Counter counter = counters.computeIfAbsent(storageKey, k -> new Counter(alignedWindowStart));

        synchronized (counter) {
            if (counter.windowStart != alignedWindowStart) {
                counter.windowStart = alignedWindowStart;
                counter.count = 0;
            }

            if (counter.count < rule.getMaxRequests()) {
                counter.count++;
                return RateLimitDecision.allowed("Allowed by fixed window");
            }

            long retryAfter = (counter.windowStart + windowMillis) - nowMillis;
            return RateLimitDecision.denied("Fixed window limit exceeded", rule.getId(), retryAfter);
        }
    }

    @Override
    public String name() {
        return "FixedWindowCounter";
    }
}
