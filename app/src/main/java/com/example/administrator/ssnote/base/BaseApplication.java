package com.example.administrator.ssnote.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2015/9/13.
 */
public class BaseApplication  extends Application{
    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
