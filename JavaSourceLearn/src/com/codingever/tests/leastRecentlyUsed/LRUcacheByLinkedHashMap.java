package com.codingever.tests.leastRecentlyUsed;

import java.util.LinkedHashMap;

public class LRUcacheByLinkedHashMap {
    int cap;
    LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();
    public void LRUcacheByLinkedHashMap(int capecity){
        this.cap = capecity;
    }

    // 将某个key提升为最近使用的
    private void makeRecently(int key){
        int val = cache.get(key);
        cache.remove(key);
        cache.put(key, val);
    }

    public int get(int key){
        if (!cache.containsKey(key)){
            return -1;
        }
        makeRecently(key);
        return cache.get(key);
    }

    public void put(int key, int val){
        if (cache.containsKey(key)){
            cache.remove(key);
            cache.put(key, val);
            return;
        }

        if (cache.size() >= cap){
            int oldestKey = cache.keySet().iterator().next();
            cache.remove(oldestKey);
        }
        cache.put(key, val);
    }
}
