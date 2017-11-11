package com.sha.kamel.sample.util;

import android.app.Application;
import android.content.Context;


/**
 * Created by Sha on 13/04/17.
 */

public final class MyApp extends Application {

    private static Context context;

    public static Context getContext(){
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

    }

}
