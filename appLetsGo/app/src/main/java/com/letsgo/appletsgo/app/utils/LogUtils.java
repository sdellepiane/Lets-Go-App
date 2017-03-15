package com.letsgo.appletsgo.app.utils;

import android.util.Log;

import com.letsgo.appletsgo.BuildConfig;


public class LogUtils {

    private static final String LOG_PREFIX = "LetsGo: ";
    private static final int LOG_PREFIX_LENGTH = LOG_PREFIX.length();
    private static final int MAX_LOG_TAG_LENGTH = 23;

    public static String makeLogTag(String str) {
        if (str.length() > (MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH)) {
            return LOG_PREFIX + str.substring(0, MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH - 1);
        }

        return LOG_PREFIX + str;
    }

    public static String makeLogTag(Class cls) {
        return makeLogTag(cls.getSimpleName());
    }

    public static void v(final String tag, String message) {
        String nTag= makeLogTag(tag);

        if (BuildConfig.DEBUG || false || Log.isLoggable(nTag, Log.DEBUG)) {
            Log.v(nTag, message);
        }
    }

    public static void LOGI(final String tag, String message, Throwable cause)
    {
        String nTag= makeLogTag(tag);

        if (BuildConfig.DEBUG || false || Log.isLoggable(nTag, Log.DEBUG)) {
            Log.i(nTag, message, cause);
        }
    }



}
