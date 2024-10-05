Dynamic Multilevel Cache System
Overview
This project implements a Dynamic Multilevel Cache System with support for multiple cache levels, each having its own size and eviction policy (LRU or LFU). The system supports adding and removing cache levels dynamically and ensures data is retrieved efficiently from the highest-priority cache level. The cache management system uses in-memory data structures for storing the cache entries, and no external services are required.

Features
Multiple Cache Levels: The system allows adding multiple cache levels (L1, L2, ..., Ln), where each level has its own size and eviction policy.
Eviction Policies: Supports two eviction policies:
Least Recently Used (LRU): Evicts the least recently accessed item.
Least Frequently Used (LFU): Evicts the least frequently accessed item.
Data Retrieval: The system retrieves data from the highest-priority cache level (L1) and moves data from lower levels to the higher level if found.
Dynamic Cache Level Management: Cache levels can be added or removed dynamically at runtime.
Concurrency (optional): Thread-safe implementation ensures consistent access when there are multiple read and write operations.
Approach
1. Cache Levels
The system has a CacheManager that holds multiple cache levels.
Each cache level is managed by a CacheLevel class, which maintains the cache entries, capacity, and eviction policy (LRU/LFU).
Cache levels are searched sequentially from L1 downwards when retrieving data. If found at a lower level, the data is moved to the higher levels.
2. Eviction Policies
Implemented two eviction policies:
LRU (Least Recently Used): Managed using a combination of a hash map and a doubly-linked list to keep track of access order.
LFU (Least Frequently Used): Managed using a priority queue to track the frequency of accesses and evict the least frequently accessed item.
Both eviction policies are implemented as classes (LRU.java and LFU.java), adhering to the EvictionPolicy interface. This allows the cache levels to choose the policy they want to use during runtime.

3. Data Retrieval and Insertion
get(key): Data retrieval is attempted from the highest level cache down to the lowest. If the data is found in a lower-level cache, it is moved up to higher levels.
put(key, value): New entries are always inserted into the L1 cache. If L1 is full, an eviction is triggered based on the eviction policy. Items may be evicted from lower cache levels if needed.
4. Dynamic Cache Level Management
addCacheLevel(size, evictionPolicy): Adds a new cache level dynamically at runtime. You can specify the size and eviction policy for the cache level.
removeCacheLevel(levelIndex): Removes a cache level at the specified index, ensuring the system can handle cache level modifications at runtime.
5. Code Structure
DynamicCacheSystem.java: Main entry point and test class for simulating cache operations (adding levels, inserting, retrieving).
CacheManager.java: Handles the dynamic addition/removal of cache levels and delegates cache operations.
CacheLevel.java: Represents a single cache level with a specific size and eviction policy.
EvictionPolicy.java: Interface for defining eviction behavior.
LRU.java: Implements the LRU eviction policy.
LFU.java: Implements the LFU eviction policy.
Key Decisions
Eviction Policies: The decision to support both LRU and LFU eviction policies was made to demonstrate flexibility in cache management strategies. LRU is used for recency-based caching, while LFU is useful in scenarios where frequency of use matters.
Cache Promotion: When data is retrieved from a lower cache level, it is promoted to the L1 cache, which ensures that frequently accessed items are moved to higher-priority caches.
Dynamic Cache Level Management: Allowing the system to dynamically add or remove cache levels provides flexibility in handling different runtime conditions, making the system more adaptive.
How to Run the Application
Prerequisites
Java Development Kit (JDK) installed (version 8 or higher).
Running the Code
Clone the repository:

bash
git clone https://github.com/your-username/dynamic-multilevel-cache-system.git
cd dynamic-multilevel-cache-system
Compile the Java files:

bash
javac src/main/*.java
Run the application:

bash

java src/main/DynamicCacheSystem
Sample Test Cases
In the DynamicCacheSystem.java, there are some basic test cases to illustrate the functionality:

Adding cache levels:

java

cacheManager.addCacheLevel(3, "LRU");  // L1 with size 3 and LRU eviction policy
cacheManager.addCacheLevel(2, "LFU");  // L2 with size 2 and LFU eviction policy
Inserting and retrieving data:

java

cacheManager.put("A", "1");
cacheManager.put("B", "2");
cacheManager.put("C", "3");
cacheManager.get("A");   // Returns "1" from L1
cacheManager.put("D", "4");  // Evicts least recently used (B)
Displaying cache levels:

java

cacheManager.displayCache();
// L1 Cache: [A: 1, D: 4, C: 3]
// L2 Cache: [B: 2]
Project Structure
css

dynamic-multilevel-cache-system/
│
├── src/
│   └── main/
│       ├── CacheLevel.java
│       ├── CacheManager.java
│       ├── DynamicCacheSystem.java
│       ├── EvictionPolicy.java
│       ├── LRU.java
│       └── LFU.java
│
└── README.md
Future Enhancements
Concurrency: Add support for thread-safety in cache operations to handle concurrent read/write operations efficiently.
Multiple Eviction Policies: Allow different cache levels to have independent eviction policies.
Persistence: Optionally persist cache data to disk for recovery in case of system failures.
