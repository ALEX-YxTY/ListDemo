package com.meishipintu.alarmtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.i("test", "alarm "+intent.getIntExtra("id",-1)+" is catched!");
//        NotificationManager mNotificationManager = (NotificationManager) context
//                .getSystemService(Context.NOTIFICATION_SERVICE);
//        //新建一个通知，第一个参数为图标,第二个参数为短暂提示标题,第三个为通知时间
//        int icon = android.R.drawable.stat_notify_chat;
//        //通知发生的时间为系统当前时间 　　
//        long when = System.currentTimeMillis();
//        Notification notification = new Notification(icon, null, when);
//        //发出默认声音
//        notification.defaults = Notification.DEFAULT_SOUND;
//        //点击通知后自动清除通知
//        notification.flags = Notification.FLAG_AUTO_CANCEL;
//        //当点击消息时就会向系统发送openintent意图
////        Intent openintent = new Intent(this, OtherActivity.class);
////        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, openintent, 0);
//        //setLatestEventInfo表示设置点击该通知的事件 　　
////        notification.setLatestEventInfo(this, "标题”, “我是内容", contentIntent);
////
////                将通知添加到通知管理器 //第一个参数为自定义的通知唯一标识 mNotificationManager.notify(0, notification);
    }

}
