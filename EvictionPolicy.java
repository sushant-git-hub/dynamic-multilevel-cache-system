public interface EvictionPolicy {
    void recordAccess(String key);
    String evict();
}
