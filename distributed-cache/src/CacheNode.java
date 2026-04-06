import java.util.HashMap;
import java.util.Map;

public class CacheNode<K, V> {
    private final String nodeId;
    private final int capacity;
    private final EvictionPolicy<K> evictionPolicy;
    private final Map<K, V> data = new HashMap<>();

    public CacheNode(String nodeId, int capacity, EvictionPolicy<K> evictionPolicy) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be > 0");
        }
        this.nodeId = nodeId;
        this.capacity = capacity;
        this.evictionPolicy = evictionPolicy;
    }

    public synchronized V get(K key) {
        V value = data.get(key);
        if (value != null) {
            evictionPolicy.onKeyAccess(key);
        }
        return value;
    }

    public synchronized void put(K key, V value) {
        if (data.containsKey(key)) {
            data.put(key, value);
            evictionPolicy.onKeyAccess(key);
            return;
        }

        if (data.size() >= capacity) {
            K evictKey = evictionPolicy.selectEvictionCandidate();
            if (evictKey != null) {
                data.remove(evictKey);
                evictionPolicy.onKeyRemove(evictKey);
            }
        }

        data.put(key, value);
        evictionPolicy.onKeyInsert(key);
    }

    public String getNodeId() {
        return nodeId;
    }
}
