package com.josuebasurto.selfupdateapp;

import android.util.Log;

public class LogHelper {

    private static final String HIGHLIGHT_CHARS = ">>>>>>>>>>";

    public static void Debug(String Tag, String msg){
        Log.d(Tag, "DEBUG " + HIGHLIGHT_CHARS + " : " + msg);
    }

    public static void Info(String Tag, String msg){
        Log.i(Tag, "INFO " + HIGHLIGHT_CHARS + " : " + msg);
    }

    public static void Warn(String Tag, String msg){
        Log.w(Tag, "WARNING " + HIGHLIGHT_CHARS + " : " + msg);
    }

    public static void Wtf(String tag, String msg){
        Log.wtf(tag, "WTF!!! " + HIGHLIGHT_CHARS + " : " + msg);
    }


}