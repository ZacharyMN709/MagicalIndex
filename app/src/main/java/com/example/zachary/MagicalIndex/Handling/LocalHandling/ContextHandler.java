package com.example.zachary.MagicalIndex.Handling.LocalHandling;

import android.app.Application;
import android.content.Context;

/**
 * Created by Zachary on 21-Sep-16.
 */
public class ContextHandler extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        ContextHandler.context = getApplicationContext();
    }

    public static Context getContext() {
        return ContextHandler.context;
    }
}
