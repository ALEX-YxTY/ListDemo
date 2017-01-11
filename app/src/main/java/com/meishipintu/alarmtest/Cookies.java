package com.meishipintu.alarmtest;

import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/12/14.
 */

public class Cookies {

    public static void setAlarm(int selfId, boolean onOrOff) {
        SharedPreferences.Editor ed = TestApplication.getSingleton().getSp().edit();
        ed.putBoolean(selfId + "", onOrOff);
        ed.commit();
    }

    public static boolean getAlarm(int selfId) {
        SharedPreferences sp = TestApplication.getSingleton().getSp();
        return sp.getBoolean(selfId + "", false);
    }
}
