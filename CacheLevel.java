import java.util.LinkedHashMap;
import java.util.Map;

public class CacheLevel {
    private final int size;
    private final EvictionPolicy evictionPolicy;
    private final Map<String, String> data;

    public CacheLevel(int size, EvictionPolicy evictionPolicy) {
        this.size = size;
        this.evictionPolicy = evictionPolicy;
        this.data = new LinkedHashMap<>();
    }

    public String get(String key) {
        if (data.containsKey(key)) {
            evictionPolicy.recordAccess(key);
            return data.get(key);
        }
        return null;
    }

    public void put(String key, String value) {
        if (data.size() == size) {
            String evictedKey = evictionPolicy.evict();
            data.remove(evictedKey);
        }
        data.put(key, value);
        evictionPolicy.recordAccess(key);
    }

    public Map<String, String> getData() {
        return data;
    }
}
