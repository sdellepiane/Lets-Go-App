package com.letsgo.appletsgo.data.store;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.letsgo.appletsgo.app.ui.core.JSONUtils;
import com.letsgo.appletsgo.domain.model.entity.DistritosSession;
import com.letsgo.appletsgo.domain.model.entity.User;

/**
 * Created by louislopez on 28/02/17.
 */

public class SessionUser {
    public static final String SESSION_USER= "session_user";
    public static final String SESSION_DISTRITOS= "session_distros";

    private static SharedPreferences.Editor editor;
    private static SharedPreferences preferences;

    public static void initPreferences(Context context, String preference) {
        preferences= context.getSharedPreferences(preference, 0);
        editor = preferences.edit();
    }

    public static void saveSessionUser(Context context, User session) {
        initPreferences(context, SESSION_USER);
        editor.putString(SESSION_USER, JSONUtils.generateJSONObject(session).toString());
        editor.commit();
    }

    public static User getSessionUser(Context context) {
        initPreferences(context, SESSION_USER);
        GsonBuilder gsonb = new GsonBuilder();
        Gson gson = gsonb.create();
        User userSession = gson.fromJson(preferences.getString(SESSION_USER, ""), User.class);

        return userSession;
    }

    public static void saveDistrosUser(Context context, DistritosSession session) {
        initPreferences(context, SESSION_DISTRITOS);
        editor.putString(SESSION_DISTRITOS, JSONUtils.generateJSONObject(session).toString());
        editor.commit();
    }

    public static DistritosSession getDistrosUser(Context context) {
        initPreferences(context, SESSION_DISTRITOS);
        GsonBuilder gsonb = new GsonBuilder();
        Gson gson = gsonb.create();
        DistritosSession distritosSession = gson.fromJson(preferences.getString(SESSION_DISTRITOS, ""), DistritosSession.class);

        return distritosSession;
    }
}
