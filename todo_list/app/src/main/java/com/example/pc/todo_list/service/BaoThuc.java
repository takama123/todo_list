package com.example.pc.todo_list.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.pc.todo_list.bean.CompareByDate;
import com.example.pc.todo_list.bean.Mission;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by PC on 1/15/2017.
 */

public class BaoThuc {
    Context context;
//    Mission mission;
    ArrayList<Mission> arrMission;
    private PendingIntent pendingIntent;

    public BaoThuc(Context context, ArrayList<Mission> arrMission) {
        this.context = context;
        this.arrMission = arrMission;
    }

    public void baothuc() {
        if (arrMission.size() == 0) {
            Log.d("test", "allMision size = 0 ::addmissionfragment");
        } else {
            Collections.sort(arrMission, new CompareByDate());
            for (Mission m : arrMission) {
                Log.d("test","."+m.getM_ngay_het_han()+".");
                if (!m.getM_ngay_het_han().equalsIgnoreCase("")) {
                    Log.d("test",m.get_id()+":id");
                    setArlarm(m);
                    break;
                }
            }
        }
    }
    public void setArlarm(Mission mission){
        Log.d("test", "reset alarm: " + mission.getM_ten_nhiem_vu());
        Bundle bundle = new Bundle();
        bundle.putParcelable("mission",mission);
        //lay thoi gian bao thuc
        Calendar calendar = mission.convertToCalendar();

        //set alarm
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(ALARM_SERVICE);
//        alarmManager.cancel(pendingIntent);// huy bao thuc co truoc

        // tao moi bao thuc
        Intent alertIntent = new Intent(context, AlarmReceiver.class);
        alertIntent.putExtras(bundle);
        pendingIntent = PendingIntent.getBroadcast(context, 0, alertIntent, 0);
        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);

    }
}
