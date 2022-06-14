package com.codingever.tests.leastRecentlyUsed;

import java.util.HashMap;

public class LRUcache {
    // key -> Node(key, value)
    private HashMap<Integer, Node> map;
    // Node(k1, v1) <-> Node(k2, v2) <-> ...
    private DoubleList cache;
    // 最大容量
    private int cap;

    public LRUcache(int capacity){
        this.cap = capacity;
        map = new HashMap<Integer, Node>();
        cache = new DoubleList();
    }

    // 将某个key提升为最近使用的
    private void makeRecently(int key){
        // 将key对应的节点从cache中删除，然后重新添加到cache末尾中
        Node node = map.get(key);
        cache.remove(node);
        cache.addLast(node);
    }

    // 添加最近使用的元素
    private void addRecently(int key, int val){
        // 创建一个节点Node(key, node),然后将其添加到cache末尾，并在map中添加kay-val
        Node node = new Node(key, val);
        map.put(key, node);
        cache.addLast(node);
    }

    // 删除一个key
    private void deleteKey(int key){
        // 先从map中查找key对应的node，然后从cache和map中删除该node
        Node node = map.get(key);
        cache.remove(node);
        map.remove(key);
    }

    // 删除最久未使用的元素
    private void deleteLeastRecenty(){
        // 最久未使用的元素即为第一个元素，获取到第一个元素的key
        // 从map和cache中删除该元素
        Node deleteNode = cache.removeFirst();
        map.remove(deleteNode.key);
    }

    public int get(int key){
        if (!map.containsKey(key))
            return -1;
        //将该数据提升为最近使用的
        makeRecently(key);
        return map.get(key).val;
    }

    public void put(int key, int val){
        // 若key已存在
        if (map.containsKey(key)){
            // 删除旧的数据
            deleteKey(key);
            addRecently(key, val);
            return;
        }
        // 若key不存在
        if (cap == cache.size()){
            // 清除最久未使用的数据
            deleteLeastRecenty();
        }
        // 将key对应的数据设置为最新的
        addRecently(key, val);
    }
}
