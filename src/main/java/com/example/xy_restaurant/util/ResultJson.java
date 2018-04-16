package com.example.xy_restaurant.util;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class ResultJson {
    private static Gson gson = new Gson();
    private static Map<String, Object> map = new HashMap<>();

    public static String result(String results) {
        map.clear();
        map.put("data", results);
        return gson.toJson(map);
    }

    public static String resultFlag(boolean flag, String results) {
        map.clear();
        map.put("code", flag);
        map.put("data", results);
        return gson.toJson(map);
    }

    public static String resultMsg(boolean result, String error){
        map.clear();
        map.put("result", result);
        map.put("error", error);
        return gson.toJson(map);
    }
}
