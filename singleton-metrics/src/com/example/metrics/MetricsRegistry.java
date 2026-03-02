package com.example.metrics;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MetricsRegistry implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // volatile ensures that threads apne cache ke sath RAM me update kare and don't leave unfinished
    private static volatile MetricsRegistry INSTANCE;

    // flag to block reflection attack
    private static boolean instanceCreated = false;

    private final Map<String, Long> counters = new HashMap<>();

    // 1) Private constructor + reflection guard
    private MetricsRegistry() {
        synchronized (MetricsRegistry.class) {
            if (instanceCreated) {
                throw new RuntimeException(
                    "Singleton violation: use getInstance() instead of reflection!");
            }
            instanceCreated = true;
        }
    }

    // 2) Double-Checked Locking — lazy + thread-safe ( jaisa sir ne bataya tha )
    public static MetricsRegistry getInstance() {
        if (INSTANCE == null) {                          // 1st check
            synchronized (MetricsRegistry.class) {
                if (INSTANCE == null) {                  // 2nd check
                    INSTANCE = new MetricsRegistry();
                }
            }
        }
        return INSTANCE;
    }

    // 3) Serialization guard — return same instance on deserialization
    @Serial
    private Object readResolve() {
        return getInstance();
    }

    public synchronized void setCount(String key, long value) {
        counters.put(key, value);
    }

    public synchronized void increment(String key) {
        counters.put(key, getCount(key) + 1);
    }

    public synchronized long getCount(String key) {
        return counters.getOrDefault(key, 0L);
    }

    public synchronized Map<String, Long> getAll() {
        return Collections.unmodifiableMap(new HashMap<>(counters));
    }
}
