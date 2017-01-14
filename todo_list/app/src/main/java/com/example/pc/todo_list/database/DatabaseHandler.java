package com.example.pc.todo_list.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;

    // Database Name
    private static final String DATABASE_NAME = "ToDoList";
    public static final String TABLE_MISSION = "DanhSachNhiemVu";
    public static final String TABLE_TYPE_LIST = "PhanLoaiNhiemVu";

    class MissionColumn implements BaseColumns {
        static final String KEY_NAME = "ten_nhiem_vu";
        static final String KEY_DATE = "ngay";
        static final String KEY_TIME = "gio";
        static final String KEY_STATUS = "trangthai";
        static final String KEY_ID_LIST = "id_danhsach";
    }

    class TypeMission implements BaseColumns {
        static final String KEY_NAME = "kieu_nhiem_vu";
    }

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String CREATE_MISSON_TABLE = "CREATE TABLE " + TABLE_MISSION + "("
            + MissionColumn._ID + " INTEGER PRIMARY KEY, " + MissionColumn.KEY_NAME + " TEXT,"
            + MissionColumn.KEY_DATE + " TEXT, " + MissionColumn.KEY_TIME+ " TEXT , "
            + MissionColumn.KEY_STATUS + " INTEGER, "
            + MissionColumn.KEY_ID_LIST+ " INTEGER "+")";
    private static final String DROP_MISSION_TABLE = "DROP TABLE IF EXISTS " + TABLE_MISSION;

    private static final String CREATE_TYPE_TABLE = "CREATE TABLE " + TABLE_TYPE_LIST + "("
            + TypeMission._ID + " INTEGER PRIMARY KEY, "
            + TypeMission.KEY_NAME + " TEXT "+")";
    private static final String DROP_TYPE_TABLE = "DROP TABLE IF EXISTS " + TABLE_TYPE_LIST;

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_MISSON_TABLE);
        sqLiteDatabase.execSQL(CREATE_TYPE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(DROP_MISSION_TABLE);
        sqLiteDatabase.execSQL(DROP_TYPE_TABLE);
        // Create tables again
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }


}

