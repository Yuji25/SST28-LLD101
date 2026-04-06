import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SlidingWindowCounterStrategy implements RateLimitingStrategy {
    private static class Counter {
        private long currentWindowStart;
        private int currentCount;
        private int previousCount;

        Counter(long currentWindowStart) {
            this.currentWindowStart = currentWindowStart;
            this.currentCount = 0;
            this.previousCount = 0;
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
            rotateWindowIfNeeded(counter, alignedWindowStart, windowMillis);

            long elapsedInCurrentWindow = nowMillis - counter.currentWindowStart;
            double previousWeight = (double) (windowMillis - elapsedInCurrentWindow) / windowMillis;
            double effectiveCount = counter.currentCount + (counter.previousCount * previousWeight);

            if (effectiveCount < rule.getMaxRequests()) {
                counter.currentCount++;
                return RateLimitDecision.allowed("Allowed by sliding window");
            }

            long retryAfter = windowMillis - elapsedInCurrentWindow;
            return RateLimitDecision.denied("Sliding window limit exceeded", rule.getId(), retryAfter);
        }
    }

    private void rotateWindowIfNeeded(Counter counter, long alignedWindowStart, long windowMillis) {
        if (alignedWindowStart == counter.currentWindowStart) {
            return;
        }

        if (alignedWindowStart == counter.currentWindowStart + windowMillis) {
            counter.previousCount = counter.currentCount;
        } else {
            counter.previousCount = 0;
        }

        counter.currentWindowStart = alignedWindowStart;
        counter.currentCount = 0;
    }

    @Override
    public String name() {
        return "SlidingWindowCounter";
    }
}
