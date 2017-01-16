package com.example.pc.todo_list.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.pc.todo_list.bean.CompareByDate;
import com.example.pc.todo_list.bean.Mission;
import com.example.pc.todo_list.database.MissionDAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by PC on 1/15/2017.
 */

public class BaoThuc {
//    private static BaoThuc instance = null;

//    Mission mission;
    private PendingIntent pendingIntent;

    public BaoThuc(){
    }

//    public static BaoThuc getInstance(){
//        if(instance == null){
//            instance = new BaoThuc();
//        }
//        return instance;
//    }

    public void baothuc(Context context) {
        MissionDAO missionDAO = new MissionDAO(context);
        ArrayList<Mission> allMission = (ArrayList<Mission>) missionDAO.getAllMissionExceptFinish();
        if (allMission.size() == 0) {
            Log.d("test", "allMision size = 0 ::addmissionfragment");
        } else {
            Collections.sort(allMission, new CompareByDate());
            for (Mission m : allMission) {
                if (!m.getM_ngay_het_han().equalsIgnoreCase("")) {
                    setArlarm(context,m);
                    break;
                }
            }
        }
    }
    public void setArlarm(Context context,Mission mission){
        Log.d("test", "reset alarm: " + mission.getM_ten_nhiem_vu());
        Bundle bundle = new Bundle();
        bundle.putParcelable("mission",mission);
        //lay thoi gian bao thuc
        Calendar calendar = mission.convertToCalendar();

        in(Calendar.getInstance());
        in(calendar);
        //set alarm
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        // tao moi bao thuc
        Intent alertIntent = new Intent(context, AlarmReceiver.class);
        alertIntent.putExtras(bundle);
        pendingIntent = PendingIntent.getBroadcast(context, 1, alertIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

    }
    public void in(Calendar c){
        String s = c.get(Calendar.YEAR)+"-"+
                c.get(Calendar.MONTH)+"-"+
                c.get(Calendar.DAY_OF_MONTH)+"-"+
                c.get(Calendar.HOUR_OF_DAY)+"-"+
                c.get(Calendar.MINUTE)+"-"+
                c.get(Calendar.AM_PM)+"-"+
                c.get(Calendar.SECOND);
        Log.d("test",s);


    }
}
