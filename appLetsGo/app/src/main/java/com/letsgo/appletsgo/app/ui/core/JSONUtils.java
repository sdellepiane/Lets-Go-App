package com.letsgo.appletsgo.app.ui.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by louislopez on 28/02/17.
 */

public class JSONUtils {
    public JSONUtils() {
    }

    public static JSONObject generateJSONObject(Object obj) {
        Gson gson = new Gson();
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(gson.toJson(obj));
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return jsonObject;
    }

    public static JSONArray generateJSONArray(Object obj) {
        Gson gson = new Gson();
        JSONArray jsonObject = null;

        try {
            jsonObject = new JSONArray(gson.toJson(obj));
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return jsonObject;
    }

    public static Object jsonToObject(JSONObject jsonObject, Class<?> objectClass) {
        GsonBuilder gsonb = new GsonBuilder();
        Gson gson = gsonb.create();
        Object nObj = gson.fromJson(jsonObject.toString(), objectClass);
        return nObj;
    }

    public static Object jsonStringToObject(String jsonString, Class<?> objectClass) {
        GsonBuilder gsonb = new GsonBuilder();
        Gson gson = gsonb.create();
        Object nObj = gson.fromJson(jsonString, objectClass);
        return nObj;
    }
}