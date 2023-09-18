package com.luwen.collectionTest;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapTest {

    public static void main(String[] args) {
        LinkedHashMapTest();
    }

    /**
     * HashMap线程不安全
     * table[Node... node]
     * DEFAULT_INITIAL_CAPACITY: 默认容量16
     * DEFAULT_LOAD_FACTOR: 加载因子0.75
     * TREEIFY_THRESHOLD
     */
    public static void hashMapTest() {

        Map<Object, Object> map = new HashMap<>();
/*        map.put(new Byte("65"), 65);
        map.put(new Short("65"), 65);
        map.put(new Integer(65), 65);
        map.put(new Long(65), 65);
        map.put("A", "A");*/
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        map.put(new ObjA("A"), "A");
        System.out.println(map.get("A"));
    }

    /**
     * LinkedHashMap 线程不安全
     * LinkedHashMap 支持LRU算法
     * 增加head tail指针，每次添加元素除了hashMap方法之外，元素增加进入双向链表操作
     */
    public static void LinkedHashMapTest() {

        LinkedHashMap linkedHashMap = new LinkedHashMap(3) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > 3;
            }
        };
        linkedHashMap.put("1", "1");
        linkedHashMap.put("3", "3");
        linkedHashMap.put("2", "2");
        linkedHashMap.put("4", "4");
        linkedHashMap.forEach((k, v) -> {
            System.out.println("k : " + k + ", v : " + v);
        });
    }

    /**
     * concurrentHashMap线程安全，数据结构同HashMap线程不安全
     * hash位为空时使用cas竞争，
     * hash位存在链时使用lock
     */
    public static void concurrentHashMap() {
        Map<String, String> concurrentHashMap = new ConcurrentHashMap<>(8);
        concurrentHashMap.put("1", "zhao");
        concurrentHashMap.put("2", "qian");
        concurrentHashMap.forEach((k,v) -> {
            System.out.println("k : " + k + ", v : "+v);
        });
    }

    public static <T> T test() {
        Object o = new Object();
        return (T) o;
    }
}

class ObjA {

    public String a;

    public ObjA() {

    }

    public ObjA(String a) {
        this.a = a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjA objA = (ObjA) o;
        return objA == this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a);
    }
}

