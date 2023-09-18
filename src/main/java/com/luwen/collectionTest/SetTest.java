package com.luwen.collectionTest;

import java.util.*;

public class SetTest {

    public static void main(String[] args) {
        treeMapTest();
    }

    public static void hashSetTest(){
        Set<Integer> set = new HashSet();
        set.add(null);
        set.add(1);

        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    public static void treeMapTest(){
        TreeMap treeMap = new TreeMap();
        treeMap.put("6", "6");
        treeMap.put("7", "7");
        treeMap.put("4", "4");
        treeMap.put("5", "5");
        treeMap.put("3", "3");
        treeMap.put("2", "2");
        treeMap.put("1", "1");
        treeMap.put("0", "0");
        treeMap.remove("6");
        Iterator iterator = treeMap.keySet().iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
