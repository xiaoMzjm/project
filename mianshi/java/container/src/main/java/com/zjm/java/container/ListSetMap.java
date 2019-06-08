package com.zjm.java.container;

import java.util.HashMap;
import java.util.Map;

public class ListSetMap {

    public static void main(String[] args) {

        Map<String,String> map = new HashMap<>();

        map.put("2" , "2");
        map.put("1" , "1");
        map.put("3" , "3");

        for(Map.Entry<String,String> entry : map.entrySet()) {
            System.out.println(entry.getKey());
        }
    }
}
