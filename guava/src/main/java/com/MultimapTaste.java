package com;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;


public class MultimapTaste {

    public static void main(String[] args) {
        Multimap<Integer, String> multimap = ArrayListMultimap.create();

        multimap.put(1, "A");
        multimap.put(1, "B");
        multimap.put(1, "C");
        multimap.put(1, "A");

        multimap.put(2, "A");
        multimap.put(2, "B");
        multimap.put(2, "C");

        multimap.put(3, "A");

        System.out.println(multimap.get(1));
        System.out.println(multimap.get(2));
        System.out.println(multimap.get(2));
        System.out.println(multimap.get(3));
    }
}
