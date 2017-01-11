package com.meishipintu.alarmtest;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private MyBind myBind;
    private AlarmManager am;

    @Override
    public void onCreate() {
        myBind = new MyBind();
        am = (AlarmManager) getSystemService(ALARM_SERVICE);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("test", "service start");
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        Log.i("test", "service destroy");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBind;
    }

    class MyBind extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }

    public void setAlarm(int id) {
        Intent intent = new Intent();
        intent.setAction("test_alarm");
        intent.putExtra("id", id);
        PendingIntent pdIntent = PendingIntent
                .getBroadcast(this, id, intent, 0);
        am.set(AlarmManager.RTC, System.currentTimeMillis() +  10* 1000l, pdIntent);
        Log.i("test", "alarm"+id+" is  on");
    }

    public void closeAlarm(int id) {
        Intent intent = new Intent();
        intent.setAction("test_alarm");
        intent.putExtra("id", id);
        PendingIntent pdIntent = PendingIntent
                .getBroadcast(this, id, intent, 0);
        am.cancel(pdIntent);
        Log.i("test", "alarm"+id+" is  off");
    }

}
