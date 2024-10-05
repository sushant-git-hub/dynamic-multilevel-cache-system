import java.util.LinkedHashSet;

public class LRU implements EvictionPolicy {
    private final LinkedHashSet<String> accessOrder;

    public LRU() {
        accessOrder = new LinkedHashSet<>();
    }

    @Override
    public void recordAccess(String key) {
        if (accessOrder.contains(key)) {
            accessOrder.remove(key);
        }
        accessOrder.add(key);
    }

    @Override
    public String evict() {
        String evictKey = accessOrder.iterator().next();
        accessOrder.remove(evictKey);
        return evictKey;
    }
}
