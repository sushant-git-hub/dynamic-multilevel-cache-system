public class DynamicCacheSystem {
    public static void main(String[] args) {
        CacheManager cacheManager = new CacheManager();
        cacheManager.addCacheLevel(3, "LRU");
        cacheManager.addCacheLevel(2, "LFU");

        cacheManager.put("A", "1");
        cacheManager.put("B", "2");
        cacheManager.put("C", "3");
        cacheManager.displayCache();

        System.out.println("Get A: " + cacheManager.get("A"));
        cacheManager.put("D", "4");
        cacheManager.displayCache();

        System.out.println("Get C: " + cacheManager.get("C"));
        cacheManager.displayCache();
    }
}
