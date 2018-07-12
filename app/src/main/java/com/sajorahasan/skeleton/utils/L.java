package com.sajorahasan.skeleton.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.sajorahasan.skeleton.BuildConfig;

public class L {

    private static final String TAG = "MyApp";

    public static void print(String message) {
        if (BuildConfig.LOG_ENABLE)
            Log.d(TAG, message);
    }

    public static void print(String TAG, String message) {
        if (BuildConfig.LOG_ENABLE)
            Log.d(TAG, message);
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
