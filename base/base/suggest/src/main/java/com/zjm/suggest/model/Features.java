package com.zjm.suggest.model;

import java.util.*;

import com.alibaba.fastjson.JSON;

/**
 * @author:小M
 * @date:2019/2/21 12:30 AM
 */
public class Features {

    private Map<String,String> featuresMap;

    public Features(){
        featuresMap = new HashMap<>(4);
    }

    public synchronized void appendFeature(String key , String value) {
        featuresMap.put(key , value);
    }

    public synchronized void appendFeatures(Map<String,String> features) {
        featuresMap.putAll(features);
    }

    public synchronized void removeFeature(String key) {
        featuresMap.remove(key);
    }

    public String get(String key) {
        return featuresMap.get(key);
    }

    @Override
    public String toString(){
        return JSON.toJSONString(featuresMap);
    }
}
