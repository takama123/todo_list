package com.example.pc.todo_list.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pc.todo_list.bean.Mission;

import java.util.ArrayList;
import java.util.List;

import static com.example.pc.todo_list.database.DatabaseHandler.MissionColumn.KEY_DATE;
import static com.example.pc.todo_list.database.DatabaseHandler.MissionColumn.KEY_ID_LIST;
import static com.example.pc.todo_list.database.DatabaseHandler.MissionColumn.KEY_NAME;
import static com.example.pc.todo_list.database.DatabaseHandler.MissionColumn.KEY_STATUS;
import static com.example.pc.todo_list.database.DatabaseHandler.MissionColumn.KEY_TIME;
import static com.example.pc.todo_list.database.DatabaseHandler.TABLE_MISSION;

public class MissionDAO {

    Context context;
    DatabaseHandler databaseHandler;

    public MissionDAO(Context context) {
        this.context = context;
        databaseHandler = new DatabaseHandler(context);
    }

    public long addMission (Mission mission) {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, mission.getM_ten_nhiem_vu());
        values.put(KEY_DATE, mission.getM_ngay_het_han());
        values.put(KEY_TIME, mission.getM_gio_het_han());
        values.put(KEY_ID_LIST, mission.getM_id_danhsach());
        values.put(KEY_STATUS, mission.getM_status());

        // Inserting Row
        long x = db.insert(TABLE_MISSION, null, values);
        db.close(); // Closing database connection
        return x;
    }

    public Mission getMissionByIdMission (int id_mission) {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();

        Cursor cursor = db.query(TABLE_MISSION, new String[] { DatabaseHandler.MissionColumn._ID,
                        KEY_NAME, KEY_DATE, KEY_TIME, KEY_ID_LIST, KEY_STATUS }, DatabaseHandler.MissionColumn._ID + "=?",
                new String[] { String.valueOf(id_mission) }, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Mission mission = new Mission(cursor.getInt(0),cursor.getString(3), cursor.getInt(4),
                cursor.getString(2), cursor.getInt(5), cursor.getString(1));
        // return contact
        return mission;
    }

    public List<Mission> getListMissonByIdTypeList(int id_list) {
        List<Mission> missionList = new ArrayList<Mission>();
        // Select All Query
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        Cursor cursor = db.query(TABLE_MISSION, new String[] { DatabaseHandler.MissionColumn._ID,
                        KEY_NAME, KEY_DATE, KEY_TIME, KEY_ID_LIST, KEY_STATUS }, KEY_ID_LIST + "=?",
                new String[] { String.valueOf(id_list) }, null, null, null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Mission mission = new Mission(cursor.getInt(0),cursor.getString(3), cursor.getInt(4),
                        cursor.getString(2), cursor.getInt(5), cursor.getString(1));
                // Adding contact to list
                missionList.add(mission);
            } while (cursor.moveToNext());
        }
        // return contact list
        return missionList;
    }

    public List<Mission> getAllMissionExceptFinish() {
        List<Mission> missionList = new ArrayList<Mission>();
        // Select All Query
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        Cursor cursor = db.query(TABLE_MISSION, new String[] { DatabaseHandler.MissionColumn._ID,
                        KEY_NAME, KEY_DATE, KEY_TIME, KEY_ID_LIST, KEY_STATUS }, KEY_ID_LIST + "!=?",
                new String[] {"2"}, null, null, null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Mission mission = new Mission(cursor.getInt(0),cursor.getString(3), cursor.getInt(4),
                        cursor.getString(2), cursor.getInt(5), cursor.getString(1));
                // Adding contact to list
                missionList.add(mission);
            } while (cursor.moveToNext());
        }
        // return contact list
        return missionList;
    }

    public int updateMission(Mission mission) {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, mission.getM_ten_nhiem_vu());
        values.put(KEY_DATE, mission.getM_ngay_het_han());
        values.put(KEY_TIME, mission.getM_gio_het_han());
        values.put(KEY_ID_LIST, mission.getM_id_danhsach());

        // updating row
        return db.update(TABLE_MISSION, values, DatabaseHandler.MissionColumn._ID + " = ?",
                new String[] { String.valueOf(mission.get_id()) });
    }

    public void deleteMission(Mission mission) {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        db.delete(TABLE_MISSION, DatabaseHandler.MissionColumn._ID + " = ? ",
                new String[] { String.valueOf(mission.get_id())});
        db.close();
    }
}
