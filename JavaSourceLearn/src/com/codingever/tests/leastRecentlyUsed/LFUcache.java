package com.codingever.tests.leastRecentlyUsed;

import java.util.HashMap;
import java.util.LinkedHashSet;

public class LFUcache {
    // 从key到val的映射，后面称为KV表
    HashMap<Integer, Integer> keyToVal;
    // key到freq的映射，
    HashMap<Integer, Integer> keyToFreq;
    // freq到key列表的映射
    HashMap<Integer, LinkedHashSet<Integer>> freqToKeys;
    // 记录最小频次
    int minFreq;
    // 记录LFU缓存的最大容量
    int cap;

    public LFUcache(int capacity){
        keyToVal = new HashMap<>();
        keyToFreq = new HashMap<>();
        freqToKeys = new HashMap<>();
        this.cap = capacity;
        this.minFreq = 0;
    }

    public int get(int key){
        if (!keyToVal.containsKey(key)) return -1;
        increaseFreq(key);
        return  keyToVal.get(key);
    }

    public void put(int key, int val){
        if (this.cap <= 0) return;

        if (keyToVal.containsKey(key)){
            keyToVal.put(key, val);
            increaseFreq(key);
            return;
        }

        if (keyToVal.size() >= cap){
            removeMinFreqKey();
        }
        keyToVal.put(key, val);
        keyToFreq.put(key, val);
        freqToKeys.putIfAbsent(1, new LinkedHashSet<>());
        freqToKeys.get(1).add(key);
        this.minFreq = 1;
    }

    public void removeMinFreqKey(){
        LinkedHashSet<Integer> keyList = freqToKeys.get(this.minFreq);
        int deleteKsy = keyList.iterator().next();
        keyList.remove(deleteKsy);
        if (keyList.isEmpty()){
            freqToKeys.remove(this.minFreq);
        }
        keyToVal.remove(deleteKsy);
        keyToFreq.remove(deleteKsy);
    }

    public void increaseFreq(int key){
        int freq = keyToFreq.get(key);
        keyToFreq.put(key, freq + 1);
        freqToKeys.putIfAbsent(freq + 1, new LinkedHashSet<>());
        freqToKeys.get(freq + 1).add(key);
        if (freqToKeys.get(freq).isEmpty()){
            freqToKeys.remove(freq);
            if (freq == this.minFreq){
                this.minFreq++;
            }
        }
    }
}
