package com.meishipintu.alarmtest;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private boolean alarmOn;
    private List<AlarmBean> pdList;
    private int i = 0;
    private MyService myService;
    private ServiceConnection serviceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(MainActivity.this, MyService.class));
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.i("test", "service bind");
                myService = ((MyService.MyBind)service).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.i("test", "service unbind");
                myService = null;
            }
        };
        bindService(new Intent(MainActivity.this, MyService.class), serviceConnection, Context.BIND_AUTO_CREATE);
        Button btAlarm = (Button) findViewById(R.id.bt_alarm_on);
        Button btAlarmOff = (Button) findViewById(R.id.bt_alarm_off);
        pdList = new ArrayList<>();
        final AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmOn = false;
        btAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setAction("test_alarm");
//                intent.putExtra("id", 1);
//                PendingIntent pdIntent = PendingIntent
//                        .getBroadcast(MainActivity.this, 1, intent, 0);
//                am.set(AlarmManager.RTC, System.currentTimeMillis() +  10* 1000l, pdIntent);
                if (myService != null) {
                    myService.setAlarm(1);
                }
            }
        });
        btAlarmOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setAction("test_alarm");
//                intent.putExtra("id", 1);
//                PendingIntent pdIntent = PendingIntent
//                        .getBroadcast(MainActivity.this, 1, intent, 0);
//                am.cancel(pdIntent);
                if (myService != null) {
                    myService.closeAlarm(1);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        unbindService(serviceConnection);
        super.onDestroy();
    }
}
