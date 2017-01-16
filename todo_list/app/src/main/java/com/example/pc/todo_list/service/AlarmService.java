package com.example.pc.todo_list.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.pc.todo_list.MainActivity;
import com.example.pc.todo_list.R;
import com.example.pc.todo_list.bean.Mission;
import com.example.pc.todo_list.database.MissionDAO;

public class AlarmService extends IntentService {
    private NotificationManager alarmNotificationManager;
    Mission mission = null;

    public AlarmService() {
        super("AlarmService");
    }

    @Override
    public void onHandleIntent(Intent intent) {
        Bundle bundle = intent.getExtras();
        this.mission = (Mission) bundle.get("mission");
        sendNotification(mission);
        MissionDAO mDao = new MissionDAO(getApplicationContext());
        mission.setM_id_danhsach(2);
        mission.setM_status(1);
        int i = mDao.updateMission(mission);
        Log.d("test",i+" : result of update"+":"+mission.get_id());
    }

    private void sendNotification(Mission mission) {
        alarmNotificationManager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        NotificationCompat.Builder alamNotificationBuilder = new NotificationCompat.Builder(
                this).setContentTitle("Nhiệm vụ lúc " + mission.getM_gio_het_han() + "!").setSmallIcon(R.mipmap.ic_launcher)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(mission.getM_ten_nhiem_vu()))
                .setContentText("style")
                ;

        alamNotificationBuilder.setSound( Uri.parse("android.resource://"
                + getApplication().getPackageName() + "/" + R.raw.chuongbaothuc));

        alamNotificationBuilder.setContentIntent(contentIntent);
        alarmNotificationManager.notify(1, alamNotificationBuilder.build());
    }

    @Override
    public void onDestroy() {
        Log.d("test","destroy");
//        BaoThuc.getInstance().baothuc(getApplicationContext());
        new BaoThuc().baothuc(getApplicationContext());
        super.onDestroy();
    }
}