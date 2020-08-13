package com.todoreminder;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class PrefConfig {

    private static final String SHARED_PREF_FILE = "com.todoreminder";
    private static final String PREF_KEY = "PREF_KEY";

    public static void SaveData(Context context, List<Todo> list) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(list);

        SharedPreferences mPreference = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = mPreference.edit();

        editor.putString(PREF_KEY, jsonString);
        editor.apply();

    }

    public static List<Todo> LoadData(Context context) {
        SharedPreferences mPreference = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonString = mPreference.getString(PREF_KEY, " ");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Todo>>() {
        }.getType();
        List<Todo> list = gson.fromJson(jsonString, type);
        return list;
    }
}
