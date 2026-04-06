public class Main {
    public static void main(String[] args) {
        Database<String, String> database = new InMemoryDatabase<>();
        database.put("A", "Alpha");
        database.put("B", "Beta");
        database.put("C", "Gamma");

        DistributedCache<String, String> cache = new DistributedCache<>(
                3,
                2,
                new ModuloDistributionStrategy<>(),
                database,
                new LRUEvictionPolicyFactory<>()
        );

        System.out.println("get(A) -> " + cache.get("A")); // miss -> DB -> cache
        System.out.println("get(A) -> " + cache.get("A")); // hit

        cache.put("D", "Delta");
        System.out.println("get(D) -> " + cache.get("D"));

        // Keys X and Y are likely to map to the same node in small node counts,
        // helping demonstrate per-node LRU eviction behavior.
        cache.put("X", "X-ray");
        cache.put("Y", "Yankee");
        System.out.println("get(X) -> " + cache.get("X"));
        System.out.println("get(Y) -> " + cache.get("Y"));
    }
}
