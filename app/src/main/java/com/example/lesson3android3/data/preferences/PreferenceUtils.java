package com.example.lesson3android3.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.lesson3android3.data.model.User;


public class PreferenceUtils {
    public static final String USER_PREFERENCE = "preference_user";
    public static final String USER_NAME = "name_user";
    public static final String USER_TOKEN = "user_token";
    private static SharedPreferences preferences;

    public static void init(Context context) {
        preferences = context.getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE);
    }

    public static void saveUser(User user) {
        preferences.edit().putString(USER_NAME, user.getName()).apply();
        preferences.edit().putString(USER_TOKEN, user.getToken()).apply();
    }

    public static User getUser() {
        String userName = preferences.getString(USER_NAME, "");
        String userToken = preferences.getString(USER_TOKEN, "");
        return new User(userName, userToken);
    }
}

