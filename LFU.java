import java.util.HashMap;
import java.util.Map;

public class LFU implements EvictionPolicy {
    private final Map<String, Integer> frequencyMap;

    public LFU() {
        frequencyMap = new HashMap<>();
    }

    @Override
    public void recordAccess(String key) {
        frequencyMap.put(key, frequencyMap.getOrDefault(key, 0) + 1);
    }

    @Override
    public String evict() {
        String evictKey = frequencyMap.entrySet()
            .stream()
            .min(Map.Entry.comparingByValue())
            .get()
            .getKey();
        frequencyMap.remove(evictKey);
        return evictKey;
    }
}
