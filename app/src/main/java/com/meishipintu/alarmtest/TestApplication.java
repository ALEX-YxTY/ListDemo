package com.meishipintu.alarmtest;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/12/14.
 */

public class TestApplication extends Application {

    private static TestApplication singleton;

    public static TestApplication getSingleton() {
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
    }

    public SharedPreferences getSp() {
        return this.getSharedPreferences("cookies", MODE_PRIVATE);
    }
}
