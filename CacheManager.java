import java.util.ArrayList;
import java.util.List;

public class CacheManager {
    private final List<CacheLevel> cacheLevels;

    public CacheManager() {
        cacheLevels = new ArrayList<>();
    }

    public void addCacheLevel(int size, String evictionPolicy) {
        EvictionPolicy policy;
        if ("LRU".equals(evictionPolicy)) {
            policy = new LRU();
        } else if ("LFU".equals(evictionPolicy)) {
            policy = new LFU();
        } else {
            throw new IllegalArgumentException("Unknown eviction policy: " + evictionPolicy);
        }
        cacheLevels.add(new CacheLevel(size, policy));
    }

    public void removeCacheLevel(int level) {
        if (level < 1 || level > cacheLevels.size()) {
            throw new IllegalArgumentException("Invalid cache level: " + level);
        }
        cacheLevels.remove(level - 1);
    }

    public String get(String key) {
        for (CacheLevel cacheLevel : cacheLevels) {
            String value = cacheLevel.get(key);
            if (value != null) {
                promoteToHigherLevel(key, value);
                return value;
            }
        }
        return null; // Cache miss
    }

    public void put(String key, String value) {
        CacheLevel L1 = cacheLevels.get(0);
        L1.put(key, value);
    }

    private void promoteToHigherLevel(String key, String value) {
        put(key, value);
    }

    public void displayCache() {
        for (int i = 0; i < cacheLevels.size(); i++) {
            System.out.println("L" + (i + 1) + " Cache: " + cacheLevels.get(i).getData());
        }
    }
}
