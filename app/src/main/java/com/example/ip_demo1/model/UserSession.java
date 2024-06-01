package com.example.ip_demo1.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

public class UserSession {
    private static final String PREFS_NAME = "user_session";
    private static final String KEY_USER = "user";
    private static SharedPreferences sharedPreferences;
    private static UserSession instance;
    private User user;

    private UserSession(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String userData = sharedPreferences.getString(KEY_USER, null);
        if (userData != null) {
            user = new Gson().fromJson(userData, User.class);
        }
    }

    public static synchronized UserSession getInstance(Context context) {
        if (instance == null) {
            instance = new UserSession(context);
        }
        return instance;
    }

    public void setUser(User user) {
        Log.d("MyTag", "setUser called");
        this.user = user;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER, new Gson().toJson(user));
        boolean success = editor.commit(); // Use commit() instead of apply() for synchronous save
        if (success) {
            Log.d("MyTag", "User data saved successfully.");
        } else {
            Log.e("MyTag", "Failed to save user data.");
        }
    }


    public User getUser() {
        return user;
    }

    public void clearUser() {
        user = null;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_USER);
        editor.apply();
    }
}

