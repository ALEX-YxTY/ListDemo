package com.meishipintu.alarmtest;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2016/12/14.
 */

public class AlarmBean {
    private static int id=0;
    private PendingIntent pdIntent;
    private boolean onOff;
    private int selfId;

    public AlarmBean(Context context) {
        this.onOff = false;
        selfId = id++;

    }


    public int getSelfId() {
        return selfId;
    }

    public void setSelfId(int selfId) {
        this.selfId = selfId;
    }

    public PendingIntent getPdIntent() {
        return pdIntent;
    }

    public void setPdIntent(PendingIntent pdIntent) {
        this.pdIntent = pdIntent;
    }

    public boolean isOnOff() {
        return onOff;
    }

    public void setOnOff(boolean onOff) {
        this.onOff = onOff;
    }
}
